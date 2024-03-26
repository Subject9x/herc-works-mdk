package org.hercworks.app.main;

import org.hercworks.app.api.Vector2DInt;

public class AppConfig {

	private static AppConfig config;
	private AppConfig(){}
	
	public static AppConfig getConfig() {
		if(config == null) {
			config = new AppConfig();
		}
		return config;
	}
	
	
	private String appName;
	private String appPath;
	private String appVer;
	
	private Vector2DInt windowSize;
	
	
	public void debugConfig() {
		
		windowSize = new Vector2DInt(1920, 1080);
		appName = "Earthsiege 2 MDK";
		appPath = "";
		appVer = "0.0.1";
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public Vector2DInt getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(Vector2DInt windowSize) {
		this.windowSize = windowSize;
	}
	
	
	
}