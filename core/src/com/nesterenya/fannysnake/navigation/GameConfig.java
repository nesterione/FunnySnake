package com.nesterenya.fannysnake.navigation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameConfig extends Game{

	public BitmapFont font, levels;
   
    @Override
    public void create() {
        font = new BitmapFont();
	    font.setColor(Color.WHITE);
        levels = new BitmapFont();
        font.setColor(Color.WHITE); // Цвет белый
        levels.setColor(Color.WHITE);
        
        this.setScreen(new MainMenuScreen(this));
    }

}
