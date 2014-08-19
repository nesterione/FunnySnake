package com.nesterenya.fannysnake.core;

public class Vpixel {
	
	private static float multiplier = 1;
	private float value;
	
	public Vpixel(float v) {
		this.value = v;
	}
	
	public Vpixel(int v) {
		this.value = v;
	}
	
	public static void setPixelMultiplier(float multiplier) {
		Vpixel.multiplier = multiplier;
	}
	
	public void valueOf(int v) {
		this.value = v;
	}
	
	public void valueOf(float v) {
		this.value = v;
	}
	
	public float floatValue() {
		return multiplier*value;
	}
	
	public int intValue() {
		return (int)(multiplier*value);
	}
}
