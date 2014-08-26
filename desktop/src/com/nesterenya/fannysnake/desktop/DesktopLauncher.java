package com.nesterenya.fannysnake.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nesterenya.fannysnake.navigation.GameConfig;

public class DesktopLauncher {
	//private static DesktopLauncher application;
	
	public static void main (String[] arg) {
		//LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new FannySnake(), config);
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		/*if (application == null) {
            application = new DesktopLauncher();
        }*/
		
		new LwjglApplication(new GameConfig(), config);
	}
}
