package org.hercworks.extract.exec;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixBitmapArray;
import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.io.transform.common.DynamixBitmapArrayTransformer;
import org.hercworks.core.io.transform.common.DynamixBitmapTransformer;
import org.hercworks.core.io.transform.common.DynamixPaletteTransformer;
import org.hercworks.core.io.write.DynFileWriter;
import org.hercworks.extract.CommandLineMain;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.ExcavatorCmdLine.OptionArgs;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.voln.Voln;
import org.hercworks.voln.io.VolFileReader;
import org.hercworks.voln.io.VolFileWriter;

public final class FileProcessor extends LoggingUtil{

	private static FileProcessor INSTANCE;
	private ExcavatorCmdLine cmdLine;
	
	private String appPath;
	private String volDirPath;
	private String unpackPath;
	
	private FileProcessor() {}
	
	private FileProcessor(ExcavatorCmdLine cmdLine) {}
	
	/**
	 * Must be called by main class, initiliazing FileProcessor state, configures paths.
	 * @param cmdLine
	 * @param logger
	 * @throws IOException
	 */
	public void init(ExcavatorCmdLine cmdLine, Logger logger) throws IOException  {
		this.cmdLine = cmdLine;
		setAppPath();
		
		this.volDirPath = getAppPath() + "/VOL/";
		this.unpackPath = getAppPath() + "/UNPACK/";
		
		setLogger(logger);
		
		File exportDir = new File(getAppPath() + "/UNPACK/");
		if(!exportDir.exists()) {
			if(!exportDir.mkdir()) {
				throw new IOException("ERROR - couldn't generate unpack dir, check location and permissions on [" + getAppPath() + "/UNPACK/" + "]");
			}
		}
	}
	
