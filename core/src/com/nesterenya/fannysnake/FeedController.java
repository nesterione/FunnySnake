package com.nesterenya.fannysnake;

import com.nesterenya.fannysnake.core.Head;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.feeds.AppleFeed;
import com.nesterenya.fannysnake.feeds.Feed;
import com.nesterenya.fannysnake.feeds.PearFeed;

public class FeedController {

	private final int MIN_SIZE = 40;
	private final int MAX_SIZE = 70;
	private Feed feed;

	private float currentTime = 0;
	private final float NEXT_STEP = 2;

	private final Field gameField;

	private Size getSizeOfFeed() {
		int siz = GameRandom.nextInt(MIN_SIZE, MAX_SIZE);
		Size s = new Size(siz, siz);
		return s;
	}

	public FeedController(Field gameField) {
		this.gameField = gameField;
		this.currentTime = 0;
		this.feed = new AppleFeed(new Point(50, 50), new Size(50, 50));

	}

	enum types {
		APPLE, PEAR
	};

	public Feed createNextFeed(Point position, Size size) {
		// TODO убрать, просто ужасная строка
		types type = types.values()[GameRandom.nextInt(types.values().length)];
		switch (type) {
		case APPLE:
			return new AppleFeed(position, size);
		case PEAR:
			return new PearFeed(position, size);
		default: 
			return new AppleFeed(position, size);
		}
	}

	public void next(float deltaTime) {
		currentTime+=deltaTime;
		if(currentTime> NEXT_STEP) {
			currentTime = 0;
				
			Size s = getSizeOfFeed();
			int borderWidth = (int)(s.getWidth()/2);
			Point p = gameField.getPointFromField(borderWidth);
			
			feed = createNextFeed(p,s);
		}
	}

	public Feed getFeed() {
		return feed;
	}

	// Posible
	public Feed eatFeed(Head head) {
		Feed eatedFeed = null;

		if (feed != null) {

			// Еда считается седеной, кода центор головы змейки пападает в
			// квадрат которым задается размер еды
			Point hd = head.getPosition();
			Point fPos = feed.getPosition();
			Size fSiz = feed.getSize();
			float halfSizeX = fSiz.getWidth() / 2;
			float halfSizeY = fSiz.getHeight() / 2;
			boolean isx = (fPos.getX() - halfSizeX) < hd.getX()
					&& hd.getX() < (fPos.getX() + halfSizeX);
			boolean isy = (fPos.getY() - halfSizeY) < hd.getY()
					&& hd.getY() < (fPos.getY() + halfSizeY);
			if (isx && isy) {
				eatedFeed = feed;
				feed = null;
			}
		}

		return eatedFeed;
	}

}
