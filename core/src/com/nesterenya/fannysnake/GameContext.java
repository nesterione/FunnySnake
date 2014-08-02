package com.nesterenya.fannysnake;

public class GameContext {
	
	private static final GameContext instance = new GameContext();
	
	private GameContext() { }
	
	public static GameContext getInstance() {
		return instance;
	}
	
	public float speed = 180.0f;
	public float time = 5;
	public float nextStep = 5;
	
	public int score = 0;
}
