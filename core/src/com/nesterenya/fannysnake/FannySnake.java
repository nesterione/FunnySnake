package com.nesterenya.fannysnake;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Size;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.feeds.AppleFeed;
import com.nesterenya.fannysnake.feeds.Feed;
import com.nesterenya.fannysnake.renderers.DecorationRenderer;
import com.nesterenya.fannysnake.renderers.SnakeRenderer;

public class FannySnake extends ApplicationAdapter {
	SpriteBatch batch;	
	TextureRegionDrawable imgBtn;
	private Snake snake;
	Feed feed;
	private BitmapFont font;
	private ImageButton pause_btn;
	
	TextureRegion tr;
	Stage stage;
	Texture block;
	Sprite wallLeft;
	Sprite wallTop;
	Sprite wallDown;
	Sprite wallRight;
	boolean isPaused = false;
	
	Music mp3Music;
	Sprite background;
	Texture backgr;
	ShapeRenderer sr;
	
	SnakeRenderer snakeRenderer;
	SoundsPlayer soundsPlayer;
	
	@Override
	public void create () {
		
		soundsPlayer = new SoundsPlayer();
		
		mp3Music = Gdx.audio.newMusic(Gdx.files.internal("music/gametheme.mp3"));
		mp3Music.play();
		sr = new ShapeRenderer();
		
		
		block = new Texture("block.png");
		block.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		wallLeft = new Sprite(block,0,0,25, 440);
		wallDown = new Sprite(block,0,0,640, 25);
		wallTop = new Sprite(block,0,0,640, 25);
		wallTop.setPosition(0, 415);
		wallRight = new Sprite(block,0,0,25, 440);
		wallRight.setPosition(615, 0);
		
		backgr = new Texture("grassbg.jpg");
		backgr.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		background = new Sprite(backgr,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		tr = new TextureRegion(new Texture("ui/pause-icon.png"));
		imgBtn = new TextureRegionDrawable(tr);
		
		pause_btn = new ImageButton(imgBtn);
		pause_btn.setPosition(530, 430);
		pause_btn.setSize(50, 50);
	    pause_btn.addListener(new EventListener() {
			
			@Override
			public boolean handle(Event event) {
				if(event.getStage().touchUp(0, 0, 0, 0)) {
					isPaused = !isPaused;
				}
				//event.toString()
				return false;
			}
		});
	    stage.addActor(pause_btn);
		//Gdx.input.setInputProcessor(processor);
		batch = new SpriteBatch();
		
		//font load 
		font = new BitmapFont();
	    font.setColor(Color.RED);
		
		feed = new AppleFeed(new Texture("feed01.png"));
		snake = new Snake(new Point(200, 200));
		
		snakeRenderer = new SnakeRenderer(batch,snake);
	}


	int posX=0;
	int posY=0;
	
	
	int lastKey;
	
	@Override
	public void render () {

		//Move tail
		snake.getTail().moveTail(new Point( snake.getHead().getPosition().getX(), snake.getHead().getPosition().getY() ));
		
		
		//Scene init
		Gdx.gl.glClearColor(0.8f, 1, 0.3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		stage.draw();
		
		if(!isPaused) {
		
		//Score render
		batch.begin();
		background.draw(batch);
    	font.draw(batch, "score: " + Integer.toString(GameContext.getInstance().score) , 50, Gdx.graphics.getHeight()-20);
    	//pause_btn.draw(batch, 1.0f);
    	batch.end();
    	
		//Event handling
		float delta = Gdx.graphics.getDeltaTime() * GameContext.getInstance().speed;
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) { 
			//TODO warn
			if((snake.getHead().getPosition().getX()-delta) > 25) {
				if(lastKey != Keys.DPAD_RIGHT) {
					snake.getHead().moveHeadX(-delta); 
					lastKey = Keys.DPAD_LEFT;
				} 
			} else {
				soundsPlayer.play(SOUNDS.BOOM);
				soundsPlayer.play(SOUNDS.OU);
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			
			if((snake.getHead().getPosition().getX()+delta) < (Gdx.graphics.getWidth()-75)) {
				if(lastKey != Keys.DPAD_LEFT) {
					snake.getHead().moveHeadX(delta); ;
					lastKey = Keys.DPAD_RIGHT;
				}
			} else {
				soundsPlayer.play(SOUNDS.BOOM);
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			if((snake.getHead().getPosition().getY()+delta) <(Gdx.graphics.getHeight()- 110)) {
				if(lastKey != Keys.DPAD_DOWN) {
					snake.getHead().moveHeadY(delta); 
					lastKey = Keys.DPAD_UP;
				}
			} else {
				soundsPlayer.play(SOUNDS.BOOM);
			}
		}		
			
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			if((snake.getHead().getPosition().getY()-delta) >25) {
				if(lastKey != Keys.DPAD_UP) {
					snake.getHead().moveHeadY(-delta); 
					lastKey = Keys.DPAD_DOWN;
				} 
			} else {
				soundsPlayer.play(SOUNDS.BOOM);
			}
		}
		
		batch.begin();
		//batch.draw(block,0,0,50, 400);
		
		wallLeft.draw(batch);
		wallRight.draw(batch);
		wallTop.draw(batch);
		wallDown.draw(batch);
		batch.end();
		
		//Direction calculation
		Point headPos = snake.getHead().getPosition();
		//float xc = headPos.getX();
		//float yc = headPos.getY();
		//snake.getHead().setPostion(new Point(xc, yc));
		
		snake.defineHeadDerection(headPos, snake.getTail().getLastPoint());
		
		DecorationRenderer.grassRender(batch);
		
		snakeRenderer.render();
		
		GameContext.getInstance().blickTime +=Gdx.graphics.getDeltaTime();
		
		if(snake.getHead().getIsCloseEyes()) {
			if(GameContext.getInstance().blickTime>GameContext.getInstance().durationRate) {
				snake.getHead().setIsCloseEyes(false);
				GameContext.getInstance().blickTime = 0;
			}
		} else {
			if(GameContext.getInstance().blickTime>GameContext.getInstance().blinkRate) {
				snake.getHead().setIsCloseEyes(true);
				GameContext.getInstance().blickTime = 0;
			}
		}
		
		//Render Feed
		GameContext.getInstance().time +=Gdx.graphics.getDeltaTime();
		Texture tx = feed.getTexture();
		
		if(GameContext.getInstance().time> GameContext.getInstance().nextStep) {
			GameContext.getInstance().time = 0;
			Random rand = new Random();
			posX = rand.nextInt(Gdx.graphics.getWidth()-(int)tx.getWidth());
			posY = rand.nextInt(Gdx.graphics.getHeight()-(int)tx.getHeight());
		}
		
		batch.begin();
		
		if(!(posX==0&&posY==0)) {
			batch.draw(tx, posX, posY);
		}
		
		batch.end();
		
		//Feed eating
		Point hd = snake.getHead().getPosition();
		Size siz = snake.getHead().getSize();
		if( ((hd.getX()+siz.getWidth())>posX&&((hd.getX())<posX+siz.getWidth()))&& ((hd.getY()+siz.getHeight())>posY&&((hd.getY())<posY+siz.getHeight()))) {
			
			soundsPlayer.play(SOUNDS.BITE);
			GameContext.getInstance().score++;
			
			//Growing snake
			snake.getTail().increase();
		
			//Hide feed
			posX = 0;
			posY = 0;
		}
		}
		
		if(snake.getTail().isPointCrossTail(snake.getHead().getPosition(), snake.getHead().getSize())) {
			GameContext.getInstance().score = -50;
			soundsPlayer.play(SOUNDS.OU);
		}
		
	}	
	
	@Override  
	public void dispose() {
		batch.dispose();
        font.dispose();
	}
}
