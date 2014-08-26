package com.nesterenya.fannysnake;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameTools {
	
	private GameTools() {
		//this is constructor don't use then it's static class
	}
	
	private static final int WORLD_WIDTH = 800;
	private static final int WORLD_HEIGHT = 480;
	
	public static int getWorldWidth() {
		return WORLD_WIDTH;
	}
	
	public static int getWorlsHeight() {
		return WORLD_HEIGHT;
	}
	
	public static Viewport getViewport(Camera camera) {
		Viewport viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		return viewport;
	}
}
