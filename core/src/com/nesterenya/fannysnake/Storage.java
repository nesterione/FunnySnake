package com.nesterenya.fannysnake;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Storage {

	
	
	public void addHighScore(int score) {
		boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
		if(isLocAvailable) {			
			
			int[] scores = readScores();
			for(int i = 0; i< scores.length; i++) {
				if(scores[i]<score) {
					for(int j = scores.length-1; j>i; j--) {
						scores[j] = scores[j-1]; 
					}	
					scores[i] = score;
					break;
				}
			}
			
			FileHandle file = Gdx.files.local("scores.txt");
			
			StringBuffer stringScores = new StringBuffer();
			for(int i = 0 ; i< scores.length;i++) {
				stringScores.append(scores[i]+" ");
			}
			
			file.writeString(stringScores.toString(),false);
		}
	}
	
	private String readString() {
		String text;
		try {
			FileHandle file = Gdx.files.local("scores.txt");
			text = file.readString();
		} catch(Exception ex) {text = ""; }
		return text;
	}
	
	private int parseInt(String s) {
		int num;
		try {
			num = Integer.parseInt(s);
		} catch(Exception ex) {
			num = 0;
		}
		return num;
	}
	
	private final static int COUNT_SCORES = 8;
	public int[] readScores() {
		int[] scores = new int[COUNT_SCORES];
		
		boolean isLocAvailable = Gdx.files.isLocalStorageAvailable();
		if(isLocAvailable) {
			String str = readString();
			String[] strs = str.trim().split("\\s+");
			for(int i = 0;(i<COUNT_SCORES)&&(i<strs.length);i++) {
				
				scores[i] = parseInt(strs[i]);
			}
		}
		
		return scores;
	}
}
