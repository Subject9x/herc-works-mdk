package org.hercworks.extract.cmd;

import java.util.HashMap;
import java.util.Map;

import org.hercworks.voln.Voln;
import org.hercworks.voln.Voln.FileType;


/***
 * Singleton core of commandline processor.
 * 
 * 
 */
public final class CommandLine {

	private static CommandLine cmd;
	private Logger logger;
	
	private CommandLine() {}
	
	
	public static CommandLine cmd() {
		if(cmd == null) {
			cmd = new CommandLine(); 
		}
		return cmd;
	}
	
	public void setLogger(Logger log) {
		this.logger = log;
	}
	
	public Map<String, FileType> parseFiles(String[] args) throws Exception{
		if(args.length == 0) {
			throw new NullPointerException("error: no files or arguments!");
		}
		
		Map<String, FileType> fileQueue = new HashMap<String, Voln.FileType>();
		
		for(String arg : args) {
			FileType t = checkFileTypes(arg);
			if(t != null) {
				fileQueue.put(arg, t);	
			}
		}
		
		return fileQueue;
	}
	
	
	public void parseCommands(String[] args) {
		for(String s : args) {
			if(s.toLowerCase().equals("-x")) {
				logger.setDebug(true);
			}
		}
	}

	private FileType checkFileTypes(String arg) {
		for(FileType t : Voln.FileType.values()) {
			if(arg.toLowerCase().contains(t.val())) {
				return t;
			}
		}
		return null;
	}
	
}
