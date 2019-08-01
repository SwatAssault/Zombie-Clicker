package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class PlayerCreate implements Screen {


    private final ZombieClicker zombieClicker;
    private Skin goback_skin;
    private Button goback_bth;

    private Skin slider_skin;
    private Slider slider1;
    private Slider slider2;
    private Slider slider3;
    private Slider slider4;
    private Slider slider5;
    private Slider slider6;

    private Skin checkBox_skin;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;

    private Skin label_skin;
    private Label label;
    private Label labe2;
    private Label labe3;
    private Label labe4;
    private Label labe5;
    private Label labe6;
    private Label labe7;
    private Label labe8;
    private Label labe9;
    private Label labe0;

    private ScrollPane scrollPane;

    private Table table;

    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Image ImageBG;
    private Image ImageBG_MainGame;

    private TextureAtlas textureAtlas;
    private TextureAtlas.AtlasRegion elements[][];
    private TextureAtlas.AtlasRegion player_bg;
    private TextureAtlas.AtlasRegion player_body;
    private TextureAtlas.AtlasRegion player_ear;
    private TextureAtlas.AtlasRegion player_face;
    private TextureAtlas.AtlasRegion player_freckless;
    private TextureAtlas.AtlasRegion player_eyes;
    private TextureAtlas.AtlasRegion player_nose;
    private TextureAtlas.AtlasRegion player_mouth;
    private TextureAtlas.AtlasRegion player_hair;
    private TextureAtlas.AtlasRegion player_bread;
    private TextureAtlas.AtlasRegion player_brows;

    private int player_image_x, player_image_y;

    private int item_widht;

    public PlayerCreate(ZombieClicker zc) {
        this.zombieClicker = zc;

        item_widht = 400;
        player_image_x = (540 - 180) / 2;
        player_image_y = 600;//483

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        zombieClicker.get_assets().load_assets_for_playerCreate();

        textureAtlas = zombieClicker.get_assets().get_asset_manager().get("PlayerCreateSkins/player.atlas", TextureAtlas.class);
        elements = new TextureAtlas.AtlasRegion[11][];
        elements[0] = new TextureAtlas.AtlasRegion[6];
        elements[1] = new TextureAtlas.AtlasRegion[1];
        elements[2] = new TextureAtlas.AtlasRegion[1];
        elements[3] = new TextureAtlas.AtlasRegion[1];
        elements[4] = new TextureAtlas.AtlasRegion[7];
        elements[5] = new TextureAtlas.AtlasRegion[5];
        elements[6] = new TextureAtlas.AtlasRegion[6];
        elements[7] = new TextureAtlas.AtlasRegion[5];
        elements[8] = new TextureAtlas.AtlasRegion[1];
        elements[9] = new TextureAtlas.AtlasRegion[5];
        elements[10] = new TextureAtlas.AtlasRegion[1];

        elements[0][0] = textureAtlas.findRegion("body", 1);
        elements[0][1] = textureAtlas.findRegion("body", 2);
        elements[0][2] = textureAtlas.findRegion("body", 3);
        elements[0][3] = textureAtlas.findRegion("body", 4);
        elements[0][4] = textureAtlas.findRegion("body", 5);
        elements[0][5] = textureAtlas.findRegion("body", 6);

        elements[1][0] = textureAtlas.findRegion("ears", 1);

        elements[2][0] = textureAtlas.findRegion("face", 1);

        elements[3][0] = textureAtlas.findRegion("freckless");

        elements[4][0] = textureAtlas.findRegion("eyes", 1);
        elements[4][1] = textureAtlas.findRegion("eyes", 2);
        elements[4][2] = textureAtlas.findRegion("eyes", 3);
        elements[4][3] = textureAtlas.findRegion("eyes", 4);
        elements[4][4] = textureAtlas.findRegion("eyes", 5);
        elements[4][5] = textureAtlas.findRegion("eyes", 6);
        elements[4][6] = textureAtlas.findRegion("eyes", 7);

        elements[5][0] = textureAtlas.findRegion("nose", 1);
        elements[5][1] = textureAtlas.findRegion("nose", 2);
        elements[5][2] = textureAtlas.findRegion("nose", 3);
        elements[5][3] = textureAtlas.findRegion("nose", 4);
        elements[5][4] = textureAtlas.findRegion("nose", 5);

        elements[6][0] = textureAtlas.findRegion("mouth", 1);
        elements[6][1] = textureAtlas.findRegion("mouth", 2);
        elements[6][2] = textureAtlas.findRegion("mouth", 3);
        elements[6][3] = textureAtlas.findRegion("mouth", 4);
        elements[6][4] = textureAtlas.findRegion("mouth", 5);
        elements[6][5] = textureAtlas.findRegion("mouth", 6);

        elements[7][0] = textureAtlas.findRegion("hair", 1);
        elements[7][1] = textureAtlas.findRegion("hair", 2);
        elements[7][2] = textureAtlas.findRegion("hair", 3);
        elements[7][3] = textureAtlas.findRegion("hair", 4);
        elements[7][4] = textureAtlas.findRegion("hair", 5);

        elements[8][0] = textureAtlas.findRegion("bread", 1);

        elements[9][0] = textureAtlas.findRegion("brows", 1);
        elements[9][1] = textureAtlas.findRegion("brows", 2);
        elements[9][2] = textureAtlas.findRegion("brows", 3);
        elements[9][3] = textureAtlas.findRegion("brows", 4);
        elements[9][4] = textureAtlas.findRegion("brows", 5);

        elements[10][0] = textureAtlas.findRegion("bg");

        player_bg = elements[10][0];

        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/invent_bg.png", Texture.class));
        ImageBG_MainGame = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        goback_bth = new Button(goback_skin);
        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_inventory();
                zombieClicker.setInventoryScreen();
                dispose();
            }
        });
        goback_bth.setPosition(10, 10);

        slider_skin = zombieClicker.get_assets().get_asset_manager().get("Other/slider.json", Skin.class);
        slider1 = new Slider(0f, 5f, 1f, false, slider_skin);
