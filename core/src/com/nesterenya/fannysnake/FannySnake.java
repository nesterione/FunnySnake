package com.nesterenya.fannysnake;

import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
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
	Sound biteSound;
	Sound bomSound;
	Sound urg;
	Music mp3Music;
	Sprite background;
	Texture backgr;
	ShapeRenderer sr;
	
	@Override
	public void create () {
		
		mp3Music = Gdx.audio.newMusic(Gdx.files.internal("music/gametheme.mp3"));
		mp3Music.play();
		sr = new ShapeRenderer();
		
		biteSound = Gdx.audio.newSound(Gdx.files.internal("sounds/slime3r.wav"));
		bomSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bom.wav"));
		urg = Gdx.audio.newSound(Gdx.files.internal("sounds/pain.mp3")); 
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
				bomSound.play();
				urg.play();
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			
			if((snake.getHead().getPosition().getX()+delta) < (Gdx.graphics.getWidth()-75)) {
				if(lastKey != Keys.DPAD_LEFT) {
					snake.getHead().moveHeadX(delta); ;
					lastKey = Keys.DPAD_RIGHT;
				}
			} else {
				bomSound.play();
			}
		}
		
		if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			if((snake.getHead().getPosition().getY()+delta) <(Gdx.graphics.getHeight()- 110)) {
				if(lastKey != Keys.DPAD_DOWN) {
					snake.getHead().moveHeadY(delta); 
					lastKey = Keys.DPAD_UP;
				}
			} else {
				bomSound.play();
			}
		}		
			
		if(Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			if((snake.getHead().getPosition().getY()-delta) >25) {
				if(lastKey != Keys.DPAD_UP) {
					snake.getHead().moveHeadY(-delta); 
					lastKey = Keys.DPAD_DOWN;
				} 
			} else {
				bomSound.play();
			}
		}
		
		
		/*
    	float mX = Gdx.input.getAccelerometerY()* GameContext.getInstance().speed;
    	float mY = Gdx.input.getAccelerometerX()* GameContext.getInstance().speed;
    	snake.getHead().moveHeadX(mX);
		snake.getHead().moveHeadY(-mY); 
					*/

    	
		//head.x += (int)Gdx.input.getAccelerometerY()*sp;
		//head.y += -(int)Gdx.input.getAccelerometerX()*sp;
		
		
		
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
		SnakeRenderer.render(batch, snake);
		
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
			
			biteSound.play();
			GameContext.getInstance().score++;
			
			//Growing snake
			snake.getTail().increase();
		
			//Hide feed
			posX = 0;
			posY = 0;
		}
		}
		
		if(isEatHimself(snake)) {
			GameContext.getInstance().score = -50;
		}
		
	}
	
	//TODO сделать проверку не на каждом шаге
	private boolean isEatHimself(Snake snake) {
		boolean isFound = false;
		
		Point[] points = snake.getTail().getPoints();
		Point hd = snake.getHead().getPosition();
		Size siz = snake.getHead().getSize();
		List<Integer> idxs = snake.getTail().getIndexesBigBalls();
		
		
		
		for(int i = 2; i < idxs.size()-1&&!isFound; i++) {
			
			int idx = snake.getTail().getIndexesBigBalls().get(i);
			int c_idx = snake.getTail().getPoints().length - idx;
			
			float posX = points[c_idx].getX();
			float posY = points[c_idx].getY();
			//System.out.println(posX + " " + posY  );
			if( ((hd.getX()+siz.getWidth()/4)>posX&&((hd.getX())<posX+siz.getWidth()/4))&& ((hd.getY()+siz.getHeight()/4)>posY&&((hd.getY())<posY+siz.getHeight()/4))) {
				isFound=true;
				urg.play();
			}
			
			/*sr.begin(ShapeType.Filled);
			sr.setColor(new Color(1, 0, 0, 1));
			sr.rect(posX+siz.getWidth()/4, posY+siz.getWidth()/4, siz.getWidth()/4, siz.getHeight()/4);
			sr.end();*/
			
		}
		
		return isFound;
	}
	
	@Override  
	public void dispose() {
		batch.dispose();
        font.dispose();
        biteSound.dispose();
        mp3Music.dispose();
        bomSound.dispose();
	}
}
