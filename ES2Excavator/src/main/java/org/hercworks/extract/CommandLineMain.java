package org.hercworks.extract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.extract.exec.impl.DynamixFileProcessor;
import org.hercworks.extract.exec.impl.FlightModelExportProcessor;
import org.hercworks.extract.exec.impl.HercDatJsonExportProcessor;
import org.hercworks.extract.exec.impl.HercDatJsonImportProcessor;
import org.hercworks.extract.exec.impl.ShellDatJsonExportProcessor;
import org.hercworks.extract.exec.impl.ShellDatJsonImportProcessor;
import org.hercworks.extract.util.FileItem;

/**
 * 
 */
public final class CommandLineMain {
	
	public static ExcavatorCmdLine cmd  = ExcavatorCmdLine.cmd();
	
	public static DynamixFileProcessor dynamixFiles = new DynamixFileProcessor();
	public static Logger log = Logger.getLogger();
	
	private static List<FileProcessor> processors  =
			Arrays.asList(new DynamixFileProcessor(), new ShellDatJsonImportProcessor(), new ShellDatJsonExportProcessor(),
					new HercDatJsonExportProcessor(), new HercDatJsonImportProcessor(), new FlightModelExportProcessor());
	
	public static void main(String[] args) {
		String version = System.getProperty("java.version");
		System.out.println("Java environment: " + version);
		
		String[] ver = version.split("\\.");
		if(ver.length > 0) {
			if(ver[0].equals(String.valueOf(1))) {
				if(Integer.valueOf(ver[1]) < 9) {
					System.out.println("Java version mismatch, requires 9+, found:" + version);
					System.exit(1);
					return;
				}
			}
			else {
				if(Integer.valueOf(ver[0]) < 9) {
					System.out.println("Java version mismatch, requires 9+, found:" + version);
					System.exit(1);
					return;
				}
			}
		}
			
		try {	
			cmd.setLogger(log);
			cmd.parseCommands(args);
			cmd.parseFiles(args);
			
			for(FileProcessor processor : processors) {
				processor.init(cmd, log);
			}
			
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
