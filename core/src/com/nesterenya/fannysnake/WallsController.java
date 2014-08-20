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
		
		//TODO ������ ��� ������� �������
		Wall left = new Wall(new Point(0,25), new Size(25,430));
		Wall down = new Wall(new Point(0,0), new Size(640,25));
		Wall up = new Wall(new Point(0,455), new Size(640,25));
		Wall right = new Wall(new Point(615,25), new Size(25,430));
		
		walls = new ArrayList<Wall>();
		walls.add(left);
		walls.add(down);
		walls.add(up);
		walls.add(right);
	}
	
	public List<Wall> getListWalls() {
		return walls;
	}
	
	public boolean checkPosiableMoving(Snake snake, float offX, float offY) {
		boolean isPosiable = true;
		
		Point phead = snake.getHead().getPosition();
		Point pNext = new Point(phead.getX()+offX, phead.getY()+offY);
		
		for(Wall wall : walls) {
			Point p1 = wall.getPositionOfLeftDownPoint();
			Point p2 = new Point(p1.getX()+ wall.getSize().getWidth(), p1.getY()+ wall.getSize().getHeight());
			
			boolean isX = (p1.getX() < pNext.getX()) && (pNext.getX() < p2.getX());
			boolean isY = (p1.getY() < pNext.getY()) && (pNext.getY() < p2.getY());
			if(isX&&isY) {
				isPosiable = false;
				break;
			}
			
		}
		
		return isPosiable;
	}
}
