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
			batch.draw(appleTx, feed.getPosition().getX(), feed.getPosition().getY(), feed.getSize().getWidth(),feed.getSize().getHeight());
			batch.end();
		}
	}
}
