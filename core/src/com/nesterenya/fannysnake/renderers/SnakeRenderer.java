package com.nesterenya.fannysnake.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.core.Tail;

public class SnakeRenderer {
	
	private static Sprite sprite;
	private static Sprite closeEyes;
	private static Texture ball;
	
	static {
		Texture headTx = new Texture("head.png");
		sprite = new Sprite(headTx);
		Texture closeEyesTx = new Texture("headClose.png");
		closeEyes = new Sprite(closeEyesTx);
		ball = new Texture("ball2.png");
	}
	
	private SnakeRenderer()	{
		//Don't use this constructor
	}
	
	public static void render(SpriteBatch batch, Snake snake) {
		drawHead(batch, snake.getHead());
		drawTail(batch, snake.getTail());
	}
	
	private static void drawHead(SpriteBatch batch,Head head) {
		
		if(head.getIsCloseEyes()) {
			closeEyes.setPosition(head.getPosition().getX(), head.getPosition().getY());
			batch.begin();
			closeEyes.setRotation(head.getDirection());
			closeEyes.draw(batch);
			batch.end();
		} else {
			sprite.setPosition(head.getPosition().getX(), head.getPosition().getY());
			batch.begin();
			sprite.setRotation(head.getDirection());
			sprite.draw(batch);
			batch.end();
		}
		
		
	}
	
	private static void drawTail(SpriteBatch batch, Tail tail) {
		batch.begin();
		for(int i = 0;i < tail.getIndexesBigBalls().size()-2;i++) {
			int idx = tail.getIndexesBigBalls().get(i);
			int c_idx = tail.getPoints().length - idx;
			batch.draw(ball, tail.getPoints()[c_idx].getX(), tail.getPoints()[c_idx].getY());
		}
		
		//Draw two last balls
		if(tail.getIndexesBigBalls().size()>=2) {
		int ii = tail.getIndexesBigBalls().size()-2;
		int idx = tail.getIndexesBigBalls().get(ii);
		int c_idx = tail.getPoints().length - idx;
		batch.draw(ball, 
				tail.getPoints()[c_idx].getX(), 
				tail.getPoints()[c_idx].getY(),
				ball.getWidth()*0.8f,ball.getHeight()*0.8f);
		ii++;
		idx = tail.getIndexesBigBalls().get(ii);
		c_idx = tail.getPoints().length - idx;
		batch.draw(ball, 
				tail.getPoints()[c_idx].getX(), 
				tail.getPoints()[c_idx].getY(),
				ball.getWidth()*0.7f,ball.getHeight()*0.7f);
		
		}
		batch.end();
	}
	
}
