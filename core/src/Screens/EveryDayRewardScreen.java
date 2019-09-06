package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EveryDayRewardScreen implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Camera camera;
    private Image bg_image;
    private Viewport viewport;
    private int days_inARow;
    private SpriteBatch batch;
    private TextButton ok_btn;
    private Button chest_btn;
    private boolean showReward;

    public EveryDayRewardScreen(ZombieClicker zc, int daysInARow){
        zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        showReward = false;
        zombieClicker.get_assets().load_assets_for_EveryDayRewardScreen();
        bg_image = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/everyday_bg.png", Texture.class));
        this.days_inARow = daysInARow;
        ok_btn = new TextButton("OK", zombieClicker.get_assets().get_asset_manager().get("Buttons/ok_btn_skin.json", Skin.class));
        ok_btn.setPosition(200,340);

        ok_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker._begin();
            }
        });

        chest_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/chest_btn.json", Skin.class));
        chest_btn.setPosition(100,520);
        chest_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                chest_btn.setDisabled(true);
                showReward = true;
            }
        });

        stage.addActor(bg_image);
        stage.addActor(ok_btn);
        stage.addActor(chest_btn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act();

        batch.begin();
        zombieClicker.getFontManager().getFont_border().getData().setScale(0.9f);
        zombieClicker.getFontManager().getFont_border().draw(batch, "DAY " + Integer.toString(days_inARow), 185 ,873);
        if(showReward){
            zombieClicker.getFontManager().getFont_border().getData().setScale(0.3f);
            zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getFont_border(),"Reward:");
            zombieClicker.getFontManager().getFont_border().draw(batch, "Reward:", 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 500);

        }
        batch.end();

        batch.setProjectionMatrix(camera.combined);
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
        if(stage != null) stage.dispose();
       // zombieClicker.get_assets().dispose_assets_for_everyDayRewardScreen();
    }
}
