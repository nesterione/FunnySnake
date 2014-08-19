package com.nesterenya.fannysnake;

import java.util.ArrayList;
import java.util.List;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.core.Wall;

public class WallsController {
	
	private final List<Wall> walls;
	
	public WallsController() {
		
		Wall left = new Wall(new Point(0,0), new Size(25,440));
		Wall down = new Wall(new Point(0,0), new Size(640,25));
		Wall up = new Wall(new Point(0,415), new Size(640,25));
		Wall right = new Wall(new Point(615,0), new Size(25,440));
		
		walls = new ArrayList<Wall>();
		walls.add(left);
		walls.add(down);
		walls.add(up);
		walls.add(right);
	}
	
	public List<Wall> getListWalls() {
		return walls;
	}
	
}
