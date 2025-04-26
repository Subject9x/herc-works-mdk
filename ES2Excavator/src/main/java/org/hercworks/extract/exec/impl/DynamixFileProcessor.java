package org.hercworks.extract.exec.impl;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.hercworks.core.data.file.dyn.DynamixBitmap;
import org.hercworks.core.data.file.dyn.DynamixBitmapArray;
import org.hercworks.core.data.file.dyn.DynamixPalette;
import org.hercworks.core.io.transform.common.DynamixBitmapArrayTransformer;
import org.hercworks.core.io.transform.common.DynamixBitmapTransformer;
import org.hercworks.core.io.transform.common.DynamixPaletteTransformer;
import org.hercworks.core.io.write.DynFileWriter;
import org.hercworks.core.util.PaletteBinding;
import org.hercworks.core.util.PaletteBindingEntry;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.ExcavatorCmdLine.OptionArgs;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.util.FileItem;
import org.hercworks.extract.util.FileItemDBM;
import org.hercworks.extract.util.FileMatcher;
import org.hercworks.extract.util.impl.DynFileMatcher;
import org.hercworks.voln.FileType;
import org.hercworks.voln.Voln;
import org.hercworks.voln.io.VolFileReader;
import org.hercworks.voln.io.VolFileWriter;

public class DynamixFileProcessor extends FileProcessor{
	
	private FileMatcher fileMatch = new DynFileMatcher();
	
	@Override
	public boolean filterFile(FileItem file) {
		//we know this should have an associated file type, ignoring nulls is intentional.
		
		FileType fileType = file.getType();
		
		if(fileType == null) {
			return false;
		}
		if(fileMatch.matchFile(file.getName())) {
			FileItemDBM itemDBM = new FileItemDBM();
			itemDBM.setName(file.getName());
			itemDBM.setType(file.getType());
			
			filesToProcess.add(itemDBM);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void processFiles() {
		for(FileItem file : filesToProcess) {
			if(file.getType().equals(FileType.VOL)) {
				unpackVolFile(file.getName());
			}
			else if(file.getType().equals(FileType.DBM)) {
				exportDBM(file.getName());
			}
			else if(file.getType().equals(FileType.DBA)
					|| file.getType().val().contains("db")
					|| file.getType().val().contains("hb")) {
				unpackDBA(file.getName());
			}
			else if(file.getType().equals(FileType.DPL)) {
				exportDPL(file.getName());
			}
		}
	}
	
	public void unpackVolFile(String volName) {
		
		try {
			getLogger().console("--------------------------VOL detected! beginning unpack.-----------------------------------");
			
			Voln volFile = VolFileReader.parseVolFile(this.volDirPath + volName);
			
			String targDirPath = makeExportPath(this.unpackPath);
			if(targDirPath == null) {
				throw new IOException("ERROR - target dir path was null.\n rootPath=[" + getAppPath() + "]");
			}
			
			File targDir = new File(targDirPath + "//");
			
			VolFileWriter.unpackVol(volFile, targDir.getAbsolutePath());
			
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
			
			//check unknown DBA derivatives: DB0/DB1/DB2/HB0/HB1/HB2/HD0/HD1/HD2
			String ext = getFileExtStr(dbaName);
			List<String> type = Arrays.asList("hb0", "hb1", "hb2", "db0", "db1", "db2");
			if(type.contains(ext.toLowerCase())) {
				dba.setFileName(dba.getFileName() + "_"+ext);
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
				DynamixPaletteTransformer dplConvert = new DynamixPaletteTransformer(4);
				
				for(FileItem file : cmdLine.getFileQueue()) {
					if(file.getType().equals(FileType.DPL)) {
						dplConvert.resetIndex();
						DynamixPalette dpl = (DynamixPalette)dplConvert.bytesToObject(loadFileBytes(getAppPath() + file.getName()));
						dpl.setFileName(getCleanFileName(fileNoExt(file.getName())));
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
							PaletteBindingEntry dplBind = PaletteBinding.instance().getPalette(dbaName);
							DynFileWriter.writeDBMToFile(dbm, dplBind.isIndex0Alpha(), dpl, targDirPath +"/");
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
			
			for(FileItem file :cmdLine.getFileQueue()) {
				if(file.getType().equals(FileType.DPL)) {
					DynamixPalette dpl = (DynamixPalette)dplConvert.bytesToObject(loadFileBytes(getAppPath() + file.getName()));
					dpl.setFileName(getCleanFileName(fileNoExt(file.getName())));
					getLogger().consoleDebug("DPL loaded:" + dpl.getFileName());
					palettes.add(dpl);
				}
			}
			if(palettes.isEmpty()) {
				getLogger().console("ERROR - could not process DPL palette files, please check paths and locations.");
				return;
			}
			for(DynamixPalette dpl : palettes) {
				PaletteBindingEntry dplBind = PaletteBinding.instance().getPalette(dbm.getFileName());
				boolean indexAlph = false;
				if(dplBind != null) {
					indexAlph = dplBind.isIndex0Alpha();
				}
				dbm.setFileName(dbm.getFileName() + "_" + dpl.getFileName());
				DynFileWriter.writeDBMToFile(dbm, indexAlph, dpl, targDirPath +"/");
			}
			
		} catch(Exception e) {
			getLogger().console(e.getMessage());
		}
	}
	
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
			
			File dplImage = new File(targDirPath + "/" + dpl.getFileName() + ".png");
			BufferedImage imageOut  = new BufferedImage(pixelCols, pixelRows, BufferedImage.TYPE_INT_RGB);
			
			WritableRaster rast = (WritableRaster)imageOut.getRaster();
			int[] rasterData = new int[pixelCols*pixelRows*colorDepth];
			
			int xOffset = 0;
			int yOffset = 0;

			for(int p=0; p < dpl.getColorCount(); p++) {
				int color = dpl.colorAt(p).getJavaColor().getRGB();
				
				getLogger().consoleDebug("Color " + p + "=(" + dpl.colorAt(p).getJavaColor().getRed() + ", "
											+  dpl.colorAt(p).getJavaColor().getGreen() + ", "
											+  dpl.colorAt(p).getJavaColor().getBlue() + ")");
				
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
}
