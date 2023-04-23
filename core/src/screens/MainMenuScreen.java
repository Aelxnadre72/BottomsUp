package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen extends Screen {
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();

    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private float scaleLogo = (float)(scaleWidth * 0.9);
    private float scaleTutorialButton = (float)(Gdx.graphics.getWidth() * 0.1);

    private Texture background;
    private Texture logo;
    private Texture hostButton;
    private Texture joinButton;
    private Texture tutorialButton;

    private Rectangle boundsHostButton;
    private Rectangle boundsJoinButton;
    private Rectangle boundsTutorialButton;

    private BitmapFont hostButtonText;
    private BitmapFont joinButtonText;

    public MainMenuScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        hostButton = new Texture("button.png");
        joinButton = new Texture("button.png");
        logo = new Texture("bottomsUpLogo.png");
        tutorialButton = new Texture("infoButton.png");
        boundsHostButton = new Rectangle((float)(width * 0.2),
                                         (float)(height * 0.6) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
        boundsJoinButton = new Rectangle((float)(width * 0.2),
                                         (float)(height * 0.75) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
        boundsTutorialButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.95 - scaleTutorialButton),
                (float) (Gdx.graphics.getHeight() * 0.1) - scaleTutorialButton,
                scaleTutorialButton,
                scaleTutorialButton);
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)height*1/24;
        parameter.color = new Color(0x022444ff);
        hostButtonText = generator.generateFont(parameter);
        joinButtonText = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsHostButton.contains(x, y)) {
                gsm.set(new HostGameScreen(gsm));
                dispose();
            } else if (boundsJoinButton.contains(x, y)) {
                gsm.set(new JoinGameScreen(gsm));
                dispose();
            } else if (boundsTutorialButton.contains(x, y)) {
                gsm.set(new TutorialScreen(gsm));
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
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.55), scaleWidth, scaleLogo);
        sb.draw(hostButton, (float)(width * 0.2), (float)(height * 0.4), scaleWidth, scaleHeight);
        sb.draw(joinButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleHeight);
        hostButtonText.draw(sb, "Host game", (float)(width * 0.27), (float)(height * 0.465));
        joinButtonText.draw(sb, "Join game", (float)(width * 0.27), (float)(height * 0.315));
        sb.draw(tutorialButton, (float)(width * 0.95 - scaleTutorialButton), (float)(height * 0.9), scaleTutorialButton, scaleTutorialButton);

        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        logo.dispose();
        hostButton.dispose();
        joinButton.dispose();
        hostButtonText.dispose();
        joinButtonText.dispose();
        tutorialButton.dispose();
    }
}
