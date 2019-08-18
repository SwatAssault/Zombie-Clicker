package Screens;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import Other.MiniSquadItem;

public class SquadSelectionScreen implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Viewport viewport;
    private Camera camera;
    private Image backimg;
    private Button x_btn;

    private MiniSquadItem miniSquadItem1;
    private MiniSquadItem miniSquadItem2;
    private MiniSquadItem miniSquadItem3;
    private MiniSquadItem miniSquadItem4;
    private MiniSquadItem miniSquadItem5;
    private MiniSquadItem miniSquadItem6;
    private MiniSquadItem miniSquadItem7;
    private MiniSquadItem miniSquadItem8;
    private MiniSquadItem miniSquadItem9;
    private MiniSquadItem miniSquadItem10;

    private Array<MiniSquadItem> miniSquadItemArray;

    private int x = 70, y = 700;

    public SquadSelectionScreen(ZombieClicker zc){
        zombieClicker = zc;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        zombieClicker.get_assets().load_assets_for_SquadSelectionScreen();
        backimg = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/squad_selection_bg.png", Texture.class));
        x_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/x_btn.json", Skin.class));
        x_btn.setPosition(460, 850);

        x_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                //Gdx.input.setInputProcessor();
            }
        });

        miniSquadItem1 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 1);
        miniSquadItem2 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 2);
        miniSquadItem3 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 3);
        miniSquadItem4 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 4);
        miniSquadItem5 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 5);
        miniSquadItem6 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 6);
        miniSquadItem7 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 7);
        miniSquadItem8 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 8);
        miniSquadItem9 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 9);
        miniSquadItem10 = new MiniSquadItem(zombieClicker, zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/icon_pistol.png", Texture.class), 10);

        miniSquadItemArray = new Array<MiniSquadItem>();
        miniSquadItemArray.add(miniSquadItem1);
        miniSquadItemArray.add(miniSquadItem2);
        miniSquadItemArray.add(miniSquadItem3);
        miniSquadItemArray.add(miniSquadItem4);
        miniSquadItemArray.add(miniSquadItem5);
        miniSquadItemArray.add(miniSquadItem6);
        miniSquadItemArray.add(miniSquadItem7);
        miniSquadItemArray.add(miniSquadItem8);
        miniSquadItemArray.add(miniSquadItem9);
        miniSquadItemArray.add(miniSquadItem10);

        stage.addActor(backimg);

        for(int i = 0; i < zombieClicker.getShop().getSquads_amount(); i++, x += 10){
            miniSquadItemArray.get(i).getStack().setPosition(x, y);
            stage.addActor(miniSquadItemArray.get(i).getStack());
            if(i == 2 || i == 5 || i == 8){
                x = 60;
                y -= 140;
            } else
                x += 130;
        }


        stage.addActor(x_btn);

        Gdx.input.setInputProcessor(stage);
    }


    public void update_status(){
        for(int i = 0; i < zombieClicker.getShop().getSquads_amount(); i++){
            if(zombieClicker.getShop().getSquadItems_array().get(i).getStatus() != 0){
                miniSquadItemArray.get(i).show_front_image(true);
            }
        }
    }

    public void update(){
        update_status();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

      //  zombieClicker.getMainGame().render(1);

        update();

        stage.act();
        stage.draw();
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
        zombieClicker.get_assets().dispose_assets_for_SquadSelectionScreen();
    }
}
