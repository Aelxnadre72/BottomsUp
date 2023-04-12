package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class MainMenuScreen extends Screen {
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();

    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private float scaleLogo = (float)(scaleWidth * 0.9);
    private Texture background;
    private Texture logo;
    private Texture hostButton;
    private Texture joinButton;

    private Rectangle boundsHostButton;
    private Rectangle boundsJoinButton;

    public MainMenuScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        hostButton = new Texture("buttonHostGame.png");
        joinButton = new Texture("buttonJoinGame.png");
        logo = new Texture("bottomsUpLogo.png");
        boundsHostButton = new Rectangle((float)(width * 0.2),
                                         (float)(height * 0.6) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
        boundsJoinButton = new Rectangle((float)(width * 0.2),
                                         (float)(height * 0.75) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsHostButton.contains(x, y)) {
                gsm.set(new HostLobbyScreen(gsm));
                dispose();
            } else if (boundsJoinButton.contains(x, y)) {
                gsm.set(new JoinGameScreen(gsm));
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
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(hostButton, (float)(width * 0.2), (float)(height * 0.4), scaleWidth, scaleHeight);
        sb.draw(joinButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleHeight);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        logo.dispose();
        hostButton.dispose();
        joinButton.dispose();
    }
}
