package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MissionsItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Image reward;
    private Image bg;
    private Label.LabelStyle labelStyle;
    private String rareness;
    private String mission;
    private String time = "";
    private float rndDgre;
    private float x, y;
    private int rew;
    private int id;
    private boolean isActive;
    // 1 - coins (min)
    // 2 - coins (mid)
    // 3 - coins (max)
    // 4 - diamonds (min)
    // 5 - diamonds (mid)
    // 6 - diamonds (max)

    private int squadOnDuty;
    private Table timeTable;
    private Label timeLabel;
    private long duration;
    private Date missionStart_date;
    private long start_time_millis;

    public MissionsItem(final ZombieClicker zc, String rareness, String mission, long _duration, int rew, float x, float y) {
        // time в МИЛЛИсекундах
        zombieClicker = zc;
        stack = new Stack();
        this.x = x;
        this.y = y;
        this.duration = _duration;
        isActive = false;
        id = zombieClicker.getNumerics().getMissionsItem().size();
        Texture paper = zombieClicker.get_assets().get_asset_manager().get("Other/paper.png", Texture.class);
        paper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bg = new Image(paper);
        missionStart_date = new Date();
        zombieClicker.getFontManager().getDescription_labelStyle().font.getData().setScale(.35f);
        timeLabel = new Label("", zombieClicker.getFontManager().getDescription_labelStyle());
        timeTable = new Table();

        Texture rew_tex = null;
        if (rew == 1)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if (rew == 2)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if (rew == 3)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/cp_2.png", Texture.class);
        if (rew == 4)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);
        if (rew == 5)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);
        if (rew == 6)
            rew_tex = zombieClicker.get_assets().get_asset_manager().get("Missions/dp_2.png", Texture.class);

        rew_tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        reward = new Image(rew_tex);

        zombieClicker.getFontManager().setBitmapFont_invent_Scale(.25f);
        labelStyle = new Label.LabelStyle(zombieClicker.getFontManager().getFont_border(), Color.WHITE);
        timeLabel.setText(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        ));


        this.rareness = rareness;
        this.mission = mission;
        this.time = "" + duration;

        rndDgre = MathUtils.random(-10, 10);
        bg.setRotation(rndDgre);
        reward.setRotation(rndDgre);
        stack.setRotation(rndDgre);

        stack.add(bg);
        stack.add(reward);
        stack.add(timeTable);
        timeTable.add(timeLabel).expand().bottom();

        stack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isActive) {
                    zombieClicker.getNumerics().setIdMission(id);


                    zombieClicker.setTipScreen("", "", "Missions", id);
                }

            }
        });

        stack.setPosition(x, y);
    }

    public void update_status(){
        if(isActive){
            timeLabel.setText(String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis)),
                    TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis))),
                    TimeUnit.MILLISECONDS.toSeconds(duration - (System.currentTimeMillis() - start_time_millis)) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)))
            ));
            if(duration - (System.currentTimeMillis() - start_time_millis) <= 0){
                isActive = false;
                zombieClicker.getShop().getSquadItems_array().get(squadOnDuty).setStatus(0);
            }
        }

    }

    public Stack getStack() {
        return stack;
    }

    public String getMission() {
        return mission;
    }

    public String getRareness() {
        return rareness;
    }

    public String getTime() {
        return time;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean x) {
        isActive = x;
    }

    public void setMissionStart_date(){
        start_time_millis = TimeUtils.millis();
        zombieClicker.getCalendar().setTimeInMillis(start_time_millis);
        missionStart_date = zombieClicker.getCalendar().getTime();
    }

    public Date getMissionStart_date(){
        return missionStart_date;
    }

    public void setSquadOnDuty(int x){
        squadOnDuty = x;
    }
}
