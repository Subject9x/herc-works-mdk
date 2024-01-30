package com.mech.works.api;

import java.io.File;
import java.io.FileNotFoundException;

import com.mech.works.engine.GameInstall;

/**
 * TODO - primary API entrance for game install state, and loading all data.
 */
public final class GameInstallConfig {
	
	public GameInstallConfig() {}
	
	/**
	 * 
	 * @param root
	 * @return {@linkplain GameInstall}
	 * @throws FileNotFoundException
	 */
	public static GameInstall buildInstall(String root) throws FileNotFoundException{
		
		GameInstall game = new GameInstall();
		
		File f = new File(root);
		
		if(!f.exists()) {
			throw new FileNotFoundException("Root path [" + root + "] not found or doesn't exist.");
		}
		
		game.setRootDir(root);
		
		return game;
	}
}
