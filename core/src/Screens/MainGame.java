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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;

import Managers.AnimationManager;

public class MainGame implements Screen {

    private final ZombieClicker zombieClicker;
    private AnimationManager animationManager;
    private SpriteBatch batch;

    private Stage stage;
    private Camera camera;
    private Image BGimage;
    private Viewport viewport;

    private boolean is_mainButton_pressed;

    private Image mainButton;
    private Button go_back_btn;
    private Button goTo_shop_btn;
    private Button achievement_btn;
    private Button map_btn;

    private Skin goback_skin;
    private Skin shop_skin;
    private Skin ach_skin;
    private Skin map_skin;

    private Texture hpBarbg;
    private Texture hpBar;
    private TextureRegion hpBarStart;
    private TextureRegion hpBarBody;
    private TextureRegion hpBarEnd;
    private int HP_positionX;
    private int HP_positionY;

    private double regen_time;
    private double time;

    private BitmapFont bitmapFont;
    private BitmapFont HP_font;
    private BitmapFont diamond_font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public MainGame(final ZombieClicker zc) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_Game();
        time = System.currentTimeMillis();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 40;
        fontParameter.color = Color.WHITE;
        bitmapFont = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 30;
        HP_font = fontGenerator.generateFont(fontParameter);
        fontParameter.size = 18;
        diamond_font = fontGenerator.generateFont(fontParameter);

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();


