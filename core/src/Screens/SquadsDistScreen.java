package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Other.SquadItem;

public class SquadsDistScreen implements Screen {

    private final ZombieClicker zombieClicker;

    private Camera camera;
    private Table table;
    private Stage stage;
    private Viewport viewport;
    private SpriteBatch batch;
    private Image back_image;
    private Button back_btn;

    private Button send_to_location1_btn;

    public SquadsDistScreen(ZombieClicker zc, SquadItem squadItem){
        zombieClicker = zc;

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        table = new Table();

        send_to_location1_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/shop_btn.json", Skin.class));
        back_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/back_btn.json", Skin.class));
        send_to_location1_btn.setPosition(250,500);
        back_btn.setPosition(50,50);


        send_to_location1_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });


        stage.addActor(back_btn);
        stage.addActor(send_to_location1_btn);

        Gdx.input.setInputProcessor(stage);
    }


    ///////////////////GETTERS/////////////////////////

    ///////////////////GETTERS/////////////////////////

    ///////////////////SETTERS/////////////////////////

    ///////////////////SETTERS/////////////////////////


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        batch.begin();

        batch.end();

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

    }
}
