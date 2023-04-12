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

        // standard sizes for player screens
        float widthMain = (float)(width * 0.6);
        float heightMain = (float)(height * 0.55);
        float widthPlayers = (float)(width * 0.32);
        float heightPlayers = (float) (height * 0.21);

        // standard sizes for buttons
        float widthBtn = (float)(width * 0.47);
        float heightBtn = (float)(widthBtn * 0.65);

        // Scales for width and height for the different buttons and screen
        double heightScaleTop = 0.141;
        double heightScaleBot = 0.01;
        double widthScale = 0.02;
        double heightScalePlayers = heightScaleBot + heightScaleTop;

        sb.begin();
        sb.draw(background, 0, 0, width, height);
        //sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(btn1, (float)(width * widthScale),              (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(btn2, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(btn3, (float)(width * widthScale),              (float)(height * heightScaleBot), widthBtn, heightBtn);
        sb.draw(btn4, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleBot), widthBtn, heightBtn);

        sb.draw(mainView,
                (float)(width * widthScale),
                (float)(height * heightScalePlayers + heightBtn),
                widthMain,
                heightMain);
        sb.draw(secondView,
                (float)(width * 3*widthScale + widthMain),
                (float)(height * heightScalePlayers + 2.2*heightPlayers + heightBtn),
                widthPlayers,
                heightPlayers);
        sb.draw(thirdView,
                (float)(width * 3*widthScale + widthMain),
                (float)(height * heightScalePlayers + 1.1*heightPlayers + heightBtn),
                widthPlayers,
                heightPlayers);
        sb.draw(fourthView,
                (float)(width * 3*widthScale + widthMain),
                (float)(height * (heightScaleTop + heightScaleBot) + heightBtn),
                widthPlayers,
                heightPlayers);


        sb.end();
    }

    @Override
    public void dispose() {

    }
}
