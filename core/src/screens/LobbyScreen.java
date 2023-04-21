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

// LobbyScreen for the players, not the host
public class LobbyScreen extends Screen {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();

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

    private BitmapFont gamePinCodeText;
    private BitmapFont gamePinCode;
    private BitmapFont playersJoinedText;
    private BitmapFont player1Text;
    private BitmapFont player2Text;
    private BitmapFont player3Text;
    private BitmapFont player4Text;

    private List<String> players;
    private List<Texture> playerImages;
    private String lobbyCode;
    private String playerId = "";
    private String playerName;

    public LobbyScreen(GameScreenManager gsm, String lobbyCode, String playerName) {
        super(gsm);
        this.lobbyCode = lobbyCode;
        this.playerName = playerName;
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
                scaleExit, scaleExit);
        logo = new Texture("bottomsUpLogoNoText.png");
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = (int)height*1/24;
        parameter.color = new Color(0x022444ff);
        gamePinCodeText = generator.generateFont(parameter);
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
        }
    }

    private void checkStartGame() {
        //check if host has pressed start, then start game
        List<String> results = FBIF.getResults(lobbyCode);
        if(results.get(0).equals("0")) {
            gsm.set(new GameScreen(gsm, playerId, lobbyCode));
            dispose();
        }
    }

    @Override
    public void update() {
        handleInput();
        getPlayers();
        checkStartGame();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(playerId.equals("")) {
            List<String> playersFromDatabase = FBIF.updatePlayerList(lobbyCode);
            for(int i = 0; i < playersFromDatabase.size(); i++) {
                if(playersFromDatabase.get(i).equals(playerName)) {
                    playerId = String.valueOf(i+1);
                    System.out.println(playerId);
                }
            }
        }

        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.63));
        sb.draw(exit, (float)(width * 0.05), (float)(height * 0.92), scaleExit, scaleExit);
        sb.draw(logo, (float)(width * 0.3), (float)(height * 0.81), scaleWidth, scaleLogo);
        sb.draw(playerImages.get(0), (float)(width * 0.1), (float)(height * 0.35), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(1), (float)(width * 0.1), (float)(height * 0.25), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(2), (float)(width * 0.1), (float)(height * 0.15), scaleWidthImage, scaleImage);
        sb.draw(playerImages.get(3), (float)(width * 0.1), (float)(height * 0.05), scaleWidthImage, scaleImage);
        gamePinCodeText.draw(sb,
                "Game pin",
                (float)(width * 0.28),
                (float)(height * 0.79));
        gamePinCode.draw(sb,
                lobbyCode,
                (float)(width * 0.3),
                (float)(height * 0.73));
        playersJoinedText.draw(sb,
                "Players joined",
                (float)(width * 0.2),
                (float)(height * 0.58));
        player1Text.draw(sb, players.get(0), (float)(width * 0.43), (float)(height * 0.45));
        player2Text.draw(sb, players.get(1), (float)(width * 0.43), (float)(height * 0.35));
        player3Text.draw(sb, players.get(2), (float)(width * 0.43), (float)(height * 0.25));
        player4Text.draw(sb, players.get(3), (float)(width * 0.43), (float)(height * 0.15));
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundUpper.dispose();
        backgroundLower.dispose();
        exit.dispose();
        logo.dispose();
    }
}
