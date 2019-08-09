package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

import Numbers.Location;

public class MapScreen implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    private Table table;
    private ScrollPane scrollPane;
    private Image imageBG;
    private Skin back_skin;
    private Skin location_skin;
    private Button back_btn;
    private Button location_btn1;  //на карте они снизу вверх 1-4
    private Button location_btn2;
    private Button location_btn3;
    private Button location_btn4;

    public MapScreen(final ZombieClicker zc) {
        zombieClicker = zc;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        zombieClicker.get_assets().load_assets_for_map();
        imageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/mapbg.png", Texture.class));

        table = new Table();
        table.setWidth(540);
        table.add(imageBG);
        scrollPane = new ScrollPane(table);
        scrollPane.setWidth(540);
        scrollPane.setHeight(960);

        back_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        back_btn = new Button(back_skin);
        back_btn.setPosition(10, 853);
        back_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                zombieClicker.get_assets().load_assets_for_location_1();
               // zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                dispose();
            }
        });

        location_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/location_btn.json");
        location_btn1 = new Button(location_skin);
        location_btn1.setPosition(55, 220);
        location_btn1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_location_1();
                zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(false);
                zombieClicker.getNumerics().setCurrent_num_location(0);
                zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(true);
                zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
                zombieClicker.setMainGame();
                dispose();
            }
        });

        location_btn2 = new Button(location_skin);
        location_btn2.setPosition(330, 210);
        location_btn2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!location_btn2.isDisabled()) {
                    zombieClicker.get_assets().load_assets_for_location_2();
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(false);
                    zombieClicker.getNumerics().setCurrent_num_location(1);
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(true);
                    zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_2_bg.png");
                    zombieClicker.setMainGame();
                    dispose();
                }
            }
        });

        location_btn3 = new Button(location_skin);
        location_btn3.setPosition(200, 450);
        location_btn3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!location_btn3.isDisabled()) {
                    zombieClicker.get_assets().load_assets_for_location_3();
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(false);
                    zombieClicker.getNumerics().setCurrent_num_location(2);
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(true);
                    zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_3_bg.png");
                    zombieClicker.setMainGame();
                    dispose();
                }
            }
        });

        location_btn4 = new Button(location_skin);
        location_btn4.setPosition(440, 500);
        location_btn4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!location_btn4.isDisabled()) {
                    zombieClicker.get_assets().load_assets_for_location_4();
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(false);
                    zombieClicker.getNumerics().setCurrent_num_location(3);
                    zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(true);
                    zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_4_bg.png");
                    zombieClicker.setMainGame();
                    dispose();
                }
            }
        });

        location_btn2.setDisabled(false);
        location_btn3.setDisabled(false);
        location_btn4.setDisabled(true);

        stage.addActor(scrollPane);
        stage.addActor(back_btn);
        stage.addActor(location_btn1);
        stage.addActor(location_btn2);
        stage.addActor(location_btn3);
        stage.addActor(location_btn4);


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
        batch.end();

        batch.setProjectionMatrix(camera.combined);
    }

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
        if (batch != null) batch.dispose();
        if (stage != null) stage.dispose();
        zombieClicker.get_assets().dispose_assets_for_map();
    }
}
