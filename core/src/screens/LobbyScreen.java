package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;

// LobbyScreen for the players, not the host
public class LobbyScreen extends Screen {
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

    private BitmapFont gamePinCode;
    private BitmapFont playersJoinedText;

    public LobbyScreen(GameScreenManager gsm) {
        super(gsm);
        backgroundUpper = new Texture("background.png");
        backgroundLower = new Texture("background2.png");
        exit = new Texture("cancelButton.png");
        boundsExitField = new Rectangle(
                (float)(width * 0.03),
                (float)(height * 0.07) - scaleExit,
                scaleExit, scaleExit);
        logo = new Texture("bottomsUpLogoNoText.png");
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter.color = new Color(0x022444ff);
        gamePinCode = generator.generateFont(parameter);
        playersJoinedText = generator.generateFont(parameter);
        generator.dispose();
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
        sb.draw(backgroundLower, 0, 0, width, (float)(height * 0.63));
        sb.draw(exit, (float)(width * 0.03), (float)(height * 0.93), scaleExit, scaleExit);
        sb.draw(logo, (float)(width * 0.3), (float)(height * 0.81), scaleWidth, scaleLogo);
        gamePinCode.draw(sb,
                "Game pin: \n" + gameCode,
                (float)(width * 0.28),
                (float)(height * 0.79));
        playersJoinedText.draw(sb,
                "Players joined:",
                (float)(width * 0.16),
                (float)(height * 0.6));
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
