package screens;

import static com.mygdx.bottomsup.BottomsUp.FBIF;

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

import java.util.ArrayList;
import java.util.List;

public class JoinGameScreen extends Screen {
    private float width = Gdx.graphics.getWidth();
    private float height = Gdx.graphics.getHeight();
    private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
    private float scaleHeight = (float)(scaleWidth * 0.3);
    private float scaleLogo = (float)(scaleWidth * 0.9);
    private float scaleButton = (float)(Gdx.graphics.getWidth() * 0.08);


    private Texture enterButton;
    private Texture background;
    private Texture logo;
    private Texture backButton;
    private String codeValue = "Enter game pin";

    private String nameValue = "Enter nickname";
    private BitmapFont code;
    private char focus;

    private BitmapFont name;
    private BitmapFont enterGameText;
    private BitmapFont enterNicknameText;
    private Texture joinField;

    private Texture nicknameField;
    private Rectangle boundsJoinField;

    private Rectangle boundsNicknameField;
    private Rectangle boundsEnterButton;
    private Rectangle boundsBackButton;

    private float keyboardAdjustment = 0;

    public JoinGameScreen(GameScreenManager gsm) {
        super(gsm);
        enterButton = new Texture("button.png");
        background = new Texture("background.png");
        logo = new Texture("bottomsUpLogo.png");
        backButton = new Texture("backButton.png");
        joinField = new Texture("button.png");
        nicknameField = new Texture("button.png");
        boundsJoinField = new Rectangle((float) (Gdx.graphics.getWidth() * 0.2),
                (float) (Gdx.graphics.getHeight() * 0.6) - scaleHeight,
                scaleWidth,
                scaleHeight);
        boundsNicknameField = new Rectangle((float) (Gdx.graphics.getWidth() * 0.2),
                (float) (Gdx.graphics.getHeight() * 0.7) - scaleHeight,
                scaleWidth,
                scaleHeight);
        boundsEnterButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.2),
                (float) (Gdx.graphics.getHeight() * 0.85) - scaleHeight,
                scaleWidth,
                scaleHeight);
        boundsBackButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.05),
                (float) (Gdx.graphics.getHeight() * 0.1) - scaleButton,
                scaleButton,
                scaleButton);
        Gdx.input.setOnscreenKeyboardVisible(false);
        setPinField(codeValue, 60, new Color(0x7999B6ff));
        setNameField(nameValue, 60, new Color(0x7999B6ff));
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 80;
        parameter.color = new Color(0x022444ff);
        enterGameText = generator.generateFont(parameter);
        enterNicknameText = generator.generateFont(parameter);
        generator.dispose();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                if (boundsJoinField.contains(x, y)) {
                    focus = 'a';
                    if (codeValue.equals("Enter game pin")) {
                        setPinField("", 80, new Color(0x022444ff));
                    }
                    Gdx.input.setOnscreenKeyboardVisible(true);
                    keyboardAdjustment = (float) (height * 0.15);
                } else if ((boundsNicknameField.contains(x, y))) {
                    focus = 'b';
                    if (nameValue.equals("Enter nickname")) {
                        setNameField("", 80, new Color(0x022444ff));
                    }
                    Gdx.input.setOnscreenKeyboardVisible(true);
                    keyboardAdjustment = (float) (height * 0.15);
                } else {
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    keyboardAdjustment = 0;
                    if (codeValue.isEmpty()) {
                        setPinField("Enter game pin", 60, new Color(0x7999B6ff));
                    }
                    if (nameValue.isEmpty()) {
                        setNameField("Enter nickname", 60, new Color(0x7999B6ff));
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (focus == 'a') {
                    switch (keycode) {
                        case Input.Keys.NUM_0:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "0");
                            }
                            break;

                        case Input.Keys.NUM_1:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "1");
                            }
                            break;

                        case Input.Keys.NUM_2:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "2");
                            }
                            break;

                        case Input.Keys.NUM_3:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "3");
                            }
                            break;

                        case Input.Keys.NUM_4:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "4");
                            }
                            break;

                        case Input.Keys.NUM_5:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "5");
                            }
                            break;

                        case Input.Keys.NUM_6:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "6");
                            }
                            break;

                        case Input.Keys.NUM_7:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "7");
                            }
                            break;

                        case Input.Keys.NUM_8:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "8");
                            }
                            break;

                        case Input.Keys.NUM_9:
                            if (getCodeValue().length() < 4) {
                                setCodeValue(getCodeValue() + "9");
                            }
                            break;

                        case Input.Keys.DEL:
                            if (getCodeValue().length() > 0) {
                                setCodeValue(getCodeValue().substring(0, getCodeValue().length() - 1));
                            }
                            break;


                        case Input.Keys.ENTER:
                            Gdx.input.setOnscreenKeyboardVisible(false);
                            keyboardAdjustment = 0;
                            if (codeValue.isEmpty()) {
                                setPinField("Enter game pin", 60, new Color(0x7999B6ff));
                            }
                            break;
                    }
                } else {
                    switch (keycode) {
                        case Input.Keys.A:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "A");
                            }
                            break;

                        case Input.Keys.B:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "B");
                            }
                            break;

                        case Input.Keys.C:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "C");
                            }
                            break;


                        case Input.Keys.D:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "D");
                            }
                            break;

                        case Input.Keys.E:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "E");
                            }
                            break;

                        case Input.Keys.F:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "F");
                            }
                            break;

                        case Input.Keys.G:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "G");
                            }
                            break;


                        case Input.Keys.H:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "H");
                            }
                            break;


                        case Input.Keys.I:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "I");
                            }
                            break;


                        case Input.Keys.J:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "J");
                            }
                            break;


                        case Input.Keys.K:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "K");
                            }
                            break;


                        case Input.Keys.L:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "L");
                            }
                            break;


                        case Input.Keys.M:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "M");
                            }
                            break;

                        case Input.Keys.N:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "N");
                            }
                            break;

                        case Input.Keys.O:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "O");
                            }
                            break;

                        case Input.Keys.P:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "P");
                            }
                            break;

                        case Input.Keys.Q:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "Q");
                            }
                            break;

                        case Input.Keys.R:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "R");
                            }
                            break;

                        case Input.Keys.S:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "S");
                            }
                            break;

                        case Input.Keys.T:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "T");
                            }
                            break;

                        case Input.Keys.U:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "U");
                            }
                            break;

                        case Input.Keys.V:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "V");
                            }
                            break;

                        case Input.Keys.W:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "W");
                            }
                            break;

                        case Input.Keys.X:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "X");
                            }
                            break;

                        case Input.Keys.Y:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "Y");
                            }
                            break;

                        case Input.Keys.Z:
                            if (getNameValue().length() < 7) {
                                setNameValue(getNameValue() + "Z");
                            }
                            break;

                        case Input.Keys.DEL:
                            if (getNameValue().length() > 0) {
                                setNameValue(getNameValue().substring(0, getNameValue().length() - 1));
                            }
                            break;


                        case Input.Keys.ENTER:
                            Gdx.input.setOnscreenKeyboardVisible(false);
                            keyboardAdjustment = 0;
                            if (nameValue.isEmpty()) {
                                setNameField("Enter nickname", 60, new Color(0x7999B6ff));
                            }
                            break;

                    }
                    return true;
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

    public void setNameValue(String s) {
        nameValue = s;
    }
    public String getNameValue() {
        return nameValue;
    }

    public void setNameField(String hint, int size, Color color) {
        setNameValue(hint);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = color;
        name = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();
            if (boundsEnterButton.contains(x, y)) {
                List<String> playersFromDatabase = FBIF.updatePlayerList(codeValue);
                if(playersFromDatabase.contains(nameValue)) {
                    // add text "the player name is taken"
                    System.out.println("The player name is taken");
                    return;
                }
                String success = FBIF.joinLobby(codeValue, nameValue, "4");
                System.out.println(success);
                if (success.equals("unavailable")) {
                    // add text "The lobby is expired/not available
                    System.out.println("The lobby is unavailable");
                    return;
                }
                else if (success.equals("full") || nameValue.isEmpty()) {
                    // add text "The lobby is full"
                    System.out.println("The lobby is full");
                    return;
                } else {
                    gsm.set(new LobbyScreen(gsm, codeValue, nameValue));
                    dispose();
                }
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
        sb.draw(joinField, (float)(width * 0.2), (float)(height * 0.4 + keyboardAdjustment), scaleWidth, scaleHeight);
        sb.draw(nicknameField, (float)(width * 0.2), (float)(height * 0.3 + keyboardAdjustment), scaleWidth, scaleHeight);
        sb.draw(enterButton, (float)(width * 0.2), (float)(height * 0.15), scaleWidth, scaleHeight);
        sb.draw(backButton, (float)(width * 0.05), (float)(height * 0.9), scaleButton, scaleButton);
        sb.draw(logo, (float)(width * 0.2), (float)(height * 0.55 + keyboardAdjustment), scaleWidth, scaleLogo);
        code.draw(sb, codeValue, (float)(width * 0.275), (float)(height * 0.46 + keyboardAdjustment));
        name.draw(sb, nameValue, (float)(width * 0.275), (float)(height * 0.36 + keyboardAdjustment));
        enterGameText.draw(sb, "Enter game", (float)(width * 0.28), (float)(height * 0.21));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        joinField.dispose();
        nicknameField.dispose();
        enterButton.dispose();
        backButton.dispose();
        logo.dispose();
        code.dispose();
        name.dispose();
        enterGameText.dispose();
        enterNicknameText.dispose();
    }
}