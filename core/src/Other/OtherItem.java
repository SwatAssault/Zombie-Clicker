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

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OtherItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Table intable;
    private Table label_table;
    private Table diamond_table;
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

    private boolean time_action;  // 0 - не связано со временем, 1 - связано

    private boolean time_running = false; //идет ли отсчет

    private long duration;
    private SimpleDateFormat date_format;
    private SimpleDateFormat date_format1;
    private Date start_date;
    private long start_time_millis;

    public OtherItem(ZombieClicker zc, final String name_of_item, String description, int max_buy_count, int baseCost, double costKoeff, long _duration){
        zombieClicker = zc;

        stack = new Stack();
        intable = new Table();
        diamond_table = new Table();
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
        this.duration = _duration;
        if(this.duration != 0){ this.time_action = true; } else this.time_action = false;
        diamond_icon = new Image(zombieClicker.getHud().getHud_icons_atlas().createSprite("diamond"));
        diamond_icon.setScale(0.8f);
        date_format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        date_format1 = new SimpleDateFormat("HH:mm:ss");
        start_date = new Date();

        name_label = new Label(name, zombieClicker.get_assets().get_asset_manager().get("LabelSkins/name_label_skin.json", Skin.class));
        description_label = new Label(description ,zombieClicker.getFontManager().getDescription_labelStyle());

        buy_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!buy_btn.isDisabled() && current_buy_counter != max_buy_counter){
                    current_buy_counter++;
                    zombieClicker.getNumerics().minus_diamonds(cost);
                    plusCost(1);
                    buy_btn.setText(Integer.toString(cost));

                    if(name.equals("1 hour") || name.equals("4 hours")){
                        buy_btn.setDisabled(true);
                        time_running = true;
                        start_time_millis = TimeUtils.millis();
                        zombieClicker.getCalendar().setTimeInMillis(start_time_millis);
                        start_date = zombieClicker.getCalendar().getTime();
                        zombieClicker.getNumerics().setGold_multiplier(2.0f);
                    } else
                        if(name.equals("squad percent")){
                            zombieClicker.getNumerics().plusSquad_reward_percent(10);
                        } else
                            if(name.equals("tap gold")){
                                zombieClicker.getNumerics().plusGold_from_taps(BigInteger.valueOf(10));
                            }

                }
            }
        });

        stack.add(otherbg);
        stack.add(intable);
        intable.add(buy_btn).expandX().right();
        stack.add(label_table);
        label_table.add(name_label).expand();
        label_table.row();
        label_table.add(description_label).expand();
        stack.add(diamond_table);
        diamond_table.add(diamond_icon).expand().right().padRight(130);
    }

    public void update_status(){

    }

    public void update_btn_text(){
        if(time_running){
            buy_btn.setDisabled(true);
            buy_btn.setText(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis)),
                         TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)) -
                         TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis))),
                         TimeUnit.MILLISECONDS.toSeconds(duration - (System.currentTimeMillis() - start_time_millis)) -
                         TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)))
            ));
            if(duration - (System.currentTimeMillis() - start_time_millis) <= 0){
                time_running = false;
                if(!zombieClicker.getShop().getOtherItems_array().get(1).isTime_running() && !zombieClicker.getShop().getOtherItems_array().get(0).isTime_running()){
                    zombieClicker.getNumerics().setGold_multiplier(1.0f);
                }
                buy_btn.setText(Integer.toString(cost));
            }
        }
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

    public boolean isTime_action() {
        return time_action;
    }

    public boolean isTime_running() {
        return time_running;
    }
    //////////////GETTERS////////////////


    public void dispose(){

    }

}
