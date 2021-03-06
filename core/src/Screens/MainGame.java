package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigDecimal;
import java.math.BigInteger;

import Managers.AnimationManager;
import Numbers.Location;
import testSpine.TestSpine;

public class MainGame implements Screen {

    private final ZombieClicker zombieClicker;
    private AnimationManager animationManager;
    private SpriteBatch batch;

    private Stage stage;
    private Camera camera;
    private TextureAtlas hud_icons_atlas;
    private Image heart_image;
    private Image stopwatch_icon;
    private Viewport viewport;

    private boolean is_mainButton_pressed;

    private Image mainButton;
    private Button goTo_shop_btn;
    private Button achievement_btn;
    private Button map_btn;
    private Button invent_btn;
    private Button missions_btn;
    private Button minigame_btn;
    private Button bossFight_btn;
    private Button leaveBossFight_btn;


    private Skin shop_skin;
    private Skin ach_skin;
    private Skin map_skin;
    private Skin invent_skin;
    private Skin missions_skin;

    private Texture hpBarbg;
    private Texture hpBar;
    private TextureRegion hpBarStart;
    private TextureRegion hpBarBody;
    private TextureRegion hpBarEnd;
    private int HP_positionX;
    private int HP_positionY;

    private Texture timeBossBar;
    private TextureRegion timeBossBarStart;
    private TextureRegion timeBossBarBody;
    private TextureRegion timeBossBarEnd;
    private int timeBoss_positionX = 130;
    private int timeBoss_positionY = 220;
    private double timeBossStart;
    private String timeStr;

    private double regen_time;
    private double time;

    private Location location;

    private boolean flag;

    //TODO test
    int y;

    public MainGame(final ZombieClicker zc, final Location location) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_Game();
        time = System.currentTimeMillis();


        this.location = location;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();


        animationManager = new AnimationManager(zc);

        hpBarbg = zombieClicker.get_assets().get_asset_manager().get("HP/hp_bg.png");
        hpBar = zombieClicker.get_assets().get_asset_manager().get("HP/hp.png");
        hpBarStart = new TextureRegion(hpBar, 0, 0, 2 + 16, hpBar.getHeight());
        hpBarBody = new TextureRegion(hpBar, 2 + 16, 0, 264, hpBar.getHeight());
        hpBarEnd = new TextureRegion(hpBar, 2 + 16 + 264, 0, 2 + 16, hpBar.getHeight());
        HP_positionX = 130;
        HP_positionY = 230;

        hud_icons_atlas = zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/hud_atlas.atlas");

        heart_image = new Image(hud_icons_atlas.createSprite("heart"));
        heart_image.setPosition(165, 236);
        stopwatch_icon = new Image(hud_icons_atlas.createSprite("stopwatch"));
        stopwatch_icon.setPosition(timeBoss_positionX - 20, timeBoss_positionY - 20);

        timeBossBar = zombieClicker.get_assets().get_asset_manager().get("BossTime/time.png");
        timeBossBarStart = new TextureRegion(timeBossBar, 0, 0, 2 + 16, timeBossBar.getHeight());
        timeBossBarBody = new TextureRegion(timeBossBar, 2 + 16, 0, 264, timeBossBar.getHeight());
        timeBossBarEnd = new TextureRegion(timeBossBar, 2 + 16 + 264, 0, 2 + 16, timeBossBar.getHeight());

