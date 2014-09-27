package com.nesterenya.fannysnake.renderers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.Field;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.GameRandom;
import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.decorations.Decoration;
import com.nesterenya.fannysnake.decorations.Grass;

public class DecorationRenderer {
	private final Texture grassTx;
	private final List<Decoration> decorations;
	
	private final int MIN_SIZE = 40;
	private final int MAX_SIZE = 80;
	
	private SpriteBatch batch;
	
	private Map<String, Texture> decorationTextures;
	
	
	
	public DecorationRenderer(SpriteBatch batch, Field gameField)	{
		this.batch = batch;
		if(FunnySnakeGame.isAnastasia) {
			grassTx = new Texture("flower.png");
		} else {
			grassTx = new Texture("grass01.png");
		}
		
			
		decorationTextures = new HashMap<String, Texture>();
		decorationTextures.put("grass",grassTx);
		
		
		decorations = new ArrayList<Decoration>();
		int countOfDecor = 10;
		for(int i = 0; i<countOfDecor; i++)	{
			
			int siz = GameRandom.nextInt(MIN_SIZE, MAX_SIZE);
			Size s = new Size(siz, siz);
			int borderWidth = siz/2;
			Point p = gameField.getPointFromField(borderWidth);
			Grass dec = new Grass(p, s);
			decorations.add(dec);
		}
	}
	
	
	
	public void render() {
		
		batch.begin();
		for(Decoration decoration : decorations ) {
		
			batch.draw(decorationTextures.get(decoration.getName()), 
					decoration.getPositionOfLeftDownPoint().getX(),
					decoration.getPositionOfLeftDownPoint().getY(),
					decoration.getSize().getWidth(),
					decoration.getSize().getHeight());
		}
		
		batch.end();
	}
}
