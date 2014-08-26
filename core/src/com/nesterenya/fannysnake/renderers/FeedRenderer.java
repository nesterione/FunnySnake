package com.nesterenya.fannysnake.renderers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nesterenya.fannysnake.feeds.Feed;

public class FeedRenderer {
	
	private SpriteBatch batch;
	private Texture appleTx;
	
	public FeedRenderer(SpriteBatch batch) {
		this.batch = batch;
		this.appleTx  = new Texture("feed01.png");
	}
	
	public void render(Feed feed) {
		if(feed!=null) {
			batch.begin();
			
			float middlePointX = feed.getSize().getWidth()/2;
			float middlePointY = feed.getSize().getHeight()/2;
			float x = feed.getPosition().getX()-middlePointX;
			float y = feed.getPosition().getY()-middlePointY;
			
			//TODO свой batchdraw для рисования относительно центра		
			batch.draw(appleTx, x,y , feed.getSize().getWidth(),feed.getSize().getHeight());
			batch.end();
		}
	}
}
