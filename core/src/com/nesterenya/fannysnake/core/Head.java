package com.nesterenya.fannysnake.core;

public class Head {

	private Point position;
	private float direction;
	private Size size;
	private boolean isCloseEyes;
	
	private float blinkRate = 5f;
	private float durationRate = 0.3f;
	private float blinkTime = 0;
	
	public boolean IsCloseEyes() {
		return isCloseEyes;
	}
	
	public void tryBlinkEyes(float delta) {
		blinkTime += delta;
		if(isCloseEyes) {
			if(blinkTime>durationRate) {
				isCloseEyes = false;
				blinkTime = 0;
			}
		} else {
			if(blinkTime>blinkRate) {
				isCloseEyes = true;
				blinkTime = 0;
			}
		}
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
	
	public void moveHead(float deltaX, float deltaY) {
		moveHeadX(deltaX);
		moveHeadY(deltaY);
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
