package com.nesterenya.fannysnake;

import com.nesterenya.fannysnake.core.Point;

public class Field {
	
	private final int offsetX;
	private final int offsetY;
	private final int width;
	private final int height;
	
	public Field(int offsetX, int offsetY, int width, int height) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getOffsetX(int addOffset) {
		return offsetX + addOffset;
	}

	public int getOffsetY(int addOffset) {
		return offsetY  + addOffset;
	}

	public int getWidth(int addOffset) {
		return width - addOffset;
	}

	public int getHeight(int addOffset) {
		return height - addOffset;
	}
	
	public Point getPointFromField(int borderOffset) {
		int bx = getOffsetX(borderOffset);
		int ex = getWidth(borderOffset);
		int x = GameRandom.nextInt(bx, ex);
		
		int by = getOffsetY(borderOffset);
		int ey = getHeight(borderOffset);
		int y = GameRandom.nextInt(by,ey);
		
		return new Point(x, y);
	}
}
