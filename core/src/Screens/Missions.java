package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import Other.MissionsItem;

public class Missions implements Screen {

    private final ZombieClicker zombieClicker;

    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Skin goback_skin;
    private Button goback_bth;
    private Image ImageBG;
    private Image ImageBG_MainGame;

    private int[][] pos;
    private float x;
    private float y;

    public Missions(final ZombieClicker zc) {
        zombieClicker = zc;
        zombieClicker.get_assets().load_assets_for_Missions();

        pos = new int[][]{
                {50, 640},
                {123, 434},
                {300, 564},
                {334, 304},
                {120, 50},
                {170, 274}};

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        goback_skin = zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class);
        goback_bth = new Button(goback_skin);
        goback_bth.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_anyLocation(zombieClicker);
                zombieClicker.setMainGame();
                dispose();
            }
        });
        goback_bth.setPosition(10, 10);

        ImageBG = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/missions_bg.png", Texture.class));
        ImageBG_MainGame = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/location_1_bg.png", Texture.class));

        zombieClicker.getNumerics().addMission(zombieClicker);

        stage.addActor(ImageBG_MainGame);
        stage.addActor(ImageBG);
        stage.addActor(goback_bth);

        for (MissionsItem q : zombieClicker.getNumerics().getMissionsItem()) {
            stage.addActor(q.getStack());
        }

       // stage.setDebugAll(true);
        Gdx.input.setInputProcessor(stage);
    }

    public Stage getStage(){
        return stage;
    }

    @Override
    public void show() {

    }

    public void update(){
        for(MissionsItem x : zombieClicker.getNumerics().getMissionsItem()){
            x.update_status();
        }
    }

    @Override
    public void render(float delta) {

        update();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        batch.setProjectionMatrix(camera.combined);
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
