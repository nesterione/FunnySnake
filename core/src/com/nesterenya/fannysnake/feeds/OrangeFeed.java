package com.nesterenya.fannysnake.feeds;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public class OrangeFeed extends Feed{

	private final Point position;
	private final Size size;
	private final String name = "orange";
	
	public OrangeFeed(Point position, Size size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public int getCost() {
		return 150;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public Size getSize() {
		return size;
	}

	@Override
	public String getName() {
		return name;
	}
}
