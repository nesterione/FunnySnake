package com.nesterenya.fannysnake.control;

import com.badlogic.gdx.Gdx;
import com.nesterenya.fannysnake.GameContext;

public class MobileControl  implements MotionControl{
	private float delta;
	private float speed;
	private float offsetX = 0;
	private float offsetY = 0;
	private float kafSlowly = 0.4f;
	
	public MobileControl() {
		this.speed = GameContext.getInstance().speed;
	}
	
	@Override
	public float getOffsetX() {
		return offsetX;
	}

	@Override
	public float getOffsetY() {
		return offsetY;
	}

	@Override
	public void update(float time) {
		
		float mX = Gdx.input.getAccelerometerY()* GameContext.getInstance().speed*time*kafSlowly;
		float mY = Gdx.input.getAccelerometerX()* GameContext.getInstance().speed*time*kafSlowly;
		
		//Attention! Directions of coordinate axis in land position of phone don't coincides
		offsetX = mX;
		offsetY = -mY;	
	}

}