//        slider1.setSize(350, 10);
//        slider1.setWidth(350);

        slider2 = new Slider(0f, 6f, 1f, false, slider_skin);
//        slider2.setSize(350, 10);

        slider3 = new Slider(0f, 4f, 1f, false, slider_skin);
//        slider3.setSize(350, 10);

        slider4 = new Slider(0f, 5f, 1f, false, slider_skin);
//        slider4.setSize(350, 10);

        slider5 = new Slider(0f, 4f, 1f, false, slider_skin);
//        slider5.setSize(350, 10);

        slider6 = new Slider(0f, 4f, 1f, false, slider_skin);
//        slider6.setSize(350, 10);

        slider1.setValue(zombieClicker.getNumerics().getPlayer_body());
        slider2.setValue(zombieClicker.getNumerics().getPlayer_eyes());
        slider3.setValue(zombieClicker.getNumerics().getPlayer_nose());
        slider4.setValue(zombieClicker.getNumerics().getPlayer_mouth());
        slider5.setValue(zombieClicker.getNumerics().getPlayer_hair());
        slider6.setValue(zombieClicker.getNumerics().getPlayer_brows());

        checkBox_skin = zombieClicker.get_assets().get_asset_manager().get("Other/checkBox.json", Skin.class);
        checkBox1 = new CheckBox("", checkBox_skin);
        checkBox2 = new CheckBox("", checkBox_skin);
        checkBox3 = new CheckBox("", checkBox_skin);

        checkBox1.setChecked(zombieClicker.getNumerics().getPlayer_ear() == 1);
        checkBox2.setChecked(zombieClicker.getNumerics().getPlayer_freckless() == 1);
        checkBox3.setChecked(zombieClicker.getNumerics().getPlayer_bread() == 1);

        label_skin = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/label_skin.json", Skin.class);
        label = new Label("BODY\n", label_skin);
        labe2 = new Label("\nEARS\n", label_skin);
        labe3 = new Label("\nFRECKLESS\n", label_skin);
        labe4 = new Label("\nEYES\n", label_skin);
        labe5 = new Label("\nNOSE\n", label_skin);
        labe6 = new Label("\nMOUTH\n", label_skin);
        labe7 = new Label("\nHAIR\n", label_skin);
        labe8 = new Label("\nBREAD\n", label_skin);
        labe9 = new Label("\nBROWS\n", label_skin);
        labe0 = new Label("\n\n", label_skin);

        table = new Table();
        table.add(label);
        table.row();
        table.add(slider1).size(350, 50);
        table.row();
        table.add(labe2);
        table.row();
        table.add(checkBox1);
        table.row();
        table.add(labe3);
        table.row();
        table.add(checkBox2);
        table.row();
        table.add(labe4);
        table.row();
        table.add(slider2).size(350, 50);
        table.row();
        table.add(labe5);
        table.row();
        table.add(slider3).size(350, 50);
        table.row();
        table.add(labe6);
        table.row();
        table.add(slider4).size(350, 50);
        table.row();
        table.add(labe7);
        table.row();
        table.add(slider5).size(350, 50);
        table.row();
        table.add(labe8);
        table.row();
        table.add(checkBox3);
        table.row();
        table.add(labe9);
        table.row();
        table.add(slider6).size(350, 50);
        table.row();
        table.add(labe0);


        table.setWidth(item_widht);
        table.setHeight(450);
        table.setPosition((540 - item_widht) / 2, 85);