        mainButton = new Image();
        mainButton.setPosition(160, 300);
        mainButton.setWidth(217);
        mainButton.setHeight(331);
        mainButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01")) {
                    is_mainButton_pressed = true;
                    zombieClicker.getNumerics().plus_global_tap_count(BigInteger.valueOf(1));
                    if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
                        zombieClicker.getNumerics().getCurrent_location().minus_Boss_health(zombieClicker.getNumerics().getPunch_power());
                    } else {
                        zombieClicker.getNumerics().getCurrent_location().minusHealth(zombieClicker.getNumerics().getPunch_power());
                    }
                    zombieClicker.getSoundManager().play_punch_sound();
                    zombieClicker.getNumerics().plus_gold(zombieClicker.getNumerics().getGold_from_taps());

                    zombieClicker.getNumerics().getTestSpine().setAnimation("hurt");
                }
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                is_mainButton_pressed = false;
            }
        });

        shop_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/shop_btn.json");
        goTo_shop_btn = new Button(shop_skin);
        goTo_shop_btn.setPosition(5, 800);
        goTo_shop_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                location.setMaxZombie_health();
                location.setMaxBoss_health();
                zombieClicker.setShopScreen();
                Gdx.input.setInputProcessor(zombieClicker.getShop().getStage());
                if (location.isBossFight()) {
                    next_level(0);
                    location.setLoseBoss(true);
                }
                zombieClicker.getNumerics().getCurrent_location().setPlayer_on_location(false);
            }
        });


        ach_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/ach_btn_skin.json");
        achievement_btn = new Button(ach_skin);
        achievement_btn.setPosition(435, 800);
        achievement_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                dispose();
//                location.setMaxZombie_health();
//                location.setMaxBoss_health();
//               // zombieClicker.setAchievementScreen();
//                if (location.isBossFight()) {
//                    next_level(0);
//                    location.setLoseBoss(true);
//                }
            }
        });


        bossFight_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/fight_boss.json", Skin.class));
        bossFight_btn.setPosition(435, 500);
        bossFight_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                location.setBossFight(true);
                timeBossStart = System.currentTimeMillis();
                location.setCount_death_zombies_betweenBoss(0);
                bossFight_btn.setVisible(false);
                leaveBossFight_btn.setVisible(true);
                animationManager.setFlag(true);
            }
        });

        leaveBossFight_btn = new Button(ach_skin);
        leaveBossFight_btn.setPosition(430, 500);
        leaveBossFight_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                next_level(0);
                bossFight_btn.setVisible(true);
                leaveBossFight_btn.setVisible(false);
                animationManager.setFlag(false);
            }
        });

        map_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/map_btn.json");
        map_btn = new Button(map_skin);
        map_btn.setPosition(435, 700);
        map_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                location.setMaxZombie_health();
                location.setMaxBoss_health();
                zombieClicker.setMapScreen();
                if (location.isBossFight()) {
                    next_level(0);
                    location.setLoseBoss(true);
                }
            }
        });

        invent_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/invent_btn_skin.json");
        invent_btn = new Button(invent_skin);
        invent_btn.setPosition(5, 700);
        invent_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                location.setMaxZombie_health();
                location.setMaxBoss_health();
                zombieClicker.setInventoryScreen();
                if (location.isBossFight()) {
                    next_level(0);
                    location.setLoseBoss(true);
                }
            }
        });

        missions_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/missions_btn_skin.json");
        missions_btn = new Button(missions_skin);
        missions_btn.setPosition(5, 600);
        missions_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                location.setMaxZombie_health();
                location.setMaxBoss_health();
                zombieClicker.setMissionsScreen();
                if (location.isBossFight()) {
                    next_level(0);
                    location.setLoseBoss(true);
                }
            }
        });

        minigame_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/minigame_btn.json", Skin.class));
        minigame_btn.setPosition(5, 300);
        minigame_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                location.setMaxZombie_health();
                location.setMaxBoss_health();
                zombieClicker.setMiniGameScreen();
                if (location.isBossFight()) {
                    next_level(0);
                    location.setLoseBoss(true);
                }
            }
        });

        stage.addActor(location.getBGimage());
        stage.addActor(goTo_shop_btn);
        stage.addActor(achievement_btn);
        stage.addActor(mainButton);
        stage.addActor(map_btn);
        stage.addActor(bossFight_btn);
        stage.addActor(leaveBossFight_btn);
        stage.addActor(heart_image);
        stage.addActor(invent_btn);
        stage.addActor(missions_btn);
        stage.addActor(minigame_btn);
        stage.addActor(zombieClicker.getHud().getGold_icon());
        stage.addActor(zombieClicker.getHud().getDiamond_icon());
        stage.addActor(stopwatch_icon);
        stage.addActor(zombieClicker.getHud().getPlus_diamonds_btn());
        stage.addActor(zombieClicker.getHud().getPlus_gold_btn());

        if (location.getLoseBoss()) {
            bossFight_btn.setVisible(true);
        } else
            bossFight_btn.setVisible(false);

        leaveBossFight_btn.setVisible(false);

        Gdx.input.setInputProcessor(stage);
        flag = false;


        //TODO test
        y = 200;
