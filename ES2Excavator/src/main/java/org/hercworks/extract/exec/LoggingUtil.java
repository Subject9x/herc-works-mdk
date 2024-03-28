package org.hercworks.extract.exec;

import org.hercworks.extract.cmd.Logger;

public abstract class LoggingUtil {


	private Logger logger;
	
	
	public void setLogger(Logger log) {
		this.logger = log;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
}
