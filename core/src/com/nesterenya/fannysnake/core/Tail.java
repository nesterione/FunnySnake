package com.nesterenya.fannysnake.core;

public class Tail {
	private int size;

	public Tail(int initSize) {
		this.size = initSize;
	}
	
	public void increase() {
		size++;
	}
	
	public int getSize() {
		return size;
	}
}
