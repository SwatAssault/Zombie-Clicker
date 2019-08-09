package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class MissionsItem {

    private final ZombieClicker zombieClicker;

    private Table table_for_text;
    private Table table_for_item;
    private Stack stack;
    private Image reward;
    private Image bg;
    private Button accept_btn;
    private Skin accept_btn_skin;
    private Label.LabelStyle labelStyle;
    private Label rareness;
    private Label mission;
    private Label time;

    public MissionsItem(ZombieClicker zc, String rareness, String mission, int time){
        // time в секундах
        zombieClicker = zc;
        table_for_text = new Table();
        table_for_item = new Table();
        stack = new Stack();

        bg = new Image(zombieClicker.get_assets().get_asset_manager().get("Missions/mission_item_bg.png", Texture.class));
        reward = new Image(zombieClicker.get_assets().get_asset_manager().get("Missions/reward.png", Texture.class));
        accept_btn_skin = zombieClicker.get_assets().get_asset_manager().get("Missions/accept_btn_skin.json", Skin.class);
        accept_btn = new Button(accept_btn_skin);
        accept_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        table_for_item.add(reward).padRight(120);
        table_for_item.add(accept_btn).padLeft(120).padBottom(50);
        table_for_item.align(Align.top).padTop(10);

        zombieClicker.getFontManager().setBitmapFont_invent_Scale(.25f);
        labelStyle = new Label.LabelStyle(zombieClicker.getFontManager().getFont_border(), Color.WHITE);

        this.rareness = new Label(rareness,labelStyle);
        this.mission = new Label(mission, labelStyle);
        this.time = new Label(String.valueOf(time) + " h.", labelStyle);

//        table_for_text.setDebug(true);

        this.mission.setWrap(true);

        table_for_text.add(this.rareness).align(Align.left).padBottom(10);
        table_for_text.row();
        table_for_text.add(this.mission).align(Align.left).width(220).padBottom(10);
        table_for_text.row();
        table_for_text.add(this.time).align(Align.left).padBottom(10);
        table_for_text.row();

        table_for_text.align(Align.left).padLeft(100).padTop(10);

//        table_for_text.

        stack.add(bg);
        stack.add(table_for_item);
        stack.add(table_for_text);

    }

    public Stack getStack(){
        return stack;
    }

}
