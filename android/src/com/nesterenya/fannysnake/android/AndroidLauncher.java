package com.nesterenya.fannysnake.android;

import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nesterenya.fannysnake.FunnySnakeGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(FunnySnakeGame.getInstance(), config);
	}
}
