package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class JoinGameScreen extends Screen {
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();
    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private Texture enterButton;
    private Texture background;
    private Texture logo;
    private String codeValue = "ENTER PIN";
    private BitmapFont code;
    private Texture joinField;
    private Rectangle boundsJoinField;
    private Rectangle boundsEnterButton;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("buttonJoinGame.png");
        background = new Texture("background.png");
        logo = new Texture("bottomsUpLogo.png");
        code = new BitmapFont();
        code.getData().setScale(3, 3);
        code.setColor(0, 0, 0, 1);
        joinField = new Texture("button.png");
        boundsJoinField = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                        (float)(Gdx.graphics.getHeight() * 0.5) - scaleHeight,
                                         scaleWidth,
                                         scaleHeight);
        boundsEnterButton = new Rectangle((float)(Gdx.graphics.getWidth() * 0.2),
                                       (float)(Gdx.graphics.getHeight() * 0.25) - scaleHeight,
                                          scaleWidth,
                                          scaleHeight);
        Gdx.input.setOnscreenKeyboardVisible(false);

        Gdx.input.setInputProcessor(new InputAdapter () {
            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                if (boundsJoinField.contains(x, y)) {
                    Gdx.input.setOnscreenKeyboardVisible(true);
                } else {
                    Gdx.input.setOnscreenKeyboardVisible(false);
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
    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsEnterButton.contains(x, y) && getCodeValue().equals("123")) {
                gsm.set(new LobbyScreen(gsm));
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
        sb.draw(joinField, (float)(width * 0.2), (float)(height * 0.5), scaleWidth, scaleHeight);
        sb.draw(enterButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleHeight);
        code.draw(sb, codeValue, (float)(width * 0.215), (float)(height * 0.565));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        joinField.dispose();
        enterButton.dispose();
        code.dispose();
    }
}