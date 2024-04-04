package org.hercworks.extract.cmd;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.hercworks.extract.exec.LoggingUtil;
import org.hercworks.voln.FileType;


/***
 * Singleton core of commandline processor.
 * Wrapped {@linkplain org.apache.commons.cli.CommandLine} classes.
 * 
 */
public final class ExcavatorCmdLine extends LoggingUtil{

	//consistent arg values.
	public static enum OptionArgs{
		
		HELP("h"),
		DBG("x"),
		DPL("p"),
		SRC("s");
		
		private String val;
		
		private OptionArgs(String val) {
			this.val = val;
		}
		
		public String val() {
			return this.val;
		}
	}
	
	private static ExcavatorCmdLine cmd;
	
	private CommandLine cmdLine;
	private CommandLineParser parser;
	private Options options;
	private Map<String, FileType> fileQueue;
	
	private ExcavatorCmdLine() {

		this.parser = new DefaultParser();
		this.options = new Options();
		this.fileQueue = new HashMap<String, FileType>();
		
		this.options.addOption(OptionArgs.HELP.val(), "Display help options");
		this.options.addOption(OptionArgs.DBG.val(), "Set log output to debug mode.");
		this.options.addOption(OptionArgs.DPL.val(), "Must provide at least 1 DPL file - will export DBA and DBM to colorized copies.");
		this.options.addOption(OptionArgs.SRC.val(), "All non-VOL files will be exported to the input file's source directory.");
	}
	
	public void parseCommands(String[] args) {
		
		try {
			cmdLine = parser.parse(options, args);
			
			if(cmdLine.hasOption(OptionArgs.HELP.val())) {
				printHelp();
			}
			
			if(cmdLine.hasOption(OptionArgs.DBG.val())) {
				getLogger().setDebug(true);
			}
			
			getLogger().console("-------------------Active options-------------------");
			for(Option o : cmdLine.getOptions()) {
				getLogger().console("-"+o.getOpt() + " = " + o.getDescription());
			}
			
		} catch (ParseException e) {
			getLogger().console(e.getMessage());
		}
	}
	
	
	public Map<String, FileType> parseFiles(String[] args) throws Exception{
		if(args.length == 0) {
			throw new NullPointerException("error: no files or arguments!");
		}
		
		for(String arg : args) {
			FileType t = checkFileTypes(arg);
			if(t != null) {
				fileQueue.put(arg, t);	
			}
		}
		
		return fileQueue;
	}
	
	private void printHelp() {
		options.getOptions().forEach((option)->{
			getLogger().console("-"+option.getOpt() + " = " + option.getDescription());
		});
	}
	
	private FileType checkFileTypes(String arg) {
		for(FileType t : FileType.values()) {
			if(arg.toLowerCase().contains(t.val())) {
				return t;
			}
		}
		return null;
	}
	
	public Map<String, FileType> getFileQueue(){
		return this.fileQueue;
	}
	
	public boolean checkOption(ExcavatorCmdLine.OptionArgs arg) {
		return cmdLine.hasOption(arg.val());
	}
	
	public static ExcavatorCmdLine cmd() {
		if(cmd == null) {
			cmd = new ExcavatorCmdLine(); 
		}
		return cmd;
	}
}
