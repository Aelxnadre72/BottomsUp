package screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {
    protected GameScreenManager gsm;

    protected Screen(GameScreenManager gsm) {this.gsm = gsm; }

    protected abstract void handleInput();
    public abstract void update();

    public void show() {
    }
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
