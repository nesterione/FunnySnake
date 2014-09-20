package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.PlayStage;
import com.nesterenya.fannysnake.PlayStage.OnHardKeyListener;
import com.nesterenya.fannysnake.Storage;

public class HiscoreScreen implements Screen {

	private PlayStage playStage;
	private TextButton openMenu;

	private Table table;
	private LabelStyle labelStyle;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	public HiscoreScreen() {
		FunnySnakeGame.getInstance().getHandler().showAds(true);
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
		buttonStyle.font = FunnySnakeGame.getInstance().font;
		buttonStyle.up = skin.getDrawable("button-up");
		buttonStyle.down = skin.getDrawable("button-down");
		buttonStyle.checked = skin.getDrawable("button-up");

		labelStyle = new LabelStyle();
		labelStyle.font = FunnySnakeGame.getInstance().font;
		table = new Table();
		table.setFillParent(true);
		table.setSkin(new Skin());
		
		openMenu = new TextButton("Menu", buttonStyle);
		ClickListener exitClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
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
		
		Storage storage = new Storage();
		int[] scores = storage.readScores();
		Label[] labels = new Label[scores.length];
		for(int i =0; i< scores.length; i++) {
			labels[i] = new Label((i+1)+". "+scores[i], labelStyle);
		}

		table.add(new Label("Your high scores:", labelStyle));
		table.row();
		table.row();
		
		for(Label label : labels) {
			table.add(label);
			table.row();
		}
		
	    table.add(openMenu);
	    
		playStage.addActor(table);

		Gdx.input.setInputProcessor(playStage);
		Gdx.input.setCatchBackKey(true);
		
		playStage.setHardKeyListener(new OnHardKeyListener() {
			@Override
			public void onHardKey(int keyCode, int state) {
				if (keyCode == Keys.BACK && state == 1) {
					FunnySnakeGame.getInstance().doMenuClickDown();
					FunnySnakeGame.getInstance().setScreen(new MainMenuScreen());
					dispose();
				}
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0.1f, 0.4f, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

		playStage.act(delta);
		playStage.draw();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height); 
	}

	@Override
	public void show() { }

	@Override
	public void hide() { }

	@Override
	public void pause() { }

	@Override
	public void resume() { }

	@Override
	public void dispose() { }

}
