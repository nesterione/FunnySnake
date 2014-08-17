package com.nesterenya.fannysnake.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.core.Tail;

public class SnakeRenderer {
	
	private Sprite headSp;
	private Sprite headClosedEyesSp;
	private Texture ball;
	private SpriteBatch batch;
	private Head head;
	private Tail tail;
		
	public SnakeRenderer(SpriteBatch batch, Snake snake)	{
		this.batch = batch;
		
		Texture headTx = new Texture("head.png");
		headSp = new Sprite(headTx);
		Texture closeEyesTx = new Texture("headClose.png");
		headClosedEyesSp = new Sprite(closeEyesTx);
		ball = new Texture("ball2.png");
		
		head = snake.getHead();
		tail = snake.getTail();
	}
	
	public void render() {
		batch.begin();
		drawTail();
		drawHead();		
		batch.end();
	}
		
	private void drawSpriteHead(Sprite sprite) {
		sprite.setPosition(head.getPosition().getX(), head.getPosition().getY());
		sprite.setRotation(head.getDirection());
		sprite.draw(batch);
	}
	
	private void drawHead() {
		
		boolean isClosed = head.getIsCloseEyes();
		if(isClosed) drawSpriteHead(headClosedEyesSp);
		else drawSpriteHead(headSp);
	}
	
	private void drawTail() {
		
		for(int i = 0;i < tail.getSize()-2;i++) {
			int idx = tail.getIndexOfBall(i);
			int c_idx = tail.getPoints().length - idx;
			batch.draw(ball, tail.getPoints()[c_idx].getX(), tail.getPoints()[c_idx].getY());
		}
		
		drawEndOfTail();
	}
	
	private void drawEndOfTail() {
		//Draw two last balls
				if(tail.getSize()>=2) {
				int ii = tail.getSize()-2;
				int idx = tail.getIndexOfBall(ii);
				int c_idx = tail.getPoints().length - idx;
				batch.draw(ball, 
						tail.getPoints()[c_idx].getX()+ball.getWidth()/6, 
						tail.getPoints()[c_idx].getY()+ball.getHeight()/6,
						ball.getWidth()/1.4f,ball.getHeight()/1.4f);
				ii++;
				idx = tail.getIndexOfBall(ii);
				c_idx = tail.getPoints().length - idx;
				batch.draw(ball, 
						tail.getPoints()[c_idx].getX()+ball.getWidth()/4, 
						tail.getPoints()[c_idx].getY()+ball.getHeight()/4,
						ball.getWidth()/2,ball.getHeight()/2);
				
				}
	}
}
