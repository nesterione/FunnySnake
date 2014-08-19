package com.nesterenya.fannysnake;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.feeds.AppleFeed;
import com.nesterenya.fannysnake.feeds.Feed;

public class FeedController {
	
	private float currentTime = 0;
	private float nextStepTime = 2;
	private Feed feed;
	
	private static Random rand;
	private final int minSize = 20;
	private final int maxSize = 80;
	
	static {
		rand = new Random();
	}
	
	public FeedController() {
		this.currentTime = 0;
		this.feed = new AppleFeed(new Point(50, 50), new Size(50, 50));
	}
	
	public void next(float deltaTime) {
		currentTime+=deltaTime;
		if(currentTime> nextStepTime) {
			currentTime = 0;
			//TODO убрать Gdx.graphics и заменить на переменную указываю реальный диапозон
			Point p = new Point(rand.nextInt(Gdx.graphics.getWidth()),rand.nextInt(Gdx.graphics.getHeight()));
			int siz = rand.nextInt(maxSize - minSize) + minSize;
			Size s = new Size(siz,siz);
			feed = new AppleFeed(p,s);
		}
	}
	
	public Feed getFeed(){
		return feed;
	}
	
	// Posible 
	public boolean eatFeedWhenItPossible(Head head) {
		boolean isFeedEating = false;
		Point hd = head.getPosition();
		Size siz = head.getSize();
		
		if(feed!=null) {
			Point fPos = feed.getPosition();
			if( ((hd.getX()+siz.getWidth())>fPos.getX()&&((hd.getX())<fPos.getX()+siz.getWidth()))&& ((hd.getY()+siz.getHeight())>fPos.getY()&&((hd.getY())<fPos.getY()+siz.getHeight()))) {
				isFeedEating = true;
				feed = null;
			}
		}
		
		return isFeedEating;
	}
	
}
