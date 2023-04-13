package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

public class ResultScreen extends Screen {

    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    float scaleLogo = (float)(scaleWidth * 0.9);
    private float scaleExit = (float)(Gdx.graphics.getWidth() * 0.1);
    private Texture backgroundUpper;
    private Texture backgroundLower;
    private Texture logo;
    private Texture cancelButton;
    private Rectangle boundsExitField;
    private BitmapFont resultText;
    private BitmapFont first;
    private BitmapFont second;
    private BitmapFont third;
    private BitmapFont fourth;

    public ResultScreen(GameScreenManager gsm) {

        super(gsm);
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("darkerBackground.png");
        logo = new Texture("bottomsUpLogoNoText.png");
        cancelButton = new Texture("cancelButton.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.05),
                (float)(height * 0.08) - scaleExit,
                scaleExit,
                scaleExit);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 164;
        parameter.color = new Color(0x022444ff);
        resultText = generator.generateFont(parameter);
        parameter.borderColor = new Color(0xffffffff);
        parameter.borderWidth = 5;
        parameter.characters = "1234.!";
        parameter.size = 169;
        fourth = generator.generateFont(parameter);
        parameter.color = new Color(0xFFD700ff);
        first = generator.generateFont(parameter);
        parameter.color = new Color(0xC0C0C0ff);
        second = generator.generateFont(parameter);
        parameter.color = new Color(0xCD7F32ff);
        third = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsExitField.contains(x, y)) {
                gsm.set(new MainMenuScreen(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.55));
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.67), scaleWidth, scaleLogo);
        sb.draw(cancelButton, (float)(width * 0.05), (float)(height * 0.92), scaleExit, scaleExit);
        resultText.draw(sb, "Results!", (float)(width * 0.19), (float)(height * 0.65));
        first.draw(sb, "1.", (float)(width * 0.05), (float)(height * 0.5));
        second.draw(sb, "2.", (float)(width * 0.05), (float)(height * 0.37));
        third.draw(sb, "3.", (float)(width * 0.05), (float)(height * 0.24));
        fourth.draw(sb, "4.", (float)(width * 0.05), (float)(height * 0.11));
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundUpper.dispose();
        backgroundLower.dispose();
        logo.dispose();
        cancelButton.dispose();
    }
}
