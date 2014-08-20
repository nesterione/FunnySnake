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

		sprite.setPosition(head.getPosition().getX() - head.getRadius(), head.getPosition().getY() - head.getRadius());
		sprite.setRotation(head.getDirection());
		sprite.draw(batch);
	}
	
	private void drawHead() {
		
		if(head.IsCloseEyes()) drawSpriteHead(headClosedEyesSp);
		else drawSpriteHead(headSp);
	}
	
	private void drawTail() {
		float rad = tail.getRadius();
		for(int i = 0;i < tail.getSize()-2;i++) {
			int idx = tail.getIndexOfBall(i);
			int c_idx = tail.getPoints().length - idx;
			batch.draw(ball, tail.getPoints()[c_idx].getX()-rad, tail.getPoints()[c_idx].getY()-rad);
		}
		
		drawEndOfTail();
	}
	
	private void drawEndOfTail() {
		//Draw two last balls
		float rad = tail.getRadius();
		float preLastR = rad*0.8f;
		float lastR = rad*0.5f;
		
		if(tail.getSize()>=2) {
			int ii = tail.getSize()-2;
			int idx = tail.getIndexOfBall(ii);
			int c_idx = tail.getPoints().length - idx;
			batch.draw(ball, 
					tail.getPoints()[c_idx].getX()-preLastR, 
					tail.getPoints()[c_idx].getY()-preLastR,
					preLastR*2.0f,preLastR*2.0f);
				ii++;
				idx = tail.getIndexOfBall(ii);
				c_idx = tail.getPoints().length - idx;
				batch.draw(ball, 
						tail.getPoints()[c_idx].getX()-lastR, 
						tail.getPoints()[c_idx].getY()-lastR,
						lastR*2.0f,lastR*2.0f);
				
				}
	}
}
