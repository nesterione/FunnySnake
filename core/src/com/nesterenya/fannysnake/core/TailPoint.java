package com.nesterenya.fannysnake.core;

public class TailPoint {
	private Point point;
	boolean isDrawable;
	boolean isExist;
	
	public TailPoint(Point point, boolean isDrawable) {
		this.point = point;
		this.isDrawable = isDrawable;
	}
	
	public Point getPoint() {
		return point;
	}
	
	public boolean isDravable() {
		return isDrawable;
	}
	
	public boolean isExist() {
		return isExist;
	}
	
	public void setExist(boolean isExist) {
		this.isExist = isExist;
	}
}
