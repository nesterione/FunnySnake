package com.nesterenya.fannysnake.control;

import com.badlogic.gdx.Application.ApplicationType;

public class MotionControlCreator {
	private MotionControlCreator() {
		//don't use 
	}
	
	public static MotionControl create(ApplicationType type) {
		switch(type) {
			case Desktop:
				return new DesktopControl();
			case Android:
				return new MobileControl();
			default: 
				return null;
		}
	}
}
