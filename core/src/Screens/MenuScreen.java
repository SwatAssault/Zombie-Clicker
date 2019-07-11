package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {

    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private Image BGimage;
    private ImageButton play_button;

    private final ZombieClicker zombieClicker;

    public MenuScreen(final ZombieClicker zc) {
        zombieClicker = zc;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);

        zombieClicker.get_assets().load_assets_for_Menu();

        BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/menubg.png", Texture.class));

        stage.addActor(BGimage);

        play_button = new ImageButton(
                new TextureRegionDrawable(zombieClicker.get_assets().get_asset_manager().get("Buttons/play_btn.png", Texture.class))
        );

        play_button.setPosition(100,200);

        play_button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                zombieClicker.get_assets().load_assets_for_location_1();
                zombieClicker.getNumerics().setCurrent_num_location(0);
                zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
                zombieClicker.setMainGame();
                dispose();
            }
        });

        stage.addActor(play_button);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {

        stage.draw();

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
        if (stage != null) stage.dispose();
        zombieClicker.get_assets().dispose_Menu_assets();
    }
}
