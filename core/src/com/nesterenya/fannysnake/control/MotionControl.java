package com.nesterenya.fannysnake.control;

public interface MotionControl {
	public float getOffsetX();
	public float getOffsetY();
	public void update(float time);
}
