package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.FeedController;
import com.nesterenya.fannysnake.Field;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.GameContext;
import com.nesterenya.fannysnake.PlayStage;
import com.nesterenya.fannysnake.SOUNDS;
import com.nesterenya.fannysnake.SoundsPlayer;
import com.nesterenya.fannysnake.WallsController;
import com.nesterenya.fannysnake.PlayStage.OnHardKeyListener;
import com.nesterenya.fannysnake.control.MotionControl;
import com.nesterenya.fannysnake.control.MotionControlCreator;
import com.nesterenya.fannysnake.core.Point;
import com.nesterenya.fannysnake.core.Snake;
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
		tr = new TextureRegion(new Texture("ui/pause-icon.png"));
		imgBtn = new TextureRegionDrawable(tr);
		scoreTx = new Texture("images/game/score-board.png");
		pause_btn = new ImageButton(imgBtn);
		pause_btn.setPosition(530, 430);
		pause_btn.setSize(50, 50);
		pause_btn.addListener(new EventListener() {

			@Override
			public boolean handle(Event event) {
				if (event.getStage().touchUp(0, 0, 0, 0)) {
					isPaused = !isPaused;
				}
				// event.toString()
				return false;
			}
		});

		// stage.addActor(pause_btn);
		playStage.addActor(pause_btn);
		// Gdx.input.setInputProcessor(processor);
		batch = new SpriteBatch();

		// font load
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(1.3f,1.3f);

		messageAboutVersion = new BitmapFont();
		messageAboutVersion.setColor(Color.BLUE);
		messageAboutVersion.setScale(2);

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
	
	Texture scoreTx;
	MotionControl control;
	SpriteBatch batch;
	TextureRegionDrawable imgBtn;
	private Snake snake;

	private BitmapFont font;
	private ImageButton pause_btn;

	TextureRegion tr;
	// Stage stage;
	PlayStage playStage;
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

	// Array<Viewport> viewports;
	Viewport viewport;

	BitmapFont messageAboutVersion;

	private OrthographicCamera camera;

	
	//TODO блочить движение змейки, определять на какой угол осуществляется поворот, и запрещать сделать большой поворот
	
	@Override
	public void render(float delta) {
		batch.setProjectionMatrix(camera.projection);
		batch.setTransformMatrix(camera.view);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Move tail
		snake.getTail().moveTail(
				new Point(snake.getHead().getPosition().getX(), snake.getHead()
						.getPosition().getY()));

		// Score render
		batch.begin();
		background.draw(batch);
		// pause_btn.draw(batch, 1.0f);
		messageAboutVersion.draw(batch, "Alpha ", 100, 300);
		messageAboutVersion.draw(batch, "@igor.nesterenya ", 200, 200);
		batch.end();
		// Gdx.graphics.getDeltaTime()
		control.update(delta);
		// Gdx.graphics.getDeltaTime()
		snake.update(delta);

		if (wallsController.checkPosiableMovingX(snake, control.getOffsetX())) {
			snake.getHead().moveHeadX(control.getOffsetX());
		} else {
			snake.reactionOnWall(soundsPlayer);
		}

		if (wallsController.checkPosiableMovingY(snake, control.getOffsetY())) {
			snake.getHead().moveHeadY(control.getOffsetY());
		} else {
			snake.reactionOnWall(soundsPlayer);
		}

		// Direction calculation
		Point headPos = snake.getHead().getPosition();
		// TODO Переместить код в логику самой змейки
		snake.defineHeadDerection(headPos, snake.getTail().getLastPoint());

		decorationRenderer.render();
		wallsRenderer.render();

		
		batch.begin();
		batch.draw(scoreTx,25, FunnySnakeGame.getWorlsHeight() - 25);
		
		
		font.draw(batch,
			Integer.toString(GameContext.getInstance().score),
				110, FunnySnakeGame.getWorlsHeight()-3);
		batch.end();

		snakeRenderer.render();

		snake.getHead().tryBlinkEyes(Gdx.graphics.getDeltaTime());

		// Render Feed
		feedController.next(Gdx.graphics.getDeltaTime());
		feedRenderer.render(feedController.getFeed());

		// TODO разделить метод на 2 и релализовать контроллер для управления
		// этим
		if (feedController.eatFeedWhenItPossible(snake.getHead())) {
			soundsPlayer.play(SOUNDS.BITE);
			GameContext.getInstance().score++;
			snake.getTail().increase();
		}

		if (snake.getTail().isPointCrossTail(snake.getHead().getPosition(),
				snake.getHead().getSize())) {
			//GameContext.getInstance().score = -50;
			soundsPlayer.play(SOUNDS.OU);
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
		batch.dispose();
		font.dispose();
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