package com.nesterenya.fannysnake.core;

public class Head {

	private Point position;
	private float direction;
	
	public Head(Point positon, float direction) {
		this.position = positon;
		this.direction = direction;
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
}
