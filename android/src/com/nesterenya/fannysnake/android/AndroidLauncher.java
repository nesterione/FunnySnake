package com.nesterenya.fannysnake.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nesterenya.fannysnake.FannySnake;
import com.nesterenya.fannysnake.navigation.GameConfig;
import com.nesterenya.fannysnake.navigation.MainMenuScreen;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		initialize(new GameConfig(), config);
	}
}
