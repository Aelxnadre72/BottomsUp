package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainMenuScreen extends Screen {

    private Texture background;

    private Texture logo;
    private Texture hostButton;
    private Texture joinButton;

    public MainMenuScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("MainMenuBackground.png");
        hostButton = new Texture("buttonHostGame.png");
        joinButton = new Texture("buttonJoinGame.png");
        logo = new Texture("bottomsUpLogo.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update() {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        float sizeButton = (float) ((float)Gdx.graphics.getWidth() * 0.6);
        float sizeButton2 = (float) (sizeButton * 0.35);

        float sizeLogo = (float) ((float)Gdx.graphics.getWidth() * 0.6);
        float sizeLogo2 = (float) (sizeLogo * 0.75);

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(logo, 70, 500, sizeLogo, sizeLogo2);
        sb.draw(hostButton, 70, 300, sizeButton, sizeButton2);
        sb.draw(joinButton, 70, 200, sizeButton, sizeButton2);
        sb.end();

    }

    @Override
    public void dispose() {
        //hostButton.dispose();
        //joinButton.dispose();
    }
}
