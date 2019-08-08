package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import Other.AchievementItem;
import sun.util.cldr.CLDRLocaleDataMetaInfo;

public class Achievements implements Screen {

    private ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private Image bgImage;
    private SpriteBatch batch;
    private Button back_btn;

    private Array<AchievementItem> all_achievements_mass;

    private BitmapFont bitmapFont;
    private BitmapFont desc_font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public Achievements(final ZombieClicker zc) {
        zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        zombieClicker.get_assets().load_assets_for_achievements();
        bgImage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/achbg.png", Texture.class));

        back_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class));
        back_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                dispose();
            }
        });
        back_btn.setPosition(5, 850);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Rubik.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        fontParameter.color = Color.WHITE;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 20;
        desc_font = fontGenerator.generateFont(fontParameter);

        all_achievements_mass = new Array<AchievementItem>();




        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.act();
        stage.draw();

        batch.begin();
      //  zombieClicker.getFontManager().getLayout().setText(bitmapFont, name);
     //   bitmapFont.draw(batch, name, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 130);
     //   zombieClicker.getFontManager().getLayout().setText(desc_font, description);
       // desc_font.draw(batch, description, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 80);
        batch.end();

        batch.setProjectionMatrix(camera.combined);
    }

    ////////////GETTERS/////////////

    ////////////GETTERS/////////////

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (bitmapFont != null) bitmapFont.dispose();
        if (desc_font != null) desc_font.dispose();
        if (batch != null) batch.dispose();
        if (stage != null) stage.dispose();
        zombieClicker.get_assets().dispose_assets_for_achiements();
    }
}