//        System.out.println(BigDecimal.valueOf(this.location.getBase_zombie_HP().floatValue() * Math.pow(this.location.getMultiplier_zombie_HP(), y - 1)).toBigInteger() + " = " +
//                zombieClicker.getNumerics().bigInteger_to_string(BigDecimal.valueOf(this.location.getBase_zombie_HP().floatValue() * Math.pow(this.location.getMultiplier_zombie_HP(), y - 1)).toBigInteger()));


        System.out.println(zombieClicker.getNumerics().bigInteger_to_string(new BigInteger("1256")));
    }

    ////////////////GETTERS//////////////////
    public boolean get_is_mainButton_pressed() {
        return is_mainButton_pressed;
    }

    public Image getMainButton() {
        return mainButton;
    }
    ////////////////GETTERS//////////////////


    ///////////////SETTERS///////////////////

    ///////////////SETTERS///////////////////

    @Override
    public void show() {

    }

    public void draw_HP() {
        //TODO floatValue исправить иначе говно будет
        batch.draw(hpBarbg, HP_positionX, HP_positionY);
        batch.draw(hpBarStart, HP_positionX, HP_positionY);
        if (!zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            if(location.getZombie_health().compareTo(BigDecimal.ZERO) > 0) {
                batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                        hpBarBody.getRegionWidth() * ((location.getZombie_health()).floatValue() / ((location.getMax_zombie_health())).floatValue()),
                        hpBarBody.getRegionHeight());
                batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                        ((location.getZombie_health()).floatValue() / ((location.getMax_zombie_health())).floatValue()), HP_positionY);
            }
        } else {
            if(location.getBoss_health().compareTo(BigDecimal.ZERO) > 0) {
                batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                        hpBarBody.getRegionWidth() * (location.getBoss_health()).floatValue() / ((location.getMax_boss_health()).floatValue()),
                        hpBarBody.getRegionHeight());

                batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                        (location.getBoss_health()).floatValue() / ((location.getMax_boss_health()).floatValue()), HP_positionY);
            }
        }
    }

    public void drawTimeBoss() {
        if (!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01"))
            if ((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / 1000 > 0) {
                if (!flag)
                    flag = true;

                batch.draw(timeBossBarStart, timeBoss_positionX, timeBoss_positionY);
                if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
                    batch.draw(timeBossBarBody, timeBoss_positionX + timeBossBarStart.getRegionWidth(), timeBoss_positionY,
                            timeBossBarBody.getRegionWidth() * (float) ((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / location.getDurationBossFight()),
                            timeBossBarBody.getRegionHeight());
                    batch.draw(timeBossBarEnd, timeBoss_positionX + timeBossBarStart.getRegionWidth() + timeBossBarBody.getRegionWidth() *
                            (float) ((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / location.getDurationBossFight()), timeBoss_positionY);
                }
            } else {
                if (flag) {
                    next_level(0);
                    System.out.println("00000");
                    location.setLoseBoss(true);
                    bossFight_btn.setVisible(true);
                    leaveBossFight_btn.setVisible(false);
                    flag = false;
                }
            }
    }

    public void draw_text() {
        zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), "Level : " + zombieClicker.getNumerics().getCurrent_location().getLevel_count());
        zombieClicker.getFontManager().getHud_font().draw(batch, "Level : " + zombieClicker.getNumerics().getCurrent_location().getLevel_count(), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 845);
        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), zombieClicker.getNumerics().bigInteger_to_string(location.getBoss_health().toBigInteger()));
            zombieClicker.getFontManager().getHud_font().draw(batch, zombieClicker.getNumerics().bigInteger_to_string(location.getBoss_health().toBigInteger()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 293);

            //boss name
            zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), "BOSS");
            zombieClicker.getFontManager().getHud_font().draw(batch, "BOSS", 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 670);

            //time
            if (!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01"))
                if ((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / 1000 > 0) {
                    timeStr = String.valueOf((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / 1000);
                    timeStr = timeStr.indexOf(".") == 1 ? timeStr.substring(0, 3) : timeStr.substring(0, 4);
                    zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), timeStr);
                    zombieClicker.getFontManager().getHud_font().draw(batch, timeStr, 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 180);
                }
        } else {
            //HP zombie
            zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), zombieClicker.getNumerics().bigInteger_to_string(location.getZombie_health().toBigInteger()));
            zombieClicker.getFontManager().getHud_font().draw(batch, zombieClicker.getNumerics().bigInteger_to_string(location.getZombie_health().toBigInteger()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 293);

            //m/n zombie
            if (!location.getLoseBoss()) {
                zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), String.valueOf(location.getCount_death_zombies_betweenBoss() + 1) + " / " + String.valueOf(location.getBetweenBoss()));
                zombieClicker.getFontManager().getHud_font().draw(batch, String.valueOf(location.getCount_death_zombies_betweenBoss() + 1) + " / " + String.valueOf(location.getBetweenBoss()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 670);
            }

        }

        zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), location.getName());
        zombieClicker.getFontManager().getHud_font().draw(batch, location.getName(), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 885);
    }

    @Override
    public void render(float delta) {
        update();

        stage.draw();

        batch.begin();
        animationManager.render_zombie(batch);

        draw_text();
        draw_HP();
        if (location.isBossFight()) {
            drawTimeBoss();

            if (!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01")) {
                stopwatch_icon.setVisible(true);
            } else stopwatch_icon.setVisible(false);

        } else stopwatch_icon.setVisible(false);

        zombieClicker.getHud().render(batch);

        batch.end();
        batch.setProjectionMatrix(camera.combined);

        zombieClicker.getNumerics().getTestSpine().render();
    }

    private void update() {


        if (location.getCount_death_zombies_betweenBoss() == location.getBetweenBoss()) {
            if (!location.getLoseBoss()) {
                location.setBossFight(true);
                timeBossStart = System.currentTimeMillis();
                location.setCount_death_zombies_betweenBoss(0);
            }
        }

        regen_time = (System.currentTimeMillis() - time) / 1000;

//        if (regen_time >= zombieClicker.getNumerics().getHow_fast_passive_damage()) {
        location.passive_punch();
        time = System.currentTimeMillis();
//        }

        zombieClicker.getKeepTrackAch().keep_track_of_achievements();

    }

    public void next_level(long lvl) {
        // lvl == 0 => lose
        // lvl == 1 => win. !!!!else ERROR!!!!
        if (lvl != 0) {
            zombieClicker.getNumerics().plus_boss_kills(1);
            zombieClicker.getNumerics().plus_diamonds(location.getBoss_kill_reward());
            location.plus_zombie_health();
            location.plus_Boss_health();
            location.plus_zombie_kill_reward();
            location.setLoseBoss(false);
            leaveBossFight_btn.setVisible(false);
            bossFight_btn.setVisible(false);
        }
        location.upLevel(lvl);
        location.setMaxZombie_health();
        location.setMaxBoss_health();
        location.plus_count_kill_boss(lvl);
        location.plus_zombie_kills(BigInteger.valueOf(lvl));
        location.setBossFight(false);
    }

    public Stage getStage() {
        return stage;
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        zombieClicker.get_assets().dispose_Game_assets();
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();

        if (zombieClicker.getNumerics().getCurrent_num_location() == 0) {
            zombieClicker.get_assets().dispose_assets_for_location_1();
        }
        if (zombieClicker.getNumerics().getCurrent_num_location() == 1) {
            zombieClicker.get_assets().dispose_assets_for_location_2();
        }
        if (zombieClicker.getNumerics().getCurrent_num_location() == 2) {
            zombieClicker.get_assets().dispose_assets_for_location_3();
        }
        if (zombieClicker.getNumerics().getCurrent_num_location() == 3) {
            zombieClicker.get_assets().dispose_assets_for_location_4();
        }
    }
}
