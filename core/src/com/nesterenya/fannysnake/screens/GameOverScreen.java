package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.FannySnake;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.PlayStage;
import com.nesterenya.fannysnake.navigation.MainMenuScreen;

public class GameOverScreen implements Screen {

	private PlayStage playStage;
	// private Stage stage;
	private TextButton playAgain, openMenu;

	private Table table;
	private LabelStyle labelStyle;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public GameOverScreen(String message, int score) {
		camera = new OrthographicCamera();
		camera.position.set(100, 100, 0);
		camera.update();	
		viewport = FunnySnakeGame.getViewport(camera);
		playStage = new PlayStage(viewport);
		
		Skin skin = new Skin();
		TextureAtlas buttonAtlas = new TextureAtlas(
				Gdx.files.internal("images/game/images.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.font = new BitmapFont();
		buttonStyle.up = skin.getDrawable("button-up");
		buttonStyle.down = skin.getDrawable("button-down");
		buttonStyle.checked = skin.getDrawable("button-up");

		labelStyle = new LabelStyle();
		labelStyle.font = new BitmapFont();
		table = new Table();
		table.setFillParent(true);
		table.setSkin(new Skin());
		
		playAgain = new TextButton("Play Again", buttonStyle);

		ClickListener playClickListener = new ClickListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// Gdx.input.vibrate(20);
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().setScreen(new FannySnake());
				dispose();
			};
		};

		playAgain.addListener(playClickListener);

		openMenu = new TextButton("Menu", buttonStyle);
		ClickListener exitClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// Gdx.input.vibrate(20);
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().setScreen(new MainMenuScreen());
				dispose();
			};
		};
		openMenu.addListener(exitClickListener);
		
		Label gameOverMsg = new Label("Game Over", labelStyle);
		Label infoMsg = new Label(message, labelStyle);
		Label scoreMsg = new Label("Score: "+score, labelStyle);
		
		table.add(gameOverMsg).colspan(2);
		table.row();
		table.add(infoMsg).colspan(2);
	    table.row();
	    table.add(scoreMsg).colspan(2);
	    table.row();
		table.add(playAgain);
	    table.add(openMenu);
	    
	    
	    
		
		playStage.addActor(table);

		Gdx.input.setInputProcessor(playStage);
		Gdx.input.setCatchBackKey(true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0.2f, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playStage.act(delta);
		playStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height); 
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
