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
    private Button start;
    private Button back_btn;
    private Skin back_btn_skin;
    private Image ImageBG_MainGame;
    private Image ImageBG;
    private Image arrow;
    private Texture wheel;

    private boolean isStart;
    private int maxSpeed;
    private int minSpeed;
    private int duration;
    private float degree;
    private float step;
    private long time;
    private long time_start;
    private int dt;
    private ArrayList<String> rew; // награды
    private int n; //кол-во секций

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

        zombieClicker.get_assets().load_assets_for_WheelOfFortuneScreen();

        back_btn_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        back_btn = new Button(back_btn_skin);
        back_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMiniGameScreen();
                dispose();
            }
        });
        back_btn.setPosition(10, 10);

        start = new Button(back_btn_skin);
        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!start.isDisabled()) {
                    isStart = true;
                    step = MathUtils.random(minSpeed, maxSpeed);
                    time_start = TimeUtils.millis();
                    duration = MathUtils.random(800, 1000);
                    degree = 0;
                    start.setDisabled(true);
                }
            }
        });
        start.setPosition(250, 100);


        ImageBG_MainGame = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));
        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/wheelOfFortune_bg.png", Texture.class));
        arrow = new Image(zombieClicker.get_assets().get_asset_manager().get("Other/arroww.png", Texture.class));
        arrow.setPosition(252, 750);

        wheel = zombieClicker.get_assets().get_asset_manager().get("Background/wheel.png", Texture.class);

        stage.addActor(ImageBG_MainGame);
        stage.addActor(ImageBG);
        stage.addActor(arrow);
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
                if (TimeUtils.timeSinceMillis(time_start) >= duration * dt / dt + 1) {
                    duration = duration * dt / dt + 1;
                    step -= 0.7;
                    time_start = TimeUtils.millis();
                }
            } else {
                reward();
                isStart = false;
                start.setDisabled(false);
            }
        }
    }

    public void reward() {
//        if(degree / (360 / n) % 2 == 0){
//            zombieClicker.getNumerics().plus_diamonds(10);
//        }else zombieClicker.getNumerics().plus_gold(new BigInteger("100"));

//        System.out.println(degree + "\n" + degree / (360 / n) % 2);
        System.out.println(rew.get((int) (degree / (360 / n))) + "\n" + degree);
    }

    @Override
    public void render(float delta) {
        update();
        stage.act();
        stage.draw();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(wheel, 145, 500, 125, 125,
                250, 250,
                1, 1,
                degree,
                0, 0,
                249, 249,
                false,
                false);
//        System.out.println(step);
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
