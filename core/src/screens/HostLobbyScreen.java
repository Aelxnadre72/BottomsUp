package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

// LobbyScreen for the host, not the players
public class HostLobbyScreen extends Screen {
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    String gameCode = "123";

    float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.4);
    float scaleHeight = (float)(scaleWidth * 0.3);
    float scaleLogo = (float)(scaleWidth * 0.8);
    float scaleExit = (float)(Gdx.graphics.getWidth() * 0.1);
    private Texture backgroundUpper;
    private Texture backgroundLower;
    private Texture exit;
    private Rectangle boundsExitField;
    private Texture logo;
    private Texture startGame;
    private Rectangle boundStartGameButton;
    private BitmapFont gamePinCode;

    public HostLobbyScreen(GameScreenManager gsm) {
        super(gsm);
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("darkerBackground.png");
        exit = new Texture("Exit.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.03),
                (float)(height * 0.06) - scaleExit,
                scaleExit,
                scaleExit);
        logo = new Texture("bottomsUpLogoNoText.png");
        startGame = new Texture("button.png");
        boundStartGameButton = new Rectangle(
                (float)(width * 0.2),
                (float)(height * 0.42) - (float)(height * 0.1),
                (float)(width * 0.6),
                (float)(height * 0.1));
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
            else if(boundStartGameButton.contains(x,y)){
                //Add new game screen
                gsm.set(new LobbyScreen(gsm));
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
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.5));
        sb.draw(exit, (float)(width * 0.03), (float)(height * 0.94), scaleExit, scaleExit);
        sb.draw(logo, (float)(width * 0.3), (float)(height * 0.84), scaleWidth, scaleLogo);
        gamePinCode.draw(sb,
                gameCode,
                (float)(width * 0.3),
                (float)(height * 0.76));
        sb.draw(startGame,
                (float)(width * 0.2),
                (float)(height * 0.58),
                (float)(width * 0.6),
                (float)(height * 0.1));
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

