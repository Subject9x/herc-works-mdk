package org.hercworks.extract.cmd;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.hercworks.extract.exec.LoggingUtil;
import org.hercworks.extract.util.FileItem;
import org.hercworks.voln.FileType;

import com.fasterxml.jackson.databind.ObjectMapper;


/***
 * Singleton core of commandline processor.
 * Wrapped {@linkplain org.apache.commons.cli.CommandLine} classes.
 * 
 */
public final class ExcavatorCmdLine extends LoggingUtil{

	//consistent arg values.
	public static enum OptionArgs{
		
		HELP("h", "-h"),
		DBG("x", "-x"),
		DPL("p","-p"),
		SRC("s", "-s"),
		MKDBA("cdba", "-cdba");
		
		private String val;
		private String arg;
		
		private OptionArgs(String val, String arg) {
			this.val = val;
			this.arg = arg;
		}
		
		public String val() {
			return this.val;
		}
		
		public String arg() {
			return this.arg;
		}
	}
	
	private static ExcavatorCmdLine cmd;
	
	private CommandLine cmdLine;
	private CommandLineParser parser;
	private Options options;
	private List<FileItem> fileQueue;
	private List<String> optionArgs = new ArrayList<String>();	//populated by parsing via CommandLineParser
	private ObjectMapper jsonMapper = new ObjectMapper();
	
	private ExcavatorCmdLine() {

		this.parser = new DefaultParser();
		this.options = new Options();
		this.fileQueue = new ArrayList<FileItem>();
		
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
				optionArgs.add(OptionArgs.HELP.arg());
			}
			
			if(cmdLine.hasOption(OptionArgs.DBG.val())) {
				getLogger().setDebug(true);
				optionArgs.add(OptionArgs.DBG.arg());
			}
			
			if(cmdLine.hasOption(OptionArgs.DPL.val())) {
				optionArgs.add(OptionArgs.DPL.arg());
			}
			
			if(cmdLine.hasOption(OptionArgs.SRC.val())) {
				optionArgs.add(OptionArgs.SRC.arg());
			}			
			
			getLogger().console("-------------------Active options-------------------");
			for(Option o : cmdLine.getOptions()) {
				getLogger().console("-"+o.getOpt() + " = " + o.getDescription());
			}
			
		} catch (ParseException e) {
			getLogger().console(e.getMessage());
		}
	}
	
	
	public List<FileItem> parseFiles(String[] args) throws Exception{
		if(args.length == 0) {
			throw new NullPointerException("error: no files or arguments!");
		}
		
		for(String arg : args) {
			if(!optionArgs.contains(arg)) {
				
				FileType t = checkFileTypes(arg);
				if(t != null) {
					fileQueue.add(new FileItem(arg, t));	
				}
				else {
					getLogger().console("invalid file[" + arg +"], ignoring!");
				}
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
	
	public List<FileItem> getFileQueue(){
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
	
	public ObjectMapper getJsonMapper() {
		return this.jsonMapper;
	}
}
