package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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

import java.math.BigInteger;

import Managers.AnimationManager;
import Numbers.Location;

public class MainGame implements Screen {

    private final ZombieClicker zombieClicker;
    private AnimationManager animationManager;
    private SpriteBatch batch;

    private Stage stage;
    private Camera camera;
    private Image heart_image;
    private Viewport viewport;

    private boolean is_mainButton_pressed;

    private Image mainButton;
    private Button go_back_btn;
    private Button goTo_shop_btn;
    private Button achievement_btn;
    private Button map_btn;
    private Button invent_btn;
    private Button missions_btn;
    private Button bossFight_btn;
    private Button leaveBossFight_btn;

    private Skin goback_skin;
    private Skin shop_skin;
    private Skin ach_skin;
    private Skin map_skin;
    private Skin invent_skin;
    private Skin missions_skin;

    private Texture heart_texture;
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
    private int timeBoss_positionX;
    private int timeBoss_positionY;
    private double timeBossStart;
    private String timeStr;

    private double regen_time;
    private double time;

    private BitmapFont bitmapFont;
    private BitmapFont HP_font;
    private BitmapFont diamond_font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private Location location;

    //нужна для фикса хуйни со временем
    private boolean flag;

    public MainGame(final ZombieClicker zc, final Location location) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_Game();
        time = System.currentTimeMillis();


        this.location = location;

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Rubik.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 40;
        fontParameter.color = Color.WHITE;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        bitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontParameter.size = 30;
        HP_font = fontGenerator.generateFont(fontParameter);
        HP_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontParameter.size = 18;
        diamond_font = fontGenerator.generateFont(fontParameter);
        diamond_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        animationManager = new AnimationManager(zc);

        hpBarbg = zombieClicker.get_assets().get_asset_manager().get("HP/hp_bg.png");
        hpBar = zombieClicker.get_assets().get_asset_manager().get("HP/hp.png");
        hpBarStart = new TextureRegion(hpBar, 0, 0, 5, hpBar.getHeight());
        hpBarBody = new TextureRegion(hpBar, 5, 0, 137, hpBar.getHeight());
        hpBarEnd = new TextureRegion(hpBar, 5 + 137, 0, 5, hpBar.getHeight());
        HP_positionX = 210;
        HP_positionY = 240;

        heart_texture = zombieClicker.get_assets().get_asset_manager().get("Other/heart.png", Texture.class);
        heart_texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        heart_image = new Image(heart_texture);
        heart_image.setPosition(150, 230);

        timeBossBar = zombieClicker.get_assets().get_asset_manager().get("BossTime/time.png");
        timeBossBarStart = new TextureRegion(timeBossBar, 0, 0, 1, timeBossBar.getHeight());
        timeBossBarBody = new TextureRegion(timeBossBar, 1, 0, 243, timeBossBar.getHeight());
        timeBossBarEnd = new TextureRegion(timeBossBar, 1 + 243, 0, 1, timeBossBar.getHeight());
        timeBoss_positionX = 160;
        timeBoss_positionY = 200;

        mainButton = new Image();
        mainButton.setPosition(160, 300);
        mainButton.setWidth(217);
        mainButton.setHeight(331);
        mainButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                is_mainButton_pressed = true;
                zombieClicker.getNumerics().plus_global_tap_count(BigInteger.valueOf(1));
                if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
                    zombieClicker.getNumerics().getCurrent_location().minus_Boss_health(zombieClicker.getNumerics().getPunch_power());
                } else {
                    zombieClicker.getNumerics().getCurrent_location().minusHealth(zombieClicker.getNumerics().getPunch_power());
                }
                zombieClicker.getSoundManager().play_punch_sound();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                is_mainButton_pressed = false;
            }
        });

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json");
        go_back_btn = new Button(goback_skin);
        go_back_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        shop_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/shop_btn.json");
        goTo_shop_btn = new Button(shop_skin);
        goTo_shop_btn.setPosition(5, 700);
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
        achievement_btn.setPosition(435, 853);
        achievement_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                //ТУТ НЕТ НИЧЕГО НЕНУЖНОГО. НА ВРЕМЯ

//                dispose();
//                location.setMaxZombie_health();
//                location.setMaxBoss_health();
//                zombieClicker.setAchievementScreen();
//                if (location.isBossFight()) {
//                    next_level(0);
//                    location.setLoseBoss(true);
//                }
            }
        });


        bossFight_btn = new Button(shop_skin);
        bossFight_btn.setPosition(430, 500);
        bossFight_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                location.setBossFight(true);
                timeBossStart = System.currentTimeMillis();
                location.setCount_death_zombies_betweenBoss(0);
                bossFight_btn.setVisible(false);
                leaveBossFight_btn.setVisible(true);
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
            }
        });

        map_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/map_btn.json");
        map_btn = new Button(map_skin);
        map_btn.setPosition(430, 700);
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
        invent_btn.setPosition(5, 600);
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
        missions_btn.setPosition(5, 500);
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


        stage.addActor(location.getBGimage());
        stage.addActor(goTo_shop_btn);
        stage.addActor(achievement_btn);
        stage.addActor(mainButton);
