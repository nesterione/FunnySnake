package com.nesterenya.fannysnake;

public class GameContext {
	
	private static final GameContext instance = new GameContext();
	
	private GameContext() { }
	
	public static GameContext getInstance() {
		return instance;
	}
	
	public float speed = 180.0f;
	public float time = 2;
	public float nextStep = 2;
	
	//TODO 
	public float timeOfRef = 0.15f;
	public float nextRefTime = 0.15f;
	
	public float disBetweenBalls = 30;
	
	public int score = 0;
}
