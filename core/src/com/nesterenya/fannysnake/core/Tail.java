package com.nesterenya.fannysnake.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tail {
	//private int size;
	
	//List<Point> tailPoints = new ArrayList<Point>();
	//List<Point> offSidePoints;
	
	Deque<Point> tailPoints = new ArrayDeque<Point>();

	List<Integer> indexesBigBalls = new ArrayList<Integer>();
	
	public Tail(/*int initSize*/) {
		//this.size = initSize;
		//offSidePoints = new ArrayList<Point>();	
	}
	
	int addingPoints = 2;
	
	public void increase() {
		//float dis = 30;
		//tailPoints.addLast(newHeadPoint);
		addingPoints++;
		
	}
	
	public Point[] getPoints() {
		
		Point[] f = tailPoints.toArray(new Point[tailPoints.size()]);
		return f;
	}
	
	float newLen = 0;
	float borderLen = 10;
	
	float del = 0.1f;
	
	public void moveTail(Point nextPos) {
		
		if(tailPoints.size()!=0) {
			float aa = nextPos.getX() - tailPoints.getLast().getX();
			float bb = nextPos.getY() - tailPoints.getLast().getY();
			
			if((Math.abs(aa)<del)&&(Math.abs(bb)<del)) {
				return;
			}
		}
		
		if(addingPoints>0) {
			
			if(tailPoints.size()==0) {
				tailPoints.addLast(nextPos);
			} else {
				
				
				
				Point last = tailPoints.getLast();
				tailPoints.addLast(nextPos);
				
				float a = last.getX() - nextPos.getX();
				float b = last.getY() - nextPos.getY();
				
				float c = (float)Math.sqrt(a*a+b*b);
				
				newLen+= c;
					
				tailPoints.addLast(nextPos);
				
				if(newLen>borderLen) {
					newLen = 0;
					indexesBigBalls.add(tailPoints.size()-1);
					addingPoints--;
				}
			}
			
		} else {
			if(tailPoints.size()!=0) {
				tailPoints.removeFirst();
				tailPoints.addLast(nextPos);
			}
		}
	}
	
	public List<Integer> getIndexesBigBalls() {
		return indexesBigBalls;
	}
	
	public int getSize() {
		return tailPoints.size();
	}
}
