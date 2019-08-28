package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;
import java.util.ArrayList;

public class WheelOfFortune implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    private TextButton start;
    private Button back_btn;
    private Skin back_btn_skin;
    private Image ImageBG_MainGame;
    private Image ImageBG;
    //    private Image arrow;
    private Texture wheel;
    private Texture arrow;

    private boolean isStart;
    private int maxSpeed;
    private int minSpeed;
    private int duration;
    private float degree;
    private float arrow_deg;
    private float step;
    private long time;
    private long time_start;
    private int dt;
    private ArrayList<String> rew; // награды
    private int n; //кол-во секций
    private boolean flag;
    private int tmp_rew;

    public WheelOfFortune(ZombieClicker zc) {
        this.zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        rew = new ArrayList<String>();
        rew.add("оранжевый");
        rew.add("желтый");
        rew.add("зеленый");
        rew.add("голубой");
        rew.add("синий");
        rew.add("фиолетовый");
        rew.add("розовый");
        rew.add("красный");
        isStart = false;
        minSpeed = 5;
        maxSpeed = 10;
        step = 0;
        dt = 1;
        n = 8;
        flag = false;

        zombieClicker.get_assets().load_assets_for_WheelOfFortuneScreen();

        back_btn_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/roll_btn_skin.json", Skin.class);
        back_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class));
        back_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMiniGameScreen();
                dispose();
            }
        });
        back_btn.setPosition(10, 10);

        start = new TextButton("КРУТИТЬ!", back_btn_skin);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!start.isDisabled()) {
                    isStart = true;
                    step = MathUtils.random(minSpeed, maxSpeed);
//                    step = 0.1f;
                    time_start = TimeUtils.millis();
                    duration = MathUtils.random(800, 1000);
                    degree = 0;
                    start.setDisabled(true);
                    zombieClicker.getNumerics().plusSpin_counter(-1);
                }
            }
        });
        start.setPosition(150, 100);
        arrow_deg = 0;

        ImageBG_MainGame = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));
        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/wheelOfFortune_bg.png", Texture.class));
//        arrow = new Image(zombieClicker.get_assets().get_asset_manager().get("Other/arrow.png", Texture.class));
//        arrow.setPosition(250, 700);

        arrow = zombieClicker.get_assets().get_asset_manager().get("Other/arrow.png", Texture.class);
        arrow.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        wheel = zombieClicker.get_assets().get_asset_manager().get("Background/wheel.png", Texture.class);
        wheel.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        stage.addActor(ImageBG_MainGame);
        stage.addActor(ImageBG);
//        stage.addActor(arrow);
        stage.addActor(back_btn);
        stage.addActor(start);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if (isStart) {
            if (step > 0 && duration > 100) {
                degree += step;
                if (degree > 360) degree = 0;
                if (TimeUtils.timeSinceMillis(time_start) >= duration) {
//                    duration = duration * dt / dt + 1;
                    step -= 0.7;
                    time_start = TimeUtils.millis();
                }

                if (flag && (step > 0) && arrow_deg > -45) {
                    arrow_deg -= step *5;
                }

                if (((int) (degree) % 45) < 20) {
                    flag = true;
                }
                if (arrow_deg != 0 && (step > 0) && (degree % 45 > 12)) {
                    arrow_deg /= 2;
                }

                if (degree % 45 > 12)
                    flag = false;


            } else {
                reward();
                isStart = false;
                start.setDisabled(false);
            }
        }

        if(zombieClicker.getNumerics().getSpin_counter() == 0){
            start.setDisabled(true);
        }

    }

    public void reward() {
//        if(degree / (360 / n) % 2 == 0){
//            zombieClicker.getNumerics().plus_diamonds(10);
//        }else zombieClicker.getNumerics().plus_gold(new BigInteger("100"));

//        System.out.println(degree + "\n" + degree / (360 / n) % 2);
        tmp_rew = (int) (degree / (360 / n));
        System.out.println(rew.get( (arrow_deg < 5 && arrow_deg > -5) ? tmp_rew : tmp_rew - 1 < 0 ? 7 : tmp_rew - 1) + "\n" + degree);
    }

    @Override
    public void render(float delta) {
        update();
        stage.act();
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(wheel, 75, 280, 200, 200,
                400, 400,
                1, 1,
                degree,
                0, 0,
                400, 400,
                false,
                false);

        batch.draw(arrow, 250, 640, 25, 50,
                50, 71,
                1, 1,
                arrow_deg,
                0, 0,
                50, 71,
                false,
                false);
//        System.out.println(step);

        zombieClicker.getFontManager().getFont_border().getData().setScale(.35f);
        zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getFont_border(), "Осталось попыток : " + Integer.toString(zombieClicker.getNumerics().getSpin_counter()));
        zombieClicker.getFontManager().getFont_border().draw(batch, "Осталось попыток : " + Integer.toString(zombieClicker.getNumerics().getSpin_counter()), 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 230);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
