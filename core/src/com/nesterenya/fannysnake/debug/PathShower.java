package com.nesterenya.fannysnake.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import com.nesterenya.fannysnake.SnakePath;

public class PathShower implements Disposable {
	
	private final SnakePath snakePath;
	private ShapeRenderer re = new ShapeRenderer();
	
	private boolean isEnableRefPoints = true;
	
	public PathShower(SnakePath snakePath) {
		this.snakePath = snakePath;
	}
	
	public void draw() {
		
		if(isEnableRefPoints) {
			
			re.begin(ShapeType.Filled);
			re.setColor(new Color(1, 0, 0, 1));
			for(int i = 0; i < snakePath.getListRefPoints().size()-1;i++) {
				//re.point(snakePath.getListRefPoints().get(i).getX(), snakePath.getListRefPoints().get(i).getY(), 0);
				re.circle(snakePath.getListRefPoints().get(i).getX(), snakePath.getListRefPoints().get(i).getY(), 2);
			}
			re.end();
		}
		
		
		re.begin(ShapeType.Line);
		re.setColor(new Color(1, 0, 0, 1));
		
		for(int i = 0; i < snakePath.getListRefPoints().size()-1;i++) {
			re.line(snakePath.getListRefPoints().get(i).getX(), snakePath.getListRefPoints().get(i).getY(), 
					snakePath.getListRefPoints().get(i+1).getX(), snakePath.getListRefPoints().get(i+1).getY());
		}
		
		re.end();
	}
	
	public void enableRefPoints(boolean status) {
		isEnableRefPoints = status;
	}

	@Override
	public void dispose() {
		re.dispose();
	}


	
}
