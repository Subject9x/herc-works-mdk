package org.hercworks.extract;

import java.io.File;
import java.net.URLDecoder;
import java.util.Map;

import org.hercworks.extract.cmd.CommandLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.voln.Voln;
import org.hercworks.voln.Voln.FileType;
import org.hercworks.voln.io.VolFileReader;
import org.hercworks.voln.io.VolFileWriter;

/**
 * 
 */
public class CommandLineMain {
	
	public static CommandLine cmd  = CommandLine.cmd();
	public static Logger log = Logger.getLogger();
	
	public static void main(String[] args) {
		
		try {
			cmd.setLogger(log);
			cmd.parseCommands(args);
			
			Map<String, FileType> fileQueue = cmd.parseFiles(args);
			
			if(fileQueue.isEmpty()) {
				log.consoleDebug("No files passed in.");
				System.exit(0);
				return;
			}
			
			for(String path : fileQueue.keySet()) {
				if(fileQueue.get(path).equals(FileType.VOL)) {
					processVolFile(path);
				}
				else if(fileQueue.get(path).equals(FileType.DBA)) {
					processDBAFile(path);
				}
			}
			
		} catch (Exception e) {
			log.console(e.getMessage());
			System.exit(1);
		}
		
		System.exit(0);
	}

	private static void processVolFile(String volName) {
		
		try {
			System.out.println("--------------------------VOL detected! beginning unpack.-----------------------------------");
			String decodedPath = URLDecoder.decode( CommandLineMain.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			String root = decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);
			
			log.consoleDebug("info - root=" + root);
			
			Voln volFile = VolFileReader.parseVolFile(root + volName);
			
			String volRoot = root + volName.substring(0, volName.lastIndexOf('.')-1) + "//";
			
			File targDir = new File(volRoot + "//");
			
			log.consoleDebug("info - targDir=" + targDir);
			
			if(!targDir.exists()) {
				if(!targDir.mkdir()) {
					log.consoleDebug("Error - cannot make target directory.");
					return;
				}
			}
			
			VolFileWriter.unpackVol(volFile, targDir.getPath());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
	
	private static void processDBAFile(String dbaName) {
		
		try {
			System.out.println("--------------------------DBA detected! beginning unpack.-----------------------------------");
			String decodedPath = URLDecoder.decode( CommandLineMain.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8");
			String root = decodedPath.substring(0, decodedPath.lastIndexOf("/") + 1);
			
			log.consoleDebug("info - root=" + root);
			
			File dbaFile = new File(root + "//" + dbaName);
			if(!dbaFile.exists() || !dbaFile.isFile()) {
				log.consoleDebug("Error - [" + dbaName + "] was not found or not a file.");
				return;
			}
			
			File targDir = new File(root + "//" + dbaName.substring(0, dbaName.lastIndexOf('.')) + "//");
			log.consoleDebug("info - targDir=" + targDir);
			
			if(!targDir.exists()) {
				if(!targDir.mkdir()) {
					log.consoleDebug("Error - cannot make target directory.");
					return;
				}
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