//        table.setDebug(true);

        scrollPane = new ScrollPane(table);

        scrollPane.setCancelTouchFocus(false);
//        scrollPane.setFlingTime(0);

        scrollPane.setWidth(item_widht);
        scrollPane.setHeight(425);
        scrollPane.setPosition((540 - item_widht) / 2, 85);

        stage.addActor(ImageBG_MainGame);
        stage.addActor(ImageBG);
        stage.addActor(goback_bth);
        stage.addActor(scrollPane);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void show() {

    }

    public void update() {

        if (slider1.isDragging() || slider2.isDragging() || slider3.isDragging() || slider4.isDragging() ||
                slider5.isDragging() || slider6.isDragging()) {
            scrollPane.cancel();
        }
//            if (zombieClicker.getNumerics().getPlayer_bg() != 0) {
//                zombieClicker.getNumerics().setPlayer_bg(0);
//                player_bg = elements[10][0];
//            }

        if (player_body != elements[0][(int) slider1.getValue()]) {
            zombieClicker.getNumerics().setPlayer_body((int) slider1.getValue());
            player_body = elements[0][(int) slider1.getValue()];
        }

        if (player_ear != (checkBox1.isChecked() ? elements[1][0] : null)) {
            zombieClicker.getNumerics().setPlayer_ear(checkBox1.isChecked() ? 1 : -1);
            player_ear = checkBox1.isChecked() ? elements[1][0] : null;
        }

        if (player_face != elements[2][0]) {
            zombieClicker.getNumerics().setPlayer_face(0);
            player_face = elements[2][0];
        }
        if (player_freckless != (checkBox2.isChecked() ? elements[3][0] : null)) {
            zombieClicker.getNumerics().setPlayer_freckless(checkBox2.isChecked() ? 1 : -1);
            player_freckless = checkBox2.isChecked() ? elements[3][0] : null;
        }
        if (player_eyes != elements[4][(int) slider2.getValue()]) {
            zombieClicker.getNumerics().setPlayer_eyes((int) slider2.getValue());
            player_eyes = elements[4][(int) slider2.getValue()];
        }
        if (player_nose != elements[5][(int) slider3.getValue()]) {
            zombieClicker.getNumerics().setPlayer_nose((int) slider3.getValue());
            player_nose = elements[5][(int) slider3.getValue()];
        }
        if (player_mouth != elements[6][(int) slider4.getValue()]) {
            zombieClicker.getNumerics().setPlayer_mouth((int) slider4.getValue());
            player_mouth = elements[6][(int) slider4.getValue()];
        }
        if (player_hair != elements[7][(int) slider5.getValue()]) {
            zombieClicker.getNumerics().setPlayer_hair((int) slider5.getValue());
            player_hair = elements[7][(int) slider5.getValue()];
        }
        if (player_bread != (checkBox3.isChecked() ? elements[8][0] : null)) {
            zombieClicker.getNumerics().setPlayer_bread(checkBox3.isChecked() ? 1 : -1);
            player_bread = checkBox3.isChecked() ? elements[8][0] : null;
        }
        if (player_brows != elements[9][(int) slider6.getValue()]) {
            zombieClicker.getNumerics().setPlayer_brows((int) slider6.getValue());
            player_brows = elements[9][(int) slider6.getValue()];
        }
    }

    @Override
    public void render(float delta) {
        update();
        stage.act();
        stage.draw();

        batch.begin();
//        bitmapFont.draw(batch, "INVENTORY", 320, 945);
        batch.draw(player_bg, player_image_x, player_image_y);
        batch.draw(player_body, player_image_x, player_image_y);
        if (player_ear != null)
            batch.draw(player_ear, player_image_x, player_image_y);
        batch.draw(player_face, player_image_x, player_image_y);
        if (player_freckless != null)
            batch.draw(player_freckless, player_image_x, player_image_y);
        batch.draw(player_eyes, player_image_x, player_image_y);
        batch.draw(player_nose, player_image_x, player_image_y);
        batch.draw(player_mouth, player_image_x, player_image_y);
        batch.draw(player_hair, player_image_x, player_image_y);
        if (player_bread != null)
            batch.draw(player_bread, player_image_x, player_image_y);
        batch.draw(player_brows, player_image_x, player_image_y);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.setProjectionMatrix(camera.combined);

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
        zombieClicker.get_assets().dispose_PlayerCreate_assets();
    }
}
