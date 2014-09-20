package com.nesterenya.fannysnake.android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.manage.IActivityRequestHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication  implements IActivityRequestHandler {
	private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;
    
    protected AdView adView;
    protected View gameView;
    
    @SuppressLint("HandlerLeak")
   	protected Handler handler = new Handler() {
           @Override
           public void handleMessage(Message msg) {
               switch(msg.what) {
               case SHOW_ADS:
                   adView.setVisibility(View.VISIBLE);
                   break;
               case HIDE_ADS:
                   adView.setVisibility(View.GONE);
                   break;
               }
           }
       };
    
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout layout = new RelativeLayout(this);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		
		
		//AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		
		
		//Обязательно нужно передать окно управляющее рекламой
		FunnySnakeGame.getInstance().init(this);
		View gameView = initializeForView(FunnySnakeGame.getInstance());
		

        adView = new AdView(this);
        adView.setAdUnitId(getResources().getString(R.string.admob_publisher_id));
        adView.setAdSize(AdSize.BANNER);
       
        // Инициирование общего запроса.
        AdRequest adRequest = new AdRequest.Builder().build();
       /* AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Эмулятор
        .addTestDevice("CB511ZBFM5") // Тестовый телефон Galaxy Nexus
        .build();*/
        
        // Загрузка adView с объявлением.
        adView.loadAd(adRequest);
        
        layout.addView(gameView);
       
       
        /*AdRequest adRequest = new AdRequest.Builder()
        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        .addTestDevice(".............")
        .build();
        adView.loadAd(adRequest);*/

        // Add the libgdx view
        //layout.addView(gameView);
        
        // Add the AdMod view
        RelativeLayout.LayoutParams adParams = 
            new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        layout.addView(adView, adParams);

        //Hook it all up        
        setContentView(layout);
		//initialize(FunnySnakeGame.getInstance(), config);
	
	}
	
	@Override
    public void showAds(boolean show) {
       handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
       Log.w("tag", "is show ads " + show);
    }
	
	@Override
	public void onResume() {
		super.onResume();
	    if (adView != null) {
	    	adView.resume();
	    }
	}

	@Override
	public void onPause() {
		super.onPause();
	    if (adView != null) {
	    	adView.pause();
	    }
	}

	
	@Override
	public void onDestroy() {
	    if (adView != null) {
	    	adView.destroy();
	    }
	    super.onDestroy();
	}
}
