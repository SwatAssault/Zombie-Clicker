package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.math.BigInteger;

public class ShopItem {

    private ZombieClicker zombieClicker;

    private BigInteger item_cost;
    private BigInteger item_value;
    private int item_number;
    private int item_widht = 530;

    private Stack stack;
    private Table table;
    private Table intable;
    private Image image;
    private Table table_for_nums;
    private TextButton upgrade_btn;
    private String name_of_item = "78";
    private String description_of_item;
    private Label name_label;
    private Label description;
    private Label number_label;
    private Label value_label;

    private Skin label_skin;
    private Skin buy_skin;

    public ShopItem(ZombieClicker zc, String name, String description, Texture texture){
        zombieClicker = zc;

        stack = new Stack();
        table = new Table();
        intable = new Table();
        table_for_nums = new Table();
        item_number = 0;
        item_cost = new BigInteger("0");

        zombieClicker.get_assets().get_asset_manager().load("Buttons/buybtn.json", Skin.class);
        zombieClicker.get_assets().get_asset_manager().update();
        zombieClicker.get_assets().get_asset_manager().finishLoading();
        buy_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/buybtn.json", Skin.class);
        upgrade_btn = new TextButton("", buy_skin);
        label_skin = new Skin(Gdx.files.internal("LabelSkins/name_label_skin.json"));
        upgrade_btn.setText(zombieClicker.getNumerics().bigInteger_to_string(item_cost));
        image = new Image(texture);

        name_label = new Label(name, label_skin);


        upgrade_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!upgrade_btn.isDisabled()) {
                    zombieClicker.getNumerics().minus_Gold(item_cost);  //вычитаем бабло за покупку
                    plusValue();   //увеличиваем прибавку на следующую покупку
                    plusItem_cost();     //увеличиваем цену на след покупку
                    //upgrade_btn.setText(zombieClicker.getNumerics().bigInteger_to_string(item_cost));   //обновляем текст цены
                    item_number++;  //прибавляем 1 к счетчику сколько раз купили
                    //number_label.setText(item_number);
                    //value_label.setText("+" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getShopNumerics().getItem1_value())); //обновляем текст сколько прибавляет


                }
            }
        });

        table.add(stack);
        stack.add(image);
        stack.add(intable);
//        table_for_nums.add(number_label).expandX().right().top().padTop(5).padRight(10);
//        table_for_nums.row();
//        table_for_nums.add(value_label).expand().right().top().padRight(10);
        stack.add(table_for_nums);
        intable.add(name_label).expand().right().top().padTop(5).padRight((item_widht - name_label.getPrefWidth()) / 2 - 110); // 530 - ширина окна предмета, 110 - ширина колонки с кнопкой
        intable.setFillParent(true);
        intable.row();
        //intable.add(description).expand().padBottom(15).right().padRight((item_widht - description.getPrefWidth()) / 2 - 110);
        intable.add(upgrade_btn).expandX().right().padTop(5);

    }

    //TODO можно оптимизировать SKIN для Label (создается для кадого предмета) - неизвестно как 16.07.2019

    public void update_cost_label(){
        upgrade_btn.setText(zombieClicker.getNumerics().bigInteger_to_string(item_cost));
    }

    ////////////////////GETTERS////////////////////////
    public Table getTable(){
        return table;
    }

    public TextButton getUpgrade_btn(){
        return upgrade_btn;
    }

    public BigInteger getItem_cost(){
        return  item_cost;
    }
    ////////////////////GETTERS////////////////////////

    ////////////////////SETTERS////////////////////////
    public void setItem_value(long x){
        item_value = BigInteger.valueOf(x);
    }

    public void setItem_cost(long x){
        item_cost = BigInteger.valueOf(x);
    }

    public void plusItem_cost(){
        item_cost = item_cost.add(BigInteger.valueOf(100));
    }

    public void plusValue(){
        item_value = item_value.add(BigInteger.valueOf(50));
    }

    public void setName_of_item(String x){
        name_of_item = x;
    }

    public void setDescription_of_item(String x){
        description_of_item = x;
    }

    public void disable_button(Boolean x){
        //x ? upgrade_btn.setDisabled(true) : upgrade_btn.setDisabled(false);
        if(x)
            upgrade_btn.setDisabled(true);
        else
            upgrade_btn.setDisabled(false);
    }
    ////////////////////SETTERS////////////////////////

    public void dispose(){
        zombieClicker.get_assets().get_asset_manager().unload("Buttons/buybtn.json");
    }

}
