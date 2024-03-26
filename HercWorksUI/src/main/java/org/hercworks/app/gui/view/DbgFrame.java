package org.hercworks.app.gui.view;

import javax.swing.JFrame;

import org.hercworks.app.main.AppConfig;

public class DbgFrame extends JFrame{

	private static final long serialVersionUID = -7920766824766813624L;
	
	public void initFrame(AppConfig appConfig) {
		
		this.setTitle(appConfig.getAppName() + " " + appConfig.getAppVer());
		this.setName(appConfig.getAppName());
		this.setBounds(128, 128, appConfig.getWindowSize().x, appConfig.getWindowSize().y);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

}
