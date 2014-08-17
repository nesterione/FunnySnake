package com.nesterenya.fannysnake.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tail {
	 
    private Deque<Point> tailPoints = new ArrayDeque<Point>();
	private List<Integer> indexesBigBalls = new ArrayList<Integer>();
	// Start size of tail 2 balls
	int addingPoints = 2;
	
	float newLen = 0;
	float borderLen = 10;
	
	float del = 0.1f;
	
	public Tail() {
	}
	
	/**
	 * Add ball to tail
	 */
	public void increase() {
		addingPoints++;
	}
	
	
	public Point[] getPoints() {	
		Point[] f = tailPoints.toArray(new Point[tailPoints.size()]);
		return f;
	}
	
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
	
	public Point getLastPoint() {
		//TODO если пустая будет ошибка
		//TODO оптимизировать
		if(tailPoints.size()>=2) {
			Point p = tailPoints.pollLast();
			Point preLast = tailPoints.getLast();
			tailPoints.addLast(p);
			return preLast;
		} else {
			return tailPoints.getLast();
		}	
	}
	
	public int getSize() {
		return indexesBigBalls.size();
	}
	
	public int getIndexOfBall(int numberOfBall) {
		return indexesBigBalls.get(numberOfBall);
	}
	
	//REFAX
	public boolean isPointCrossTail(Point hd, Size siz) {
		boolean isFound = false;	
		Point[] points = getPoints();
	
		for(int i = 2; i < getSize()-1&&!isFound; i++) {
			
			int idx = getIndexOfBall(i);
			int c_idx = getPoints().length - idx;
			
			float posX = points[c_idx].getX();
			float posY = points[c_idx].getY();
			if( ((hd.getX()+siz.getWidth()/4)>posX&&((hd.getX())<posX+siz.getWidth()/4))&& ((hd.getY()+siz.getHeight()/4)>posY&&((hd.getY())<posY+siz.getHeight()/4))) {
				isFound=true;
				
			}
		}
		
		return isFound;
	}
}
