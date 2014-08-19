package com.nesterenya.fannysnake.decorations;

import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;

public interface Decoration {

	public Size getSize();
	public Point getPositionOfLeftDownPoint();
	public String getName();
}
