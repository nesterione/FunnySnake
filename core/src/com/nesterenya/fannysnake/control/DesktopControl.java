package com.nesterenya.fannysnake.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.nesterenya.fannysnake.GameContext;

public class DesktopControl implements MotionControl {
	
	float delta;
	float speed;
	public DesktopControl() {
		this.speed = GameContext.getInstance().speed;
	}
	
	private float offsetX = 0;
	private float offsetY = 0;
	
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
		delta = time * speed;
		offsetX = offsetY = 0;
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) { 
			offsetX = -delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) { 
			offsetX = delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) { 
			offsetY = delta;
		}
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) { 
			offsetY = -delta;
		}
	}
	
}
