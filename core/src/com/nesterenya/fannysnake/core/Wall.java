package com.nesterenya.fannysnake.core;

public class Wall {
	private final Point positionOfLeftDownPoint;
	private final Size size;
	
	public Wall(Point positionOfLeftDownPoint, Size size) {
		this.positionOfLeftDownPoint = positionOfLeftDownPoint;
		this.size = size;
	}

	public Size getSize() {
		return size;
	}

	public Point getPositionOfLeftDownPoint() {
		return positionOfLeftDownPoint;
	}
	
	//TODO все параметры хранить в SIZE - Лучше создать тип данных для конвертации размеров
	
}
