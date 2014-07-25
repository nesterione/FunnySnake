package com.nesterenya.fannysnake.feeds;

import com.badlogic.gdx.graphics.Texture;

public class AppleFeed extends Feed{
	
	private final Texture texture;

	public AppleFeed(Texture texture) {
		this.texture = texture;
	}
	
	@Override
	public int getCost() {
		
		return 10;
	}

	@Override
	public Texture getTexture() {
		
		return texture;
	}

}
