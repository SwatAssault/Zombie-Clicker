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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
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
    private Label header_label;
    private ScrollPane scrollPane;
    private ScrollPane squad_scrollPane;
    private ScrollPane other_scrollPane;
    private Stack scroll_stack;
    private Table stack_table;
    private Table table;
    private Table table_squads;
    private Table table_other;
    private Button goback_bth;
    private SpriteBatch batch;
    private int item_widht;
    private int items_amount;
    private int squads_amount;
    private int other_items_amount;
    private int spare_squads_counter;   //сколько свободных отрядов

    ////////////////SQUADS//////////////////
    private SquadItem squad1;
    private SquadItem squad2;
    private SquadItem squad3;
    private SquadItem squad4;
    private SquadItem squad5;
    private Array<SquadItem> squadItems_array;
    ////////////////SQUADS//////////////////

    ///////////////SHOP ITEMS///////////////////
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
    private Array<ShopItem> shopItems_array;
    ///////////////SHOP ITEMS///////////////////

    private Skin goback_skin;
    private Skin tab_skin;
    private Skin header_label_skin;

    private String squads_tab_string = "ОТРЯДЫ";
    private String items_tab_string = "ITEMS";
    private String other_tab_string = "OTHER";
    private String header_string = "SHOP";

    private TextButton shop_tab_btn;
    private TextButton squad_tab_btn;
    private TextButton other_tab_btn;

    //ВРЕМЕННО
    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public Shop(final ZombieClicker zc) {
        zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        zombieClicker.get_assets().load_assets_for_Shop();
        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/shopbg.png", Texture.class));
        batch = new SpriteBatch();

        item_widht = 474;
        items_amount = 10;
        squads_amount = 5;
        other_items_amount = 0;

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        tab_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/tab_skin.json", Skin.class);
        header_label_skin = zombieClicker.get_assets().get_asset_manager().get("LabelSkins/header_label_skin.json", Skin.class);
        header_label = new Label(header_string, header_label_skin);
        goback_bth = new Button(goback_skin);
        shop_tab_btn = new TextButton(items_tab_string, tab_skin);
        squad_tab_btn = new TextButton(squads_tab_string, tab_skin);
        other_tab_btn = new TextButton(other_tab_string, tab_skin);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Rubik.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 25;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


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
                    table_other.setVisible(false);
                    shop_tab_btn.setDisabled(true);
                    squad_tab_btn.setDisabled(false);
                    other_tab_btn.setDisabled(false);
                    scrollPane.setZIndex(1);
                    other_scrollPane.setZIndex(0);
                    squad_scrollPane.setZIndex(0);
                }
            }
        });

        squad_tab_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!squad_tab_btn.isDisabled()){
                    table_squads.setVisible(true);
                    table.setVisible(false);
                    table_other.setVisible(false);
                    squad_tab_btn.setDisabled(true);
                    shop_tab_btn.setDisabled(false);
                    other_tab_btn.setDisabled(false);
                    scrollPane.setZIndex(0);
                    squad_scrollPane.setZIndex(1);
                    other_scrollPane.setZIndex(0);
                }
            }
        });

        other_tab_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!other_tab_btn.isDisabled()){
                    table_other.setVisible(true);
                    table.setVisible(false);
                    table_squads.setVisible(false);
                    other_tab_btn.setDisabled(true);
                    shop_tab_btn.setDisabled(false);
                    squad_tab_btn.setDisabled(false);
                    scrollPane.setZIndex(0);
                    other_scrollPane.setZIndex(1);
                    squad_scrollPane.setZIndex(0);
                }
            }
        });

        shop_tab_btn.setPosition(40,770);
        squad_tab_btn.setPosition(195,770);
        other_tab_btn.setPosition(350,770);

        goback_bth.setPosition(10, 10);

        header_label.setPosition(540 / 2 - header_label.getGlyphLayout().width / 2, 900);

        /////////////////////SHOP ITEMS INITIALIZATION/////////////////////
        shopItem1 = new ShopItem(zc, "ANNIHILATION GUN", "TAP DAMAGE", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem1.setItem_cost(100);
        shopItem1.setItem_value(5);

        shopItem2 = new ShopItem(zc, "Gun", "DPS", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem2.setItem_cost(200);
        shopItem2.setItem_value(10);

        shopItem3 = new ShopItem(zc, "Gun", "kek", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem3.setItem_cost(200);
        shopItem3.setItem_value(10);

        shopItem4 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem4.setItem_cost(200);
        shopItem4.setItem_value(10);

        shopItem5 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem5.setItem_cost(200);
        shopItem5.setItem_value(10);

        shopItem6 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem6.setItem_cost(200);
        shopItem6.setItem_value(10);

        shopItem7 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem7.setItem_cost(200);
        shopItem7.setItem_value(10);

        shopItem8 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem8.setItem_cost(200);
        shopItem8.setItem_value(10);

        shopItem9 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem9.setItem_cost(200);
        shopItem9.setItem_value(10);

        shopItem10 = new ShopItem(zc, "Gun", "lol", zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        shopItem10.setItem_cost(200);
        shopItem10.setItem_value(10);

        //Заполняю объекты в массив, чтобы было легче работать
        shopItems_array = new Array<ShopItem>();
        shopItems_array.add(shopItem1);
        shopItems_array.add(shopItem2);
        shopItems_array.add(shopItem3);
        shopItems_array.add(shopItem4);
        shopItems_array.add(shopItem5);
        shopItems_array.add(shopItem6);
        shopItems_array.add(shopItem7);
        shopItems_array.add(shopItem8);
        shopItems_array.add(shopItem9);
        shopItems_array.add(shopItem10);
        /////////////////////SHOP ITEMS INITIALIZATION/////////////////////

        table = new Table();
        table.setWidth(474);
        table.setHeight(130 * items_amount);

        for(int i = 0; i < items_amount; i++){
            table.add(shopItems_array.get(i).getTable());
            table.row();
        }


        stack_table = new Table();
        stack_table.setWidth(item_widht);
        stack_table.setHeight(140 * 5 - 40);
        stack_table.setPosition((540 - item_widht) / 2, 110);

        scroll_stack = new Stack();
        stack_table.add(scroll_stack);

        scrollPane = new ScrollPane(table);
        scrollPane.setHeight(140 * 5 - 40);
        scrollPane.setWidth(item_widht);
        scroll_stack.add(scrollPane);
        scrollPane.setZIndex(1);

        /////////////////////SQUAD ITEMS INITIALIZATION//////////////////////
        squad1 = new SquadItem(zc, "ПОВАРЫ", "SILICON SJENFOSEFN АТАКУЮТ ПОВАРЕШКАМИ", zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));
        squad2 = new SquadItem(zc, "ФИЗРУКИ", "description", zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));
        squad3 = new SquadItem(zc, "S.W.A.T.", "kek", zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));
        squad4 = new SquadItem(zc, "lil", "kek", zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));
        squad5 = new SquadItem(zc, "lil", "kek", zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));


        //Заполняю объекты в массив, чтобы было легче работать
        squadItems_array = new Array<SquadItem>();
        squadItems_array.add(squad1);
        squadItems_array.add(squad2);
        squadItems_array.add(squad3);
        squadItems_array.add(squad4);
        squadItems_array.add(squad5);
        /////////////////////SQUAD ITEMS INITIALIZATION/////////////////////

        table_squads = new Table();
        table_squads.setWidth(item_widht);
        table_squads.setVisible(false);

        table_squads.add(squad1.get_table());
        table_squads.row();
        table_squads.add(squad2.get_table());
        table_squads.row();
        table_squads.add(squad3.get_table());
        table_squads.row();
        table_squads.add(squad4.get_table());
        table_squads.row();
        table_squads.add(squad5.get_table());

        squad_scrollPane = new ScrollPane(table_squads);
        squad_scrollPane.setHeight(140 * 5 - 40);
        squad_scrollPane.setWidth(item_widht);
        scroll_stack.add(squad_scrollPane);
        squad_scrollPane.setZIndex(0);

        ///////////////////OTHER ITEMS INITIALIZATION///////////////////////

        ///////////////////OTHER ITEMS INITIALIZATION///////////////////////

        table_other = new Table();
        table_other.setWidth(item_widht);
        table_other.setVisible(false);



        other_scrollPane = new ScrollPane(table_other);
        other_scrollPane.setHeight(140 * 5 - 40);
        other_scrollPane.setWidth(item_widht);
        scroll_stack.add(other_scrollPane);
        other_scrollPane.setZIndex(0);

        stage.addActor(ImageBG);
        stage.addActor(header_label);
        stage.addActor(shop_tab_btn);
        stage.addActor(squad_tab_btn);
        stage.addActor(other_tab_btn);
        stage.addActor(stack_table);
        stage.addActor(goback_bth);

     //   stage.setDebugAll(true);

        Gdx.input.setInputProcessor(stage);
    }

    ///////////////GETTERS//////////////////////
    public Stage getStage(){
        return stage;
    }
    ///////////////GETTERS//////////////////////

    ///////////////SETTERS//////////////////////
    public void setSpare_squads_counter(int x){
        spare_squads_counter += x;
    }
    ///////////////SETTERS//////////////////////

    @Override
    public void show() {

    }

    public void update() {


        check_money();
        update_labels();
    }

    public void check_money(){

        //shop items
        for(int i = 0; i < items_amount; i++)
            if (zombieClicker.getNumerics().getGold().compareTo(shopItems_array.get(i).getItem_cost()) < 0) {
                shopItems_array.get(i).disable_button(true);
            } else
                shopItems_array.get(i).disable_button(false);

        //squad items

        //other items
    }

    public void update_labels(){

        //shop items
        for(int i = 0; i < items_amount; i++){
            shopItems_array.get(i).update_cost_label();
            shopItems_array.get(i).update_value_label();
        }


        //squad items
        for(int i = 0; i < squads_amount; i++){
            squadItems_array.get(i).update_buy_label();
        }
        //other items

    }

    @Override
    public void render(float delta) {
        update();

        stage.act();
        stage.draw();

        batch.begin();
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 320, 945);
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
        if (fontGenerator != null) fontGenerator.dispose();
        if (bitmapFont != null) bitmapFont.dispose();
        zombieClicker.get_assets().dispose_Shop_assets();
        if (stage != null) stage.dispose();
    }
}
