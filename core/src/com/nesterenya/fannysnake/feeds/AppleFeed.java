package com.nesterenya.fannysnake.feeds;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public class AppleFeed extends Feed{
	
	private final Point position;
	private final Size size;
	private final String name = "apple";
	
	public AppleFeed(Point position, Size size) {
		this.position = position;
		this.size = size;
	}
	
	@Override
	public int getCost() {
		
		return 10;
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
