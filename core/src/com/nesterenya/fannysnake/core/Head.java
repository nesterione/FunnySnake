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
	
	private float radius;
	
	public float getRadius() {
		return radius;
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
		this.radius = 25;
	}
	
	public Point getPosition() {
		return position;
	}
	
	public void setPostion(Point position) {
		this.position = position;
	}
	
	final private float EPS_HEAD_ROTATE = 1.5f;
		
	private float calcDistance(Point p1, Point p2) {
		
		float x = p1.getX() - p2.getX();
		float y = p1.getY() - p2.getY();
		
		return (float)Math.sqrt(x*x+y*y);
	}
	
	private WallChecker checker;
	public void setWallChecer( WallChecker checker ) {
		this.checker = checker; 
	}
	
	public void moveHead(float deltaX, float deltaY, Point tail) {
		Point nPos = new Point(position.getX() + deltaX, position.getY() + deltaY);
		Point hPos = position;
		float r1 = calcDistance( hPos, tail);
		float r2 = calcDistance( nPos, tail);
		
		if(r2 < r1) {
			if(Math.abs(deltaX)>Math.abs(deltaY)) {
				nPos = new Point(hPos.getX() + deltaX*0.3f, hPos.getY() + deltaX);
			} else {
				nPos = new Point(hPos.getX() + deltaY, hPos.getY() + deltaY*0.3f);
			}
		}
		
		int ch = checker.check(deltaX, deltaY);
		if(ch==1) {
			nPos.setX(hPos.getX());
		}
		if(ch==2) {
			nPos.setY(hPos.getY());
		}
		if(ch==3) {
			nPos.setX(hPos.getX());
			nPos.setY(hPos.getY());
		}
		
		
		if((Math.abs(deltaX)+Math.abs(deltaY))>EPS_HEAD_ROTATE) {	
			defineHeadDerection(nPos, position);
		}
		position = nPos;
	}
		
	private void defineHeadDerection(Point currentPositon, Point lastPostion) {
		float xc = currentPositon.getX();
		float yc = currentPositon.getY();
		
		float xL = lastPostion.getX();
		float yL = lastPostion.getY();
		
		float Lpcat = yc - yL;
		float Locat = xL - xc;
		
		float ff = (float)(Math.atan2(Locat, Lpcat)*180 / Math.PI);
		
		direction = ff;
	}
		
	public float getDirection() {
		return direction;
	}
	
	public Size getSize() {
		return size;
	}
}
