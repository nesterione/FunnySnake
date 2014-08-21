package com.nesterenya.fannysnake;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.control.DesktopControl;
import com.nesterenya.fannysnake.control.MotionControl;
import com.nesterenya.fannysnake.control.MotionControlCreator;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
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
	
	Array<Viewport> viewports;
	Viewport viewport;
	Array<String> names;
	String name;
	BitmapFont messageAboutVersion;
	
	private Texture texture;
	private OrthographicCamera camera;
	
	static public Array<Viewport> getViewports (Camera camera) {
		int minWorldWidth = 800;
		int minWorldHeight = 480;
		int maxWorldWidth = 800;
		int maxWorldHeight = 480;

		Array<Viewport> viewports = new Array<Viewport>();
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
		Array<String> names = new Array<String>();
	
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
		
		GameContext.getInstance().disp = new Rectangle(0, 0, 800, 480);
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
		
		//disp magic
		//Gdx.graphics.setDisplayMode(640, 480, false);
		Gdx.graphics.setDisplayMode(1280, 720, false);
		
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
	    font.setColor(Color.WHITE);
		
		messageAboutVersion = new BitmapFont();
		messageAboutVersion.setColor(Color.BLUE);
		messageAboutVersion.setScale(2);
	    
		snake = new Snake(new Point(200, 200));
		
		snakeRenderer = new SnakeRenderer(batch,snake);
		feedRenderer = new FeedRenderer(batch);
		wallsRenderer = new WallsRenderer(batch, wallsController.getListWalls());
		decorationRenderer = new DecorationRenderer(batch);
		
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
		
		ApplicationType type = Gdx.app.getType();
		control = MotionControlCreator.create(type);
		
		shr = new ShapeRenderer();
		
	}
	ShapeRenderer shr;
	int lastKey;
	MotionControl control;
	
	@Override
	public void render () {
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		shr.setProjectionMatrix(camera.projection);
		shr.setTransformMatrix(camera.view);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Move tail
		snake.getTail().moveTail(new Point( snake.getHead().getPosition().getX(), snake.getHead().getPosition().getY() ));
			
		//Score render
		batch.begin();
		background.draw(batch);
    	//pause_btn.draw(batch, 1.0f);
    	messageAboutVersion.draw(batch, "Alpha " , 100, 300);
    	messageAboutVersion.draw(batch, "@igor.nesterenya " , 200, 200);
    	batch.end();
    	
    	control.update(Gdx.graphics.getDeltaTime());
    	snake.update(Gdx.graphics.getDeltaTime());
    	
    	if(wallsController.checkPosiableMoving(snake, control.getOffsetX(), control.getOffsetY())) {
    		snake.getHead().moveHead(control.getOffsetX(), control.getOffsetY());
    	} else {
    		snake.reactionOnWall(soundsPlayer);
    	}
    	
    	
		//Direction calculation
		Point headPos = snake.getHead().getPosition();
		//TODO Переместить код в логику самой змейки
		snake.defineHeadDerection(headPos, snake.getTail().getLastPoint());
		
		decorationRenderer.render();
		wallsRenderer.render();
		
		shr.begin(ShapeType.Filled);
		shr.setColor(0.8f, 0.8f, 0f, 1f);
		shr.rect(25, GameContext.getInstance().disp.getHeight()-22, 100, 20);
		shr.end();
		
		batch.begin();
		font.draw(batch, "score: " + Integer.toString(GameContext.getInstance().score) , 50, GameContext.getInstance().disp.getHeight()-5);
		batch.end();
		
		
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
