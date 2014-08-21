package com.nesterenya.fannysnake.navigation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class GameConfig extends Game{

	public BitmapFont font, levels;
    //private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    @Override
    public void create() {
        // Я взял шрифт RussoOne с Google Fonts. Сконвертировал его в TTF. (как я понял, только ttf и поддерживается)
        //FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/russoone.ttf"));   
        FreeTypeFontParameter param = new FreeTypeFontParameter();
        
       // param.size = Gdx.graphics.getHeight() / 18; // Размер шрифта. Я сделал его исходя из размеров экрана. Правда коряво, но вы сами можете поиграться, как вам угодно.
        //param.characters = FONT_CHARACTERS; // Наши символы
        //font = generator.generateFont(param); // Генерируем шрифт
        font = new BitmapFont();
	    font.setColor(Color.WHITE);
	   
        levels = new BitmapFont();
	    //param.size = Gdx.graphics.getHeight() / 20;
        //levels = generator.generateFont(param);
        font.setColor(Color.WHITE); // Цвет белый
        levels.setColor(Color.WHITE);
        //generator.dispose(); // Уничтожаем наш генератор за ненадобностью.
        
        this.setScreen(new MainMenuScreen(this));
    }

	
}
