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
import com.badlogic.gdx.math.Rectangle;

public class HostGameScreen extends Screen{

        private float width = Gdx.graphics.getWidth();
        private float height = Gdx.graphics.getHeight();
        private float scaleWidth = (float)(Gdx.graphics.getWidth() * 0.6);
        private float scaleHeight = (float)(scaleWidth * 0.3);
        private float scaleLogo = (float)(scaleWidth * 0.9);
        private float scaleButton = (float)(Gdx.graphics.getWidth() * 0.08);

        private Texture hostGameButton;
        private Texture background;
        private Texture logo;
        private Texture backButton;
        private String nameValue = "Enter nickname";
        private String errorMessageValue = "";
        private char focus;
        private BitmapFont name;
        private BitmapFont hostGameText;
        private BitmapFont enterNicknameText;
        private BitmapFont errorMessage;
        private Texture nicknameField;

        private Rectangle boundsNicknameField;
        private Rectangle boundsHostGameButton;
        private Rectangle boundsBackButton;

        public HostGameScreen(GameScreenManager gsm) {
            super(gsm);
            background = new Texture("background.png");
            logo = new Texture("bottomsUpLogo.png");
            backButton = new Texture("backButton.png");
            hostGameButton = new Texture("button.png");
            nicknameField = new Texture("button.png");

            boundsNicknameField = new Rectangle((float) (Gdx.graphics.getWidth() * 0.2),
                    (float) (Gdx.graphics.getHeight() * 0.6) - scaleHeight,
                    scaleWidth,
                    scaleHeight);
            boundsHostGameButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.2),
                    (float) (Gdx.graphics.getHeight() * 0.75) - scaleHeight,
                    scaleWidth,
                    scaleHeight);
            boundsBackButton = new Rectangle((float) (Gdx.graphics.getWidth() * 0.05),
                    (float) (Gdx.graphics.getHeight() * 0.1) - scaleButton,
                    scaleButton,
                    scaleButton);
            Gdx.input.setOnscreenKeyboardVisible(false);
            setNameField(nameValue, (int)height*1/30, new Color(0x7999B6ff));
            FreeTypeFontGenerator.setMaxTextureSize(2048);
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
            parameter.size = (int)height*1/24;
            parameter.color = new Color(0x022444ff);
            hostGameText = generator.generateFont(parameter);
            enterNicknameText = generator.generateFont(parameter);
            parameter.color = new Color(0xe21617ff);
            parameter.size = (int)height*1/33;
            errorMessage = generator.generateFont(parameter);
            generator.dispose();

            Gdx.input.setInputProcessor(new InputAdapter() {
                @Override
                public boolean touchDown(int x, int y, int pointer, int button) {
                    if ((boundsNicknameField.contains(x, y))) {
                        focus = 'b';
                        if (nameValue.equals("Enter nickname")) {
                            setNameField("", (int)height*1/24, new Color(0x022444ff));
                        }
                        Gdx.input.setOnscreenKeyboardVisible(true);
                    } else {
                        Gdx.input.setOnscreenKeyboardVisible(false);
                        errorMessageValue = "";
                        if (nameValue.isEmpty()) {
                            setNameField("Enter nickname", (int)height*1/30, new Color(0x7999B6ff));
                        }
                    }
                    return true;
                }

                @Override
                public boolean keyDown(int keycode) {
                    if (focus == 'a') {
                        switch (keycode) {

                            case Input.Keys.ENTER:
                                Gdx.input.setOnscreenKeyboardVisible(false);
                                errorMessageValue = "";
                                if (nameValue.isEmpty()) {
                                    setNameField("Enter nickname", (int)height*1/30, new Color(0x7999B6ff));
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
                                errorMessageValue = "";
                                if (nameValue.isEmpty()) {
                                    setNameField("Enter nickname", (int)height*1/30, new Color(0x7999B6ff));
                                }
                                break;

                        }
                        return true;
                    }
                    return true;
                }
            });
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
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
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
                if (boundsHostGameButton.contains(x, y)) {
                    if(nameValue.equals("Enter nickname")) {
                        errorMessageValue = "Enter a nickname";
                        return;
                    }
                    String code = FBIF.hostLobby(nameValue, "4");
                    gsm.set(new HostLobbyScreen(gsm, code));
                    Gdx.input.setInputProcessor(null);
                    dispose();
                }
                else if (boundsBackButton.contains(x, y)) {
                    gsm.set(new MainMenuScreen(gsm));
                    Gdx.input.setInputProcessor(null);
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
            sb.draw(nicknameField, (float)(width * 0.2), (float)(height * 0.4), scaleWidth, scaleHeight);
            sb.draw(hostGameButton, (float)(width * 0.2), (float)(height * 0.25), scaleWidth, scaleHeight);
            sb.draw(backButton, (float)(width * 0.05), (float)(height * 0.9), scaleButton, scaleButton);
            sb.draw(logo, (float)(width * 0.2), (float)(height * 0.55), scaleWidth, scaleLogo);
            name.draw(sb, nameValue, (float)(width * 0.24), (float)(height * 0.46));
            hostGameText.draw(sb, "Host game", (float)(width * 0.275), (float)(height * 0.315));
            errorMessage.draw(sb, errorMessageValue, (float)(width * 0.23), (float)(height * 0.39));
            sb.end();
        }

        @Override
        public void dispose() {
            background.dispose();
            nicknameField.dispose();
            hostGameButton.dispose();
            backButton.dispose();
            logo.dispose();
            name.dispose();
            hostGameText.dispose();
            enterNicknameText.dispose();
        }
}
