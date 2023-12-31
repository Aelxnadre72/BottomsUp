package screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameScreenManager {

    private Stack<Screen> screens;

    public GameScreenManager() {
        this.screens = new Stack<Screen>();
    }

    public void push(Screen screen) {
        screens.push(screen);
    }

    public void pop() {
        screens.pop();
    }

    public void set(Screen screen) {
        screens.pop();
        screens.push(screen);
    }

    public void update() {
        screens.peek().update();
    }

    public void render(SpriteBatch sb) {
        screens.peek().render(sb);
    }
}