//        stage.addActor(go_back_btn);
        stage.addActor(map_btn);
        stage.addActor(bossFight_btn);
        stage.addActor(leaveBossFight_btn);
        stage.addActor(heart_image);
        stage.addActor(invent_btn);
        stage.addActor(missions_btn);


        if (location.getLoseBoss()) {
            bossFight_btn.setVisible(true);
        } else
            bossFight_btn.setVisible(false);

        leaveBossFight_btn.setVisible(false);

        Gdx.input.setInputProcessor(stage);
        flag = false;
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
        batch.draw(hpBarbg, HP_positionX, HP_positionY);
        batch.draw(hpBarStart, HP_positionX, HP_positionY);
        if (!zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                    hpBarBody.getRegionWidth() * ((location.getZombie_health()).floatValue() / ((location.getMax_zombie_health())).floatValue()), hpBarBody.getRegionHeight());
            batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                    ((location.getZombie_health()).floatValue() / ((location.getMax_zombie_health())).floatValue()), HP_positionY);
        } else {
            batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                    hpBarBody.getRegionWidth() * (location.getBoss_health()).floatValue() / ((location.getMax_boss_health()).floatValue()), hpBarBody.getRegionHeight());

            batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                    (location.getBoss_health()).floatValue() / ((location.getMax_boss_health()).floatValue()), HP_positionY);
        }
    }

    // Время на убийство босса
    public void drawTimeBoss() {
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
        zombieClicker.getFontManager().getLayout().setText(HP_font, "" + zombieClicker.getNumerics().getCurrent_location().getLevel_count());
        HP_font.draw(batch, "" + zombieClicker.getNumerics().getCurrent_location().getLevel_count(), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 790);
        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            zombieClicker.getFontManager().getLayout().setText(HP_font, zombieClicker.getNumerics().bigInteger_to_string(location.getBoss_health()));
            HP_font.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(location.getBoss_health()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 293);

            //boss name
            zombieClicker.getFontManager().getLayout().setText(HP_font, "BOSS");
            HP_font.draw(batch, "BOSS", 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 670);

            //time
            if ((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / 1000 > 0) {
                timeStr = String.valueOf((location.getDurationBossFight() - (System.currentTimeMillis() - timeBossStart)) / 1000);
                timeStr = timeStr.indexOf(".") == 1 ? timeStr.substring(0, 3) : timeStr.substring(0, 4);
                zombieClicker.getFontManager().getLayout().setText(HP_font, timeStr);
                HP_font.draw(batch, timeStr, 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 180);
            }
        } else {
            //HP zombie
            zombieClicker.getFontManager().getLayout().setText(HP_font, zombieClicker.getNumerics().bigInteger_to_string(location.getZombie_health()));
            HP_font.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(location.getZombie_health()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 293);

            //m/n zombie
            if (!location.getLoseBoss()) {
                zombieClicker.getFontManager().getLayout().setText(HP_font, String.valueOf(location.getCount_death_zombies_betweenBoss() + 1) + " / " + String.valueOf(location.getBetweenBoss()));
                HP_font.draw(batch, String.valueOf(location.getCount_death_zombies_betweenBoss() + 1) + " / " + String.valueOf(location.getBetweenBoss()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 670);
            }

        }

        zombieClicker.getFontManager().getLayout().setText(bitmapFont, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()));
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 918);
        zombieClicker.getFontManager().getLayout().setText(diamond_font, "" + zombieClicker.getNumerics().getDiamonds());
        diamond_font.draw(batch, "" + zombieClicker.getNumerics().getDiamonds(), 540 / 2f - zombieClicker.getFontManager().getLayout().width / 2, 860);
    }

    @Override
    public void render(float delta) {

        update();

        stage.draw();

        batch.begin();
        animationManager.render_zombie(batch);
        //zombieClicker.getFontManager().draw_text_forGame(batch);
        draw_text();
        draw_HP();
        if (location.isBossFight()) {
            drawTimeBoss();
        }
        batch.end();

        batch.setProjectionMatrix(camera.combined);
    }

    private void update() {

        if (location.getCount_death_zombies_betweenBoss() == location.getBetweenBoss()) {
//            System.out.println(location.getLoseBoss());
            if (!location.getLoseBoss()) {
                location.setBossFight(true);

//            if (bossFight_btn.isVisible())
//                bossFight_btn.setVisible(false);

                timeBossStart = System.currentTimeMillis();
                location.setCount_death_zombies_betweenBoss(0);
            }
        }

        regen_time = (System.currentTimeMillis() - time) / 1000;

        if (regen_time >= zombieClicker.getNumerics().getHow_fast_passive_damage()) {
            location.passive_punch();
            time = System.currentTimeMillis();
        }


        zombieClicker.getKeepTrackAch().keep_track_of_achievements();

    }

    public void next_level(long lvl) {
        // lvl == 0 => lose
        // lvl == 1 => win. !!!!else ERROR!!!!
        //УВЕЛИЧЕНИЕ ХП ЗОМБИ
        //УВЕЛИЧЕНИЕ ХП БОССА
        //УВЕЛИЧЕНИЕ ЗОЛОТА, ПОЛУЧАЕМОГО С КАЖДОГО ЗОМБИ
        //ПОЛУЧЕНИЕ АЛМАЗОВ ЗА ПРОХОЖДЕНИЕ УРОВНЯ
        //И ВСЕ ОСТАЛЬНОЕ
//        location.setZeroBossHealth();
        if (lvl != 0) {
            System.out.println("new level");
            zombieClicker.getNumerics().plus_boss_kills(1);
            zombieClicker.getNumerics().plus_diamonds(location.getBoss_kill_reward());
            location.plus_zombie_health();
            location.plus_Boss_health();
            location.setLoseBoss(false);
            leaveBossFight_btn.setVisible(false);
            bossFight_btn.setVisible(false);
        }
        location.setMaxZombie_health();
        location.setMaxBoss_health();

        location.upLevel(lvl);
        location.plus_count_kill_boss(lvl);
        location.plus_zombie_kills(BigInteger.valueOf(lvl));
//        location.plus(BigInteger.valueOf(lvl));
        location.setBossFight(false);

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

        if (diamond_font != null) diamond_font.dispose();
        if (bitmapFont != null) bitmapFont.dispose();
        if (fontGenerator != null) fontGenerator.dispose();
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
