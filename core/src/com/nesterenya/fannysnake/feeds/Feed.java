package com.nesterenya.fannysnake.feeds;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public abstract class Feed {
	public abstract int getCost();
	public abstract String getName();
	public abstract Point getPosition();
	public abstract Size getSize();
}
