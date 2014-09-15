package com.nesterenya.fannysnake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

public class SoundsPlayer implements Disposable {
	
	Map<SOUNDS, Sound> soundsMap;
	
	public SoundsPlayer() {
		soundsMap = new HashMap<SOUNDS, Sound>();
		soundsMap.put(SOUNDS.BITE, Gdx.audio.newSound(Gdx.files.internal("sounds/slime3r.wav")));
		soundsMap.put(SOUNDS.BOOM, Gdx.audio.newSound(Gdx.files.internal("sounds/bom.wav")));
		soundsMap.put(SOUNDS.OU, Gdx.audio.newSound(Gdx.files.internal("sounds/ou.wav")));
	}
	
	/*public void playOneTime(SOUNDS sou) {
	}*/
	
	public void play(SOUNDS sou) {
		soundsMap.get(sou).play();
	}
	
	@Override
	public void dispose() {
	        
	   if(soundsMap!=null) {
		   Collection<Sound> values = soundsMap.values();
		   for(Sound sound : values) {
			   sound.dispose();
		   }
		   values = null;
		   soundsMap.clear();
		   soundsMap = null;
	   }
		
	}
}
