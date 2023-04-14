package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import components.BlockTower;

public class GameScreen extends Screen {
    private Texture background;

    private Texture btn0;
    private Texture btn1;
    private Texture btn2;
    private Texture btn3;
    private Texture invisibleBlock;
    private Texture errorBlock;
    private Texture mainView;
    private Texture secondView;
    private Texture thirdView;
    private Texture fourthView;

    private Rectangle boundsBtn0;
    private Rectangle boundsBtn1;
    private Rectangle boundsBtn2;
    private Rectangle boundsBtn3;

    private BlockTower blockTower;

    private long timeoutTime = System.currentTimeMillis();;

    private long startTime = System.currentTimeMillis();

    private long elapsedTime;

    private long timeoutDuration = 3000;

    private float width;
    private float height;

    // standard sizes for buttons
    private float widthBtn;
    private float heightBtn;

    // Scales for width and height for the different buttons and screen
    private double heightScaleTop;
    private double heightScaleBot;
    private double widthScale;


    public GameScreen(GameScreenManager gsm) {
        super(gsm);
        background = new Texture("background.png");
        btn0 = new Texture("block0.png");
        btn1 = new Texture("block1.png");
        btn2 = new Texture("block2.png");
        btn3 = new Texture("block3.png");
        errorBlock = new Texture("errorBlock.png");
        invisibleBlock = new Texture("invisibleBlock.png");
        blockTower = new BlockTower(50);

        mainView = new Texture("button.png");
        secondView = new Texture("button.png");
        thirdView = new Texture("button.png");
        fourthView = new Texture("button.png");

        // width and height of phone screen
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        // standard sizes for buttons
        widthBtn = (float)(width * 0.47);
        heightBtn = (float)(widthBtn * 0.5);

        // Scales for width and height for the different buttons and screen
        heightScaleTop = 0.141;
        heightScaleBot = 0.01;
        widthScale = 0.02;
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched(0)) {
            int pointerX = Gdx.input.getX(0);
            int pointerY = Gdx.input.getY(0);
            System.out.println("abcde");


            if (blockTower.getCopyOfCurrentList().isEmpty()) {
                // stopp spillet

            } else {
                System.out.println("fghij");


                //if (timeoutTime < elapsedTime) {

                    if (isBoundedByBtn(0, pointerX, pointerY)) {
                        System.out.println("test1");
                        checkPopTimeoutBlock(0);

                    } else if (isBoundedByBtn(1, pointerX, pointerY)) {
                        checkPopTimeoutBlock(1);
                        System.out.println("test2");

                    } else if (isBoundedByBtn(2, pointerX, pointerY)) {
                        checkPopTimeoutBlock(2);
                        System.out.println("test3");

                    } else if (isBoundedByBtn(3, pointerX, pointerY)) {
                        checkPopTimeoutBlock(3);
                        System.out.println("test4");
                    }
                //}
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

        // standard sizes for player screens
        float widthMain = (float)(width * 0.6);
        float heightMain = (float)(height * 0.58);
        float widthPlayers = (float)(width * 0.32);
        float heightPlayers = (float) (height * 0.21);
        float widthMainBlock = (float)(width * 0.25);

        double heightScalePlayers = heightScaleBot + heightScaleTop;

        sb.begin();
        sb.draw(background, 0, 0, width, height);
        //sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(btn0, (float)(width * widthScale),              (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(btn1, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(btn2, (float)(width * widthScale),              (float)(height * heightScaleBot), widthBtn, heightBtn);
        sb.draw(btn3, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleBot), widthBtn, heightBtn);

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

        // draw blocktower
        List<Integer> bt = blockTower.getCopyOfCurrentList();
        if(blockTower.getCurrentHeight() <= 3) {
            for (int j = 0; j < 4-blockTower.getCurrentHeight(); j++) {
                bt.add(4); // when the number 4 is read, and invisible block will render instead
            }
        }

        sb.draw(getBlockTowerImage(bt.get(0)), (float)(widthMain/2 - widthMainBlock * 0.42), (float)(height/2.7), widthMainBlock,
                widthMainBlock);
        sb.draw(getBlockTowerImage(bt.get(1)), (float)(widthMain/2 - widthMainBlock * 0.42), (float)(height/2.7 + width * 0.25), widthMainBlock,
                widthMainBlock);
        sb.draw(getBlockTowerImage(bt.get(2)), (float)(widthMain/2 - widthMainBlock * 0.42), (float)(height/2.7 + width * 0.5), widthMainBlock,
                widthMainBlock);
        sb.draw(getBlockTowerImage(bt.get(3)), (float)(widthMain/2 - widthMainBlock * 0.42), (float)(height/2.7 + width * 0.75), widthMainBlock,
                widthMainBlock);

        sb.end();
    }

    @Override
    public void dispose() {
        btn0.dispose();
        btn1.dispose();
        btn2.dispose();
        btn3.dispose();
        errorBlock.dispose();
        mainView.dispose();
        secondView.dispose();
        thirdView.dispose();
        fourthView.dispose();
        invisibleBlock.dispose();
    }

    private void checkPopTimeoutBlock(int blockNumber) {
        if (blockTower.checkNextBlock(blockNumber)) { // sjekke om de matcher med ønsket knapp
            blockTower.popNextBlock();
            //sender nyeste liste til databasen!!!!!
        } else {
            timeoutTime = System.currentTimeMillis() + timeoutDuration;
        }
    }

    private Texture getBlockTowerImage(int blockNumber) {
        if(blockNumber == 0) {
            return btn0;
        }
        else if (blockNumber == 1){
            return btn1;
        }
        else if (blockNumber == 2){
            return btn2;
        }
        else if (blockNumber == 3){
            return btn3;
        }
        // uses invisible block image when there is less than 4 blocks left of the tower
        else if (blockNumber == 4) {
            return invisibleBlock;
        }
        else {
            return errorBlock;
        }
    }

    private Boolean isBoundedByBtn(int btnNumber, int x, int y) {
        //NB: sb.draw og gdx.input.getY/getX har forskjellig default grid.
        // Origo til sb.draw er nederst til venstre, mens origo til gdx.input er øverst til venstre.

        if(btnNumber == 0) {
            return (x >= width*widthScale &&
                    x <= width*widthScale+widthBtn &&
                    y >= height-height*heightScaleTop-heightBtn &&
                    y <= height-height*heightScaleTop);
        }
        else if (btnNumber == 1) {
            return (x >= width*2*widthScale+widthBtn &&
                    x <= width*widthScale+widthBtn*2 &&
                    y >= height-height*heightScaleTop-heightBtn &&
                    y <= height-height*heightScaleTop);
        }
        else if (btnNumber == 2) {
            return (x >= width*widthScale &&
                    x <= width*widthScale+widthBtn &&
                    y >= height-height*heightScaleBot-heightBtn &&
                    y <= height-height*heightScaleBot);
        }
        else {
            return (x >= width*2*widthScale+widthBtn &&
                    x <= width*widthScale+widthBtn*2 &&
                    y >= height-height*heightScaleBot-heightBtn &&
                    y <= height-height*heightScaleBot);
        }
    }
}
