package com.nesterenya.fannysnake;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.core.Vpixel;
import com.nesterenya.fannysnake.renderers.DecorationRenderer;
import com.nesterenya.fannysnake.renderers.FeedRenderer;
import com.nesterenya.fannysnake.renderers.SnakeRenderer;
import com.nesterenya.fannysnake.renderers.WallsRenderer;

public class FannySnake extends ApplicationAdapter {
	SpriteBatch batch;	
	TextureRegionDrawable imgBtn;
	private Snake snake;
	
	private BitmapFont font;
	private ImageButton pause_btn;
	
	TextureRegion tr;
	Stage stage;
	Texture block;
	
	boolean isPaused = false;
	
	Music mp3Music;
	Sprite background;
	Texture backgr;
	ShapeRenderer sr;
	
	SnakeRenderer snakeRenderer;
	FeedRenderer feedRenderer;
	SoundsPlayer soundsPlayer;
	FeedController feedController;
	WallsController wallsController;
	WallsRenderer wallsRenderer;
	DecorationRenderer decorationRenderer;
	
	
	//private static final int VIRTUAL_WIDTH = 640;
    //private static final int VIRTUAL_HEIGHT = 480;
    //private static final float ASPECT_RATIO = (float)VIRTUAL_WIDTH/(float)VIRTUAL_HEIGHT;

    //private Camera camera;
    //private Rectangle viewport;
   // private Viewport viewport;
	Array<Viewport> viewports;
	Viewport viewport;
	Array<String> names;
	String name;
	
	//private SpriteBatch batch;
	private Texture texture;
	//private BitmapFont font;
	private OrthographicCamera camera;
	
	static public Array<Viewport> getViewports (Camera camera) {
		int minWorldWidth = 640;
		int minWorldHeight = 480;
		int maxWorldWidth = 800;
		int maxWorldHeight = 480;

		Array<Viewport> viewports = new Array();
		//viewports.add(new StretchViewport(minWorldWidth, minWorldHeight, camera));
		//viewports.add(new FillViewport(minWorldWidth, minWorldHeight, camera));
		viewports.add(new FitViewport(minWorldWidth, minWorldHeight, camera));
		viewports.add(new ExtendViewport(minWorldWidth, minWorldHeight, camera));
		viewports.add(new ExtendViewport(minWorldWidth, minWorldHeight, maxWorldWidth, maxWorldHeight, camera));
		viewports.add(new ScreenViewport(camera));

		ScreenViewport screenViewport = new ScreenViewport(camera);
		screenViewport.setUnitsPerPixel(0.75f);
		viewports.add(screenViewport);

		viewports.add(new ScalingViewport(Scaling.none, minWorldWidth, minWorldHeight, camera));
		return viewports;
	}
	
	static public Array<String> getViewportNames () {
		Array<String> names = new Array();
		//names.add("StretchViewport");
		//names.add("FillViewport");
		names.add("FitViewport");
		names.add("ExtendViewport: no max");
		names.add("ExtendViewport: max");
		names.add("ScreenViewport: 1:1");
		names.add("ScreenViewport: 0.75:1");
		names.add("ScalingViewport: none");
		return names;
	}
	
	@Override
	public void create () {
		
		GameContext.getInstance().disp = new Rectangle(0, 0, 640, 480);
		Pixmap pixmap = new Pixmap(16, 16, Format.RGBA8888);
		pixmap.setColor(0, 0, 0, 1);
		pixmap.fill();
		texture = new Texture(pixmap);

		batch = new SpriteBatch();
		
		camera = new OrthographicCamera();
		camera.position.set(100, 100, 0);
		camera.update();

		viewports = FannySnake.getViewports(camera);
		viewport = viewports.first();

		names = FannySnake.getViewportNames();
		name = names.first();
		
		//camera = new OrthographicCamera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		//camera = new PerspectiveCamera();
	    //viewport = new FitViewport(640, 480, camera); //800,640
		
		//disp magic
		//Gdx.graphics.setDisplayMode(640, 480, false);
		
		
		Gdx.graphics.setDisplayMode(1280, 720, false);
		// int  SCREEN_HEIGHT = Gdx.graphics.getHeight();
		//int SCREEN_WIDTH = (int) (SCREEN_HEIGHT * (640/480));
		//Vpixel.setPixelMultiplier(Gdx.graphics.getHeight()/480.0f);
		//System.out.println(Gdx.graphics.getHeight());
		//Screen.width/size.x,Screen.height/size.y
		//int  SCREEN_HEIGHT = Gdx.graphics.getHeight();
		//int SCREEN_WIDTH = (int) (SCREEN_HEIGHT * (640/480));
			
		//Gdx.gl20.glViewport(0, 0, 640, 480);
		//OrthographicCamera c = new OrthographicCamera();
		
		
		//GameContext.getInstance().disp.getWidth();
		
		
		
		
		soundsPlayer = new SoundsPlayer();
		feedController = new FeedController();
		wallsController = new WallsController();
		
		mp3Music = Gdx.audio.newMusic(Gdx.files.internal("music/gametheme.mp3"));
		mp3Music.play();
		sr = new ShapeRenderer();
		
		
		backgr = new Texture("grassbg.jpg");
		backgr.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		background = new Sprite(backgr,0,0,(int)GameContext.getInstance().disp.getWidth(),(int)GameContext.getInstance().disp.getHeight());
		
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
		
		
		snake = new Snake(new Point(200, 200));
		
		snakeRenderer = new SnakeRenderer(batch,snake);
		feedRenderer = new FeedRenderer(batch);
		wallsRenderer = new WallsRenderer(batch, wallsController.getListWalls());
		decorationRenderer = new DecorationRenderer(batch);
		
		//this.resize(640, 480);
		
		Gdx.input.setInputProcessor(new InputAdapter() {
			public boolean keyDown (int keycode) {
				if (keycode == Input.Keys.SPACE) {
					int index = (viewports.indexOf(viewport, true) + 1) % viewports.size;
					name = names.get(index);
					viewport = viewports.get(index);
					resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
				}
				return false;
			}
		});
	}