        //////////УСТАНОВКА ЛОКАЦИИ////////////
        if(zombieClicker.getNumerics().getSelected_level().equals("level_1")){     //если первая локация
            //загрузить анимации зомби для 1 локации
            //И НЕ ЗАБЫТЬ ВЫГРУЗИТЬ ПОТОМ!!!
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count1());
            zombieClicker.get_assets().load_assets_for_location_1();
            BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));
            //и прочее
        }

        if(zombieClicker.getNumerics().getSelected_level().equals("level_2")){
            //загрузить анимации зомби для 2 локации
            //И НЕ ЗАБЫТЬ ВЫГРУЗИТЬ ПОТОМ!!!
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count2());
            zombieClicker.get_assets().load_assets_for_location_2();
            BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_2_bg.png", Texture.class));
            //и прочее
        }

        if(zombieClicker.getNumerics().getSelected_level().equals("level_3")){
            //загрузить анимации зомби для 3 локации
            //И НЕ ЗАБЫТЬ ВЫГРУЗИТЬ ПОТОМ!!!
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count3());
            zombieClicker.get_assets().load_assets_for_location_3();
            BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_3_bg.png", Texture.class));
            //и прочее
        }

        if(zombieClicker.getNumerics().getSelected_level().equals("level_4")){
            //загрузить анимации зомби для 4 локации
            //И НЕ ЗАБЫТЬ ВЫГРУЗИТЬ ПОТОМ!!!
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count4());
            zombieClicker.get_assets().load_assets_for_location_4();
            BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_4_bg.png", Texture.class));
            //и прочее
        }

        if(zombieClicker.getNumerics().getSelected_level().equals("level_5")){
            //загрузить анимации зомби для 5 локации
            //И НЕ ЗАБЫТЬ ВЫГРУЗИТЬ ПОТОМ!!!
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count5());
//            zombieClicker.get_assets().load_assets_for_location_5();
//            BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_5_bg.png", Texture.class));
            //и прочее
        }
        //////////УСТАНОВКА ЛОКАЦИИ////////////

        animationManager = new AnimationManager(zc);

        hpBarbg = zombieClicker.get_assets().get_asset_manager().get("HP/hp_bg.png");
        hpBar = zombieClicker.get_assets().get_asset_manager().get("HP/hp.png");
        hpBarStart = new TextureRegion(hpBar, 0, 0, 10, hpBar.getHeight());
        hpBarBody = new TextureRegion(hpBar, 10, 0, 195, hpBar.getHeight());
        hpBarEnd = new TextureRegion(hpBar, 10 + 195, 0, 10, hpBar.getHeight());
        HP_positionX = 160;
        HP_positionY = 240;

        mainButton = new Image();
        mainButton.setPosition(160, 300);
        mainButton.setWidth(217);
        mainButton.setHeight(331);
        mainButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                is_mainButton_pressed = true;
                zombieClicker.getNumerics().plus_global_tap_count(BigInteger.valueOf(1));
                if (zombieClicker.getNumerics().get_bossFight()) {
                    zombieClicker.getNumerics().minus_Boss_health(zombieClicker.getNumerics().getPunch_power());
                } else {
                    zombieClicker.getNumerics().minusHealth(zombieClicker.getNumerics().getPunch_power());
                }
                zombieClicker.getSoundManager().play_punch_sound();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                is_mainButton_pressed = false;
            }
        });

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/goback_btn.json");
        go_back_btn = new Button(goback_skin);
        go_back_btn.setPosition(5, 853);
        go_back_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setMenuScreen();
                dispose();
            }
        });

        shop_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/shopbtn.json");
        goTo_shop_btn = new Button(shop_skin);
        goTo_shop_btn.setPosition(5, 700);
        goTo_shop_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setShopScreen();
                dispose();
            }
        });

        ach_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/ach_btn.json");
        achievement_btn = new Button(ach_skin);
        achievement_btn.setPosition(435,853);
        achievement_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setAchievementScreen();
                dispose();
            }
        });

        map_skin = zombieClicker.get_assets().get_asset_manager().get("SkinJson/map_btn.json");
        map_btn = new Button(map_skin);
        map_btn.setPosition(430, 700);
        map_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setMapScreen();
                dispose();
            }
        });

        stage.addActor(BGimage);
        stage.addActor(goTo_shop_btn);
        stage.addActor(achievement_btn);
        stage.addActor(mainButton);
        stage.addActor(go_back_btn);
        stage.addActor(map_btn);

        Gdx.input.setInputProcessor(stage);
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
        if (!zombieClicker.getNumerics().get_bossFight()) {
            batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                    hpBarBody.getRegionWidth() * ((zombieClicker.getNumerics().getZombie_health()).floatValue() / ((zombieClicker.getNumerics().getMax_zombie_health())).floatValue()), hpBarBody.getRegionHeight());
            batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                    ((zombieClicker.getNumerics().getZombie_health()).floatValue() / ((zombieClicker.getNumerics().getMax_zombie_health())).floatValue()), HP_positionY);
        } else {
            batch.draw(hpBarBody, HP_positionX + hpBarStart.getRegionWidth(), HP_positionY,
                    hpBarBody.getRegionWidth() * (zombieClicker.getNumerics().getBoss_health()).floatValue() / ((zombieClicker.getNumerics().getMax_boss_health()).floatValue()), hpBarBody.getRegionHeight());

            batch.draw(hpBarEnd, HP_positionX + hpBarStart.getRegionWidth() + hpBarBody.getRegionWidth() *
                    (zombieClicker.getNumerics().getBoss_health()).floatValue() / ((zombieClicker.getNumerics().getMax_boss_health()).floatValue()), HP_positionY);
        }
    }

    //ТЕКСТА РАБОТАЮТ ТОЛЬКО В ЭТОМ КЛАССЕ. КАКОГО ХЕРА?

    public void draw_text() {
        zombieClicker.getFontManager().getLayout().setText(HP_font, "" + zombieClicker.getNumerics().getLevel_count());
        HP_font.draw(batch, "" + zombieClicker.getNumerics().getLevel_count(), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 790);
        if (zombieClicker.getNumerics().get_bossFight()) {
            zombieClicker.getFontManager().getLayout().setText(HP_font, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getBoss_health()));
            HP_font.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getBoss_health()), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 293);
        } else {
            zombieClicker.getFontManager().getLayout().setText(HP_font, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getZombie_health()));
            HP_font.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getZombie_health()), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 293);
        }

        zombieClicker.getFontManager().getLayout().setText(bitmapFont, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()));
        bitmapFont.draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 918);
        zombieClicker.getFontManager().getLayout().setText(diamond_font, "" + zombieClicker.getNumerics().getDiamonds());
        diamond_font.draw(batch, "" + zombieClicker.getNumerics().getDiamonds(), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 860);
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
        batch.end();

        batch.setProjectionMatrix(camera.combined);
    }

    private void update() {

        if (zombieClicker.getNumerics().getZombie_kills().subtract(zombieClicker.getNumerics().getLast_kills()).compareTo(BigInteger.valueOf(5)) == 0) {
            zombieClicker.getNumerics().set_bossFight(true);
        }

        regen_time = (System.currentTimeMillis() - time) / 1000;

        if (regen_time >= zombieClicker.getNumerics().getHow_fast_passive_damage()) {
            zombieClicker.getNumerics().passive_punch();
            time = System.currentTimeMillis();
        }

        zombieClicker.getKeepTrackAch().keep_track_of_achievements();

    }

    public void next_level() {
        //УВЕЛИЧЕНИЕ ХП ЗОМБИ
        //УВЕЛИЧЕНИЕ ХП БОССА
        //УВЕЛИЧЕНИЕ ЗОЛОТА, ПОЛУЧАЕМОГО С КАЖДОГО ЗОМБИ
        //ПОЛУЧЕНИЕ АЛМАЗОВ ЗА ПРОХОЖДЕНИЕ УРОВНЯ
        //И ВСЕ ОСТАЛЬНОЕ
        System.out.println("new level");
        zombieClicker.getNumerics().plus_zombie_health();
        zombieClicker.getNumerics().plus_Boss_health();
        zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
        zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
        if(zombieClicker.getNumerics().getSelected_level().equals("level_1")){
            zombieClicker.getNumerics().plusLevel_count1(1);
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count1());
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_2")){
            zombieClicker.getNumerics().plusLevel_count2(1);
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count2());
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_3")){
            zombieClicker.getNumerics().plusLevel_count3(1);
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count3());
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_4")){
            zombieClicker.getNumerics().plusLevel_count4(1);
            zombieClicker.getNumerics().setLevel_count(zombieClicker.getNumerics().getLevel_count4());
        }
        zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
        zombieClicker.getNumerics().plus_diamonds(1);
        zombieClicker.getNumerics().setLast_kills(zombieClicker.getNumerics().getZombie_kills());
        zombieClicker.getNumerics().set_bossFight(false);
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
        if(diamond_font != null) diamond_font.dispose();
        if (bitmapFont != null) bitmapFont.dispose();
        if (fontGenerator != null) fontGenerator.dispose();
        zombieClicker.get_assets().dispose_Game_assets();
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();
        if(zombieClicker.getNumerics().getSelected_level().equals("level_1")){
            zombieClicker.get_assets().dispose_assets_for_location_1();
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_2")){
            zombieClicker.get_assets().dispose_assets_for_location_2();
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_3")){
            zombieClicker.get_assets().dispose_assets_for_location_3();
        }
        if(zombieClicker.getNumerics().getSelected_level().equals("level_4")){
            zombieClicker.get_assets().dispose_assets_for_location_4();
        }
    }
}
