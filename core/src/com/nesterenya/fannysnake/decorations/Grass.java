package com.nesterenya.fannysnake.decorations;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public class Grass implements Decoration{
	private final Point positionOfLeftDownPoint;
	private final Size size;
	
	public Grass(Point positionOfLeftDownPoint, Size size) {
		this.positionOfLeftDownPoint = positionOfLeftDownPoint;
		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public Point getPositionOfLeftDownPoint() {
		return positionOfLeftDownPoint;
	}

	@Override
	public String getName() {
		return "grass";
	}
}
