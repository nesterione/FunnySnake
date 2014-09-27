package com.nesterenya.fannysnake;

import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.feeds.AppleFeed;
import com.nesterenya.fannysnake.feeds.CoinFeed;
import com.nesterenya.fannysnake.feeds.DiamondFeed;
import com.nesterenya.fannysnake.feeds.Feed;
import com.nesterenya.fannysnake.feeds.OrangeFeed;
import com.nesterenya.fannysnake.feeds.PearFeed;
import com.nesterenya.fannysnake.feeds.StoneFeed;

public class FeedController {

	private final int MIN_SIZE = 40;
	private final int MAX_SIZE = 70;
	
	private Feed[] feeds = new Feed[] {
		new AppleFeed(new Point(50, 50), new Size(50, 50)),
		new AppleFeed(new Point(300, 400), new Size(50, 50))
	};
	
	private final static float MIN_TIME = 2.0f;
	private final static float MAX_TIME = 5.0f;
	
	private float[] timeLive = new float[] {2, 3};
	private float[] currentTime = new float[] {0,0};
	
	private final Field gameField;

	private Size getSizeOfFeed() {
		int siz = GameRandom.nextInt(MIN_SIZE, MAX_SIZE);
		Size s = new Size(siz, siz);
		return s;
	}

	public FeedController(Field gameField) {
		this.gameField = gameField;
	}

	enum types {
		APPLE, PEAR, ORANGE, COIN, DIAMOND, STONE
	};

	public Feed createNextFeed(Point position, Size size) {
		// TODO убрать, просто ужасная строка
		types type = types.values()[GameRandom.nextInt(types.values().length)];
		switch (type) {
		case APPLE:
			return new AppleFeed(position, size);
		case PEAR:
			return new PearFeed(position, size);
		case ORANGE:
			return new OrangeFeed(position, size);
		case COIN:
			return new CoinFeed(position, size);
		case DIAMOND: 
			return new DiamondFeed(position, size);
		case STONE:
			return new StoneFeed(position, size);
		default: 
			return new AppleFeed(position, size);
		}
	}

	public void next(float deltaTime) {
		for (int i = 0; i < currentTime.length; i++) {
			currentTime[i] += deltaTime;
			//System.out.println(timeLive[i]);
		}
		
		for(int numberFeed =0; numberFeed< currentTime.length; numberFeed++) {
			if(currentTime[numberFeed] > timeLive[numberFeed]) {
				currentTime[numberFeed] = 0;
				Size s = getSizeOfFeed();
				int borderWidth = (int)(s.getWidth()/2);
				Point p = gameField.getPointFromField(borderWidth);
				feeds[numberFeed] = createNextFeed(p,s); 
				timeLive[numberFeed] = GameRandom.nextFloat(MIN_TIME, MAX_TIME);
			}
		}
	}

	public Feed[] getFeeds() {
		return feeds;
	}

	// Posible
	public Feed eatFeed(Head head) {
		Feed eatedFeed = null;
		
		for(int i =0;i< feeds.length;i++) {
			if (feeds[i] != null) {

				// Еда считается седеной, кода центор головы змейки пападает в
				// квадрат которым задается размер еды
				Point hd = head.getPosition();
				Point fPos = feeds[i].getPosition();
				Size fSiz = feeds[i].getSize();
				float halfSizeX = fSiz.getWidth() / 2;
				float halfSizeY = fSiz.getHeight() / 2;
				boolean isx = (fPos.getX() - halfSizeX) < hd.getX()
						&& hd.getX() < (fPos.getX() + halfSizeX);
				boolean isy = (fPos.getY() - halfSizeY) < hd.getY()
						&& hd.getY() < (fPos.getY() + halfSizeY);
				if (isx && isy) {
					eatedFeed = feeds[i];
					feeds[i] = null;
				}
			}
		}
		return eatedFeed;
	}

}
