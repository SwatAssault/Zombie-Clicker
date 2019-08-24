package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class OtherItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Table intable;
    private Table label_table;
    private Image otherbg;
    private Image icon;
    private Image diamond_icon;
    private TextButton buy_btn;
    private int cost;               //цена в алмазах
    private int base_cost;
    private double cost_koeff;
    private int max_buy_counter;        //максимальное количество раз, сколько можно купить предмет
    private int current_buy_counter;        //текущее количество раз, сколько купили предмет
    private String name;                   //по нему будет отпределяться, что делается в листенере
    private String description;

    private Label name_label;
    private Label description_label;

    private SimpleDateFormat start_date;
    private int current_time;
    private int active_time;          //сколько длится эффект

    LocalDateTime localDateTime;
    private long time_millis;

    public OtherItem(ZombieClicker zc, final String name_of_item, String description, int max_buy_count, int baseCost, double costKoeff, int activeTime){
        zombieClicker = zc;

        stack = new Stack();
        intable = new Table();
        label_table = new Table();
        otherbg = new Image(zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        cost = 1;
        buy_btn = new TextButton(Integer.toString(cost), zombieClicker.get_assets().get_asset_manager().get("Buttons/buybtn.json", Skin.class));
        this.max_buy_counter = max_buy_count;
        current_buy_counter = 0;
        this.name = name_of_item;
        this.description = description;
        this.base_cost = baseCost;
        this.cost_koeff = costKoeff;
        this.active_time = activeTime;
        start_date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        name_label = new Label(name, zombieClicker.get_assets().get_asset_manager().get("LabelSkins/name_label_skin.json", Skin.class));

        buy_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!buy_btn.isDisabled() && current_buy_counter != max_buy_counter){
                    current_buy_counter++;
                    zombieClicker.getNumerics().minus_diamonds(cost);
                    plusCost(1);
                    buy_btn.setText(Integer.toString(cost));
                    buy_btn.setDisabled(true);
                    if(name.equals("1 hour")){

                    }
                    time_millis = TimeUtils.millis();

                    System.out.println(start_date.format(new Date()));
                }
            }
        });

        stack.add(otherbg);
        stack.add(intable);
        intable.add(buy_btn).expandX().right();
        stack.add(label_table);
        label_table.add(name_label).expandX();
        label_table.row();
        label_table.add(description_label).expandX();
    }

    public void update_status(){

    }

    public void disable_buy_btn(boolean x){
        buy_btn.setDisabled(x);
    }

    public void plusCost(int x){
        cost += x;
    }

    //////////////SETTERS////////////////
    public void setCost(int x){
        cost = x;
    }

    public void setMax_buy_counter(int x){
        max_buy_counter = x;
    }
    //////////////SETTERS////////////////

    //////////////GETTERS////////////////
    public int getCost(){
        return cost;
    }

    public Stack getStack(){
        return stack;
    }

    public TextButton getBuy_btn(){
        return buy_btn;
    }

    public int getMax_buy_counter(){
        return max_buy_counter;
    }

    public int getCurrent_buy_counter() {
        return current_buy_counter;
    }
    //////////////GETTERS////////////////


    public void dispose(){

    }

}
