package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class OtherItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Table intable;
    private Image otherbg;
    private TextButton buy_btn;
    private int cost;               //цена в алмазах
    private int max_buy_counter;        //максимальное количество раз, сколько можно купить предмет
    private int current_buy_counter;        //текущее количество раз, сколько купили предмет
    private String name;                   //по нему будет отпределяться, что делается в листенере

    public OtherItem(ZombieClicker zc, String name_of_item, int max_buy_count){
        zombieClicker = zc;

        stack = new Stack();
        intable = new Table();
        otherbg = new Image(zombieClicker.get_assets().get_asset_manager().get("item1.png", Texture.class));
        cost = 1;
        buy_btn = new TextButton(Integer.toString(cost), zombieClicker.get_assets().get_asset_manager().get("Buttons/buybtn.json", Skin.class));
        this.max_buy_counter = max_buy_count;
        current_buy_counter = 0;
        this.name = name_of_item;

        buy_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!buy_btn.isDisabled() && current_buy_counter <= max_buy_counter){
                    current_buy_counter++;
                    zombieClicker.getNumerics().minus_diamonds(cost);
                    plusCost(1);
                    buy_btn.setText(Integer.toString(cost));

                }
            }
        });

        stack.add(otherbg);
        stack.add(intable);
        intable.add(buy_btn).expandX().right();
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
