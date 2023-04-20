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

    float scaleWidthImage = (float)(Gdx.graphics.getWidth() * 0.23);
    float scaleImage = (float)(scaleWidthImage * 14/10);
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

    private BitmapFont firstName;
    private BitmapFont secondName;
    private BitmapFont thirdName;
    private BitmapFont fourthName;

    private BitmapFont firstTime;
    private BitmapFont secondTime;
    private BitmapFont thirdTime;
    private BitmapFont fourthTime;

    private String lobbyCode;
    private List<String> playerResults;
    private List<String> textChoice;

    private List<Texture> playerImages;
private List<String> playerResultsName;
    public ResultScreen(GameScreenManager gsm, String lobbyCode) {
        super(gsm);
        this.lobbyCode = lobbyCode;
        playerResults = Arrays.asList("", "", "", "");
        playerResultsName = Arrays.asList("", "", "", "");
        textChoice = Arrays.asList("", "", "", "");
        playerImages = Arrays.asList(new Texture("invisibleBlock.png"),
                new Texture("invisibleBlock.png"),
                new Texture("invisibleBlock.png"),
                new Texture("invisibleBlock.png"));
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
        parameter.size = (int)height*1/24;
        parameter.color = new Color(0xffffffff);
        firstName = generator.generateFont(parameter);
        secondName = generator.generateFont(parameter);
        thirdName = generator.generateFont(parameter);
        fourthName = generator.generateFont(parameter);
        parameter.size = (int)height*1/30;
        firstTime = generator.generateFont(parameter);
        secondTime = generator.generateFont(parameter);
        thirdTime = generator.generateFont(parameter);
        fourthTime = generator.generateFont(parameter);
        parameter.color = new Color(0x022444ff);
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
        List<String> resultsName = FBIF.updatePlayerList(lobbyCode);
        for (int i = 0; i < results.size(); i++) {
            playerResults.set(i, results.get(i));
            playerResultsName.set(i, resultsName.get(i));
            if (i < results.size() - 1) {
                textChoice.set(i, "sec");
            } else if (i == results.size() - 1){
                textChoice.set(i, "blocks");
            }
            String path = "" + "p" + String.valueOf(i+1) + ".png";
            playerImages.set(i, new Texture(path));
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
        first.draw(sb, "1.", (float)(width * 0.05), (float)(height * 0.49));
        second.draw(sb, "2.", (float)(width * 0.05), (float)(height * 0.36));
        third.draw(sb, "3.", (float)(width * 0.05), (float)(height * 0.23));
        fourth.draw(sb, "4.", (float)(width * 0.05), (float)(height * 0.1));
        firstName.draw(sb, playerResultsName.get(0), (float)(width * 0.3), (float)(height * 0.5));
        secondName.draw(sb, playerResultsName.get(1), (float)(width * 0.3), (float)(height * 0.37));
        thirdName.draw(sb, playerResultsName.get(2), (float)(width * 0.3), (float)(height * 0.24));
        fourthName.draw(sb, playerResultsName.get(3), (float)(width * 0.3), (float)(height * 0.11));
        firstTime.draw(sb, "" + playerResults.get(0) + " " + textChoice.get(0), (float)(width * 0.3), (float)(height * 0.45));
        secondTime.draw(sb, "" + playerResults.get(1) + " " + textChoice.get(1), (float)(width * 0.3), (float)(height * 0.32));
        thirdTime.draw(sb, "" + playerResults.get(2) + " " + textChoice.get(2), (float)(width * 0.3), (float)(height * 0.19));
        fourthTime.draw(sb, "" + playerResults.get(3) + " " + textChoice.get(3), (float)(width * 0.3), (float)(height * 0.06));
        sb.draw(playerImages.get(0), (float)(width * 0.7), (float)(height * 0.37), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(1), (float)(width * 0.7), (float)(height * 0.24), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(2), (float)(width * 0.7), (float)(height * 0.11), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(3), (float)(width * 0.7), (float)(height * 0.08 - height * 0.1), scaleWidthImage, scaleImage);
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
