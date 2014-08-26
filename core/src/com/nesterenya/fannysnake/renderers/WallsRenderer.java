package com.nesterenya.fannysnake.renderers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Wall;

public class WallsRenderer {
	private final Texture block;
	private final List<Sprite> wallSprites;
	private final SpriteBatch batch;
	
	public WallsRenderer(SpriteBatch batch, List<Wall> walls) {
		block = new Texture("wall01.jpg");
		block.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		this.batch = batch;
		wallSprites = new ArrayList<Sprite>();
		for(Wall wall : walls) {
			Sprite sprite = new Sprite(block, 0,0, (int)wall.getSize().getWidth(), (int)wall.getSize().getHeight());
			sprite.setPosition(wall.getPositionOfLeftDownPoint().getX(), wall.getPositionOfLeftDownPoint().getY());
			wallSprites.add(sprite);
		}
	}
	
	public void render() {
		
		batch.begin();		
		for(Sprite sprite : wallSprites) {
			sprite.draw(batch);
		}
		batch.end();
	}

}
