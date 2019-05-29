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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
    private ImageButton go_back_btn;
    private ImageButton goTo_shop_btn;

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
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    public MainGame(final ZombieClicker zc) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_Game();
        time = System.currentTimeMillis();
        animationManager = new AnimationManager(zc);

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Pangolin-Regular.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 24;
        fontParameter.color = Color.WHITE;
        bitmapFont = fontGenerator.generateFont(fontParameter);


        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/gamebg.png", Texture.class));

        hpBarbg = zombieClicker.get_assets().get_asset_manager().get("HP/hp_bg.png");
        hpBar = zombieClicker.get_assets().get_asset_manager().get("HP/hp.png");
        hpBarStart = new TextureRegion(hpBar, 0, 0, 10, hpBar.getHeight());
        hpBarBody = new TextureRegion(hpBar, 10, 0, 195, hpBar.getHeight());
        hpBarEnd = new TextureRegion(hpBar, 10 + 195, 0, 10, hpBar.getHeight());
        HP_positionX = 160;
        HP_positionY = 130;

        mainButton = new Image();
        mainButton.setPosition(160, 200);
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

        go_back_btn = new ImageButton(
                new TextureRegionDrawable(zombieClicker.get_assets().get_asset_manager().get("Buttons/back.png", Texture.class))
        );

        go_back_btn.setPosition(450, 800);

        go_back_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setMenuScreen();
                dispose();
            }
        });

        goTo_shop_btn = new ImageButton(
                new TextureRegionDrawable(zombieClicker.get_assets().get_asset_manager().get("Buttons/shop_btn.png", Texture.class))
        );

        goTo_shop_btn.setPosition(0, 800);

        goTo_shop_btn.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.getNumerics().setZombie_health(zombieClicker.getNumerics().getMax_zombie_health());
                zombieClicker.getNumerics().setBoss_health(zombieClicker.getNumerics().getMax_boss_health());
                zombieClicker.setShopScreen();
                dispose();
            }
        });

        stage.addActor(BGimage);
        stage.addActor(goTo_shop_btn);
        stage.addActor(mainButton);
        stage.addActor(go_back_btn);

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
        bitmapFont.draw(batch, "CLICKS : " + zombieClicker.getNumerics().getGlobal_tap_count(), 100, 50);
        bitmapFont.draw(batch, "LEVEL : " + zombieClicker.getNumerics().getLevel_count(), 100, 800);
        if (zombieClicker.getNumerics().get_bossFight()) {
            bitmapFont.draw(batch, "HP : " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getBoss_health()), 200, 180);
        } else
            bitmapFont.draw(batch, "HP : " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getZombie_health()), 200, 180);
        bitmapFont.draw(batch, "KILLS : " + zombieClicker.getNumerics().getZombie_kills(), 300, 800);
        bitmapFont.draw(batch, "GOLD : " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 300, 700);
        bitmapFont.draw(batch, "DIAMONDS : " + zombieClicker.getNumerics().getDiamonds(), 300, 600);
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

        zombieClicker.getAchievements().keep_track_of_achievements();

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
        zombieClicker.getNumerics().plus_level_count(1);
        zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
        zombieClicker.getNumerics().plus_diamonds(1);
        zombieClicker.getNumerics().setLast_kills(zombieClicker.getNumerics().getZombie_kills());
        zombieClicker.getNumerics().set_bossFight(false);
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        if(fontGenerator != null) fontGenerator.dispose();
        zombieClicker.get_assets().dispose_Game_assets();
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();
    }
}
