package com.nesterenya.fannysnake;

import java.util.Random;

public class GameRandom {
	
	private GameRandom() {
		//don't use this constructor
	}
	
	private static Random random;
	
	static {
		random = new Random();
	}
	
	public static float nextFloat(float begin, float end) {
		 
		return (begin + (random.nextFloat()*Math.abs(end-begin)));
	}
	
	public static int nextInt(int end) {
		return random.nextInt(end);
	}
	
	public static int nextInt(int begin, int end) {
		return (int)(begin + random.nextInt(end-begin));
	}
}
