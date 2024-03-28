package org.hercworks.extract;

import org.hercworks.extract.cmd.ExcavatorCmdLine;
import org.hercworks.extract.cmd.Logger;
import org.hercworks.extract.exec.FileProcessor;
import org.hercworks.voln.Voln.FileType;

/**
 * 
 */
public class CommandLineMain {
	
	public static ExcavatorCmdLine cmd  = ExcavatorCmdLine.cmd();
	public static FileProcessor processor = FileProcessor.instance();
	public static Logger log = Logger.getLogger();
	
	public static void main(String[] args) {
		
		try {
			
			cmd.setLogger(log);
			cmd.parseCommands(args);
			cmd.parseFiles(args);
			
			processor.init(cmd, log);
			
			if(cmd.getFileQueue().isEmpty()) {
				log.consoleDebug("No files passed in.");
				System.exit(0);
				return;
			}
			
			for(String path : cmd.getFileQueue().keySet()) {
				if(cmd.getFileQueue().get(path).equals(FileType.VOL)) {
					processor.unpackVolFile(path);
				}
				else if(cmd.getFileQueue().get(path).equals(FileType.DBA)) {
					processor.unpackDBA(path);
				}
				else if(cmd.getFileQueue().get(path).equals(FileType.DPL)) {
					processor.exportDPL(path);
				}
				else if(cmd.getFileQueue().get(path).equals(FileType.DBM)) {
					processor.exportDBM(path);
				}
			}
			
		} catch (Exception e) {
			log.console(e.getMessage());
			System.exit(1);
		}
		
		System.exit(0);
	}
}
