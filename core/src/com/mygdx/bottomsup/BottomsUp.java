package com.mygdx.bottomsup;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import screens.GameScreenManager;
import screens.MainMenuScreen;


public class BottomsUp extends Game {
	private GameScreenManager gsm;
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameScreenManager();
		ScreenUtils.clear(0, 0, 0, 1);
		gsm.push(new MainMenuScreen(gsm));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		gsm.update();
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
