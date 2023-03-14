package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class JoinGameScreen extends Screen {

    private Texture enterButton;
    private  Texture background;
    private Texture logo;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("buttonJoinGame.png");
        background = new Texture("badlogic.jpg");
        logo = new Texture("bottomsUpLogo.png");

    }

    @Override
    public void handleInput(){

    }

    @Override
    public void update(){
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
        sb.draw(enterButton, 70, 300, sizeButton, sizeButton2);
        sb.end();
    }

    @Override
    public void dispose(){

    }
}
