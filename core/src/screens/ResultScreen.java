package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ResultScreen extends Screen {

    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    float scaleHeight = (float)(scaleWidth * 0.3);
    float scaleLogo = (float)(scaleWidth * 0.75);
    private Texture background;
    private Texture background2;
    private Texture logo;
    private Texture resultText;
    private Texture first;
    private Texture second;
    private Texture third;
    private Texture fourth;

    public ResultScreen(GameScreenManager gsm) {

        super(gsm);
        background = new Texture("background.png");
        background2 = new Texture("darkerBackground.png");
        first = new Texture("1.png");
        second = new Texture("2.png");
        third = new Texture("3.png");
        fourth = new Texture("4.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
