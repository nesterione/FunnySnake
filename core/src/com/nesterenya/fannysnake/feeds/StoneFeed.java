package com.nesterenya.fannysnake.feeds;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public class StoneFeed extends Feed{
	private final Point position;
	private final Size size;
	private final String name = "stone";
	
	public StoneFeed(Point position, Size size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public int getCost() {
		return -500;
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
	
	@Override
	public int grow() {
		return -3;
	}
}