	/**
	 * 
	 * @param volName
	 */
	public void unpackVolFile(String volName) {
		
		try {
			getLogger().console("--------------------------VOL detected! beginning unpack.-----------------------------------");
			
			Voln volFile = VolFileReader.parseVolFile(this.volDirPath + volName);
			
			String targDirPath = makeExportPath(this.unpackPath);
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}
			
			File targDir = new File(targDirPath + "//");
			
			VolFileWriter.unpackVol(volFile, targDir.getPath());
			
			getLogger().console("Vol [" + volName +"] unpacked successfully.");
		} catch (Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	public void unpackDBA(String dbaName) {
		try {
			getLogger().console("--------------------------DBA Unpacking-----------------------------------");
			
			DynamixBitmapArrayTransformer dbaConvert = new  DynamixBitmapArrayTransformer();
			DynamixBitmapArray dba = (DynamixBitmapArray)dbaConvert.bytesToObject(loadFileBytes(getAppPath() + dbaName));
			
			if(dba == null) {
				throw new Exception("ERROR - failed to convert [" + dbaName + "] to File Object.");
			}
			
			dba.setFileName(getCleanFileName(fileNoExt(dbaName)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DBA export to source directory.");
				dba.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(dbaName));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath + getCleanFileName(fileNoExt(dbaName)));
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			//unpack DBM's regardless of options.
			DynamixBitmapTransformer dbmConverter = new DynamixBitmapTransformer();
			
			for(DynamixBitmap dbm : dba.getImages()) {
				getLogger().console("exported: " + dba.getFileName() + dbm.getFileName());
				dbm.setFileName(dba.getFileName() + dbm.getFileName());
				try (FileOutputStream fozz = new FileOutputStream(new File(targDirPath + "/" + dbm.getFileName() + "." + dbm.getExt().val().toUpperCase()))){
					fozz.write(dbmConverter.objectToBytes(dbm));
				} catch (FileNotFoundException e) {
					getLogger().consoleDebug(e.getMessage());
				} catch (IOException e) {
					getLogger().consoleDebug(e.getMessage());
				}
			}
			
			
			//Check for any DPL palette files that were loaded in args
			if(cmdLine.checkOption(ExcavatorCmdLine.OptionArgs.DPL)){
				List<DynamixPalette> palettes = new ArrayList<DynamixPalette>();
				DynamixPaletteTransformer dplConvert = new DynamixPaletteTransformer();
				
				for(String fileName : cmdLine.getFileQueue().keySet()) {
					if(cmdLine.getFileQueue().get(fileName).equals(Voln.FileType.DPL)) {
						DynamixPalette dpl = (DynamixPalette)dplConvert.bytesToObject(loadFileBytes(getAppPath() + fileName));
						dpl.setFileName(getCleanFileName(fileNoExt(fileName)));
						getLogger().consoleDebug("DPL loaded:" + dpl.getFileName());
						palettes.add(dpl);
					}
				}
				if(palettes.isEmpty()) {
					//FIXME - is this really ever necessary?
					getLogger().console("WARN - no DPL palettes entered in command line, exporting black-and-white images");
					for(DynamixBitmap dbm : dba.getImages()) {
						getLogger().console("exported: " + dbm.getFileName() +"_RAW");
						dbm.setFileName(dbm.getFileName() + ".DBM");	//TODO - writeDBMToFileNoPalette dependency condition that aint great.
						DynFileWriter.writeDBMToFileNoPalette(dbm,  targDirPath +"/");
					}
				}
				else {
					for(DynamixPalette dpl : palettes) {
						for(DynamixBitmap dbm : dba.getImages()) {
							dbm.setFileName(dbm.getFileName() + "_" + dpl.getFileName());
							DynFileWriter.writeDBMToFile(dbm, dpl, targDirPath +"/");
						}
					}
				}
			}
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	
	public void exportDBM(String dbmName) {
		try {
			getLogger().console("--------------------------DBM Parsing-----------------------------------");
			
			DynamixBitmapTransformer dbaConvert = new  DynamixBitmapTransformer();
			DynamixBitmap dbm = (DynamixBitmap)dbaConvert.bytesToObject(loadFileBytes(getAppPath() + dbmName));
			
			if(dbm == null) {
				throw new Exception("ERROR - failed to convert [" + dbmName + "] to File Object.");
			}
			
			dbm.setFileName(getCleanFileName(fileNoExt(dbmName)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DBM export to source directory.");
				dbm.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(dbmName));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath + getCleanFileName(fileNoExt(dbmName)));
			}
			
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}

			//Check for any DPL palette files that were loaded in args
			if(!cmdLine.checkOption(ExcavatorCmdLine.OptionArgs.DPL)){
				getLogger().console("ERROR - no DPL file passed as argument!");
				return;
			}
			List<DynamixPalette> palettes = new ArrayList<DynamixPalette>();
			DynamixPaletteTransformer dplConvert = new DynamixPaletteTransformer();
			
			for(String fileName : cmdLine.getFileQueue().keySet()) {
				if(cmdLine.getFileQueue().get(fileName).equals(Voln.FileType.DPL)) {
					DynamixPalette dpl = (DynamixPalette)dplConvert.bytesToObject(loadFileBytes(getAppPath() + fileName));
					dpl.setFileName(getCleanFileName(fileNoExt(fileName)));
					getLogger().consoleDebug("DPL loaded:" + dpl.getFileName());
					palettes.add(dpl);
				}
			}
			if(palettes.isEmpty()) {
				getLogger().console("ERROR - could not process DPL palette files, please check paths and locations.");
				return;
			}
			for(DynamixPalette dpl : palettes) {
				dbm.setFileName(dbm.getFileName() + "_" + dpl.getFileName());
				DynFileWriter.writeDBMToFile(dbm, dpl, targDirPath +"/");
			}
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	/**
	 * @param  dplName
	 */
	public void exportDPL(String dplName) {
		
		if(cmdLine.checkOption(OptionArgs.DPL)) {
			getLogger().console("WARN - Skipping DPL export, file will be used by DBM/DBA that was on arg list.");
			return;
		}
		
		try {
			getLogger().console("--------------------------DPL - export Dynamix Palette-----------------------------------");
			
			DynamixPaletteTransformer dplConvert = new DynamixPaletteTransformer();
			DynamixPalette dpl = (DynamixPalette)dplConvert.bytesToObject(loadFileBytes(getAppPath() + dplName));
			
			if(dpl == null) {
				throw new Exception("ERROR - failed to convert [" + dplName + "] to File Object.");
			}
			
			dpl.setFileName(getCleanFileName(fileNoExt(dplName)));
			
			String targDirPath = null;
			if(cmdLine.checkOption(OptionArgs.SRC)) {
				getLogger().consoleDebug("--keeping DBA export to source directory.");
				dpl.assignDir(getAppPath());
				targDirPath = makeExportPath(getAppPath() + fileNoExt(dplName));
			}
			else {
				targDirPath = makeExportPath(this.unpackPath);
			}

			int pixel = 32;
			int colorDepth = 3;
			int cols = dpl.getColorCount() / 16;
			int rows = dpl.getColorCount() / cols;
			
			int pixelRows = rows * pixel;
			int pixelCols = cols * pixel;
			
			File dplImage = new File(targDirPath + dpl.getFileName() + ".png");
			BufferedImage imageOut  = new BufferedImage(pixelCols, pixelRows, BufferedImage.TYPE_INT_RGB);
			
			WritableRaster rast = (WritableRaster)imageOut.getRaster();
			int[] rasterData = new int[pixelCols*pixelRows*colorDepth];
			
			int xOffset = 0;
			int yOffset = 0;

			for(int p=0; p < dpl.getColorCount(); p++) {
				int color = dpl.colorAt(p).getColor().getRGB();
				
				for(int r=yOffset; r < yOffset + pixel; r++) {
					for(int c=xOffset; c < xOffset + pixel; c++) {
						rasterData[(r * pixelCols) + c] = color;
					}
				}
				xOffset += pixel;
				if(xOffset > pixelCols) {
					xOffset = 0;
					yOffset += pixel;
				}
			}

			rast.setDataElements(0, 0, pixelCols, pixelRows, rasterData);
			imageOut.setData(rast);
			
			boolean wrote = ImageIO.write(imageOut, "png", dplImage);			
			
			if(wrote) {
				getLogger().consoleDebug("note: Palette values have been multiplied by 4, \n and when stored to DPL are crushed by 4 to fit within UINT8 ranges.");
			}
			else {
				getLogger().console("ERROR: image failed to write, check path and permissions.");
			}
		} catch (Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
	private String getRootDir() {
		String decodedPath = null;
		try {
			decodedPath = URLDecoder.decode( CommandLineMain.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			return decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);
		} catch (UnsupportedEncodingException e) {
			getLogger().console(e.getMessage());
		}
		return null;
	}
	
	private String makeExportPath(String fullTargPath){
		
		getLogger().consoleDebug("DEBUG - targDir=[" + fullTargPath +"]");
		
		File dir = new File(fullTargPath);
		
		if(!dir.exists()) {
			if(!dir.mkdir()) {
				getLogger().console("ERROR - cannot make target directory.[" + fullTargPath +"]");
				return null;
			}
		}
		return dir.getAbsolutePath();
	}
	
	private String getCleanFileName(String fileNamePath) {
		int dirIdx = fileNamePath.lastIndexOf('\\');
		if(dirIdx <= 0) {
			dirIdx = fileNamePath.lastIndexOf('/');
		}
		if(dirIdx <= 0) {
			dirIdx = 0;
		}
		else {
			dirIdx += 1;
		}
		return  fileNamePath.substring(dirIdx);
	}
	
	private String fileNoExt(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}
	
	private byte[] loadFileBytes(String path) {
		
		byte[] data = null;
		
		try(FileInputStream fizz = new FileInputStream(path)){
			ByteArrayOutputStream bizz = new ByteArrayOutputStream();
			
			bizz.write(fizz.readAllBytes());
			data = bizz.toByteArray();
			
		} catch (FileNotFoundException e) {
			getLogger().console(e.getMessage());
		} catch (IOException e) {
			getLogger().console(e.getMessage());
		}
		
		return data;
	}
	
	public void setAppPath() {
		this.appPath = getRootDir();
	}
	
	public String getAppPath() {
		return this.appPath;
	}
	
	public static FileProcessor instance() {
		if(INSTANCE == null) {
			INSTANCE = new FileProcessor();
		}
		return INSTANCE;
	}
}
