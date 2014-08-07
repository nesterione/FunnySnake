package com.nesterenya.fannysnake.core;

public class Head {

	private Point position;
	private float direction;
	private Size size;
	private boolean isCloseEyes = false;
	
	public boolean getIsCloseEyes() {
		return isCloseEyes;
	}
	
	public void setIsCloseEyes(boolean isCl) {
		isCloseEyes = isCl;
	}
	
	public Head(Point positon, float direction) {
		this.position = positon;
		this.direction = direction;
		this.size = new Size(50, 50);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPostion(Point position) {
		this.position = position;
	}
	
	public void moveHeadX(float delta) {
		position.setX( position.getX() + delta );
	}
	
	public void moveHeadY(float delta) {
		position.setY( position.getY() + delta );
	}
	
	public void setDirection(float direction) {
		this.direction = direction;
	}
	
	public float getDirection() {
		return direction;
	}
	
	public Size getSize() {
		return size;
	}
}
