package org.hercworks.extract.exec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.hercworks.extract.CommandLineMain;
import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.util.FileItem;

public abstract class FileProcessor extends LoggingUtil{

	protected ExcavatorCmdLine cmdLine;
	protected String appPath;
	protected String volDirPath;
	protected String unpackPath;
	
	protected List<FileItem> filesToProcess;
	
	protected FileProcessor() {}
	
	protected FileProcessor(ExcavatorCmdLine cmdLine) {}
	
	public abstract boolean filterFile(FileItem file);
	
	public abstract void processFiles();
	
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
		filesToProcess = new ArrayList<FileItem>();
	}
	
	

	protected String getRootDir() {
		String decodedPath = null;
		try {
			decodedPath = URLDecoder.decode( CommandLineMain.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			return decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);
		} catch (UnsupportedEncodingException e) {
			getLogger().console(e.getMessage());
		}
		return null;
	}
	
	protected String makeExportPath(String fullTargPath){
		
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
	
	protected String getCleanFileName(String fileNamePath) {
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
	
	protected String fileNoExt(String fileName) {
		return fileName.substring(0, fileName.lastIndexOf('.'));
	}
	
	protected String getFileExtStr(String fileName) {
		return fileName.substring(fileName.lastIndexOf('.')+1);
	}
	
	protected byte[] loadFileBytes(String path) {
		
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
	
	public boolean hasFiles() {
		return !filesToProcess.isEmpty();
	}
	
	
	
}
