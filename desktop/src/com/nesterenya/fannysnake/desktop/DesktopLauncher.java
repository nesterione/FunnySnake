package com.nesterenya.fannysnake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.manage.IActivityRequestHandler;

public class DesktopLauncher implements IActivityRequestHandler{
	private static DesktopLauncher application;
	
	public static void main (String[] arg) {		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		if (application == null) {
            application = new DesktopLauncher();
        }
		FunnySnakeGame.getInstance().init(application);
		new LwjglApplication(FunnySnakeGame.getInstance(), config);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub
		
	}
}
