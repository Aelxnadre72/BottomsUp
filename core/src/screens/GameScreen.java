package screens;

import static com.mygdx.bottomsup.BottomsUp.FBIF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

import components.BlockTower;

public class GameScreen extends Screen {
    private Texture background;
    private Texture background2;
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
    private Texture boundsBtn0;
    private Texture boundsBtn1;
    private Texture boundsBtn2;
    private Texture boundsBtn3;
    private Texture timeoutSplash;
    private Texture cancelButton;
    private Texture nametag1;
    private Texture nametag2;
    private Texture nametag3;
    private Texture nametag4;
    private Texture screenDivider;
    private BlockTower blockTower;

    private long timeoutTime = 0;

    private long startTime = System.currentTimeMillis();
    private long lastUpdateTime = 0;
    private long finishTime = 0;

    // private long elapsedGameTime;
    // For the leaderboard, in render: elapsedGameTime = System.currentTimeMillis() - startTime;

    private long timeoutDuration = 3000;

    private float width;
    private float height;

    // standard sizes for buttons
    private float widthBtn;
    private float heightBtn;

    // size for the avatar
    private float widthAvatar;
    private float heightAvatar;

    // Scales for width and height for the different buttons and screen
    private double heightScaleTop;
    private double heightScaleBot;
    private double widthScale;

    // standard sizes for player screens
    private float widthMain;
    private float heightMain;
    private float widthPlayers;
    private float heightPlayers;
    private float widthMainBlock;

    private double heightScalePlayers;

    private Rectangle boundsExitField;

    private float scaleExit = (float)(Gdx.graphics.getWidth() * 0.1);
    private String playerId;
    private String lobbyCode;

    private List<List<Integer>> otherPlayers;
    private List<Integer> mainBlockTower;

    private Texture playerImage;

    private long isFinished = 0;
    private boolean gameOver = false;
    private boolean solo = false;

    public GameScreen(GameScreenManager gsm, String playerId, String lobbyCode) {
        super(gsm);
        blockTower = new BlockTower(5);
        this.playerId = playerId;
        this.lobbyCode = lobbyCode;
        mainBlockTower = new ArrayList<>();
        otherPlayers = new ArrayList<>();
        String path = "" + "p" + String.valueOf(playerId) + "InGame.png";
        playerImage = new Texture(path);
        background = new Texture("background.png");
        background2 = new Texture("background2.png");
        nametag1 = new Texture("background2.png");
        nametag2 = new Texture("background2.png");
        nametag3 = new Texture("background2.png");
        nametag4 = new Texture("background2.png");
        screenDivider = new Texture("background2.png");


        btn0 = new Texture("block0.png");
        btn1 = new Texture("block1.png");
        btn2 = new Texture("block2.png");
        btn3 = new Texture("block3.png");
        errorBlock = new Texture("errorBlock.png");
        invisibleBlock = new Texture("invisibleBlock.png");
        timeoutSplash = new Texture("splash.png");
        cancelButton = new Texture("cancelButton.png");

        boundsBtn0 = new Texture("blockButton0.png");
        boundsBtn1 = new Texture("blockButton1.png");
        boundsBtn2 = new Texture("blockButton2.png");
        boundsBtn3 = new Texture("blockButton3.png");

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

        // size for the avatar
        widthAvatar = (float)(width * 0.47);
        heightAvatar = (float)(widthAvatar * 1.4);

        // Scales for width and height for the different buttons and screen
        heightScaleTop = 0.141;
        heightScaleBot = 0.01;
        widthScale = 0.02;

        // standard sizes for player screens
        widthMain = (float)(width * 0.6);
        heightMain = (float) (height * 0.62);
        widthPlayers = (float)(width * 0.38);
        heightPlayers = (float) (height-(heightMain*0.105*3)-(height * 0.27))/3;
        widthMainBlock = (float)(width * 0.25);

        heightScalePlayers = heightScaleBot + heightScaleTop;

        boundsExitField = new Rectangle(
                (float)(width * 0.05),
                (float)(height * 0.08) - scaleExit,
                scaleExit,
                scaleExit);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            int pointerX = Gdx.input.getX(0);
            int pointerY = Gdx.input.getY(0);
            System.out.println("X: " + pointerX);
            System.out.println("Y: " + pointerY);

            if (blockTower.getCurrentHeight() != 0 && timeoutTime < System.currentTimeMillis()) {
                if (isBoundedByBtn(0, pointerX, pointerY)) {
                    checkPopTimeoutBlock(0);

                } else if (isBoundedByBtn(1, pointerX, pointerY)) {
                    checkPopTimeoutBlock(1);

                } else if (isBoundedByBtn(2, pointerX, pointerY)) {
                    checkPopTimeoutBlock(2);

                } else if (isBoundedByBtn(3, pointerX, pointerY)) {
                    checkPopTimeoutBlock(3);
                }

                // if finished
                if(blockTower.getCurrentHeight() == 0) {
                    System.out.println("Finished");
                    finishTime = Math.round((System.currentTimeMillis() - startTime)/1000);
                    isFinished = 1;
                    // send finishTime to players database here:
                    FBIF.setResult(lobbyCode, playerId, String.valueOf(finishTime));
                    if (solo) {
                        gameOver = true;
                    }
                }
            }

            if (boundsExitField.contains(pointerX, pointerY)) {
                gsm.set(new MainMenuScreen(gsm));
                dispose();
            }
        }
    }

    private void getOtherPlayers() {
        otherPlayers = FBIF.updateOthers(lobbyCode, playerId);
        long count = 0;
        for(List<Integer> tower : otherPlayers) {
            if(tower.size() == 0) {
                count += 1;
            }
        }
        if (otherPlayers.size() == 0) {
            solo = true;
        }

        if(count + isFinished == otherPlayers.size() && !solo) {
            gameOver = true;
        }

        // For the other players: replaces the last block images with an invisible block when there is less than 4 blocks left
        for(int i = 0; i < otherPlayers.size(); i++) {
            if (otherPlayers.get(i).size() <= 3) {
                List<Integer> tempTower = otherPlayers.get(i);
                Integer num = 4-otherPlayers.get(i).size();
                for (int j = 0; j < num; j++) {
                    tempTower.add(4); // when the number 4 is read in getBlockTowerImage, and invisible block will render instead
                }
                otherPlayers.set(i, tempTower);
            }
        }
    }
    @Override
    public void update() {
        if(mainBlockTower.isEmpty()) {
            FBIF.updateBlockTower(lobbyCode, playerId, blockTower.getCopyOfCurrentList().subList(0, 4).toString());
        }
        handleInput();
        if(System.currentTimeMillis() > lastUpdateTime + 700) {
            getOtherPlayers();
            lastUpdateTime = System.currentTimeMillis();
            checkGameOver();
        }
    }

    private void checkGameOver(){
        if(gameOver) {
            if(isFinished == 0) {
                int blocksLeft = blockTower.getCurrentHeight();
                String lastPlaceResult = String.valueOf(blocksLeft) + " blocks";
                if (blocksLeft == 1) {
                    lastPlaceResult = String.valueOf(blocksLeft) + " block";
                }
                //send how many blocks left to database
                FBIF.setResult(lobbyCode, playerId, lastPlaceResult);
            }
            //finish time in seconds is already sent to database for those that did not finish last
            gsm.set(new ResultScreen(gsm, lobbyCode));
        }
    }

