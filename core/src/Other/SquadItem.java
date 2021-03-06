package Other;


import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

import java.math.BigInteger;

import javax.swing.GroupLayout;

public class SquadItem {

    private ZombieClicker zombieClicker;

    private Stack stack;
    private Table table;
    private Table intable;
    private Table name_table;
    private Table description_table;
    private Table icon_table;
    private Image image;
    private Image icon;
    private Label name_of_squad;
    private Label description_label;
    private Label DPS;
    private Label crit;
    private int buy_number;

    private BigInteger dps;
    private BigInteger squad_cost;

    private String send_btn_string = "ОТПРАВИТЬ";
    private String send_btn_cancel_string = "CANCEL";

    private TextButton buy_btn;
    private TextButton send_to_location_btn;
    private TextButton number_btn;

    private int status; // 0 - свободен, 1,2... - на какой-то локации, -1 - на задании
    private boolean bought; //куплен или нет

    private Array<Image> mini_icons;

    private int crit_chance;
    private BigInteger base_cost;
    private double cost_koeff;
    private BigInteger base_dps;
    private double dps_koeff;

    public SquadItem(final ZombieClicker zc, String name, String description, BigInteger base_cost, double cost_koeff, BigInteger base_dps, double dps_koeff, final int critchance, TextureAtlas.AtlasRegion _icon){
        zombieClicker = zc;

        this.base_cost = base_cost;
        this.cost_koeff = cost_koeff;
        this.base_dps = base_dps;
        this.dps_koeff = dps_koeff;
        this.crit_chance = critchance;

        stack = new Stack();
        table = new Table();
        icon_table = new Table();
        intable = new Table();
        name_table = new Table();
        description_table = new Table();
        image = new Image(zombieClicker.get_assets().get_asset_manager().get("Squads/squad_item_bg.png", Texture.class));
        icon = new Image(_icon);
        buy_number = 0;
        squad_cost = base_cost;
        dps = base_dps;
        status = 0;
        bought = false;

        buy_btn = new TextButton("0", zombieClicker.get_assets().get_asset_manager().get("Buttons/buybtn.json", Skin.class));
        name_of_squad = new Label(name, zombieClicker.get_assets().get_asset_manager().get("LabelSkins/name_label_skin.json", Skin.class));
        name_of_squad.setAlignment(Align.center);
        name_of_squad.setWrap(true);
        description_label = new Label(description, zombieClicker.get_assets().get_asset_manager().get("Squads/desc_label_skin.json", Skin.class));
        description_label.setAlignment(Align.bottomLeft);
        description_label.setWrap(true);
        DPS = new Label("DPS: +" + zombieClicker.getNumerics().bigInteger_to_string(dps), zombieClicker.get_assets().get_asset_manager().get("LabelSkins/description_label_skin.json", Skin.class));
        crit = new Label("Crit. chance: " + Integer.toString(crit_chance) + "%", zombieClicker.get_assets().get_asset_manager().get("LabelSkins/description_label_skin.json", Skin.class));
        DPS.setFontScale(0.8f);
        crit.setFontScale(0.8f);
        send_to_location_btn = new TextButton(send_btn_string, zombieClicker.get_assets().get_asset_manager().get("Squads/send_btn_skin.json", Skin.class));
        number_btn = new TextButton(Integer.toString(buy_number), zombieClicker.get_assets().get_asset_manager().get("Other/buy_counter_skin.json", Skin.class));

        mini_icons = new Array<Image>();

        buy_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!buy_btn.isDisabled()){
                    buy_number++;
                    zombieClicker.getNumerics().minus_Gold(squad_cost);
                    plus_cost();
                    if(!bought){
                        zombieClicker.getShop().setSpare_squads_counter(1);
                    }
                    plus_DPS();
                    if(crit_chance < 100 && buy_number % 2 == 0){
                        crit_chance++;
                    }
                    bought = true;

                    zombieClicker.getShop().saveAllSquadItems();
                }
            }
        });

        send_to_location_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(status != -1)
                    if(!send_to_location_btn.isDisabled()){
                        disable_send_btn();
                        zombieClicker.setSquadsDistScreen(zombieClicker, getSquadItem());
                    } else { //cancel
                        activate_send_btn();
                        zombieClicker.getMyThread().remove_squad_from_location(getSquadItem(), status);
                        zombieClicker.getShop().setSpare_squads_counter(1);
                        status = 0;
                    }
                zombieClicker.getShop().saveAllSquadItems();
            }
        });

        table.add(stack);
        stack.add(image);
        stack.add(intable);
        stack.add(name_table);
        name_table.add(name_of_squad).expandY().top().padTop(5).width(300);
        name_table.row();


        intable.add(number_btn).expand().right().padTop(10);
        intable.row();
        intable.add(buy_btn).expand().right().padTop(name_table.getPadY());
        intable.row();
        intable.add(send_to_location_btn).expand().right();
        name_table.add(DPS).expandY().top().padBottom(5).height(1);
        name_table.row();
        name_table.add(crit).expandY().top().padBottom(5).height(1);
        stack.add(icon_table);
        icon_table.add(icon).expand().left().padTop(20);

        stack.add(description_table);
        description_table.add(description_label).expand().bottom().left().width(380);

    }

    public SquadItem getSquadItem(){
        return this;
    }

    public void update_buy_label(){
        buy_btn.setText(zombieClicker.getNumerics().bigInteger_to_string(squad_cost));
        send_to_location_btn.setVisible(bought);
        number_btn.setText(Integer.toString(buy_number));
        crit.setText("Crit. chance: " + Integer.toString(crit_chance) + "%");
        DPS.setText("DPS: +" + zombieClicker.getNumerics().bigInteger_to_string(dps));
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

    public double getCost_koeff() {
        return cost_koeff;
    }

    public double getDps_koeff() {
        return dps_koeff;
    }

    public int getCrit_chance(){
        return  crit_chance;
    }

    public int getBuy_number(){
        return buy_number;
    }
    //////////////////GETTERS////////////////////////


    //////////////////SETTERS////////////////////////
    public void setSquad_cost(BigInteger x){
        squad_cost = x;
    }

    public void setStatus(int x){
        status = x;
    }

    public void setBought(boolean x){
        bought = x;
    }

    public void setCost_koeff(double cost_koeff) {
        this.cost_koeff = cost_koeff;
    }

    public void setDps_koeff(double dps_koeff) {
        this.dps_koeff = dps_koeff;
    }

    public void setCrit_chance(int x){
        crit_chance = x;
    }

    public void setBuy_number(int x){
        buy_number = x;
    }

    public void setDps(BigInteger x){
        dps = x;
    }
    //////////////////SETTERS////////////////////////

    public void dispose(){
        zombieClicker.get_assets().get_asset_manager().unload("Shop/squad_item_bg.png");
    }

}