	int lastKey;
	
	private void control() {
		//Event handling
				float delta = Gdx.graphics.getDeltaTime() * GameContext.getInstance().speed;
				
				//ApplicationType type = Gdx.app.getType();
				//TODO Сделать чтобы при инициировалось управление при запуске приложения, в том числе скорость итд.
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
					
					if((snake.getHead().getPosition().getX()+delta) < (GameContext.getInstance().disp.getWidth()-75)) {
						if(lastKey != Keys.DPAD_LEFT) {
							snake.getHead().moveHeadX(delta); ;
							lastKey = Keys.DPAD_RIGHT;
						}
					} else {
						soundsPlayer.play(SOUNDS.BOOM);
					}
				}
				
				if(Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
					if((snake.getHead().getPosition().getY()+delta) <(GameContext.getInstance().disp.getHeight()- 110)) {
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
				
				
				/*
		    		float mX = Gdx.input.getAccelerometerY()* GameContext.getInstance().speed;
		    		float mY = Gdx.input.getAccelerometerX()* GameContext.getInstance().speed;
		    		snake.getHead().moveHeadX(mX);
					snake.getHead().moveHeadY(-mY); 
				*/

				//head.x += (int)Gdx.input.getAccelerometerY()*sp;
				//head.y += -(int)Gdx.input.getAccelerometerX()*sp;
				
	}
	
	@Override
	public void render () {
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);

		//Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*camera = new OrthographicCamera();
		

	
		camera.update();

        // set viewport
       Gdx.gl.glViewport((int) viewport.x, (int) viewport.y,
                          (int) viewport.width, (int) viewport.height);
       // Gdx.gl20.glViewport(0, 0, 1280, 720);
        
        // clear previous frame*/

       // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		

		
		//Move tail
		snake.getTail().moveTail(new Point( snake.getHead().getPosition().getX(), snake.getHead().getPosition().getY() ));
				
		//stage.draw();
		//if(!isPaused) {
		
		//Score render
		batch.begin();
		background.draw(batch);
    	font.draw(batch, "score: " + Integer.toString(GameContext.getInstance().score) , 50, GameContext.getInstance().disp.getHeight()-20);
    	//pause_btn.draw(batch, 1.0f);
    	batch.end();
    	
		control();
		
		
		//Direction calculation
		Point headPos = snake.getHead().getPosition();
		//TODO Переместить код в логику самой змейки
		snake.defineHeadDerection(headPos, snake.getTail().getLastPoint());
		
		decorationRenderer.render();
		wallsRenderer.render();
		snakeRenderer.render();
		
		snake.getHead().tryBlinkEyes(Gdx.graphics.getDeltaTime());
		
		//Render Feed
		feedController.next(Gdx.graphics.getDeltaTime());
		feedRenderer.render(feedController.getFeed());
		
		//TODO разделить метод на 2 и релализовать контроллер для управления этим
		if(feedController.eatFeedWhenItPossible(snake.getHead())) {
			soundsPlayer.play(SOUNDS.BITE);
			GameContext.getInstance().score++;
			snake.getTail().increase();
		}
		
		if(snake.getTail().isPointCrossTail(snake.getHead().getPosition(), snake.getHead().getSize())) {
			GameContext.getInstance().score = -50;
			soundsPlayer.play(SOUNDS.OU);
		}
		
		if (viewport instanceof ScalingViewport) {
			// This shows how to set the viewport to the whole screen and draw within the black bars.
			ScalingViewport scalingViewport = (ScalingViewport)viewport;
			int screenWidth = Gdx.graphics.getWidth();
			int screenHeight = Gdx.graphics.getHeight();
			Gdx.gl.glViewport(0, 0, screenWidth, screenHeight);
			batch.getProjectionMatrix().idt().setToOrtho2D(0, 0, screenWidth, screenHeight);
			batch.getTransformMatrix().idt();
			batch.begin();
			float leftGutterWidth = scalingViewport.getLeftGutterWidth();
			if (leftGutterWidth > 0) {
				batch.draw(texture, 0, 0, leftGutterWidth, screenHeight);
				batch.draw(texture, scalingViewport.getRightGutterX(), 0, scalingViewport.getRightGutterWidth(), screenHeight);
			}
			float bottomGutterHeight = scalingViewport.getBottomGutterHeight();
			if (bottomGutterHeight > 0) {
				batch.draw(texture, 0, 0, screenWidth, bottomGutterHeight);
				batch.draw(texture, 0, scalingViewport.getTopGutterY(), screenWidth, scalingViewport.getTopGutterHeight());
			}
			batch.end();
			viewport.update(screenWidth, screenHeight, true); // Restore viewport.
		}
	}	
	
	
	public void resize (int width, int height) {
		System.out.println(name);
		viewport.update(width, height);
	}
	
	@Override  
	public void dispose() {
		batch.dispose();
        font.dispose();
	}
}
