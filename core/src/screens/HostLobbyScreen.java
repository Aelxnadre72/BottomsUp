package screens;

import static com.mygdx.bottomsup.BottomsUp.FBIF;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

// LobbyScreen for the host, not the players
public class HostLobbyScreen extends Screen {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    String lobbyCode;
    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.4);
    float scaleWidthImage = (float)(Gdx.graphics.getWidth() * 0.23);

    float scaleHeight = (float)(scaleWidth * 0.3);
    float scaleLogo = (float)(scaleWidth * 0.8);
    float scaleImage = (float)(scaleWidthImage * 14/10);

    float scaleExit = (float)(Gdx.graphics.getWidth() * 0.1);
    private Texture backgroundUpper;
    private Texture backgroundLower;
    private Texture exit;
    private Rectangle boundsExitField;
    private Texture logo;
    private Texture startGame;
    private Rectangle boundStartGameButton;
    private BitmapFont gamePinCodeText;
    private BitmapFont gamePinCode;
    private BitmapFont startGameText;
    private BitmapFont playersJoinedText;

    private BitmapFont player1Text;
    private BitmapFont player2Text;
    private BitmapFont player3Text;
    private BitmapFont player4Text;
    private String playerId = "1";

    private List<String> players;
    private List<Texture> playerImages;

    public HostLobbyScreen(GameScreenManager gsm, String lobbyCode) {
            super(gsm);
        this.lobbyCode = lobbyCode;
        players = Arrays.asList("", "", "", "");
        playerImages = Arrays.asList(new Texture("invisibleBlock.png"),
                                     new Texture("invisibleBlock.png"),
                                     new Texture("invisibleBlock.png"),
                                     new Texture("invisibleBlock.png"));
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("background2.png");
        exit = new Texture("cancelButton.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.05),
                (float)(height * 0.08) - scaleExit,
                scaleExit,
                scaleExit);
        logo = new Texture("bottomsUpLogoNoText.png");
        startGame = new Texture("button.png");
        boundStartGameButton = new Rectangle(
                (float)(width * 0.2),
                (float)(height * 0.47) - (float)(height * 0.1),
                (float)(width * 0.6),
                (float)(height * 0.1));

        Gdx.input.setOnscreenKeyboardVisible(false);
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)height*1/24;
        parameter.color = new Color(0x022444ff);
        gamePinCodeText = generator.generateFont(parameter);
        startGameText = generator.generateFont(parameter);
        parameter.color = new Color(0xffffffff);
        playersJoinedText = generator.generateFont(parameter);
        player1Text = generator.generateFont(parameter);
        player2Text = generator.generateFont(parameter);
        player3Text = generator.generateFont(parameter);
        player4Text = generator.generateFont(parameter);
        parameter.size = (int)height*1/12;
        parameter.color = new Color(0x022444ff);
        gamePinCode = generator.generateFont(parameter);
        generator.dispose();
    }

    public void setFont(BitmapFont textHolder, int size, int color) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = new Color(color);
        textHolder = generator.generateFont(parameter);
        generator.dispose();
    }

    public void getPlayers() {
        List<String> playersFromDatabase = FBIF.updatePlayerList(lobbyCode);
        for(int i = 0; i < playersFromDatabase.size(); i++) {
            players.set(i, playersFromDatabase.get(i));
            String path = "" + "p" + String.valueOf(i+1) + ".png";
            playerImages.set(i, new Texture(path));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()){
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsExitField.contains(x, y)) {
                gsm.set(new MainMenuScreen(gsm));
                dispose();
            }
            else if(boundStartGameButton.contains(x,y)){
                //Add new game screen
                FBIF.hostStartGame(lobbyCode);
                // the other players has a 1 second delay on the start signal from the database
                try {
                    TimeUnit.MILLISECONDS.sleep(1400);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                gsm.set(new GameScreen(gsm, playerId, lobbyCode));
                dispose();
            }
        }
    }

    @Override
    public void update() {
        handleInput();
        getPlayers();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.5));
        sb.draw(exit, (float)(width * 0.05), (float)(height * 0.92), scaleExit, scaleExit);
        sb.draw(logo, (float)(width * 0.3), (float)(height * 0.81), scaleWidth, scaleLogo);
        sb.draw(playerImages.get(0), (float)(width * 0.1), (float)(height * 0.25), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(1), (float)(width * 0.1), (float)(height * 0.15), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(2), (float)(width * 0.1), (float)(height * 0.05), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(3), (float)(width * 0.1), (float)(height * 0.05 - height * 0.1), scaleWidthImage, scaleImage);
        gamePinCodeText.draw(sb,
                "Game pin",
                (float)(width * 0.28),
                (float)(height * 0.79));
        gamePinCode.draw(sb,
                lobbyCode,
                (float)(width * 0.3),
                (float)(height * 0.73));
        sb.draw(startGame,
                (float)(width * 0.18),
                (float)(height * 0.53),
                (float)(width * 0.64),
                (float)(height * 0.1));
        startGameText.draw(sb,
                "Start game",
                (float)(width * 0.27),
                (float)(height * 0.6));
        playersJoinedText.draw(sb,
                "Players joined",
                (float)(width * 0.20),
                (float)(height * 0.47));
        player1Text.draw(sb, players.get(0), (float)(width * 0.43), (float)(height * 0.35));
        player2Text.draw(sb, players.get(1), (float)(width * 0.43), (float)(height * 0.25));
        player3Text.draw(sb, players.get(2), (float)(width * 0.43), (float)(height * 0.15));
        player4Text.draw(sb, players.get(3), (float)(width * 0.43), (float)(height * 0.05));
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundUpper.dispose();
        backgroundLower.dispose();
        exit.dispose();
        logo.dispose();
        startGame.dispose();
    }
}

