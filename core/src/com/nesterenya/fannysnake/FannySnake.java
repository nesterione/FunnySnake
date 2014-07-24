package com.nesterenya.fannysnake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.renderers.SnakeRenderer;

public class FannySnake extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	private Snake snake;
	Sprite sprite;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("head.png");
		sprite = new Sprite(img);
		snake = new Snake(new Point(0, 0));
	}

	//TODO плохо
	Point lastPos = new Point(0,0);
	
	@Override
	public void render () {
		//Scene init
		Gdx.gl.glClearColor(0.8f, 1, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		lastPos = new Point(snake.getHead().getPosition().getX(),snake.getHead().getPosition().getY());
		
		//Event handling
		float delta = Gdx.graphics.getDeltaTime() * GameContext.getInstance().speed;
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) { 
			//TODO warn
		    snake.getHead().moveHeadX(-delta); 
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			snake.getHead().moveHeadX(delta); ;
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			 snake.getHead().moveHeadY(delta); 
		}		
			
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			 snake.getHead().moveHeadY(-delta); 
		}
		
		//Direction calculation
		Point headPos = snake.getHead().getPosition();
		
		float xc = headPos.getX();
		float yc = headPos.getY();
		
		snake.defineHeadDerection(headPos, lastPos);
		
		sprite.setPosition(xc, yc);
		
		batch.begin();
		sprite.setRotation(snake.getHead().getDirection());
		sprite.draw(batch);
		batch.end();
		
		
		SnakeRenderer.render(snake);
		
		
		//batch.draw(headSnake, nodes.get(jj).x, nodes.get(jj).y);
		//lastX = (int) nodes.get(jj).x;
		//lastY = (int) nodes.get(jj).y;
		//lastPos = headPos;
		
		
		
		//Scene drawing
		//batch.begin();
		//batch.draw(img, headPos.getX(), headPos.getY());
		//batch.end();
	}
}
