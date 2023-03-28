package com.mygdx.bottomsup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import jdk.tools.jmod.Main;
import screens.GameScreen;
import screens.GameScreenManager;
import screens.MainMenuScreen;


public class BottomsUp extends Game {

	public static final int WIDTH = 360;
	public static final int HEIGHT = 800;
	private GameScreenManager gsm;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameScreenManager();
		ScreenUtils.clear(0, 0, 0, 1);
		gsm.push(new GameScreen(gsm));
	}

	@Override
	public void render () {
		gsm.update();
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
