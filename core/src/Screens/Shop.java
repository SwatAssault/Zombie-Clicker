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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Other.ShopItem;
import Other.SquadItem;

public class Shop implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private Image ImageBG;
    private ScrollPane scrollPane;
    private ScrollPane squads_scrollPane;
    private Table table;
    private Table table_squads;
    private Button goback_bth;
    private SpriteBatch batch;
    private int item_widht;
    private int items_amount;

    ////////////////SQUADS//////////////////
    private SquadItem squad1;
    ////////////////SQUADS//////////////////

    ///////////////ITEMS///////////////////
    private ShopItem shopItem1;
    private ShopItem shopItem2;
    private ShopItem shopItem3;
    private ShopItem shopItem4;
    private ShopItem shopItem5;
    private ShopItem shopItem6;
    private ShopItem shopItem7;
    private ShopItem shopItem8;
    private ShopItem shopItem9;
    private ShopItem shopItem10;
    ///////////////ITEMS///////////////////

    private Skin goback_skin;
    private Skin tab_skin;

    private Button shop_tab_btn;
    private Button squad_tab_btn;

    //ВРЕМЕННО
    private BitmapFont bitmapFont;
    private BitmapFont font_for_plus;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font_for_description;
    private final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
    private Label.LabelStyle style_for_description;
    private Label.LabelStyle style_for_name;
    private Label.LabelStyle style_for_plus;

    public Shop(final ZombieClicker zc) {
        zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        zombieClicker.get_assets().load_assets_for_Shop();
        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/shopbg.png", Texture.class));
        batch = new SpriteBatch();
        item_widht = 474;
        items_amount = 11;

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        tab_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/tab_skin.json", Skin.class);
        goback_bth = new Button(goback_skin);
        shop_tab_btn = new Button(tab_skin);
        squad_tab_btn = new Button(tab_skin);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontParameter.size = 22;
        font_for_description = fontGenerator.generateFont(fontParameter);
        font_for_description.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        style_for_description = new Label.LabelStyle(font_for_description, Color.WHITE);
        style_for_name = new Label.LabelStyle(bitmapFont, Color.WHITE);
        fontParameter.size = 18;
        font_for_description = fontGenerator.generateFont(fontParameter);
        font_for_plus = fontGenerator.generateFont(fontParameter);
        font_for_plus.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        style_for_plus = new Label.LabelStyle(font_for_plus, Color.WHITE);

        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                //dispose();
            }
        });

        shop_tab_btn.setDisabled(true);

        shop_tab_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!shop_tab_btn.isDisabled()){
                    table_squads.setVisible(false);
                    table.setVisible(true);
                    shop_tab_btn.setDisabled(true);
                    squad_tab_btn.setDisabled(false);
                }
            }
        });

        squad_tab_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!squad_tab_btn.isDisabled()){
                    table_squads.setVisible(true);
                    table.setVisible(false);
                    squad_tab_btn.setDisabled(true);
                    shop_tab_btn.setDisabled(false);
                }
            }
        });

        shop_tab_btn.setPosition(65,770);
        squad_tab_btn.setPosition(280,770);

        goback_bth.setPosition(10, 10);

//        name_of_item1 = new Label(name1_rus, style_for_name);
//        description_1 = new Label(description1_rus, style_for_description);
//        number_label1 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem1_number()), style_for_description);
//        plus_label1 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_value()), style_for_plus);


        /////////////////////SHOP ITEMS INITIALIZATION/////////////////////
        shopItem1 = new ShopItem(zc);
        shopItem1.setItem_cost("100");
        shopItem1.setItem_value("5");

        shopItem2 = new ShopItem(zc);
        shopItem2.setItem_cost("200");
        shopItem2.setItem_value("10");

        shopItem3 = new ShopItem(zc);
        shopItem3.setItem_cost("200");
        shopItem3.setItem_value("10");

        shopItem4 = new ShopItem(zc);
        shopItem4.setItem_cost("200");
        shopItem4.setItem_value("10");

        shopItem5 = new ShopItem(zc);
        shopItem5.setItem_cost("200");
        shopItem5.setItem_value("10");

        shopItem6 = new ShopItem(zc);
        shopItem6.setItem_cost("200");
        shopItem6.setItem_value("10");

        shopItem7 = new ShopItem(zc);
        shopItem7.setItem_cost("200");
        shopItem7.setItem_value("10");

        shopItem8 = new ShopItem(zc);
        shopItem8.setItem_cost("200");
        shopItem8.setItem_value("10");

        shopItem9 = new ShopItem(zc);
        shopItem9.setItem_cost("200");
        shopItem9.setItem_value("10");

        shopItem10 = new ShopItem(zc);
        shopItem10.setItem_cost("200");
        shopItem10.setItem_value("10");
        /////////////////////SHOP ITEMS INITIALIZATION/////////////////////

        table = new Table();
        table.setWidth(474);
        table.setHeight(130 * items_amount);

        table.add(shopItem1.getTable());
        table.row();
        table.add(shopItem2.getTable());
        table.row();
        table.add(shopItem3.getTable());
        table.row();
        table.add(shopItem4.getTable());
        table.row();
        table.add(shopItem5.getTable());
        table.row();
        table.add(shopItem6.getTable());
        table.row();
        table.add(shopItem7.getTable());
        table.row();
        table.add(shopItem8.getTable());
        table.row();
        table.add(shopItem9.getTable());
        table.row();
        table.add(shopItem10.getTable());

        scrollPane = new ScrollPane(table);
        scrollPane.setPosition((540 - item_widht) / 2, 110);
        scrollPane.setHeight(140 * 5 - 40);
        scrollPane.setWidth(item_widht);

        /////////////////////SQUAD ITEMS INITIALIZATION/////////////////////
        squad1 = new SquadItem(zc);

        /////////////////////SQUAD ITEMS INITIALIZATION/////////////////////

        table_squads = new Table();
        table_squads.setWidth(474);
        table_squads.setVisible(false);

        table_squads.add(squad1.get_table());


        squads_scrollPane = new ScrollPane(table_squads);
        squads_scrollPane.setPosition((540 - item_widht) / 2, 0);
        squads_scrollPane.setHeight(140 * 5 - 20);
        squads_scrollPane.setWidth(item_widht);


        stage.addActor(ImageBG);
        stage.addActor(shop_tab_btn);
        stage.addActor(squad_tab_btn);
        stage.addActor(squads_scrollPane);
        stage.addActor(scrollPane);
        stage.addActor(goback_bth);


        Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage(){
        return stage;
    }

    @Override
    public void show() {

    }

    public void update() {
        //проверка на бабло
        if (zombieClicker.getNumerics().getGold().compareTo(shopItem1.getItem_cost()) < 0) {
            shopItem1.disable_button(true);
        } else
            shopItem1.disable_button(false);


        if (zombieClicker.getNumerics().getGold().compareTo(shopItem2.getItem_cost()) < 0) {
            shopItem2.disable_button(true);
        } else
            shopItem2.disable_button(false);


    }

    @Override
    public void render(float delta) {
        update();

        stage.act();
        stage.draw();

        batch.begin();
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 320, 730);
        batch.end();

        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        if (font_for_description != null) font_for_description.dispose();
        if (font_for_plus != null) font_for_plus.dispose();
        if (fontGenerator != null) fontGenerator.dispose();
        if (bitmapFont != null) bitmapFont.dispose();
        zombieClicker.get_assets().dispose_Shop_assets();
        if (stage != null) stage.dispose();
    }
}
