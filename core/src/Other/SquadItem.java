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

import java.math.BigInteger;

public class SquadItem {

    private ZombieClicker zombieClicker;

    private Stack stack;
    private Table table;
    private Table intable;
    private Table name_table;
    private Table abilities_table;
    private Table description_table;
    private Image image;
    private Label name_of_squad;
    private Label description_label;
    private Label DPS;
    private int buy_number;

    private BigInteger dps;
    private BigInteger squad_cost;
    private BigInteger kill_reward;

    private String send_btn_string = "ОТПРАВИТЬ";
    private String send_btn_cancel_string = "CANCEL";

    private TextButton buy_btn;
    private TextButton send_to_location_btn;
    private TextButton number_btn;

    private int status; // 0 - свободен, 1,2... - на какой-то локации, -1 - на задании
    private boolean bought; //куплен или нет

    public SquadItem(final ZombieClicker zc, String name, String description, Texture texture){
        zombieClicker = zc;

        stack = new Stack();
        table = new Table();
        intable = new Table();
        name_table = new Table();
        abilities_table = new Table();
        description_table = new Table();
        image = new Image(texture);
        buy_number = 0;
        squad_cost = new BigInteger("10");
        kill_reward = new BigInteger("10");
        dps = new BigInteger("1");
        status = 0;
        bought = false;


        buy_btn = new TextButton("0", zombieClicker.get_assets().get_asset_manager().get("Buttons/buybtn.json", Skin.class));
        name_of_squad = new Label(name, zombieClicker.get_assets().get_asset_manager().get("LabelSkins/name_label_skin.json", Skin.class));
        description_label = new Label(description, zombieClicker.get_assets().get_asset_manager().get("Squads/desc_label_skin.json", Skin.class));
        DPS = new Label("DPS: +" + zombieClicker.getNumerics().bigInteger_to_string(dps), zombieClicker.get_assets().get_asset_manager().get("LabelSkins/description_label_skin.json", Skin.class));
        send_to_location_btn = new TextButton(send_btn_string, zombieClicker.get_assets().get_asset_manager().get("Squads/send_btn_skin.json", Skin.class));
        number_btn = new TextButton(Integer.toString(buy_number), zombieClicker.get_assets().get_asset_manager().get("Other/buy_counter_skin.json", Skin.class));

        send_to_location_btn.setVisible(false);

        buy_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!buy_btn.isDisabled()){
                    buy_number++;
                    number_btn.setText(Integer.toString(buy_number));
                    zombieClicker.getNumerics().minus_Gold(squad_cost);
                    send_to_location_btn.setVisible(true);
                    plus_cost();
                    if(!bought){
                        zombieClicker.getShop().setSpare_squads_counter(1);
                    }
                    plus_DPS();
                    DPS.setText("DPS: +" + zombieClicker.getNumerics().bigInteger_to_string(dps));
                    bought = true;
                }
            }
        });

        send_to_location_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!send_to_location_btn.isDisabled()){
                    disable_send_btn();
                    zombieClicker.setSquadsDistScreen(zombieClicker, getSquadItem());
                } else {
                    activate_send_btn();
                    zombieClicker.getMyThread().remove_squad_from_location(getSquadItem(), status);
                    zombieClicker.getShop().setSpare_squads_counter(1);
                    status = 0;
                }

            }
        });


        table.add(stack);
        stack.add(image);
        stack.add(intable);
        intable.add(number_btn).expand().right().padTop(5);
        intable.row();
        intable.add(buy_btn).expand().right();
        intable.row();
        intable.add(send_to_location_btn).expand().right();
        stack.add(name_table);
        name_table.add(name_of_squad).expandY().top().padTop(10);
        stack.add(abilities_table);
        abilities_table.add(DPS).expandY();
        stack.add(description_table);
        description_table.add(description_label).expand().left().bottom();


    }

    public SquadItem getSquadItem(){
        return this;
    }

    public void update_buy_label(){
        buy_btn.setText(zombieClicker.getNumerics().bigInteger_to_string(squad_cost));
    }

    public void plus_cost(){
        squad_cost = squad_cost.add(BigInteger.valueOf(25));
    }

    public void plus_DPS(){
        dps = dps.add(BigInteger.valueOf(1));
    }

    public void disable_buy_btn(boolean x){
            buy_btn.setDisabled(x);
    }

    public void disable_send_btn(){
            send_to_location_btn.setDisabled(true);
            send_to_location_btn.setText(send_btn_cancel_string);
    }

    public void activate_send_btn(){
        send_to_location_btn.setDisabled(false);
        send_to_location_btn.setText(send_btn_string);
    }

    //////////////////GETTERS////////////////////////
    public Table get_table(){
        return table;
    }

    public TextButton getSend_to_location_btn(){
        return send_to_location_btn;
    }

    public TextButton getBuy_btn(){
        return buy_btn;
    }

    public int getStatus(){
        return status;
    }

    public boolean isBought(){
        return bought;
    }

    public BigInteger getDps(){
        return dps;
    }

    public BigInteger getSquad_cost(){
        return squad_cost;
    }
    //////////////////GETTERS////////////////////////


    //////////////////SETTERS////////////////////////
    public void setKill_reward(BigInteger x){
        kill_reward = kill_reward.add(x);
    }

    public void setSquad_cost(BigInteger x){
        squad_cost = x;
    }

    public void setStatus(int x){
        status = x;
    }

    public void setBought(boolean x){
        bought = x;
    }
    //////////////////SETTERS////////////////////////

    public void dispose(){
        zombieClicker.get_assets().get_asset_manager().unload("Shop/squad_item_bg.png");
    }

}
