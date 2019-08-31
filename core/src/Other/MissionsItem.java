package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MissionsItem {

    private final ZombieClicker zombieClicker;

    private Image rewardImage;
    private TextureAtlas stars_atlas;
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
    private TextureAtlas iconsAtlas;
    private Image gold_image;
    private Image diamond_image;
    private Table rewardTable;
    private Table rewardTableWrapper;
    private Label reward_label;
    private Table rewardLabelTable;
    private Table starsTable;

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
        rewardTable = new Table();
        rewardTableWrapper = new Table();
        rewardLabelTable = new Table();
        starsTable = new Table();
        this.x = x;
        this.y = y;
        this.duration = _duration;
        isActive = false;
        isCompleted = false;
        id = zombieClicker.getNumerics().getMissionsItem().size();
        Texture paper = zombieClicker.get_assets().get_asset_manager().get("Other/paper.png", Texture.class);
        paper.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bg = new Image(paper);
        iconsAtlas = zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/hud_atlas.atlas");
        gold_image = new Image(iconsAtlas.createSprite("gold"));
        diamond_image = new Image(iconsAtlas.createSprite("diamond"));
        gold_image.setScale(0.6f);
        diamond_image.setScale(0.6f);
        stars_atlas = zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/starsAtlas.atlas");

        rewardImage = new Image(stars_atlas.createSprite("star1"));
        if(rareness.equals("rare")) {rewardImage = new Image(stars_atlas.createSprite("star2"));} else {rewardImage = new Image(stars_atlas.createSprite("star3"));}
        missionStart_date = new Date();
        reward_label = new Label("Награда", zombieClicker.getFontManager().getDescription_labelStyle());
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
        timeLabel.setText(time);

        this.rareness = rareness;
        this.mission = mission;
        this.goldReward = _goldReward;
        this.diamondReward = _diamondReward;

        rndDgre = MathUtils.random(-10, 10);
        bg.setRotation(rndDgre);
        stack.setRotation(rndDgre);
        timeTable.setTransform(true);
        timeTable.setRotation(rndDgre);
        rewardTableWrapper.setTransform(true);
        rewardTableWrapper.setRotation(rndDgre);
        rewardLabelTable.setTransform(true);
        rewardLabelTable.setRotation(rndDgre);
        starsTable.setTransform(true);
        starsTable.setRotation(rndDgre);

        stack.add(bg);
        stack.add(timeTable);
        timeTable.add(timeLabel).expand().bottom().padBottom(10);
        stack.add(rewardTableWrapper);
        rewardTableWrapper.add(rewardTable).expand().bottom().padBottom(40);
        stack.add(rewardLabelTable);
        rewardLabelTable.add(reward_label).expand();
        stack.add(starsTable);
        starsTable.add(rewardImage).expand().top().padTop(20);

        if(goldReward.compareTo(BigInteger.valueOf(0)) != 0 && diamondReward != 0){
            rewardTable.add(gold_image).expandX().padLeft(18);
            rewardTable.add(diamond_image).expandX();
        } else
        if(goldReward.compareTo(BigInteger.valueOf(0)) != 0 && diamondReward == 0){
            rewardTable.add(gold_image).expandX().padLeft(18);
        } else
        if(goldReward.compareTo(BigInteger.valueOf(0)) == 0 && diamondReward != 0){
            rewardTable.add(diamond_image).expandX().padLeft(12);
        } else {

        }


        stack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!isActive && !isCompleted) {
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


        stack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!isActive || isCompleted){
                    bg.setScale(0.95f);
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    bg.setScale(1f);
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
