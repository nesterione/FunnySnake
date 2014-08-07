package com.nesterenya.fannysnake.renderers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Point;

public class DecorationRenderer {
	
	private DecorationRenderer()	{
		//Don't use this constructor
	}
	
	private static List<Texture> grasses = new ArrayList<Texture>();
	private static List<Point> posOfGrasses = new ArrayList<Point>();
	
	static {
		// TODO переделать все в одну картинку
		grasses.add(new Texture("grass01.png"));
		grasses.add(new Texture("grass02.png"));
		grasses.add(new Texture("grass03.png"));
		grasses.add(new Texture("grass01.png"));
		grasses.add(new Texture("grass02.png"));
		grasses.add(new Texture("grass03.png"));
		
		Random rand = new Random();
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
		posOfGrasses.add(new Point(rand.nextInt( Gdx.graphics.getWidth()), rand.nextInt( Gdx.graphics.getHeight())));
	}
	
	public static void grassRender(SpriteBatch batch) {
		
		batch.begin();
		for(int i = 0; i < grasses.size(); i++ ) {
			
			batch.draw(grasses.get(i), posOfGrasses.get(i).getX() , posOfGrasses.get(i).getY());
		}
		
		
		batch.end();
	}
}
