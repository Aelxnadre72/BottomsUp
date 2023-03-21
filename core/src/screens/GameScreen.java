package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends Screen {

    private Texture background;

    private Texture btn1;
    private Texture btn2;
    private Texture btn3;
    private Texture btn4;
    private Texture mainView;
    private Texture secondView;
    private Texture thirdView;
    private Texture fourthView;

    private Rectangle boundsBtn1;
    private Rectangle boundsBtn2;
    private Rectangle boundsBtn3;
    private Rectangle boundsBtn4;

    private Vector2 pointer;

    public GameScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("MainMenuBackground.png");
        btn1 = new Texture("buttonJoinGame.png");
        btn2 = new Texture("buttonHostGame.png");
        btn3 = new Texture("buttonHostGame.png");
        btn4 = new Texture("buttonHostGame.png");
        mainView = new Texture("badlogic.jpg");
        secondView = new Texture("badlogic.jpg");
        thirdView = new Texture("badlogic.jpg");
        fourthView = new Texture("badlogic.jpg");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            pointer.x = Gdx.input.getX();
            pointer.y = Gdx.input.getY();
            if (boundsBtn1.contains(pointer.x, pointer.y)) {
                //spørre til databasen med btn1 som info
            } else if (boundsBtn2.contains(pointer.x, pointer.y)) {

            } else if (boundsBtn3.contains(pointer.x, pointer.y)) {

            } else if (boundsBtn4.contains(pointer.x, pointer.y)) {

            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float scaleWidthMain = (float)(Gdx.graphics.getWidth() * 0.6);
        float scaleWidthPlayers = (float)(Gdx.graphics.getWidth() * 0.32);
        float scaleWidthBtn = (float)(Gdx.graphics.getWidth() * 0.47);
        float scaleButton = (float)(scaleWidthBtn * 0.65);

        double heightScaleTop = 0.141;
        double heightScaleBot = 0.01;
        double widthScaleLeft = 0.02;
        double widthScaleRight = 2*widthScaleLeft + 0.47;
        double widthScalePlayers = 0.6;

        sb.begin();
        sb.draw(background, 0, 0, width, height);
        //sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(btn1, (float)(width * widthScaleLeft), (float)(height * heightScaleTop), scaleWidthBtn, scaleButton);
        sb.draw(btn2, (float)(width * widthScaleRight), (float)(height * heightScaleTop), scaleWidthBtn, scaleButton);
        sb.draw(btn3, (float)(width * widthScaleLeft), (float)(height * heightScaleBot), scaleWidthBtn, scaleButton);
        sb.draw(btn4, (float)(width * widthScaleRight), (float)(height * heightScaleBot), scaleWidthBtn, scaleButton);
        //sb.draw(joinButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleButton);

        double heightScalePlayers = 0.22;
        sb.draw(mainView,
                (float)(width * widthScaleLeft),
                (float)(height * (heightScaleTop + heightScaleBot) + scaleButton),
                scaleWidthMain,
                (float)(height * 0.55));
        sb.draw(secondView,
                (float)(scaleWidthMain + 0.05*width),
                (float)(height * (heightScaleTop + heightScaleBot + 2*heightScalePlayers+ 2*0.01) + scaleButton),
                scaleWidthPlayers,
                (float)(height * heightScalePlayers));
        sb.draw(thirdView,
                (float)(scaleWidthMain + 0.05*width),
                (float)(height * (heightScaleTop + heightScaleBot + heightScalePlayers + 0.01) + scaleButton),
                scaleWidthPlayers,
                (float)(height * heightScalePlayers));
        sb.draw(fourthView,
                (float)(scaleWidthMain + 0.05*width),
                (float)(height * (heightScaleTop + heightScaleBot) + scaleButton),
                scaleWidthPlayers,
                (float)(height * heightScalePlayers));
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
