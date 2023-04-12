package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;

public class JoinGameScreen extends Screen {
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();
    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private float scaleLogo = (float)(scaleWidth * 0.9);
    private float scaleButton = (float)(Gdx.graphics.getWidth() * 0.1);

    private Texture enterButton;
    private Texture background;
    private Texture logo;
    private Texture backButton;
    private String codeValue = "Enter game pin";
    private BitmapFont code;
    private Texture joinField;
    private Rectangle boundsJoinField;
    private Rectangle boundsEnterButton;
    private Rectangle boundsBackButton;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("buttonJoinGame.png");
        background = new Texture("background.png");
        logo = new Texture("bottomsUpLogo.png");
        backButton = new Texture("backButton.png");
        joinField = new Texture("button.png");
        boundsJoinField = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                        (float)(Gdx.graphics.getHeight() * 0.6) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
        boundsEnterButton = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                       (float)(Gdx.graphics.getHeight() * 0.75) - scaleHeight,
                                          scaleWidth,
                                          scaleHeight);
        boundsBackButton = new Rectangle((float)(Gdx.graphics.getWidth() * 0.05),
                                       (float)(Gdx.graphics.getHeight() * 0.1) - scaleButton,
                                          scaleButton,
                                          scaleButton);
        Gdx.input.setOnscreenKeyboardVisible(false);
        setPinField(codeValue, 60, new Color(0x7999B6ff));

        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                if (boundsJoinField.contains(x, y)) {
                    if (codeValue.equals("Enter game pin")) {
                        setPinField("", 80, new Color(0x022444ff));
                    }
                    Gdx.input.setOnscreenKeyboardVisible(true);
                } else {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    if (codeValue.isEmpty()) {
                        setPinField("Enter game pin", 60, new Color(0x7999B6ff));
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown (int keycode) {
                switch (keycode)
                {
                    case Input.Keys.NUM_0:
                        setCodeValue(getCodeValue() + "0");
                        break;

                    case Input.Keys.NUM_1:
                        setCodeValue(getCodeValue() + "1");
                        break;

                    case Input.Keys.NUM_2:
                        setCodeValue(getCodeValue() + "2");
                        break;

                    case Input.Keys.NUM_3:
                        setCodeValue(getCodeValue() + "3");
                        break;

                    case Input.Keys.NUM_4:
                        setCodeValue(getCodeValue() + "4");
                        break;

                    case Input.Keys.NUM_5:
                        setCodeValue(getCodeValue() + "5");
                        break;

                    case Input.Keys.NUM_6:
                        setCodeValue(getCodeValue() + "6");
                        break;

                    case Input.Keys.NUM_7:
                        setCodeValue(getCodeValue() + "7");
                        break;

                    case Input.Keys.NUM_8:
                        setCodeValue(getCodeValue() + "8");
                        break;

                    case Input.Keys.NUM_9:
                        setCodeValue(getCodeValue() + "9");
                        break;

                    case Input.Keys.BACKSPACE:
                        if (getCodeValue().length() > 0) {
                            setCodeValue(getCodeValue().substring(0, getCodeValue().length() - 1));
                        }
                        break;

                    case Input.Keys.ENTER:
                        Gdx.input.setOnscreenKeyboardVisible(false);
                        if (codeValue.isEmpty()) {
                            setPinField("Enter game pin", 60, new Color(0x7999B6ff));
                        }
                        break;

                }
                return true;
            }
        });
    }

    public void setCodeValue(String s) {
        codeValue = s;
    }
    public String getCodeValue() {
        return codeValue;
    }

    public void setPinField(String hint, int size, Color color) {
        setCodeValue(hint);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        code = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsEnterButton.contains(x, y) && getCodeValue().equals("123")) {
                //Add server logic to find lobby
                gsm.set(new LobbyScreen(gsm));
                dispose();
            }
            else if (boundsBackButton.contains(x, y)) {
                gsm.set(new MainMenuScreen(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update() {
        handleInput();
    }


    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(joinField, (float)(width * 0.2), (float)(height * 0.4), scaleWidth, scaleHeight);
        sb.draw(enterButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleHeight);
        sb.draw(backButton, (float)(width * 0.05), (float)(height * 0.9), scaleButton, scaleButton);
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.6), scaleWidth, scaleLogo);
        code.draw(sb, codeValue, (float)(width * 0.275), (float)(height * 0.47));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        joinField.dispose();
        enterButton.dispose();
        backButton.dispose();
        logo.dispose();
        code.dispose();
    }
}