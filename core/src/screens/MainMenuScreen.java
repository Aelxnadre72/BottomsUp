package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MainMenuScreen extends Screen {

    private Texture background;

    private Texture logo;
    private Texture hostButton;
    private Texture joinButton;

    private Rectangle boundsHostButton;
    private Rectangle boundsJoinButton;

    private Vector2 pointer;

    public MainMenuScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("MainMenuBackground.png");
        hostButton = new Texture("buttonHostGame.png");
        joinButton = new Texture("buttonJoinGame.png");
        logo = new Texture("bottomsUpLogo.png");
        boundsHostButton = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                         (float)(Gdx.graphics.getHeight() * 0.4),
                                         hostButton.getWidth(),
                                         hostButton.getHeight());
        boundsJoinButton = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                         (float)(Gdx.graphics.getHeight() * 0.25),
                                         joinButton.getWidth(),
                                         joinButton.getHeight());
        pointer = new Vector2(0,0);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            pointer.x = Gdx.input.getX();
            pointer.y = Gdx.input.getY();
            if (boundsHostButton.contains(pointer.x, pointer.y)) {
                gsm.set(new LobbyScreen(gsm));
                dispose();
            } else if (boundsJoinButton.contains(pointer.x, pointer.y)) {
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
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
        float scaleButton = (float)(scaleWidth * 0.3);
        float scaleLogo = (float)(scaleWidth * 0.75);

        sb.begin();
        sb.draw(background, 0, 0, width, height);
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(hostButton, (float)(width * 0.2), (float)(height * 0.4), scaleWidth, scaleButton);
        sb.draw(joinButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleButton);
        sb.end();

    }

    @Override
    public void dispose() {
        hostButton.dispose();
        joinButton.dispose();
        logo.dispose();
    }
}
