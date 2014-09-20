package com.nesterenya.fannysnake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.nesterenya.fannysnake.FunnySnakeGame;
import com.nesterenya.fannysnake.GameContext;
import com.nesterenya.fannysnake.PlayStage;

public class MainMenuScreen implements Screen{

	private PlayStage playStage;
	//private Stage stage;
	private TextButton play, hiscore, exit, levels;
	private Viewport viewport;
	private Table table;
	private LabelStyle labelStyle;
	private OrthographicCamera camera;
	private ImageButton nastiaButton;
	private int counterNastia = 0;
	
	public MainMenuScreen() {

		FunnySnakeGame.getInstance().getHandler().showAds(false);
		
		Texture smileTx = new Texture("smile.png");
		TextureRegion region = new TextureRegion(smileTx);
		TextureRegionDrawable smile = new TextureRegionDrawable(region);
		ButtonStyle bStyle = new ButtonStyle(smile, smile, smile);
		ImageButtonStyle style = new ImageButtonStyle(bStyle);
		counterNastia = 0;
		nastiaButton = new ImageButton(style);
		nastiaButton.setPosition(600, 300);
		nastiaButton.setSize(76, 76);
		nastiaButton.addListener(new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            	counterNastia++;
            	if(counterNastia>2) {
            		FunnySnakeGame.getInstance().setScreen(new PassScreen());
                	dispose();
            	}
            };
		});
		
		//stage = new Stage(new ScreenViewport());
		camera = new OrthographicCamera();
		camera.position.set(100, 100, 0);
		camera.update();	
		viewport = FunnySnakeGame.getViewport(camera);
		playStage = new PlayStage(viewport);
		
		Skin skin = new Skin();
		TextureAtlas buttonAtlas =  new TextureAtlas(Gdx.files.internal("images/game/images.pack"));
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
		
		play = new TextButton("Play", buttonStyle);
		ClickListener playClickListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                FunnySnakeGame.getInstance().setScreen(new FannySnakeScreen());
                dispose();
            };
		};
		play.addListener(playClickListener);

		hiscore = new TextButton("High scores", buttonStyle);
		ClickListener hiscoreClickListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                FunnySnakeGame.getInstance().setScreen(new HiscoreScreen());
                dispose();
            };
		};
		hiscore.addListener(hiscoreClickListener);
		
		levels = new TextButton("Missons", buttonStyle);
		ClickListener levelsClickListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                FunnySnakeGame.getInstance().setScreen(new LevelsScreen());
                dispose();
            };
		};
		levels.addListener(levelsClickListener);
		
		exit = new TextButton("Exit", buttonStyle);
		ClickListener exitClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				FunnySnakeGame.getInstance().doMenuClickDown();
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                dispose();
            };
		};
		exit.addListener(exitClickListener);
		
		table.add();
		table.row();
		table.add(play);
		table.row();
		table.add(hiscore);
		table.row();
		table.add(levels);
		table.row();
		table.add(exit);
		table.row();
		
		//TODO убрать пустую кнопку
		//table.add(new TextButton("", buttonStyle));
		
		//table.setCenterPosition(0	, 40);
		
		
		//stage.addActor(table);
		playStage.addActor(table);

		playStage.addActor(nastiaButton);
		
		//Gdx.input.setInputProcessor(stage);
		Gdx.input.setInputProcessor(playStage);
		Gdx.input.setCatchBackKey(true);
		
		FunnySnakeGame.getInstance().getHandler().showAds(true);
		
		
	}
	
	@Override
	public void render(float delta) {
		
		//batch.setProjectionMatrix(camera.projection);
		//batch.setTransformMatrix(camera.view);
		
		Gdx.gl20.glClearColor(0.1f, 0.4f, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//stage.act(delta);
		//stage.draw();
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
	public void dispose() {
		//stage.dispose();
	    playStage.dispose();
		//game.dispose();
	}

}
