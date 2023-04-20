package screens;

import static com.mygdx.bottomsup.BottomsUp.FBIF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;
import java.util.List;

public class ResultScreen extends Screen {

    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.5);
    float scaleLogo = (float)(scaleWidth * 0.85);
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

    private String lobbyCode;
    private List<String> playerResults;

    public ResultScreen(GameScreenManager gsm, String lobbyCode) {
        super(gsm);
        this.lobbyCode = lobbyCode;
        playerResults = Arrays.asList("", "", "", "");
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("darkerBackground.png");
        logo = new Texture("bottomsUpLogoNoText.png");
        cancelButton = new Texture("cancelButton.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.05),
                (float)(height * 0.08) - scaleExit,
                scaleExit,
                scaleExit);
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)height*1/13;
        parameter.color = new Color(0x022444ff);
        resultText = generator.generateFont(parameter);
        parameter.borderColor = new Color(0xffffffff);
        parameter.borderWidth = 5;
        parameter.characters = "1234.!";
        parameter.size = (int)height*1/12;
        fourth = generator.generateFont(parameter);
        parameter.color = new Color(0xFFD700ff);
        first = generator.generateFont(parameter);
        parameter.color = new Color(0xC0C0C0ff);
        second = generator.generateFont(parameter);
        parameter.color = new Color(0xCD7F32ff);
        third = generator.generateFont(parameter);
        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    public void getResults(){
        List<String> results = FBIF.getResults(lobbyCode);
        for (int i = 0; i < results.size(); i++) {
            playerResults.set(i, results.get(i));
        }
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
        getResults();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.55));
        sb.draw(logo, (float)(width * 0.25), (float)(height * 0.7), scaleWidth, scaleLogo);
        sb.draw(cancelButton, (float)(width * 0.05), (float)(height * 0.92), scaleExit, scaleExit);
        resultText.draw(sb, "Results!", (float)(width * 0.2), (float)(height * 0.65));
        first.draw(sb, "1.    " + playerResults.get(0), (float)(width * 0.05), (float)(height * 0.5));
        second.draw(sb, "2.    " + playerResults.get(1), (float)(width * 0.05), (float)(height * 0.37));
        third.draw(sb, "3.    " + playerResults.get(2), (float)(width * 0.05), (float)(height * 0.24));
        fourth.draw(sb, "4.    " + playerResults.get(3), (float)(width * 0.05), (float)(height * 0.11));
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