@Override
    public void render(SpriteBatch sb) {
        if(lastUpdateTime == 0) {
            update();
            lastUpdateTime = System.currentTimeMillis();
        }

        sb.begin();
        sb.draw(background, 0, 0, width, height);
        sb.draw(background2, 0, 0, width, (float)(height * 0.27));
        sb.draw(screenDivider, widthMain, 0, (width-(widthPlayers)-widthMain), height);

        //sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        sb.draw(boundsBtn0, (float)(width * widthScale),              (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(boundsBtn1, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleTop), widthBtn, heightBtn);
        sb.draw(boundsBtn2, (float)(width * widthScale),              (float)(height * heightScaleBot), widthBtn, heightBtn);
        sb.draw(boundsBtn3, (float)(width * 2*widthScale + widthBtn), (float)(height * heightScaleBot), widthBtn, heightBtn);

        sb.draw(cancelButton, (float)(width * 0.05), (float)(height * 0.92), scaleExit, scaleExit);

        // draw blocktower
        mainBlockTower = blockTower.getCopyOfCurrentList();
        // replaces the last block images with an invisible block when there is less than 4 blocks left
        if(blockTower.getCurrentHeight() <= 3) {
            for (int j = 0; j < 4-blockTower.getCurrentHeight(); j++) {
                mainBlockTower.add(4); // when the number 4 is read in getBlockTowerImage, and invisible block will render instead
            }
        }


    // draw the other players towers
        for(int i = 0; i < otherPlayers.size(); i++) {
            drawTower(sb, otherPlayers.get(i), i+1);
            //System.out.println("tower: " + tower.toString());
        }

        if(timeoutTime < System.currentTimeMillis()) {
            drawTower(sb, mainBlockTower, 0);
            // avatar and name tags are drawn in both if and else since it needs to be behind the splat,
            // but in front of the blocks
            sb.draw(playerImage, (widthMain / 3 - widthMainBlock), (float)(height / 3.2),
                    widthAvatar, heightAvatar);
            sb.draw(nametag1, 0, (float)(height * 0.84), widthMain, (float)(heightMain*0.105));
            sb.draw(nametag2, (width-(widthPlayers)), height-(float)(heightMain*0.105), widthPlayers,
                    (float)(heightMain*0.105));
            sb.draw(nametag3, (width-(widthPlayers)), (float)(height-heightMain*0.105*2-heightPlayers), widthPlayers,
                    (float)(heightMain*0.105));
            sb.draw(nametag4, (width-(widthPlayers)), (float)(height-(heightMain*0.105*3)-heightPlayers*2), widthPlayers,
                    (float)(heightMain*0.105));
        }
        else {
            sb.draw(playerImage, (widthMain / 3 - widthMainBlock), (float)(height / 3.2),
                    widthAvatar, heightAvatar);
            sb.draw(nametag1, 0, (float)(height * 0.84), widthMain, (float)(heightMain*0.105));
            sb.draw(nametag2, (width-(widthPlayers)), height-(float)(heightMain*0.105), widthPlayers,
                    (float)(heightMain*0.105));
            sb.draw(nametag3, (width-(widthPlayers)), (float)(height-heightMain*0.105*2-heightPlayers), widthPlayers,
                    (float)(heightMain*0.105));
            sb.draw(nametag4, (width-(widthPlayers)), (float)(height-(heightMain*0.105*3)-heightPlayers*2), widthPlayers,
                    (float)(heightMain*0.105));
            sb.draw(timeoutSplash,0,0, width, height);
            // makes the tower invisible while there is a timeout/splash
            sb.draw(invisibleBlock, (float) (widthMain / 2.85 - widthMainBlock * 0.42), (float) (height / 2.47), widthMainBlock,
                    widthMainBlock);
            sb.draw(invisibleBlock, (float) (widthMain / 2.85 - widthMainBlock * 0.42), (float) (height / 2.47 + width * 0.25), widthMainBlock,
                    widthMainBlock);
            sb.draw(invisibleBlock, (float) (widthMain / 2.85 - widthMainBlock * 0.42), (float) (height / 2.47 + width * 0.5), widthMainBlock,
                    widthMainBlock);
            sb.draw(invisibleBlock, (float) (widthMain / 2.85 - widthMainBlock * 0.42), (float) (height / 2.47 + width * 0.75), widthMainBlock,
                    widthMainBlock);
        }
        sb.end();
    }

    private void drawTower(SpriteBatch sb, List<Integer> bt, int placement) {
        double shiftRight = 2.8;
        double shiftVertically = 0;
        double resize = 1;
        double excessVertically = 0;

        if(placement != 0) {
            shiftRight = 0.93;
            resize = 0.38;
            excessVertically = widthMainBlock * 0.62;
            if(placement == 1) {
                shiftVertically = (height * 0.379);
            }
            else if(placement == 2) {
                shiftVertically = (height * 0.135);

            }
            else {
                shiftVertically = (height * -0.108);

            }
        }
            sb.draw(getBlockTowerImage(bt.get(0)), (float) ( widthMain / shiftRight + widthMainBlock * 0.42), (float) (shiftVertically + height / 2.55), (float) (resize * widthMainBlock),
                    (float) (resize * widthMainBlock));
            sb.draw(getBlockTowerImage(bt.get(1)), (float) (widthMain / shiftRight + widthMainBlock * 0.42), (float) (shiftVertically - excessVertically + height / 2.55 + width * 0.25), (float) (resize * widthMainBlock),
                    (float) (resize * widthMainBlock));
            sb.draw(getBlockTowerImage(bt.get(2)), (float) (widthMain / shiftRight + widthMainBlock * 0.42), (float) (shiftVertically - (excessVertically * 2) + height / 2.55 + width * 0.5), (float) (resize * widthMainBlock),
                    (float) (resize * widthMainBlock));
            sb.draw(getBlockTowerImage(bt.get(3)), (float) (widthMain / shiftRight + widthMainBlock * 0.42), (float) (shiftVertically  - (excessVertically * 3) + height / 2.55 + width * 0.75), (float) (resize * widthMainBlock),
                    (float) (resize * widthMainBlock));
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
        cancelButton.dispose();
    }

    private void checkPopTimeoutBlock(int blockNumber) {
        if (blockTower.checkNextBlock(blockNumber)) { // sjekke om de matcher med ønsket knapp
            blockTower.popNextBlock();
            // update your tower in the database
            if(blockTower.getCurrentHeight() > 3) {
                FBIF.updateBlockTower(lobbyCode, playerId, blockTower.getCopyOfCurrentList().subList(0, 4).toString());
            }
            else {
                FBIF.updateBlockTower(lobbyCode, playerId, blockTower.getCopyOfCurrentList().toString());
            }
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
