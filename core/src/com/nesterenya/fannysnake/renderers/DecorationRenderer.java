package com.nesterenya.fannysnake.renderers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.decorations.Decoration;
import com.nesterenya.fannysnake.decorations.Grass;

public class DecorationRenderer {
	private final Texture grassTx;
	private final List<Decoration> decorations;
	
	private final int MIN_SIZE = 20;
	private final int MAX_SIZE = 80;
	
	private SpriteBatch batch;
	
	private Map<String, Texture> decorationTextures;
	
	public DecorationRenderer(SpriteBatch batch)	{
		this.batch = batch;
		Random rand = new Random();
		grassTx = new Texture("grass01.png");
			
		decorationTextures = new HashMap<String, Texture>();
		decorationTextures.put("grass",grassTx);
		
		
		decorations = new ArrayList<Decoration>();
		int countOfDecor = 10;
		for(int i = 0; i<countOfDecor; i++)	{
			//TODO убрать GDX и заменить на свой экран
			int x = rand.nextInt( Gdx.graphics.getWidth());
			//TODO убрать GDX и заменить на свой экран
			int y = rand.nextInt( Gdx.graphics.getHeight());
			
			int siz = rand.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
			
			Grass dec = new Grass(new Point(x,y), new Size(siz, siz));
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
