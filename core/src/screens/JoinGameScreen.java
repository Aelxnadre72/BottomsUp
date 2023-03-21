package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import org.w3c.dom.Text;

import screens.GameScreenManager;
import screens.TextInputListener;

public class JoinGameScreen extends Screen {

    private Texture enterButton;
    private Texture background;
    private Texture logo;
    private TextField  pin_input;

    private TextInputListener listener;

    private TextField.TextFieldListener txtListener;
    private Skin uiskin;
    private Stage stage;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("buttonJoinGame.png");
        background = new Texture("background.png");
        logo = new Texture("bottomsUpLogo.png");

        uiskin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage(new ScreenViewport());

        pin_input= new TextField("", uiskin);

        pin_input = new TextField("", uiskin);
        pin_input.setSize(300, 50);
        pin_input.setPosition((300), 500);

        listener = new TextInputListener();
        pin_input.setTextFieldListener(txtListener);
        stage.addActor(pin_input);

        Gdx.input.getTextInput(listener, "", "", "Enter game pin");
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setOnscreenKeyboardVisible(false);
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setOnscreenKeyboardVisible(false);
        Gdx.input.getTextInput(listener, "", "", "ENTER PIN");

    }


    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Gdx.input.setOnscreenKeyboardVisible(true);
        }
    }

    @Override
    public void update() {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
        float scaleButton = (float)(scaleWidth * 0.3);
        float delta = Gdx.graphics.getDeltaTime();

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(enterButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleButton);
        stage.act(delta);
        stage.draw();
        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}