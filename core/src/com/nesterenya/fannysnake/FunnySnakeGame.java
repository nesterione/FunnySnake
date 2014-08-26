package com.nesterenya.fannysnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.navigation.MainMenuScreen;

public class FunnySnakeGame extends Game{

	private static final FunnySnakeGame INSTANCE = new FunnySnakeGame();
	private static final int WORLD_WIDTH = 800;
	private static final int WORLD_HEIGHT = 480;
	
	private FunnySnakeGame() {	}
	
	public static FunnySnakeGame getInstance() {
		return INSTANCE;
	}
	
	public BitmapFont font, levels;
   
    @Override
    public void create() {
        font = new BitmapFont();
	    font.setColor(Color.WHITE);
        levels = new BitmapFont();
        font.setColor(Color.WHITE); 
        levels.setColor(Color.WHITE);
        this.setScreen(new MainMenuScreen());
    }
    
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
    
	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
		levels.dispose();
	}
}
