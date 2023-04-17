package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.bottomsup.FireBaseInterface;

// LobbyScreen for the players, not the host
public class LobbyScreen extends Screen {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    String gameCode = "123456";

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.4);
    float scaleHeight = (float)(scaleWidth * 0.3);
    float scaleLogo = (float)(scaleWidth * 0.8);
    float scaleExit = (float)(Gdx.graphics.getWidth() * 0.1);
    private Texture backgroundUpper;
    private Texture backgroundLower;
    private Texture exit;
    private Rectangle boundsExitField;
    private Texture logo;
    private Texture gamePin;
    private Texture playersJoined;
    private BitmapFont gamePinCode;

    private FireBaseInterface FBIF;

    public LobbyScreen(GameScreenManager gsm) {
        super(gsm);
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("DarkerBackground.png");
        exit = new Texture("Exit.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.03),
                (float)(height * 0.06) - scaleExit,
                scaleExit, scaleExit);
        logo = new Texture("bottomsUpLogoNoText.png");
        gamePin = new Texture("GamePin.png");
        playersJoined = new Texture("PlayersJoined.png");
        gamePinCode = new BitmapFont();
        gamePinCode.getData().setScale(3, 3);
        gamePinCode.setColor(0, 0, 0, 1);
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

    @Override
    public void update() {
        handleInput();
    }


    // Getter for the game code
    public String getGameCode(){
        return gameCode;
    }

    // Setter for the game code (unsure if needed)
    public void setGameCode(String gameCode){
        this.gameCode = gameCode;
    }

    // Function which will get the players from the database and add them to the page
    public String getPlayers(){
        return "DUMMY VALUE";
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroundUpper, 0, 0, width, height);
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.55));
        sb.draw(exit, (float)(width * 0.03), (float)(height * 0.94), scaleExit, scaleExit);
        sb.draw(logo, (float)(width * 0.3), (float)(height * 0.825), scaleWidth, scaleLogo);
        sb.draw(gamePin, 0, (float)(height * 0.75), width, scaleHeight);
        sb.draw(playersJoined, 0, (float)(height * 0.55), width, (float)(height * 0.1));
        gamePinCode.draw(sb,
                gameCode,
                (float)(width * 0.3),
                (float)(height * 0.725));
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundUpper.dispose();
        backgroundLower.dispose();
        exit.dispose();
        logo.dispose();
        gamePin.dispose();
        playersJoined.dispose();
    }
}
