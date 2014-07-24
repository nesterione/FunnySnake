package com.nesterenya.fannysnake;

public class GameContext {
	
	private static final GameContext instance = new GameContext();
	
	private GameContext() { }
	
	public static GameContext getInstance() {
		return instance;
	}
	
	public float speed = 80.0f;
	
}
