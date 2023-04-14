package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import components.BlockTower;

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

    private BlockTower blockTower;

    private long timeoutTime = System.currentTimeMillis();;

    private long startTime = System.currentTimeMillis();

    private long elapsedTime;

    private long timeoutDuration = 3000;

    public GameScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        btn1 = new Texture("block0.png");
        btn2 = new Texture("block1.png");
        btn3 = new Texture("block2.png");
        btn4 = new Texture("block3.png");
        mainView = new Texture("button.png");
        secondView = new Texture("button.png");
        thirdView = new Texture("button.png");
        fourthView = new Texture("button.png");
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            pointer.x = Gdx.input.getX();
            pointer.y = Gdx.input.getY();

            if (blockTower.getCopyOfCurrentList().isEmpty()) {
                // stopp spillet

            } else {

                if (timeoutTime < elapsedTime) {

                    if (boundsBtn1.contains(pointer.x, pointer.y)) {
                        //spørre til databasen med btn1 som info
                        checkPopTimeoutBlock(0);

                    } else if (boundsBtn2.contains(pointer.x, pointer.y)) {
                        checkPopTimeoutBlock(1);

                    } else if (boundsBtn3.contains(pointer.x, pointer.y)) {
                        checkPopTimeoutBlock(2);

                    } else if (boundsBtn4.contains(pointer.x, pointer.y)) {
                        checkPopTimeoutBlock(3);
                    }
                }
            }
        }
    }
    @Override
    public void update() {
        elapsedTime = System.currentTimeMillis() - startTime;
        handleInput();
    }

@Override
    public void render(SpriteBatch sb) {

        // vet ikke helt hvor den skal stå ennå men skal med et sted
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

    private void checkPopTimeoutBlock(int blockNumber) {
        if (blockTower.checkNextBlock(blockNumber)) { // sjekke om de matcher med ønsket knapp
            blockTower.popNextBlock();
        } else {
            timeoutTime = System.currentTimeMillis() + timeoutDuration;
        }
    }
}
