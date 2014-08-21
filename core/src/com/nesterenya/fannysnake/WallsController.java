package com.nesterenya.fannysnake;

import java.util.ArrayList;
import java.util.List;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.core.Wall;

public class WallsController {
	
	private final List<Wall> walls;
	
	public WallsController() {
		
		//TODO убрать эти неясные размеры
		Wall left = new Wall(new Point(0,25), new Size(25,430));
		Wall down = new Wall(new Point(0,0), new Size(840,25));
		Wall up = new Wall(new Point(0,455), new Size(840,25));
		Wall right = new Wall(new Point(775,25), new Size(25,430));
		
		walls = new ArrayList<Wall>();
		walls.add(left);
		walls.add(down);
		walls.add(up);
		walls.add(right);
	}
	
	public List<Wall> getListWalls() {
		return walls;
	}
	
	public boolean checkPosiableMovingX(Snake snake, float offX) {
		boolean isPosiable = true;
		
		Point phead = snake.getHead().getPosition();
		Point pNext = new Point(phead.getX()+offX, phead.getY());
		float rad = snake.getHead().getRadius();
		
		for(Wall wall : walls) {
			Point p1 = wall.getPositionOfLeftDownPoint();
			Point p2 = new Point(p1.getX()+ wall.getSize().getWidth(), p1.getY()+ wall.getSize().getHeight());
			
			boolean noPosiableX = (p1.getX() < (pNext.getX()+rad)) && ((pNext.getX()-rad) < p2.getX());
			boolean noPosiableY = (p1.getY() < (pNext.getY()+rad)) && ((pNext.getY()-rad) < p2.getY());
			if(noPosiableX&&noPosiableY) {
				isPosiable = false;
				break;
			}
		}
		
		return isPosiable;
	}
	
	public boolean checkPosiableMovingY(Snake snake, float offY) {
		boolean isPosiable = true;
		
		Point phead = snake.getHead().getPosition();
		Point pNext = new Point(phead.getX(), phead.getY()+offY);
		float rad = snake.getHead().getRadius();
		
		for(Wall wall : walls) {
			Point p1 = wall.getPositionOfLeftDownPoint();
			Point p2 = new Point(p1.getX()+ wall.getSize().getWidth(), p1.getY()+ wall.getSize().getHeight());
			
			boolean noPosiableX = (p1.getX() < (pNext.getX()+rad)) && ((pNext.getX()-rad) < p2.getX());
			boolean noPosiableY = (p1.getY() < (pNext.getY()+rad)) && ((pNext.getY()-rad) < p2.getY());
			if(noPosiableX&&noPosiableY) {
				isPosiable = false;
				break;
			}
		}
		
		return isPosiable;
	}
	
	public boolean checkPosiableMoving(Snake snake, float offX, float offY) {
		boolean isPosiable = checkPosiableMovingX(snake, offX)||checkPosiableMovingY(snake, offY);
		return isPosiable;
	}
}
