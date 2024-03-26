package org.hercworks.app.main;

import org.hercworks.app.gui.view.DbgFrame;

public class AppMain {

	private static AppConfig appConfig;
	
	public static void main(String[] args) {
		
		appConfig = AppConfig.getConfig();
		appConfig.debugConfig();
		
		
		DbgFrame main = new DbgFrame();
		main.initFrame(appConfig);
		
	}

}
