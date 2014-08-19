package com.nesterenya.fannysnake.core;

public class Vsize {
	private final Vpixel width;
	private final Vpixel height;
	
	public Vsize(Vpixel width, Vpixel height) {
		this.width = width;
		this.height = height;
	}
	
	public Vsize(float width, float height) {
		this.width = new Vpixel(width);
		this.height = new Vpixel(height);
	}
	
	public Vsize(int width, int height) {
		this.width = new Vpixel(width);
		this.height = new Vpixel(height);
	}

	public Vpixel getWidth() {
		return width;
	}

	public Vpixel getHeight() {
		return height;
	}	
}
