package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;
import java.util.List;

public class TutorialScreen extends Screen {

    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();

    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private float scaleWidthButton = (float)(Gdx.graphics.getWidth() * 0.3);
    private float scaleHeightButton = (float)(scaleWidthButton * 0.4);
    private float scaleLogo = (float)(scaleWidth * 0.9);
    private float scaleExitButton = (float)(Gdx.graphics.getWidth() * 0.08);
    private Texture background;
    private Texture logo;
    private Texture nextButton;
    private Texture backButton;
    private Texture exitButton;

    private Texture tutTexture1;
    private Texture tutTexture2;
    private Texture tutTexture3;
    private Texture tutTexture4;

    private Rectangle boundsNextButton;
    private Rectangle boundsBackButton;

    private Rectangle boundsExitButton;

    private BitmapFont nextButtonText;
    private BitmapFont backButtonText;
    private BitmapFont tutHeader;
    private BitmapFont tutTxt;

    private int textCounter;

    private List<String> tutsHeader;
    private List<String> tutsTxt;
    private List<Texture> tutsTexture;


    public TutorialScreen(GameScreenManager gsm) {
        super(gsm);
        textCounter = 0;
        tutsHeader = Arrays.asList("Step 1: \nHost the game",
                "Step 2: \nJoin the game",
                "Step 3: \nStart the game",
                "\nHow to play");
        tutsTxt = Arrays.asList("First, one player has to " +
                    "\nhost the game. Press the " +
                    "\n'Host game' button from " +
                    "\nthe main menu and enter " +
                    "\na nickname on the next " +
                    "\npage. The host will then " +
                    "\nget a lobby with the game " +
                    "\ncode displayed.",
                "Then the other players " +
                    "\ncan join the lobby " +
                    "\nby pressing the 'Join " +
                    "\ngame' button from the " +
                    "\nmain menu. There they" +
                    "\ncan enter the game pin" +
                    "\nand a nickname to join" +
                    "\nthe lobby.",
                "When the desired number" +
                    "\nof players have joined, " +
                    "\nthe host can start the " +
                    "\ngame by pressing the " +
                    "\n'Start game!' button (only " +
                    "\nvisible for the host) and " +
                    "\nthe game will start!",
                "Use the four buttons at " +
                    "\nthe bottom of the screen" +
                    "\nto knock down the tower" +
                    "\nof drinks. Match the right " +
                    "\nbutton to the first block" +
                    "\nin the tree to progress." +
                    "\nFirst player to the top" +
                    "\nof their tower wins!");
        tutTexture1 = new Texture("tutTexture1.png");
        tutTexture2 = new Texture("tutTexture2.png");
        tutTexture3 = new Texture("tutTexture3.png");
        tutTexture4 = new Texture("tutTexture4.png");
        tutsTexture = Arrays.asList(tutTexture1, tutTexture2, tutTexture3, tutTexture4);
        background = new Texture("background.png");
        nextButton = new Texture("button.png");
        backButton = new Texture("button.png");
        exitButton = new Texture("backButton.png");
        logo = new Texture("bottomsUpLogo.png");
        boundsNextButton = new Rectangle((float)(width * 0.95-scaleWidthButton),
                (float)(height * 0.965) - scaleHeightButton,
                scaleWidthButton,
                scaleHeightButton);
        boundsBackButton = new Rectangle((float)(width * 0.05),
                (float)(height * 0.965) - scaleHeightButton,
                scaleWidthButton,
                scaleHeightButton);
        boundsExitButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.05),
                (float) (Gdx.graphics.getHeight() * 0.1) - scaleExitButton,
                scaleExitButton,
                scaleExitButton);
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size =(int)height*1/22;
        parameter.color = new Color(0x022444ff);
        nextButtonText = generator.generateFont(parameter);
        backButtonText = generator.generateFont(parameter);
        parameter.color = new Color(0xffffffff);
        parameter.size =(int)height*1/34;
        tutTxt = generator.generateFont(parameter);
        parameter.size =(int)height*1/24;
        tutHeader = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsNextButton.contains(x, y)) {
                if (textCounter == 3) {
                    textCounter = 0;
                } else {
                    textCounter++;
                }

            } else if (boundsBackButton.contains(x, y)) {
                if (textCounter == 0) {
                    textCounter = 3;
                } else {
                    textCounter--;
                }
            } else if (boundsExitButton.contains(x, y)) {
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
        sb.draw(background, 0, 0, width, height);
        sb.draw(tutsTexture.get(textCounter), (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(nextButton, (float)(width * 0.95-scaleWidthButton), (float)(height * 0.035), scaleWidthButton, scaleHeightButton);
        sb.draw(backButton, (float)(width * 0.05), (float)(height * 0.035), scaleWidthButton, scaleHeightButton);
        nextButtonText.draw(sb, "Next", (float)(width * 0.99-scaleWidthButton), (float)(height * 0.085));
        backButtonText.draw(sb, "Back", (float)(width * 0.09), (float)(height * 0.085));
        sb.draw(exitButton, (float)(width * 0.05), (float)(height * 0.9), scaleExitButton, scaleExitButton);
        tutHeader.draw(sb, tutsHeader.get(textCounter), (float)(width * 0.1), (float)(height * 0.58));
        tutTxt.draw(sb, tutsTxt.get(textCounter), (float)(width * 0.1), (float)(height * 0.45));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        logo.dispose();
        nextButton.dispose();
        backButton.dispose();
        nextButtonText.dispose();
        backButtonText.dispose();
    }
}
