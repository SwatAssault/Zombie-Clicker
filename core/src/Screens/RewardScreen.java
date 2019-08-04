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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;

public class RewardScreen implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private Image bgImage;
    private TextButton okBtn;
    String what;
    BigInteger amount;
    String lastScreen;

    public RewardScreen(ZombieClicker zc, String what, BigInteger amount, String lastScreen){
        zombieClicker = zc;
        this.what = what;
        this.lastScreen = lastScreen;
        this.amount = amount;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540,960, camera);
        stage = new Stage(viewport);
        zombieClicker.get_assets().load_assets_for_RewardScreen();
        bgImage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/rewardbg.png", Texture.class));
        okBtn = new TextButton("OK", zombieClicker.get_assets().get_asset_manager().get("Buttons/ok_btn_skin.json", Skin.class));
        okBtn.setPosition(90,250);

        okBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        stage.addActor(bgImage);
        stage.addActor(okBtn);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act();

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
        zombieClicker.get_assets().dispose_assets_for_RewardScreen();
        if (stage != null) stage.dispose();
    }
}
