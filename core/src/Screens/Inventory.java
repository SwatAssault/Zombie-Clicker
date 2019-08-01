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
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Other.CraftItem;

public class Inventory implements Screen {

    private final ZombieClicker zombieClicker;

    private TextureAtlas textureAtlas;
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
    private int player_fr_on;
    private int player_br_on;
    private int player_er_on;

    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    private Table table;

    private Skin goback_skin;
    private Button goback_bth;
    private Image ImageBG;
    private Image ImageBG_MainGame;

    private Skin playerCreate_skin;
    private Button playerCreate_btn;

    private ScrollPane scrollPane;
    private Stack scroll_stack;
    private Table stack_table;

    private int items_amount;
    private int item_widht;

    ///////////////SHOP ITEMS///////////////////
    private CraftItem craftItem1;
    private CraftItem craftItem2;
    private CraftItem craftItem3;
    private CraftItem craftItem4;
    private CraftItem craftItem5;
    private Array<CraftItem> craftItems_array;
    ///////////////SHOP ITEMS///////////////////

    private int player_image_x, player_image_y;

    public Inventory(final ZombieClicker zc) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_inventory();

        textureAtlas = zombieClicker.get_assets().get_asset_manager().get("PlayerCreateSkins/player.atlas", TextureAtlas.class);
        player_bg = textureAtlas.findRegion("bg");
        player_body = textureAtlas.findRegion("body", zombieClicker.getNumerics().getPlayer_body() + 1);
        player_ear = textureAtlas.findRegion("ears", 1);
        player_er_on = zombieClicker.getNumerics().getPlayer_ear();
        player_face = textureAtlas.findRegion("face", 1);
        player_freckless = textureAtlas.findRegion("freckless");
        player_fr_on = zombieClicker.getNumerics().getPlayer_freckless();
        player_eyes = textureAtlas.findRegion("eyes", zombieClicker.getNumerics().getPlayer_eyes() + 1);
        player_nose = textureAtlas.findRegion("nose", zombieClicker.getNumerics().getPlayer_nose() + 1);
        player_mouth = textureAtlas.findRegion("mouth", zombieClicker.getNumerics().getPlayer_mouth() + 1);
        player_hair = textureAtlas.findRegion("hair", zombieClicker.getNumerics().getPlayer_hair() + 1);
        player_bread = textureAtlas.findRegion("bread", 1);
        player_br_on = zombieClicker.getNumerics().getPlayer_bread();
        player_brows = textureAtlas.findRegion("brows", zombieClicker.getNumerics().getPlayer_brows() + 1);
        player_bg.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_body.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_ear.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_face.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_freckless.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_eyes.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_nose.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_mouth.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_hair.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_bread.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        player_brows.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        player_image_x = 160 - 180 / 2;
        player_image_y = 620;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        goback_bth = new Button(goback_skin);
        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                dispose();
            }
        });
        goback_bth.setPosition(10, 10);

        playerCreate_skin = zombieClicker.get_assets().get_asset_manager().get("Other/alfa_btn.json", Skin.class);
        playerCreate_btn = new Button(playerCreate_skin);
        playerCreate_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_playerCreate();
                zombieClicker.setPlayerCreateScreen();
                dispose();
            }
        });
        playerCreate_btn.setSize(180, 197);
        playerCreate_btn.setPosition(player_image_x, player_image_y);


        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/invent_bg.png", Texture.class));
        ImageBG_MainGame = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));

        /////////////////////////////////// CRAFT ITEM INIT ///////////////////////////////////
        craftItem1 = new CraftItem(zc, "ButtonsInventory/coinsPack_1.json", 1, 1, 1, 1, 1);
//        craftItem1.setItem_cost(100);
//        craftItem1.setItem_value(5);

        craftItem2 = new CraftItem(zc, "ButtonsInventory/coinsPack_2.json", 1, 1, 2);
//        craftItem2.setItem_cost(100);
//        craftItem2.setItem_value(5);

        craftItem3 = new CraftItem(zc, "ButtonsInventory/diamondsPack_1.json", 1, 1, 1, 1, 3);
//        craftItem3.setItem_cost(100);
//        craftItem3.setItem_value(5);

        craftItem4 = new CraftItem(zc, "ButtonsInventory/coinsPack_3.json", 1, 4);
//        craftItem4.setItem_cost(100);
//        craftItem4.setItem_value(5);

        craftItem5 = new CraftItem(zc, "ButtonsInventory/diamondsPack_2.json", 1, 1, 1, 5);

        craftItems_array = new Array<CraftItem>();
        craftItems_array.add(craftItem1);
        craftItems_array.add(craftItem2);
        craftItems_array.add(craftItem3);
        craftItems_array.add(craftItem4);
        craftItems_array.add(craftItem5);
        /////////////////////////////////// CRAFT ITEM INIT ///////////////////////////////////

        items_amount = 5;
        item_widht = 400;

        table = new Table();
        table.setWidth(474);
        table.setHeight(130 * items_amount);

        for (int i = 0; i < items_amount; i++) {
            table.add(craftItems_array.get(i).getTable());
            table.row();
        }

        stack_table = new Table();
        stack_table.setWidth(item_widht);
        stack_table.setHeight(425);
        stack_table.setPosition((540 - item_widht) / 2, 85);

        scroll_stack = new Stack();
        stack_table.add(scroll_stack);

        scrollPane = new ScrollPane(table);
        scrollPane.setHeight(140 * 5 - 40);
        scrollPane.setWidth(item_widht);
        scroll_stack.add(scrollPane);
        scrollPane.setZIndex(1);

        stage.addActor(ImageBG_MainGame);
        stage.addActor(ImageBG);
        stage.addActor(stack_table);
        stage.addActor(goback_bth);
        stage.addActor(playerCreate_btn);
//        stage.addActor(player_name);

//        stage.setDebugAll(true);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    public void update() {
        craftItem1.update();
        craftItem2.update();
        craftItem3.update();
        craftItem4.update();
        craftItem5.update();
    }

    @Override
    public void render(float delta) {
        update();
        stage.act();
        stage.draw();

        batch.begin();
        batch.draw(player_bg, player_image_x, player_image_y);
        batch.draw(player_body, player_image_x, player_image_y);
        if (player_er_on == 1)
            batch.draw(player_ear, player_image_x, player_image_y);
        batch.draw(player_face, player_image_x, player_image_y);
        if (player_fr_on == 1)
            batch.draw(player_freckless, player_image_x, player_image_y);
        batch.draw(player_eyes, player_image_x, player_image_y);
        batch.draw(player_nose, player_image_x, player_image_y);
        batch.draw(player_mouth, player_image_x, player_image_y);
        batch.draw(player_hair, player_image_x, player_image_y);
        if (player_br_on == 1)
            batch.draw(player_bread, player_image_x, player_image_y);
        batch.draw(player_brows, player_image_x, player_image_y);

        zombieClicker.getFontManager().draw_text_forInvent(batch);
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
        zombieClicker.get_assets().dispose_Inventory_assets();
        if (stage != null) stage.dispose();
    }
}
