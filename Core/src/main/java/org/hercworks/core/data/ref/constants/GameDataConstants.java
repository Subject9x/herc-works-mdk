package org.hercworks.core.data.ref.constants;

public final class GameDataConstants {

	private static GameDataConstants instance;
	
	private GameDataConstants() {}
	
	
	
	
	
	public static GameDataConstants getInstance() {
		if(instance == null) {
			instance = new GameDataConstants();
		}
		return instance;
	}
}
