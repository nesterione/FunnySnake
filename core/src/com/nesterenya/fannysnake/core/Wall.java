package com.nesterenya.fannysnake.core;

public class Wall {
	private final Vpoint positionOfLeftDownPoint;
	private final Vsize size;
	
	public Wall(Vpoint positionOfLeftDownPoint, Vsize size) {
		this.positionOfLeftDownPoint = positionOfLeftDownPoint;
		this.size = size;
	}

	public Vsize getSize() {
		return size;
	}

	public Vpoint getPositionOfLeftDownPoint() {
		return positionOfLeftDownPoint;
	}
	
	//TODO все параметры хранить в SIZE - Лучше создать тип данных для конвертации размеров
	
}
