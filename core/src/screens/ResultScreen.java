package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class ResultScreen extends Screen {

    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    float scaleLogo = (float)(scaleWidth * 0.9);
    float scaleText = (float)(scaleWidth * 0.2);
    private float scaleButton = (float)(Gdx.graphics.getWidth() * 0.1);
    private float scaleNumbers = (float)(Gdx.graphics.getWidth() * 0.1);
    private Texture backgroundUpper;
    private Texture backgroundLower;
    private Texture logo;
    private Texture resultText;
    private Texture cancelButton;
    private Texture first;
    private Texture second;
    private Texture third;
    private Texture fourth;

    public ResultScreen(GameScreenManager gsm) {
        super(gsm);
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("DarkerBackground.png");
        logo = new Texture("bottomsUpLogoNoText.png");
        cancelButton = new Texture("cancelButton.png");
        resultText = new Texture("results.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.55));
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.65), scaleWidth, scaleLogo);
        sb.draw(cancelButton, (float)(width * 0.05), (float)(height * 0.9), scaleButton, scaleButton);
        sb.draw(resultText, (float)(width * 0.2), (float)(height * 0.57), scaleWidth, scaleText);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
