package com.nesterenya.fannysnake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.screens.MainMenuScreen;

public class FunnySnakeGame extends Game{

	public static boolean isAnastasia = false;
	private static final FunnySnakeGame INSTANCE = new FunnySnakeGame();
	private static final int WORLD_WIDTH = 800;
	private static final int WORLD_HEIGHT = 480;
	private Sound menuSound;
		
	public void doMenuClickDown() {
		Gdx.input.vibrate(50);
		
		FunnySnakeGame.getInstance().menuSound.play();
	}
	
	private FunnySnakeGame() {
		
	}
	
	public static FunnySnakeGame getInstance() {
		return INSTANCE;
	}
	
	public BitmapFont font;
   
    @Override
    public void create() {
        font = new BitmapFont();
	    font.setColor(Color.WHITE);
	    font.setScale(2);
	    
       
        
        this.setScreen(new MainMenuScreen());
        
        menuSound = Gdx.audio.newSound(Gdx.files.internal("sounds/piv.wav"));
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
		menuSound.dispose();
	}


}
