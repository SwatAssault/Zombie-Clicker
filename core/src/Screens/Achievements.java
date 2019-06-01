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
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import sun.util.cldr.CLDRLocaleDataMetaInfo;

public class Achievements implements Screen { //КЛАСС НУЖЕН ДЛЯ ВИЗУАЛЬНОГО ПРЕДСТАВЛЕНИЯ АЧИВОК (СКРИН)

    final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private Image bgImage;
    private SpriteBatch batch;

    private Button kills10;
    private Button kills100;
    private Button kills500;
    private Button kills1000;
    private Button kills10k;
    private Button kills100k;
    private Button kills500k;
    private Button clicks100;
    private Button clicks500;
    private Button clicks1000;
    private Button clicks10k;
    private Button clicks100k;

    private Skin kills_skin;
    private Skin click_skin;

    private ArrayList<String> name_translated_mass;
    private ArrayList<String> description_translated_mass;

    private String name_for_10_kills_rus;
    private String name_for_100_kills_rus;
    private String name_for_500kills_rus;
    private String name_for_1000_kills_rus;
    private String name_for_10k_kills_rus;
    private String name_for_100k_kills_rus;
    private String name_for_500k_kills_rus;
    private String name_for_100_clicks_rus;
    private String name_for_500_clicks_rus;
    private String name_for_1000_clicks_rus;
    private String name_for_10k_clicks_rus;
    private String name_for_100k_clicks_rus;

    private String desc_for_10_kills_rus;
    private String desc_for_100_kills_rus;
    private String desc_for_500_kills_rus;
    private String desc_for_1000_kills_rus;
    private String desc_for_10k_kills_rus;
    private String desc_for_100k_kills_rus;
    private String desc_for_500k_kills_rus;


    private String name;
    private String description;

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
        kills_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/kills.json");
        click_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/clicks.json");
        bgImage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/achbg.png", Texture.class));

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        fontParameter.color = Color.WHITE;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 20;
        desc_font = fontGenerator.generateFont(fontParameter);

        ///////////РУССКИЙ////////////
        name_for_10_kills_rus = "Смотритель кладбища";
        name_for_100_kills_rus = "Гробовщик";
        name_for_500kills_rus = "Могильщик";
        name_for_1000_kills_rus = "Чумной врач";
        name_for_10k_kills_rus = "Святой отец";
        name_for_100k_kills_rus = "Коронер";
        name_for_500k_kills_rus = "Тот, чье имя принято не называть";
        name_for_100_clicks_rus = "";
        name_for_500_clicks_rus = "";
        name_for_1000_clicks_rus = "";
        name_for_10k_clicks_rus = "";
        name_for_100k_clicks_rus = "";
        desc_for_10_kills_rus = "Вы убили 10 зомби";
        desc_for_100_kills_rus = "Вы убили 100 зомби";
        desc_for_500_kills_rus = "Вы убили 500 зомби";
        desc_for_1000_kills_rus = "Вы убили 1000 зомби";
        desc_for_10k_kills_rus = "Вы убили 10k зомби";
        desc_for_100k_kills_rus = "Вы убили 100k зомби";
        desc_for_500k_kills_rus = "Вы убили 500k зомби";
        ///////////РУССКИЙ////////////

        //В ЗАВИСИМОСТИ КАКОЙ ЯЗЫК ВЫБРАН В НАСТРОЙКАХ ТАКИМИ СТРОКАМИ И ЗАПОЛНЯТЬ ЭТИ МАССИВЫ
        name_translated_mass = new ArrayList<String>(Arrays.asList(name_for_10_kills_rus, name_for_100_kills_rus, name_for_500kills_rus, name_for_1000_kills_rus, name_for_10k_kills_rus, name_for_100k_kills_rus,
                name_for_500k_kills_rus));

        description_translated_mass = new ArrayList<String>(Arrays.asList(desc_for_10_kills_rus, desc_for_100_kills_rus, desc_for_500_kills_rus, desc_for_1000_kills_rus, desc_for_10k_kills_rus,
                desc_for_100k_kills_rus, desc_for_500k_kills_rus));
        //

        name = "";
        description = "";

        kills10 = new Button(kills_skin);
        kills100 = new Button(kills_skin);
        kills500 = new Button(kills_skin);
        kills1000 = new Button(kills_skin);
        kills10k = new Button(kills_skin);
        kills100k = new Button(kills_skin);
        kills500k = new Button(kills_skin);

        clicks100 = new Button(click_skin);
        clicks500 = new Button(click_skin);
        clicks1000 = new Button(click_skin);
        clicks10k = new Button(click_skin);
        clicks100k = new Button(click_skin);

        kills10.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[0]);
        kills100.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[1]);
        kills500.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[2]);
        kills1000.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[3]);
        kills10k.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[4]);
        kills100k.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[5]);
        kills500k.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[6]);
        clicks100.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[7]);
        clicks1000.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[8]);
        clicks500.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[9]);
        clicks10k.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[10]);
        clicks100k.setDisabled(!zombieClicker.getKeepTrackAch().get_achievement_mass()[11]);


        kills10.setPosition(25, 700);
        kills100.setPosition(153, 700);
        kills500.setPosition(282, 700);
        kills1000.setPosition(410, 700);

        kills10k.setPosition(25, 580);
        kills100k.setPosition(153, 580);
        kills500k.setPosition(282, 580);
        clicks100.setPosition(410, 580);

        clicks500.setPosition(25, 460);
        clicks1000.setPosition(153, 460);
        clicks10k.setPosition(282, 460);
        clicks100k.setPosition(410, 460);

        kills10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills10.isDisabled()) {
                    name = name_translated_mass.get(0);
                    description = description_translated_mass.get(0);
                }
            }
        });

        kills100.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills100.isDisabled()) {
                    name = name_translated_mass.get(1);
                    description = description_translated_mass.get(1);
                }
            }
        });

        kills500.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills500.isDisabled()) {
                    name = name_translated_mass.get(2);
                    description = description_translated_mass.get(2);
                }
            }
        });

        kills1000.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills1000.isDisabled()) {
                    name = name_translated_mass.get(3);
                    description = description_translated_mass.get(3);
                }
            }
        });

        kills10k.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills10k.isDisabled()) {
                    name = name_translated_mass.get(4);
                    description = description_translated_mass.get(4);
                }
            }
        });

        kills100k.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills100k.isDisabled()) {
                    name = name_translated_mass.get(5);
                    description = description_translated_mass.get(5);

                }
            }
        });

        kills500k.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!kills500k.isDisabled()) {
                    name = name_translated_mass.get(6);
                    description = description_translated_mass.get(6);

                }
            }
        });


        stage.addActor(bgImage);
        stage.addActor(kills10);
        stage.addActor(kills100);
        stage.addActor(kills500);
        stage.addActor(kills1000);
        stage.addActor(kills10k);
        stage.addActor(kills100k);
        stage.addActor(kills500k);
        stage.addActor(clicks100);
        stage.addActor(clicks500);
        stage.addActor(clicks1000);
        stage.addActor(clicks10k);
        stage.addActor(clicks100k);


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
        zombieClicker.getFontManager().getLayout().setText(bitmapFont, name);
        bitmapFont.draw(batch, name, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 130);
        zombieClicker.getFontManager().getLayout().setText(desc_font, description);
        desc_font.draw(batch, description, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 80);
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
