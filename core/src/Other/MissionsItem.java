package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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

    private Stack stack;
    private Image reward;
    private Image bg;
    private Button accept_btn;
    private Skin accept_btn_skin;
    private Label.LabelStyle labelStyle;
    private Label rareness;
    private Label mission;
    private Label time;
    private float rndDgre;
    private float x, y;
    private int rew;
    // 1 - coins (min)
    // 2 - coins (mid)
    // 3 - coins (max)
    // 4 - diamonds (min)
    // 5 - diamonds (mid)
    // 6 - diamonds (max)

    public MissionsItem(ZombieClicker zc, String rareness, String mission, int time, int rew, float x, float y) {
        // time в секундах
        zombieClicker = zc;
        stack = new Stack();

        this.x = x;
        this.y = y;

        Texture paper = zombieClicker.get_assets().get_asset_manager().get("Other/paper.png", Texture.class);
        paper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        bg = new Image(paper);

        Texture rew_tex = null;
        if(rew == 1)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if(rew == 2)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if(rew == 3)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if(rew == 4)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);
        if(rew == 5)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);
        if(rew == 6)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);

        rew_tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        reward = new Image(rew_tex);
        accept_btn_skin = zombieClicker.get_assets().get_asset_manager().get("Missions/accept_btn_skin.json", Skin.class);
        accept_btn = new Button(accept_btn_skin);
        accept_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        zombieClicker.getFontManager().setBitmapFont_invent_Scale(.25f);
        labelStyle = new Label.LabelStyle(zombieClicker.getFontManager().getFont_border(), Color.WHITE);

        this.rareness = new Label(rareness, labelStyle);
        this.mission = new Label(mission, labelStyle);
        this.time = new Label(String.valueOf(time/60) + " h.", labelStyle);


        this.mission.setWrap(true);

        rndDgre = MathUtils.random(-10, 10);
        bg.setRotation(rndDgre);
        reward.setRotation(rndDgre);
        stack.setRotation(rndDgre);


        stack.add(bg);
        stack.add(reward);

        stack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("yopta");
            }
        });

        stack.setPosition(x, y);


    }

    public Stack getStack() {
        return stack;
    }

}
