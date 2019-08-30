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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.math.BigInteger;

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
    private TextureAtlas goldAndDiamond_icons;
    private Image gold_image;
    private Image diamond_image;
    private int whichMission;

    public TipScreen(final ZombieClicker zc, String header, String mainText, final String lastScreen, final int which_mission) {
        zombieClicker = zc;
        this.header = header;
        this.mainText = mainText;
        this.lastScreen = lastScreen;
        this.whichMission = which_mission;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new SpriteBatch();
        zombieClicker.get_assets().load_assets_for_TipScreen();
        goldAndDiamond_icons = zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/hud_atlas.atlas");
        gold_image = new Image(goldAndDiamond_icons.createSprite("gold"));
        diamond_image = new Image(goldAndDiamond_icons.createSprite("diamond"));
        gold_image.setVisible(false);
        diamond_image.setVisible(false);
        bgimage = new Image(zombieClicker.get_assets().get_asset_manager().get("Background/tipbg.png", Texture.class));
        ok_btn = new TextButton("OK", zombieClicker.get_assets().get_asset_manager().get("Buttons/ok_btn_skin.json", Skin.class));
        ok_btn.setPosition(200, 280);
        x_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/x_btn.json", Skin.class));
        x_btn.setPosition(455, 655);

        ok_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                if (lastScreen.equals("Missions")) {
                    zombieClicker.setSquadSelectionScreen(which_mission);
                }

                if(lastScreen.equals("maingame")){
                    zombieClicker.setMainGame();
                }

                if(lastScreen.equals("shop")){
                    zombieClicker.setShopScreen();
                    Gdx.input.setInputProcessor(zombieClicker.getShop().getStage());
                }
            }
        });

        x_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                if (lastScreen.equals("Missions")) {
                    Gdx.input.setInputProcessor(zombieClicker.getMissions().getStage());
                }

                if(lastScreen.equals("maingame")){
                    zombieClicker.setMainGame();
                }

                if(lastScreen.equals("shop")){
                    zombieClicker.setShopScreen();
                    Gdx.input.setInputProcessor(zombieClicker.getShop().getStage());
                }
            }
        });

        if(which_mission >= 0)
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) != 0 &&
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() != 0){
                gold_image.setVisible(true);
                diamond_image.setVisible(true);
                gold_image.setPosition(80, 400);
                diamond_image.setPosition(300,400);
            }else
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) != 0 &&     //если только gold
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() == 0){
                gold_image.setVisible(true);
                gold_image.setPosition(100, 400);
            } else
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) == 0 &&     //если только diamonds
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() != 0){
                diamond_image.setVisible(true);
                diamond_image.setPosition(100, 400);
            } else{  //если предметы для крафта

            }


        stage.addActor(bgimage);
        stage.addActor(ok_btn);
        stage.addActor(x_btn);
        stage.addActor(diamond_image);
        stage.addActor(gold_image);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (lastScreen.equals("Missions")) {
            zombieClicker.getMissions().render(1);
        }

        if(lastScreen.equals("maingame")){
            zombieClicker.getMainGame().render(1);
        }

        if(lastScreen.equals("shop")){
            zombieClicker.getShop().render(1);
        }

        stage.draw();
        stage.act();

        batch.begin();
        if (lastScreen.equals("Missions")) {
            render_for_mission();
        }

        renderRewardDepending(batch);

        batch.end();
        batch.setProjectionMatrix(camera.combined);
    }

    public void renderRewardDepending(SpriteBatch batch){
        if(whichMission >= 0)
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) != 0 &&     //если и gold и diamonds
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() != 0){
                zombieClicker.getFontManager().getHud_font().draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward()), 100, 440);
                zombieClicker.getFontManager().getHud_font().draw(batch, Integer.toString(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward()), 300, 460);
            } else
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) != 0 &&     //если только gold
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() == 0){
                zombieClicker.getFontManager().getHud_font().draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward()), 100, 440);
            } else
            if(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getGoldReward().compareTo(BigInteger.valueOf(0)) == 0 &&     //если только diamonds
                    zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward() != 0){
                zombieClicker.getFontManager().getHud_font().draw(batch, Integer.toString(zombieClicker.getNumerics().getMissionsItem().get(whichMission).getDiamondReward()), 300, 460);
            } else{  //если предметы для крафта

            }
    }

    private void render_for_mission() {
        if (zombieClicker.getNumerics().getIdMission() != -1)
            zombieClicker.getFontManager().draw_text_forMissionInfo(batch,
                    zombieClicker.getNumerics().getMissionsItem().get(zombieClicker.getNumerics().getIdMission()).getMission(),
                    zombieClicker.getNumerics().getMissionsItem().get(zombieClicker.getNumerics().getIdMission()).getRareness(),
                    zombieClicker.getNumerics().getMissionsItem().get(zombieClicker.getNumerics().getIdMission()).getTime());
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
        if (stage != null) stage.dispose();
        if (batch != null) batch.dispose();
    }
}
