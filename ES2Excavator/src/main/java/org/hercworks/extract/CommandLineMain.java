package org.hercworks.extract;

import java.util.ArrayList;
import java.util.List;

import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.exec.impl.DynamixFileProcessor;
import org.hercworks.extract.exec.impl.JsonExportProcessor;
import org.hercworks.extract.exec.impl.JsonImportProcessor;
import org.hercworks.extract.util.FileItem;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 */
public final class CommandLineMain {
	
	public static ExcavatorCmdLine cmd  = ExcavatorCmdLine.cmd();
	
	public static DynamixFileProcessor dynamixFiles = new DynamixFileProcessor();
	public static JsonImportProcessor jsonImportFiles = new JsonImportProcessor();
	public static JsonExportProcessor jsonExportFiles = new JsonExportProcessor();
	public static Logger log = Logger.getLogger();
	
	private static List<FileProcessor> processors  = new ArrayList<FileProcessor>();
	
	public static void main(String[] args) {
		
		try {
			
			cmd.setLogger(log);
			cmd.parseCommands(args);
			cmd.parseFiles(args);
			
			dynamixFiles.init(cmd, log);
			jsonImportFiles.init(cmd, log);
			jsonExportFiles.init(cmd, log);
			
			processors.add(dynamixFiles);
			processors.add(jsonImportFiles);
			processors.add(jsonExportFiles);
			
			if(cmd.getFileQueue().isEmpty()) {
				log.consoleDebug("No files passed in.");
				System.exit(0);
				return;
			}
			
			List<FileItem> skipList = new ArrayList<FileItem>();
			for(FileItem file : cmd.getFileQueue()) {
				if(!skipList.contains(file)) {
					for(FileProcessor p : processors) {
						if(p.filterFile(file)) {
							skipList.add(file);
						}
					}
				}
			}
			skipList = null;
			
			for(FileProcessor p : processors) {
				if(p.hasFiles()) {
					p.processFiles();
				}
			}
			
		} catch (Exception e) {
			log.console(e.getMessage());
			System.exit(1);
		}
		
		System.exit(0);
	}
}
