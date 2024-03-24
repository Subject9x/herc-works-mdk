package org.hercworks.extract.cmd;

public class Logger {

	private static Logger log;
	
	private boolean debug;
	
	private Logger() {}
	
	public void console(String msg) {
		System.out.println(msg);
	}
	
	public void consoleDebug(String msg) {
		if(debug) {
			System.out.println(msg);
		}
	}
	
	public static Logger getLogger() {
		if(log == null) {
			log = new Logger();
		}
		return log;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
