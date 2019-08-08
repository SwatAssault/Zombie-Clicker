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

public class TipScreen implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private SpriteBatch batch;
    private Image bgimage;
    private TextButton ok_btn;
    private Button x_btn;
    private String header;
    private String mainText;
    private String lastScreen;

    public TipScreen(ZombieClicker zc, String header, String mainText, final String lastScreen){
        zombieClicker = zc;
        this.header = header;
        this.mainText = mainText;
        this.lastScreen = lastScreen;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        zombieClicker.get_assets().load_assets_for_TipScreen();
        bgimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/tipbg.png", Texture.class));
        ok_btn = new TextButton("OK", zombieClicker.get_assets().get_asset_manager().get("Buttons/ok_btn_skin.json", Skin.class));
        ok_btn.setPosition(200,280);
        x_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/x_btn.json", Skin.class));
        x_btn.setPosition(455, 655);

        ok_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                if(lastScreen.equals("maingame")){
                    Gdx.input.setInputProcessor(zombieClicker.getMainGame().getStage());
                }
            }
        });

        x_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                if(lastScreen.equals("maingame")){
                    Gdx.input.setInputProcessor(zombieClicker.getMainGame().getStage());
                }
            }
        });

        stage.addActor(bgimage);
        stage.addActor(ok_btn);
        stage.addActor(x_btn);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(lastScreen.equals("maingame")){
            zombieClicker.getMainGame().render(1);
        }

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
        zombieClicker.get_assets().dispose_assets_for_TipScreen();
        if(stage != null) stage.dispose();
        if(batch != null) batch.dispose();
    }
}
