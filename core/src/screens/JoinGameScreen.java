package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class JoinGameScreen extends Screen {

    private Texture enterButton;
    private Texture background;
    private Texture logo;

    private Texture textfield;

    private final TextField pin_input;
    private TextInputListener listener;

    private TextField.TextFieldListener txtListener;
    private Skin uiskin;
    private Stage stage;
    private Table table;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("buttonJoinGame.png");
        background = new Texture("background.png");
        logo = new Texture("bottomsUpLogo.png");

        uiskin = new Skin(Gdx.files.internal("uiskin.json"));

        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        stage.addActor(table);

        pin_input = new TextField("", uiskin);
        pin_input.setSize(300, 50);
        pin_input.setPosition((300), 500);

        listener = new TextInputListener();
        pin_input.setTextFieldListener(txtListener);
        table.add(pin_input).width(400).fill();
        stage.addActor(pin_input);
    }

    public void keyTyped(TextField textField, char key) {
        if (key == '\n') {
            textField.getOnscreenKeyboard().show(false);
        }
    }

    @Override
    public void handleInput() {
        keyTyped(pin_input, 'h');
    }

    @Override
    public void update() {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        float sizeButton = (float) ((float) Gdx.graphics.getWidth() * 0.6);
        float sizeLogo = (float) ((float) Gdx.graphics.getWidth() * 0.6);
        float sizeLogo2 = (float) (sizeLogo * 0.75);

        Gdx.input.getTextInput(listener, "", "Enter game pin", "Hint Value");
        float delta = Gdx.graphics.getDeltaTime();
        stage.act(delta);
        stage.draw();

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setOnscreenKeyboardVisible(true);

        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}