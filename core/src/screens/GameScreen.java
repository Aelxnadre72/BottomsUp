package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.bottomsup.BottomsUp;

public class GameScreen extends ScreenAdapter {
    BottomsUp game;

    public GameScreen(BottomsUp game) {
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    //game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
