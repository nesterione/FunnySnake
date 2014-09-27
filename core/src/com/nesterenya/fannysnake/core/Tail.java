package com.nesterenya.fannysnake.core;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.nesterenya.fannysnake.DoubleList;

//TODO refactor this class
public class Tail {
	 
		DoubleList<Point> tailPoints = new DoubleList<Point>();
		//List<Point> tailPoints = new ArrayList<Point>(256);
		
		public void removeFirst() {
			tailPoints.removeFirst();
		}
		
		public Point getLast() {
			return tailPoints.getLast();
		}
		
		public void addLast(Point p) {
			tailPoints.addLast(p);
		}
		
		public Point get(int i) {
			return tailPoints.get(i);
		}    
    
    
	private List<Integer> indexesBigBalls = new ArrayList<Integer>();
	// Start size of tail 2 balls
	int addingPoints = 2;
	int removingPoints = 0;
	
	float newLen = 0;
	float borderLen = 34;
	
	float del = 2f;
	
	public Tail() {
		
	}
	
	public float getRadius() {
		return 25;
	}
	
	public void grow(int c) {
		if(c>0) {
			addingPoints+=c;
		}
		if(c < 0) {
			if((indexesBigBalls.size()+c)>=2) {
				removingPoints-=c;
			} else {
				//TODO dead snake show message
			}
		}
	}
	
	/**
	 * Add ball to tail
	 */
	/*public void increase() {
		addingPoints++;
	}
	*/
	/*public void reduction() {
		
	}*/
		
	public Point getTailPointFromHead(int number) {
		int idx = getIndexOfBall(number);
		int c_idx =tailPoints.size() - idx;
		Point p = get(c_idx); 
		return p;
	}
	
	private void removeBigPoint() {
		Integer pre = indexesBigBalls.get(indexesBigBalls.size()-2);
		
		while(tailPoints.size()> pre) {
			removeFirst();
		}		
		
		indexesBigBalls.remove(indexesBigBalls.size()-1);
		removingPoints--;
	}
	
	private void addBigPoint(Point nextPos) {
		if(tailPoints.size()==0) {
			addLast(nextPos);
		} else {
						
			Point last = getLast();
			addLast(nextPos);
			
			float a = last.getX() - nextPos.getX();
			float b = last.getY() - nextPos.getY();
			float c = (float)Math.sqrt(a*a+b*b);
			
			newLen+= c;
				
			addLast(nextPos);
			
			if(newLen>borderLen) {
				newLen = 0;
				indexesBigBalls.add(tailPoints.size()-1);
				addingPoints--;
			}
		}
	}
	
	public void moveTail(Point nextPos) {
		
		//Пропустить если смещение слишком маленькое
		if(tailPoints.size()!=0) {
			float aa = nextPos.getX() - getLast().getX();
			float bb = nextPos.getY() - getLast().getY();
			float dist = (float)Math.sqrt(aa*aa + bb*bb);
			if(dist<del) return;
		}
		
		if(removingPoints>0) {
			removeBigPoint();
		}

		if(addingPoints>0) {
			addBigPoint(nextPos);
		} else {
			if(tailPoints.size()!=0) {
				
				Point p1 = getLast();
				Point p2 = nextPos;
				float a = p1.getX() - p2.getX();
				float b = p1.getY() - p2.getY();
				float L = (float)Math.sqrt(a*a + b*b);
				
				int border = (int) Math.ceil(L/del);
				float stepX = (p2.getX() - p1.getX())/border;
				float stepY = (p2.getY() - p1.getY())/border;
				
				//System.out.println(Math.ceil(L/2));
				
				for(int i = 0; i < border; i++ ) {
					
					Point pp = getLast();
					Point newP = new Point(pp.getX() + stepX, pp.getY() + stepY);
					
					removeFirst();
					addLast(newP);
				}
			}
		}
	}
		
	public int getSize() {
		return indexesBigBalls.size();
	}
	
	public int getIndexOfBall(int numberOfBall) {
		if(indexesBigBalls.size()==0) return 0;
		return indexesBigBalls.get(numberOfBall);
	}
	
	//TODO REFAX
	public boolean isPointCrossTail(Point hd, Size siz) {
		boolean isFound = false;	
		//Point[] points = getPoints();
	
		for(int i = 2; i < getSize()-1&&!isFound; i++) {
			
			//int idx = getIndexOfBall(i);
			//int c_idx = getPoints().length - idx;
			Point p = getTailPointFromHead(i);
			
			float posX = p.getX();
			float posY = p.getY();
			if( ((hd.getX()+siz.getWidth()/4)>posX&&((hd.getX())<posX+siz.getWidth()/4))&& ((hd.getY()+siz.getHeight()/4)>posY&&((hd.getY())<posY+siz.getHeight()/4))) {
				isFound=true;
				
			}
		}
		return isFound;
	}
	
}
