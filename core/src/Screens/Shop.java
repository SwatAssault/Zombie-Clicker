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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Shop implements Screen {          //КЛАСС ТЕСТИРУЕТСЯ

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private Image ImageBG;
    private ScrollPane scrollPane;
    private Table table;
    private ImageButton goback_bth;
    private SpriteBatch batch;
    private int item_widht;
    private int items_amount;   //сколько предметов в магазине

    private Image item1;
    private Image item2;
    private Image item3;
    private Image item4;
    private Image item5;
    private Image item6;
    private Image item7;
    private Image item8;
    private Image item9;
    private Image item10;
    private Image item11;

    private Table table_for_nums1;
    private Table table_for_nums2;
    private Table table_for_nums3;
    private Table table_for_nums4;
    private Table table_for_nums5;
    private Table table_for_nums6;
    private Table table_for_nums7;
    private Table table_for_nums8;
    private Table table_for_nums9;
    private Table table_for_nums10;
    private Table table_for_nums11;

    private Table table1;
    private Table table2;
    private Table table3;
    private Table table4;
    private Table table5;
    private Table table6;
    private Table table7;
    private Table table8;
    private Table table9;
    private Table table10;
    private Table table11;

    private Table intable1;
    private Table intable2;
    private Table intable3;
    private Table intable4;
    private Table intable5;
    private Table intable6;
    private Table intable7;
    private Table intable8;
    private Table intable9;
    private Table intable10;
    private Table intable11;

    private Stack s1;
    private Stack s2;
    private Stack s3;
    private Stack s4;
    private Stack s5;
    private Stack s6;
    private Stack s7;
    private Stack s8;
    private Stack s9;
    private Stack s10;
    private Stack s11;

    private TextButton b1;
    private TextButton b2;
    private TextButton b3;
    private TextButton b4;
    private TextButton b5;
    private TextButton b6;
    private TextButton b7;
    private TextButton b8;
    private TextButton b9;
    private TextButton b10;
    private TextButton b11;

    //НАЗВАНИЕ КАЖДОГО ПРЕДМЕТА
    private Label name_of_item1;
    private Label name_of_item2;
    private Label name_of_item3;
    private Label name_of_item4;
    private Label name_of_item5;
    private Label name_of_item6;
    private Label name_of_item7;
    private Label name_of_item8;
    private Label name_of_item9;
    private Label name_of_item10;
    private Label name_of_item11;

    //ОПИСАНИЕ
    private Label description_1;
    private Label description_2;
    private Label description_3;
    private Label description_4;
    private Label description_5;
    private Label description_6;
    private Label description_7;
    private Label description_8;
    private Label description_9;
    private Label description_10;
    private Label description_11;

    //СКОЛЬКО РАЗ КУПЛЕН ЭТОТ ПРЕДМЕТ
    private Label number_label1;
    private Label number_label2;
    private Label number_label3;
    private Label number_label4;
    private Label number_label5;
    private Label number_label6;
    private Label number_label7;
    private Label number_label8;
    private Label number_label9;
    private Label number_label10;
    private Label number_label11;

    //СКОЛЬКО ПРИБАВЛЯЕТ
    private Label plus_label1;
    private Label plus_label2;
    private Label plus_label3;
    private Label plus_label4;
    private Label plus_label5;
    private Label plus_label6;
    private Label plus_label7;
    private Label plus_label8;
    private Label plus_label9;
    private Label plus_label10;
    private Label plus_label11;

    private Skin skin1;

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
        goback_bth = new ImageButton(new TextureRegionDrawable(new Texture("Buttons/back.png")));
        item_widht = 530;
        items_amount = 11;

        skin1 = new Skin(Gdx.files.internal("SkinJson/buybtn.json"));

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Pangolin-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 30;
        fontParameter.characters = FONT_CHARACTERS;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        fontParameter.size = 23;
        font_for_description = fontGenerator.generateFont(fontParameter);
        style_for_description = new Label.LabelStyle(font_for_description, Color.BLACK);
        style_for_name = new Label.LabelStyle(bitmapFont, Color.BLACK);
        fontParameter.size = 20;
        font_for_description = fontGenerator.generateFont(fontParameter);
        font_for_plus = fontGenerator.generateFont(fontParameter);
        style_for_plus = new Label.LabelStyle(font_for_plus, Color.BLACK);


        name_of_item1 = new Label("Деревянная лопата", style_for_name);
        name_of_item2 = new Label("Кирка", style_for_name);
        name_of_item3 = new Label("Деревянный гроб", style_for_name);
        name_of_item4 = new Label("Вилы и факел", style_for_name);
        name_of_item5 = new Label("Осиновый кол", style_for_name);
        name_of_item6 = new Label("Револьвер 1864 года", style_for_name);
        name_of_item7 = new Label("Контактная мина", style_for_name);
        name_of_item8 = new Label("Пулемет", style_for_name);
        name_of_item9 = new Label("Бульдозер", style_for_name);
        name_of_item10 = new Label("Святой Альманах", style_for_name);
        name_of_item11 = new Label("Крест и дробовик", style_for_name);

        description_1 = new Label("Теперь я могу" + "\n" + "их закапывать!", style_for_description);
        description_2 = new Label("         Это все, что я" + "\n" + "могу для тебя сделать.", style_for_description);
        description_3 = new Label("Спи спокойно.", style_for_description);
        description_4 = new Label("Ты не пройдешь!", style_for_description);
        description_5 = new Label("А вдруг сработает?", style_for_description);
        description_6 = new Label("Наконец-то я могу" + "\n" + "     не подходить.", style_for_description);
        description_7 = new Label("      Эх, теперь по" + "\n" + "кусочкам собирать...", style_for_description);
        description_8 = new Label("Ускорим процесс.", style_for_description);
        description_9 = new Label("А ямы все глубже...", style_for_description);
        description_10 = new Label("Хватит насилия.", style_for_description);
        description_11 = new Label("Как в старые" + "\n" + "добрые времена...", style_for_description);

        number_label1 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem1_number()), style_for_description);
        number_label2 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem2_number()), style_for_description);
        number_label3 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem3_number()), style_for_description);
        number_label4 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem4_number()), style_for_description);
        number_label5 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem5_number()), style_for_description);
        number_label6 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem6_number()), style_for_description);
        number_label7 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem7_number()), style_for_description);
        number_label8 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem8_number()), style_for_description);
        number_label9 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem9_number()), style_for_description);
        number_label10 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem11_number()), style_for_description);
        number_label11 = new Label("" + (100 - zombieClicker.getShopNumerics().getItem11_number()), style_for_description);

        plus_label1 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_value()), style_for_plus);
        plus_label2 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem2_value()), style_for_plus);
        plus_label3 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem3_value()), style_for_plus);
        plus_label4 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem4_value()), style_for_plus);
        plus_label5 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem5_value()), style_for_plus);
        plus_label6 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem6_value()), style_for_plus);
        plus_label7 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem7_value()), style_for_plus);
        plus_label8 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem8_value()), style_for_plus);
        plus_label9 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem9_value()), style_for_plus);
        plus_label10 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem10_value()), style_for_plus);
        plus_label11 = new Label("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem11_value()), style_for_plus);

        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.setMainGame();
                dispose();
            }
        });

        goback_bth.setPosition(0, 850);

        b1 = new TextButton("", skin1);
        b2 = new TextButton("", skin1);
        b3 = new TextButton("", skin1);
        b4 = new TextButton("", skin1);
        b5 = new TextButton("", skin1);
        b5 = new TextButton("", skin1);
        b6 = new TextButton("", skin1);
        b7 = new TextButton("", skin1);
        b8 = new TextButton("", skin1);
        b9 = new TextButton("", skin1);
        b10 = new TextButton("", skin1);
        b11 = new TextButton("", skin1);

        b1.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_cost()));
        b2.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem2_cost()));
        b3.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem3_cost()));
        b4.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem4_cost()));
        b5.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem5_cost()));
        b6.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem6_cost()));
        b7.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem7_cost()));
        b8.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem8_cost()));
        b9.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem9_cost()));
        b10.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem10_cost()));
        b11.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem11_cost()));

        //работает заебись. вроде
        b1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem1_number() > 0 && !b1.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem1_cost());  //вычитаем бабло за покупку
                    zombieClicker.getShopNumerics().plus_Item1_value();   //увеличиваем прибавку на следующую покупку
                    zombieClicker.getShopNumerics().plusItem1_cost();     //увеличиваем цену на след покупку
                    b1.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_cost()));   //обновляем текст цены
                    zombieClicker.getShopNumerics().setItem1_number(zombieClicker.getShopNumerics().getItem1_number() - 1);  //прибавляем 1 к счетчику сколько раз купили
                    number_label1.setText(100 - zombieClicker.getShopNumerics().getItem1_number());  //обновляем текст сколько раз куплено
                    plus_label1.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_value())); //обновляем текст сколько прибавляе
                }
            }
        });

        b2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem2_number() > 0 && !b2.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem2_cost());
                    zombieClicker.getShopNumerics().plusItem2_cost();
                    b2.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem2_cost()));
                    zombieClicker.getShopNumerics().plus_Item2_value();
                    zombieClicker.getShopNumerics().setItem2_number(zombieClicker.getShopNumerics().getItem2_number() - 1);
                    number_label2.setText(100 - zombieClicker.getShopNumerics().getItem2_number());
                    plus_label2.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem2_value())); //обновление строки
                }
            }
        });

        b3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem3_number() > 0 && !b3.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem3_cost());
                    zombieClicker.getShopNumerics().plusItem3_cost();
                    b3.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem3_cost()));
                    zombieClicker.getShopNumerics().plus_Item3_value();
                    zombieClicker.getShopNumerics().setItem3_number(zombieClicker.getShopNumerics().getItem3_number() - 1);
                    number_label3.setText(100 - zombieClicker.getShopNumerics().getItem3_number());
                    plus_label3.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem3_value())); //обновление строки
                }
            }
        });

        b4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem4_number() > 0 && !b4.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem4_cost());
                    zombieClicker.getShopNumerics().plusItem4_cost();
                    b4.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem4_cost()));
                    zombieClicker.getShopNumerics().plus_Item4_value();
                    zombieClicker.getShopNumerics().setItem4_number(zombieClicker.getShopNumerics().getItem4_number() - 1);
                    number_label4.setText(100 - zombieClicker.getShopNumerics().getItem4_number());
                    plus_label4.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem4_value())); //обновление строки
                }
            }
        });

        b5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem5_number() > 0 && !b5.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem5_cost());
                    zombieClicker.getShopNumerics().plusItem5_cost();
                    b5.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem5_cost()));
                    zombieClicker.getShopNumerics().plus_Item5_value();
                    zombieClicker.getShopNumerics().setItem5_number(zombieClicker.getShopNumerics().getItem5_number() - 1);
                    number_label5.setText(100 - zombieClicker.getShopNumerics().getItem5_number());
                    plus_label5.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem5_value())); //обновление строки
                }
            }
        });

        b6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem6_number() > 0 && !b6.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem6_cost());
                    zombieClicker.getShopNumerics().plusItem6_cost();
                    b6.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem6_cost()));
                    zombieClicker.getShopNumerics().plus_Item6_value();
                    zombieClicker.getShopNumerics().setItem6_number(zombieClicker.getShopNumerics().getItem6_number() - 1);
                    number_label6.setText(100 - zombieClicker.getShopNumerics().getItem6_number());
                    plus_label6.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem6_value())); //обновление строки
                }
            }
        });

        b7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem7_number() > 0 && !b7.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem7_cost());
                    zombieClicker.getShopNumerics().plusItem7_cost();
                    b7.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem7_cost()));
                    zombieClicker.getShopNumerics().plus_Item7_value();
                    zombieClicker.getShopNumerics().setItem7_number(zombieClicker.getShopNumerics().getItem7_number() - 1);
                    number_label7.setText(100 - zombieClicker.getShopNumerics().getItem7_number());
                    plus_label7.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem7_value())); //обновление строки
                }
            }
        });

        b8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem8_number() > 0 && !b8.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem8_cost());
                    zombieClicker.getShopNumerics().plusItem8_cost();
                    b8.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem8_cost()));
                    zombieClicker.getShopNumerics().plus_Item8_value();
                    zombieClicker.getShopNumerics().setItem8_number(zombieClicker.getShopNumerics().getItem8_number() - 1);
                    number_label8.setText(100 - zombieClicker.getShopNumerics().getItem8_number());
                    plus_label8.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem8_value())); //обновление строки
                }
            }
        });

        b9.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem9_number() > 0 && !b9.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem9_cost());
                    zombieClicker.getShopNumerics().plusItem9_cost();
                    b9.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem9_cost()));
                    zombieClicker.getShopNumerics().plus_Item9_value();
                    zombieClicker.getShopNumerics().setItem9_number(zombieClicker.getShopNumerics().getItem9_number() - 1);
                    number_label9.setText(100 - zombieClicker.getShopNumerics().getItem9_number());
                    plus_label9.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem9_value())); //обновление строки
                }
            }
        });

        b10.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem10_number() > 0 && !b10.isDisabled()) {

                    zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem10_cost());
                    zombieClicker.getShopNumerics().plusItem10_cost();
                    b10.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem10_cost()));
                    zombieClicker.getShopNumerics().plus_Item10_value();
                    zombieClicker.getShopNumerics().setItem10_number(zombieClicker.getShopNumerics().getItem10_number() - 1);
                    number_label10.setText(100 - zombieClicker.getShopNumerics().getItem10_number());
                    plus_label10.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem10_value())); //обновление строки
                }
            }
        });

        b11.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (zombieClicker.getShopNumerics().getItem11_number() > 0 && !b11.isDisabled()) {

                    //zombieClicker.getNumerics().minus_Gold(zombieClicker.getShopNumerics().getItem11_cost());
                    zombieClicker.getShopNumerics().plusItem11_cost();
                    b11.setText(zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem11_cost()));
                    zombieClicker.getShopNumerics().plus_Item11_value();
                    zombieClicker.getShopNumerics().setItem11_number(zombieClicker.getShopNumerics().getItem11_number() - 1);
                    number_label11.setText(100 - zombieClicker.getShopNumerics().getItem11_number());
                    plus_label11.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem11_value())); //обновление строки
                }
            }
        });

        //ВРЕМЕННО !!!!!!!! НЕ БОМБИТЬ
        item1 = new Image(new Texture("item1.png"));
        item2 = new Image(new Texture("item1.png"));
        item3 = new Image(new Texture("item1.png"));
        item4 = new Image(new Texture("item1.png"));
        item5 = new Image(new Texture("item1.png"));
        item6 = new Image(new Texture("item1.png"));
        item7 = new Image(new Texture("item1.png"));
        item8 = new Image(new Texture("item1.png"));
        item9 = new Image(new Texture("item1.png"));
        item10 = new Image(new Texture("item1.png"));
        item11 = new Image(new Texture("item1.png"));

        table1 = new Table();
        table2 = new Table();
        table3 = new Table();
        table4 = new Table();
        table5 = new Table();
        table6 = new Table();
        table7 = new Table();
        table8 = new Table();
        table9 = new Table();
        table10 = new Table();
        table11 = new Table();

        s1 = new Stack();
        s2 = new Stack();
        s3 = new Stack();
        s4 = new Stack();
        s5 = new Stack();
        s6 = new Stack();
        s7 = new Stack();
        s8 = new Stack();
        s9 = new Stack();
        s10 = new Stack();
        s11 = new Stack();

        table1.add(s1);
        table2.add(s2);
        table3.add(s3);
        table4.add(s4);
        table5.add(s5);
        table6.add(s6);
        table7.add(s7);
        table8.add(s8);
        table9.add(s9);
        table10.add(s10);
        table11.add(s11);

        s1.add(item1);
        s2.add(item2);
        s3.add(item3);
        s4.add(item4);
        s5.add(item5);
        s6.add(item6);
        s7.add(item7);
        s8.add(item8);
        s9.add(item9);
        s10.add(item10);
        s11.add(item11);

        intable1 = new Table();
        intable2 = new Table();
        intable3 = new Table();
        intable4 = new Table();
        intable5 = new Table();
        intable6 = new Table();
        intable7 = new Table();
        intable8 = new Table();
        intable9 = new Table();
        intable10 = new Table();
        intable11 = new Table();

        s1.add(intable1);
        s2.add(intable2);
        s3.add(intable3);
        s4.add(intable4);
        s5.add(intable5);
        s6.add(intable6);
        s7.add(intable7);
        s8.add(intable8);
        s9.add(intable9);
        s10.add(intable10);
        s11.add(intable11);

        table_for_nums1 = new Table();
        table_for_nums2 = new Table();
        table_for_nums3 = new Table();
        table_for_nums4 = new Table();
        table_for_nums5 = new Table();
        table_for_nums6 = new Table();
        table_for_nums7 = new Table();
        table_for_nums8 = new Table();
        table_for_nums9 = new Table();
        table_for_nums10 = new Table();
        table_for_nums11 = new Table();

        table_for_nums1.add(number_label1).expandX().right().top().padTop(5).padRight(10);
        table_for_nums1.row();
        table_for_nums1.add(plus_label1).expand().right().top().padRight(20);

        table_for_nums2.add(number_label2).expandX().right().top().padTop(5).padRight(5);
        table_for_nums2.row();
        table_for_nums2.add(plus_label2).expand().right().top();

        table_for_nums3.add(number_label3).expandX().right().top().padTop(5).padRight(10);
        table_for_nums3.row();
        table_for_nums3.add(plus_label3).expand().right().top();

        table_for_nums4.add(number_label4).expandX().right().top().padTop(5).padRight(5);
        table_for_nums4.row();
        table_for_nums4.add(plus_label4).expand().right().top();

        table_for_nums5.add(number_label5).expandX().right().top().padTop(5).padRight(5);
        table_for_nums5.row();
        table_for_nums5.add(plus_label5).expand().right().top();

        table_for_nums6.add(number_label6).expandX().right().top().padTop(5).padRight(5);
        table_for_nums6.row();
        table_for_nums6.add(plus_label6).expand().right().top();

        table_for_nums7.add(number_label7).expandX().right().top().padTop(5).padRight(5);
        table_for_nums7.row();
        table_for_nums7.add(plus_label7).expand().right().top();

        table_for_nums8.add(number_label8).expandX().right().top().padTop(5).padRight(5);
        table_for_nums8.row();
        table_for_nums8.add(plus_label8).expand().right().top();

        table_for_nums9.add(number_label9).expandX().right().top().padTop(5).padRight(5);
        table_for_nums9.row();
        table_for_nums9.add(plus_label9).expand().right().top();

        table_for_nums10.add(number_label10).expandX().right().top().padTop(5).padRight(5);
        table_for_nums10.row();
        table_for_nums10.add(plus_label10).expand().right().top();

        table_for_nums11.add(number_label11).expandX().right().top().padTop(5).padRight(5);
        table_for_nums11.row();
        table_for_nums11.add(plus_label11).expand().right().top();

        s1.add(table_for_nums1);
        s2.add(table_for_nums2);
        s3.add(table_for_nums3);
        s4.add(table_for_nums4);
        s5.add(table_for_nums5);
        s6.add(table_for_nums6);
        s7.add(table_for_nums7);
        s8.add(table_for_nums8);
        s9.add(table_for_nums9);
        s10.add(table_for_nums10);
        s11.add(table_for_nums11);

        //name_of_item1.getGlyphLayout().width; - ширина текста В КОНСТРУКТОРЕ НЕ ОПРЕДЕЛЕНА почему-то - ЭТО ПРОБЛЕМА

        intable1.add(name_of_item1).expand().right().top().padRight((item_widht - 249) / 2 - 110); // 530 - ширина окна предмета, 249 - ширина текста, 110 - ширина колонки с кнопкой
        intable1.row();
        intable1.add(description_1).expand().right().padRight((item_widht - 156) / 2 - 110);
        intable1.add(b1).padRight(10).bottom().padBottom(10);

        intable2.add(name_of_item2).expand().right().top().padRight((item_widht - 73) / 2 - 110);
        intable2.row();
        intable2.add(description_2).expand().right().padRight((item_widht - 238) / 2 - 110);
        intable2.add(b2).padRight(10).bottom().padBottom(10);

        intable3.add(name_of_item3).expand().right().top().padRight((item_widht - 221) / 2 - 110);
        intable3.row();
        intable3.add(description_3).expand().right().padRight((item_widht - 140) / 2 - 110);
        intable3.add(b3).padRight(10).bottom().padBottom(10);

        intable4.add(name_of_item4).expand().right().top().padRight((item_widht - 175) / 2 - 110);
        intable4.row();
        intable4.add(description_4).expand().right().padRight((item_widht - 169) / 2 - 110);
        intable4.add(b4).padRight(10).bottom().padBottom(10);

        intable5.add(name_of_item5).expand().right().top().padRight((item_widht - 180) / 2 - 110);
        intable5.row();
        intable5.add(description_5).expand().right().padRight((item_widht - 196) / 2 - 110);
        intable5.add(b5).padRight(10).bottom().padBottom(10);

        intable6.add(name_of_item6).expand().right().top().padRight((item_widht - 274) / 2 - 110);
        intable6.row();
        intable6.add(description_6).expand().right().padRight((item_widht - 188) / 2 - 110);
        intable6.add(b6).padRight(10).bottom().padBottom(10);

        intable7.add(name_of_item7).expand().right().top().padRight((item_widht - 220) / 2 - 110);
        intable7.row();
        intable7.add(description_7).expand().right().padRight((item_widht - 207) / 2 - 110);
        intable7.add(b7).padRight(10).bottom().padBottom(10);

        intable8.add(name_of_item8).expand().right().top().padRight((item_widht - 114) / 2 - 110);
        intable8.row();
        intable8.add(description_8).expand().right().padRight((item_widht - 180) / 2 - 110);
        intable8.add(b8).padRight(10).bottom().padBottom(10);

        intable9.add(name_of_item9).expand().right().top().padRight((item_widht - 140) / 2 - 110);
        intable9.row();
        intable9.add(description_9).expand().right().padRight((item_widht - 191) / 2 - 110);
        intable9.add(b9).padRight(10).bottom().padBottom(10);

        intable10.add(name_of_item10).expand().right().top().padRight((item_widht - 226) / 2 - 110);
        intable10.row();
        intable10.add(description_10).expand().right().padRight((item_widht - 157) / 2 - 110);
        intable10.add(b10).padRight(10).bottom().padBottom(10);

        intable11.add(name_of_item11).expand().right().top().padRight((item_widht - 231) / 2 - 110);
        intable11.row();
        intable11.add(description_11).expand().right().padRight((item_widht - 127) / 2 - 110);
        intable11.add(b11).padRight(10).bottom().padBottom(10);

        table = new Table();
        table.setWidth(530);
        table.setHeight(130 * items_amount);

        table.add(table1).padBottom(10);
        table.row();
        table.add(table2).padBottom(10);
        table.row();
        table.add(table3).padBottom(10);
        table.row();
        table.add(table4).padBottom(10);
        table.row();
        table.add(table5).padBottom(10);
        table.row();
        table.add(table6).padBottom(10);
        table.row();
        table.add(table7).padBottom(10);
        table.row();
        table.add(table8).padBottom(10);
        table.row();
        table.add(table9).padBottom(10);
        table.row();
        table.add(table10).padBottom(10);
        table.row();
        table.add(table11);
        table.row();


        scrollPane = new ScrollPane(table);
        scrollPane.setPosition((540 - item_widht) / 2, 30);
        scrollPane.setHeight(130 * 6);
        scrollPane.setWidth(item_widht);
        scrollPane.setSmoothScrolling(true);


        stage.addActor(ImageBG);
        stage.addActor(goback_bth);
        stage.addActor(scrollPane);
        //stage.setDebugAll(true);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    public void update() {
        //проверка на бабло
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem1_cost()) < 0) {
            b1.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem2_cost()) < 0) {
            b2.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem3_cost()) < 0) {
            b3.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem4_cost()) < 0) {
            b4.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem5_cost()) < 0) {
            b5.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem6_cost()) < 0) {
            b6.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem7_cost()) < 0) {
            b7.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem8_cost()) < 0) {
            b8.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem9_cost()) < 0) {
            b9.setDisabled(true);
        }
        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem10_cost()) < 0) {
            b10.setDisabled(true);
        }
//        if (zombieClicker.getNumerics().getGold().compareTo(zombieClicker.getShopNumerics().getItem11_cost()) < 0) {
//            b11.setDisabled(true);
//        }
    }

    @Override
    public void render(float delta) {
        update();

        stage.act();
        stage.draw();

        batch.begin();
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 250, 850);
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
