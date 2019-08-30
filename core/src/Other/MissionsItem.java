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

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MissionsItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Image bg;
    private String rareness;
    private String mission;
    private String time;
    private float rndDgre;
    private float x, y;
    private int id;
    private boolean isActive;
    private boolean isCompleted;

    private int squadOnDuty;
    private Table timeTable;
    private Label timeLabel;
    private long duration;
    private Date missionStart_date;
    private long start_time_millis;

    private BigInteger goldReward;
    private int diamondReward;

    public MissionsItem(final ZombieClicker zc, String rareness, String mission, long _duration, float x, float y, BigInteger _goldReward, int _diamondReward) {
        zombieClicker = zc;
        stack = new Stack();
        this.x = x;
        this.y = y;
        this.duration = _duration;
        isActive = false;
        isCompleted = false;
        id = zombieClicker.getNumerics().getMissionsItem().size();
        Texture paper = zombieClicker.get_assets().get_asset_manager().get("Other/paper.png", Texture.class);
        paper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bg = new Image(paper);
        missionStart_date = new Date();
        timeLabel = new Label("", zombieClicker.getFontManager().getDescription_labelStyle());
        timeTable = new Table();
        time = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );

        zombieClicker.getFontManager().getDescription_labelStyle().font.getData().setScale(.28f);
        timeLabel.setText(String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(duration),
                TimeUnit.MILLISECONDS.toMinutes(duration) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        ));

        this.rareness = rareness;
        this.mission = mission;
        this.goldReward = _goldReward;
        this.diamondReward = _diamondReward;

        rndDgre = MathUtils.random(-10, 10);
        bg.setRotation(rndDgre);
        stack.setRotation(rndDgre);
        timeTable.setTransform(true);
        timeTable.setRotation(rndDgre);

        stack.add(bg);
        stack.add(timeTable);
        timeTable.add(timeLabel).expand().bottom().padBottom(10);

        stack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isActive && !isCompleted) {
                    zombieClicker.getNumerics().setIdMission(id);
                    zombieClicker.setTipScreen("", "", "Missions", id);
                }
                if(isCompleted){
                    timeLabel.setColor(Color.WHITE);
                    zombieClicker.getNumerics().plus_gold(goldReward);
                    zombieClicker.getNumerics().plus_diamonds(diamondReward);
                }
            }
        });

        stack.setPosition(this.x, this.y);
    }

    public void update_status(){
        if(isActive){
            zombieClicker.getFontManager().getDescription_labelStyle().font.getData().setScale(.28f);
            timeLabel.setText(String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis)),
                    TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration - (System.currentTimeMillis() - start_time_millis))),
                    TimeUnit.MILLISECONDS.toSeconds(duration - (System.currentTimeMillis() - start_time_millis)) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration - (System.currentTimeMillis() - start_time_millis)))
            ));
            if(duration - (System.currentTimeMillis() - start_time_millis) <= 0){
                isActive = false;
                isCompleted = true;
                timeLabel.setColor(Color.GREEN);
                zombieClicker.getShop().getSquadItems_array().get(squadOnDuty).setStatus(0);
            }
        }
        if(isCompleted){
            timeLabel.setText("Completed");
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

    public void setCompleted(boolean x){
        isCompleted = x;
    }

    public BigInteger getGoldReward() {
        return goldReward;
    }

    public int getDiamondReward() {
        return diamondReward;
    }
}
