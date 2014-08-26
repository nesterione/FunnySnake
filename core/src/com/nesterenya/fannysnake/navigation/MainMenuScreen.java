package com.nesterenya.fannysnake.navigation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.nesterenya.fannysnake.FannySnake;
import com.nesterenya.fannysnake.PlayStage;

public class MainMenuScreen implements Screen{

	private PlayStage playStage;
	//private Stage stage;
	private TextButton play, hiscore, exit;
	
	private Table table;
	private LabelStyle labelStyle;
	
	public MainMenuScreen(final GameConfig game) {

		//stage = new Stage(new ScreenViewport());
		playStage = new PlayStage(new ScreenViewport());
		
		Skin skin = new Skin();
		TextureAtlas buttonAtlas =  new TextureAtlas(Gdx.files.internal("images/game/images.pack"));
		skin.addRegions(buttonAtlas);
		TextButtonStyle buttonStyle = new TextButtonStyle();
		buttonStyle.font = game.font;
		buttonStyle.up = skin.getDrawable("button-up");
		buttonStyle.down = skin.getDrawable("button-down");
		buttonStyle.checked = skin.getDrawable("button-up");
		
		labelStyle = new LabelStyle();
		labelStyle.font = game.font;
		table = new Table();
		table.setFillParent(true);
		
		play = new TextButton("Play", buttonStyle);
		
		ClickListener playClickListener = new ClickListener() {
			@Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //TODO ������ ����
            	//TODO вообще бред
            	game.setScreen(new FannySnake(game));
                dispose();
            };
		};
		play.addListener(playClickListener);
		
		exit = new TextButton("Exit", buttonStyle);
		ClickListener exitClickListener = new ClickListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //Gdx.input.vibrate(20);
                return true;
            };
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                dispose();
            };
		};
		exit.addListener(exitClickListener);
		
		hiscore = new TextButton("Hiscore", buttonStyle);
		
		table.add(play);
		table.row();
		table.add(hiscore);
		table.row();
		table.add(exit);
		//stage.addActor(table);
		playStage.addActor(table);

		//Gdx.input.setInputProcessor(stage);
		Gdx.input.setInputProcessor(playStage);
		Gdx.input.setCatchBackKey(true);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0, 0, 0, 1);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//stage.act(delta);
		//stage.draw();
		playStage.act(delta);
		playStage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		//stage.dispose();
	    playStage.dispose();
		//game.dispose();
	}

}
