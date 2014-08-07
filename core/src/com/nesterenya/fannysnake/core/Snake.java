package com.nesterenya.fannysnake.core;

public class Snake {
	
	private Head head;
	private Tail tail;
	
	public void defineHeadDerection(Point currentPositon, Point lastPostion) {
		
		float xc = currentPositon.getX();
		float yc = currentPositon.getY();
		
		float xL = lastPostion.getX();
		float yL = lastPostion.getY();
		
		float Lpcat = yc - yL;
		float Locat = xL - xc;
		
		float ff = (float)(Math.atan2(Locat, Lpcat)*180 / Math.PI);
		
		head.setDirection(ff);
	}
	
	public Snake(Point initHeadPosition) {
		head = new Head(initHeadPosition, 0);
		tail = new Tail();
	}
	
	public Head getHead() {
		return head;
	}

	public Tail getTail() {
		return tail;
	}
}
