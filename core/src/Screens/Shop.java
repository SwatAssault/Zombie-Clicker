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

import java.math.BigInteger;

import Other.OtherItem;
import Other.ShopItem;
import Other.SquadItem;

public class Shop implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private Image ImageBG;
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
    private SquadItem squad6;
    private SquadItem squad7;
    private SquadItem squad8;
    private SquadItem squad9;
    private SquadItem squad10;
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

    ///////////////OTHER ITEMS//////////////////
    private OtherItem otherItem1;
    private OtherItem otherItem2;
    private OtherItem otherItem3;
    private OtherItem otherItem4;
    private OtherItem otherItem5;
    private OtherItem otherItem6;
    private Array<OtherItem> otherItems_array;
    ///////////////OTHER ITEMS//////////////////

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
        squads_amount = 10;
        other_items_amount = 6;

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        tab_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/tab_skin.json", Skin.class);
        header_label_skin = zombieClicker.get_assets().get_asset_manager().get("LabelSkins/header_label_skin.json", Skin.class);
        goback_bth = new Button(goback_skin);
        shop_tab_btn = new TextButton(items_tab_string, tab_skin);
        squad_tab_btn = new TextButton(squads_tab_string, tab_skin);
        other_tab_btn = new TextButton(other_tab_string, tab_skin);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Rubik.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 20;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(true);
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

        /////////////////////SHOP ITEMS INITIALIZATION/////////////////////
        //                                                     base cost                                 base value
        shopItem1 = new ShopItem(zc, "ДУБИНКА", new BigInteger("50"),1.25f, new BigInteger("1"),1.07f,"TAP DAMAGE");
        shopItem2 = new ShopItem(zc, "Предмет 2", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem3 = new ShopItem(zc, "Предмет 3", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem4 = new ShopItem(zc, "Предмет 4", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem5 = new ShopItem(zc, "Предмет 5", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem6 = new ShopItem(zc, "Предмет 6", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem7 = new ShopItem(zc, "Предмет 7", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem8 = new ShopItem(zc, "Предмет 8", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem9 = new ShopItem(zc, "Предмет 9", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");
        shopItem10 = new ShopItem(zc, "Предмет 10", new BigInteger("150"),1.07f, new BigInteger("1"),1.1f,"DPS");

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
        //                                                                                    cost                                    dps                                       0-100%
        squad1 = new SquadItem(zc, "ПИРАТЫ", "АТАКУЮТ ТОПОРАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 3, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/pirates.png", Texture.class));
        squad2 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad3 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad4 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad5 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad6 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad7 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad8 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad9 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));
        squad10 = new SquadItem(zc, "ПОВАРЫ", "АТАКУЮТ ПОВАРЕШКАМИ", new BigInteger("10"),1.1f, new BigInteger("1"), 1.1f, 0, 0,
                zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class));

        //Заполняю объекты в массив, чтобы было легче работать
        squadItems_array = new Array<SquadItem>();
        squadItems_array.add(squad1);
        squadItems_array.add(squad2);
        squadItems_array.add(squad3);
        squadItems_array.add(squad4);
        squadItems_array.add(squad5);
        squadItems_array.add(squad6);
        squadItems_array.add(squad7);
        squadItems_array.add(squad8);
        squadItems_array.add(squad9);
        squadItems_array.add(squad10);
        /////////////////////SQUAD ITEMS INITIALIZATION/////////////////////

        table_squads = new Table();
        table_squads.setWidth(item_widht);
        table_squads.setVisible(false);

        for(int i = 0; i < squads_amount; i++){
            table_squads.add(squadItems_array.get(i).get_table());
            table_squads.row();
        }

        squad_scrollPane = new ScrollPane(table_squads);
        squad_scrollPane.setHeight(140 * 5 - 40);
        squad_scrollPane.setWidth(item_widht);
        scroll_stack.add(squad_scrollPane);
        squad_scrollPane.setZIndex(0);

        ///////////////////OTHER ITEMS INITIALIZATION///////////////////////
        otherItem1 = new OtherItem(zc,"A","tre",-1);     //если предмет можно покупать бесконечно, ставить -1
        otherItem2 = new OtherItem(zc,"B","",10);
        otherItem3 = new OtherItem(zc,"C","",10);
        otherItem4 = new OtherItem(zc,"D","",10);
        otherItem5 = new OtherItem(zc,"E","",10);
        otherItem6 = new OtherItem(zc,"F","",10);

        otherItems_array = new Array<OtherItem>();
        otherItems_array.add(otherItem1);
        otherItems_array.add(otherItem2);
        otherItems_array.add(otherItem3);
        otherItems_array.add(otherItem4);
        otherItems_array.add(otherItem5);
        otherItems_array.add(otherItem6);
        ///////////////////OTHER ITEMS INITIALIZATION///////////////////////

        table_other = new Table();
        table_other.setWidth(item_widht);
        table_other.setVisible(false);

        for(int i = 0; i < other_items_amount; i++){
            table_other.add(otherItems_array.get(i).getStack());
            table_other.row();
        }


        other_scrollPane = new ScrollPane(table_other);
        other_scrollPane.setHeight(140 * 5 - 40);
        other_scrollPane.setWidth(item_widht);
        scroll_stack.add(other_scrollPane);
        other_scrollPane.setZIndex(0);

        stage.addActor(ImageBG);
        stage.addActor(shop_tab_btn);
        stage.addActor(squad_tab_btn);
        stage.addActor(other_tab_btn);
        stage.addActor(stack_table);
        stage.addActor(goback_bth);

        Gdx.input.setInputProcessor(stage);
    }

    ///////////////GETTERS//////////////////////
    public Stage getStage(){
        return stage;
    }

    public Array<SquadItem> getSquadItems_array(){
        return squadItems_array;
    }

    public int getSquads_amount(){
        return squads_amount;
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
        for(int i = 0; i < squads_amount; i++){
            if(zombieClicker.getNumerics().getGold().compareTo(squadItems_array.get(i).getSquad_cost()) < 0){
                squadItems_array.get(i).disable_buy_btn(true);
            } else
                squadItems_array.get(i).disable_buy_btn(false);
        }

        //other items
        for(int i = 0; i < other_items_amount; i++){
            if(otherItems_array.get(i).getCurrent_buy_counter() == otherItems_array.get(i).getMax_buy_counter()){
                otherItems_array.get(i).getBuy_btn().setText("MAX");
                otherItems_array.get(i).disable_buy_btn(true);
            } else
                if(zombieClicker.getNumerics().getDiamonds() < otherItems_array.get(i).getCost()){
                    otherItems_array.get(i).disable_buy_btn(true);
                } else
                    otherItems_array.get(i).disable_buy_btn(false);
        }
    }

    public void update_labels(){
        //shop items
        for(int i = 0; i < items_amount; i++){
            shopItems_array.get(i).update_value_label();
            shopItems_array.get(i).update_cost_label();
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
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 100, 950);
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
