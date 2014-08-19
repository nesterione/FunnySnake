package com.nesterenya.fannysnake.core;

public class Vpoint {
	private Vpixel x;
	private Vpixel y;
	
	public Vpoint(Vpixel x, Vpixel y) {
		this.setX(x);
		this.setY(y);
	}

	public Vpoint(float x, float y) {
		this.setX( new Vpixel(x));
		this.setY( new Vpixel(y));
	}
	
	public Vpoint(int x, int y) {
		this.setX( new Vpixel(x));
		this.setY( new Vpixel(y));
	}
	
	public Vpixel getX() {
		return x;
	}

	public void setX(Vpixel x) {
		this.x = x;
	}

	public Vpixel getY() {
		return y;
	}

	public void setY(Vpixel y) {
		this.y = y;
	}
}
