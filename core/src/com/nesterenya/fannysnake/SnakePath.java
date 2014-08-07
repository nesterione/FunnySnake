package com.nesterenya.fannysnake;

import java.util.ArrayList;
import java.util.List;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.debug.Logger;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.ExplicitGroup;

public class SnakePath {
	
	private final List<Point> path;
	
	public List<Point> getListRefPoints() {
		//TODO возращает текущий объект
		return path;
	}
	
	public SnakePath() {
		path = new ArrayList<Point>();
	}
	
	public void add(Point point) {
		path.add(point);
	}
	
	//TODO заменить на стек
	
	public List<Point> getListOfBodyPoints(int tailLen, float dist, Point headPos) {
		
		List<Point> points = new ArrayList<Point>();
		
		
		
		
		return points;
	}
	
	/**
	 * 
	 * @param back parcel number counting from the head of the snake (using absolute value of number)
	 * @return
	 */
	public Point getPoingBack(int back, float dist, Point headPos) {
		
		back = Math.abs(back);
		
		if(path.size()-1<back) {
			return new Point(0,0);
		} else {
			
			float expLength = dist * (back+1);
			
			Point activeP = headPos;
			int current = path.size()-1;
			
			while(expLength>dist) {
				
				float a = activeP.getX()-path.get(current).getX();
				float b = activeP.getY()-path.get(current).getY();
				activeP = path.get(current);
				current--;
				
				float c = (float)Math.sqrt(a*a + b*b);
				/*if(c>expLength) {
					break;
				}*/
			
				expLength -= c;
			}
			
			//Calculation detailes point
		float a = activeP.getX()-path.get(current).getX();
		float b = activeP.getY()-path.get(current).getY();
			
		float L = (float)Math.sqrt(a*a + b*b);
			
			float pp = expLength / L;
			
			//Logger.log(Float.toString(pp));
			
			float newX = -a * pp;
			float newY = -b * pp;
			
			return new Point(newX+activeP.getX(), newY + activeP.getY());
			
			//return path.get(path.size() - 1 - back);
		}		
	}
}
