package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
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

public class PassScreen implements Screen{
	private PlayStage playStage;
	
	
	private TextButton oneButton;
	private TextButton twoButton;
	private TextButton threeButton;
	private TextButton fourButton;

	private Table table;
	private LabelStyle labelStyle;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	private String entered = "";
	private void doEnder(int k) {
		entered += k;
		
		if(entered.equals("114422")) {
			FunnySnakeGame.isAnastasia = true;
			FunnySnakeGame.getInstance().setScreen(new FannySnakeScreen());
			dispose();
		}
		
		if(entered.length()>7) {
			FunnySnakeGame.getInstance().setScreen(new MainMenuScreen());
			dispose();
		}
	}
	
	public PassScreen() {
		FunnySnakeGame.getInstance().getHandler().showAds(false);
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
		
		 
		
		oneButton = new TextButton("1", buttonStyle);
		twoButton = new TextButton("2", buttonStyle);
		threeButton = new TextButton("3", buttonStyle);
		fourButton = new TextButton("4", buttonStyle);
		
		ClickListener oneClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				doEnder(1);
			};
		};
		ClickListener twoClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				doEnder(2);
			};
		};
		ClickListener threeClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				doEnder(3);
			};
		};
		
		ClickListener fourClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
				return true;
			};

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				doEnder(4);
			};
		};
		
		oneButton.addListener(oneClickListener);
		twoButton.addListener(twoClickListener);
		threeButton.addListener(threeClickListener);
		fourButton.addListener(fourClickListener);
		
		 
		 
		
		Label messageOne = new Label("Enter password!", labelStyle);
		
		table.add(messageOne);
		table.row();
		table.add(oneButton);
		table.add(twoButton);
		table.row();
		table.add(threeButton);
		table.add(fourButton);
		
	    
		playStage.addActor(table);

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
	public void dispose() {	}
	
}
