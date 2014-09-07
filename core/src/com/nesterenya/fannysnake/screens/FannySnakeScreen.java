package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.FeedController;
import com.nesterenya.fannysnake.Field;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.GameContext;
import com.nesterenya.fannysnake.PlayStage;
import com.nesterenya.fannysnake.PlayStage.OnHardKeyListener;
import com.nesterenya.fannysnake.SOUNDS;
import com.nesterenya.fannysnake.SoundsPlayer;
import com.nesterenya.fannysnake.WallsController;
import com.nesterenya.fannysnake.control.MotionControl;
import com.nesterenya.fannysnake.control.MotionControlCreator;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
import com.nesterenya.fannysnake.feeds.Feed;
import com.nesterenya.fannysnake.renderers.DecorationRenderer;
import com.nesterenya.fannysnake.renderers.FeedRenderer;
import com.nesterenya.fannysnake.renderers.SnakeRenderer;
import com.nesterenya.fannysnake.renderers.WallsRenderer;

public class FannySnakeScreen implements Screen {

	public FannySnakeScreen() {
		GameContext.getInstance().score = 0;

		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.position.set(100, 100, 0);
		camera.update();	
		viewport = FunnySnakeGame.getViewport(camera);

		soundsPlayer = new SoundsPlayer();
		Field gameField = new Field(25, 25, FunnySnakeGame.getWorldWidth()-25, FunnySnakeGame.getWorlsHeight()-25);
		feedController = new FeedController(gameField);
		wallsController = new WallsController();

		mp3Music = Gdx.audio
				.newMusic(Gdx.files.internal("music/gametheme.mp3"));
		mp3Music.play();
		sr = new ShapeRenderer();

		backgr = new Texture("grassbg.jpg");
		backgr.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		background = new Sprite(backgr, 0, 0, FunnySnakeGame.getWorldWidth(),
				FunnySnakeGame.getWorlsHeight());

		playStage = new PlayStage(viewport);
		// Gdx.input.setInputProcessor(stage);
		Gdx.input.setInputProcessor(playStage);
		
		scoreTx = new Texture("images/game/score-board.png");
		
		tr = new TextureRegion(new Texture("button.png"));
		imgBtn = new TextureRegionDrawable(tr);

		// font load
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(1.3f,1.3f);
		
		setButtons(font);
		
		playStage.addActor(pause_btn);
		playStage.addActor(again_btn);
		playStage.addActor(menu_btn);
		playStage.addActor(resume_btn);
		
		batch = new SpriteBatch();

		snake = new Snake(new Point(200, 200));

		snakeRenderer = new SnakeRenderer(batch, snake);
		feedRenderer = new FeedRenderer(batch);
		wallsRenderer = new WallsRenderer(batch, wallsController.getListWalls());
		decorationRenderer = new DecorationRenderer(batch, gameField);

		ApplicationType type = Gdx.app.getType();
		control = MotionControlCreator.create(type);

		Gdx.input.setInputProcessor(playStage);
		Gdx.input.setCatchBackKey(true);
		playStage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					FunnySnakeGame.getInstance().setScreen(new MainMenuScreen());
					dispose();
				}
			}
		});
	}
	
	private void setButtons(BitmapFont bitFont) {
		
		TextButtonStyle st = new TextButtonStyle(imgBtn, imgBtn, imgBtn, bitFont);
		//3c0b0b
		st.fontColor = new Color(0x3c0b0bff);
		final int SIZE_HEIGHT = 50;
		final int SIZE_WIDTH = 100;
		final int POS_HEIGHT = 440;
		
		pause_btn = new TextButton("Pause", st);
		pause_btn.setPosition(450, POS_HEIGHT);
		pause_btn.setSize(SIZE_WIDTH, SIZE_HEIGHT);
		
		ClickListener pauseListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                isPaused=!isPaused;
                String newText = isPaused?"Play":"Pause";
                pause_btn.setText(newText);
                resume_btn.setVisible(isPaused);
            };
		};
		
		pause_btn.addListener(pauseListener);
		
		again_btn = new TextButton("Again", st);
		again_btn.setPosition(450+SIZE_WIDTH+5, POS_HEIGHT);
		again_btn.setSize(SIZE_WIDTH, SIZE_HEIGHT);
		ClickListener againListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                FunnySnakeGame.getInstance().setScreen(new FannySnakeScreen());
                dispose();
            };
		};
		again_btn.addListener(againListener);
		
		menu_btn = new TextButton("Menu", st);
		menu_btn.setPosition(450+2*SIZE_WIDTH+10, POS_HEIGHT);
		menu_btn.setSize(SIZE_WIDTH, SIZE_HEIGHT);
		ClickListener menuListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                FunnySnakeGame.getInstance().setScreen(new MainMenuScreen());
                dispose();
            };
		};
		menu_btn.addListener(menuListener);
		
		resume_btn = new TextButton("Resume", st);
		//TODO исправить опечатку
		resume_btn.setPosition(FunnySnakeGame.getWorldWidth()/2-100, FunnySnakeGame.getWorlsHeight()/2-50);
		resume_btn.setSize(200, 100);
		resume_btn.addListener(pauseListener);
		resume_btn.setVisible(isPaused);
	}
	
	Texture scoreTx;
	MotionControl control;
	SpriteBatch batch;
	TextureRegionDrawable imgBtn;
	private Snake snake;

	private BitmapFont font;
	private TextButton pause_btn;
	private TextButton again_btn;
	private TextButton menu_btn;
	private TextButton resume_btn;

	TextureRegion tr;
	// Stage stage;
	PlayStage playStage;
	Texture block;

	boolean isPaused = true;

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

	// Array<Viewport> viewports;
	Viewport viewport;

	private OrthographicCamera camera;

	
	//TODO блочить движение змейки, определять на какой угол осуществляется поворот, и запрещать сделать большой поворот
	
	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Score render
		batch.begin();
		background.draw(batch);
		batch.end();
		
		if(isPaused) {
			
		} else {
			logicStep(delta);
		}
		
		snakeRenderer.render();
		feedRenderer.render(feedController.getFeed());
		decorationRenderer.render();
		wallsRenderer.render();

		
		batch.begin();
		batch.draw(scoreTx,25, FunnySnakeGame.getWorlsHeight() - 25);
		font.draw(batch,
			Integer.toString(GameContext.getInstance().score),
				110, FunnySnakeGame.getWorlsHeight()-3);
		batch.end();
		
		playStage.act(delta);
		playStage.draw();
	}

	private void moveHeadIfPosiable() {
		if (wallsController.checkPosiableMovingX(snake, control.getOffsetX())) {
			snake.getHead().moveHeadX(control.getOffsetX());
		} else {
			snake.reactionOnWall(soundsPlayer);
			GameContext.getInstance().score *= WallsController.HIT_FINE;
		}

		if (wallsController.checkPosiableMovingY(snake, control.getOffsetY())) {
			snake.getHead().moveHeadY(control.getOffsetY());
			
		} else {
			snake.reactionOnWall(soundsPlayer);
			GameContext.getInstance().score *= WallsController.HIT_FINE;
		}
	}
	
	private void logicStep(float delta) {
		//Move tail
		Point headPosition = snake.getHead().getPosition();
		Point next = new Point(headPosition.getX(), headPosition.getY());
		snake.getTail().moveTail(next);
		
		control.update(delta);
		snake.update(delta);
		
		moveHeadIfPosiable();
		
		// Direction calculation
		Point headPos = snake.getHead().getPosition();
		// TODO Переместить код в логику самой змейки
		snake.defineHeadDerection(headPos, snake.getTail().getLastPoint());
		
		snake.getHead().tryBlinkEyes(Gdx.graphics.getDeltaTime());

		// Render Feed
		feedController.next(Gdx.graphics.getDeltaTime());
		
		// TODO разделить метод на 2 и релализовать контроллер для управления
				// этим
				Feed feed = feedController.eatFeed(snake.getHead());
				if (feed !=null) {
					soundsPlayer.play(SOUNDS.BITE);
					GameContext.getInstance().score += feed.getCost();
					snake.getTail().increase();
				}

				if (snake.getTail().isPointCrossTail(snake.getHead().getPosition(),
						snake.getHead().getSize())) {
					//GameContext.getInstance().score = -50;
					//TODO звук в GameOverScreen при запуске
					//soundsPlayer.play(SOUNDS.OU);
					FunnySnakeGame.getInstance().setScreen(new GameOverScreen("You bit yourself", GameContext.getInstance().score));
					dispose();
				}
	}
	
	public void resize(int width, int height) { 
		viewport.update(width, height); 
	}

	@Override
	public void dispose() { 
		//TODO проверить или все диспожу
		playStage.dispose();
		//batch.dispose();
		soundsPlayer.dispose();
		mp3Music.dispose();
	}

	@Override
	public void show() {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
}