package testSpine;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;

import java.util.ArrayList;
import java.util.Arrays;

public class TestSpine implements Screen {

    private final ZombieClicker zombieClicker;
    private Stage stage;
    private Camera camera;
    private Viewport viewport;
    private PolygonSpriteBatch batch;
    private TextureAtlas atlasSmall;
    private TextureAtlas atlasNormal;
    private TextureAtlas atlasHeavy;
    private TextureAtlas atlasBoss;

    private SkeletonRendererDebug debugRenderer;
    private SkeletonRenderer renderer;
    private Skeleton skeletonSmall;
    private AnimationState stateSmall;
    private Skeleton skeletonNormal;
    private AnimationState stateNormal;
    private Skeleton skeletonHeavy;
    private AnimationState stateHeavy;
    private Skeleton skeletonBoss;
    private AnimationState stateBoss;

    private ArrayList<String> boss_skin;
    private ArrayList<String> heavy_skin;
    private ArrayList<String> normal_skin;
    private ArrayList<String> small_skin;

    private int cur_zombie;
    // 0 small
    // 1 normal
    // 2 heavy
    // 3 boss

    public TestSpine(final ZombieClicker zc) {
        zombieClicker = zc;

        cur_zombie = -1;

        boss_skin = new ArrayList<String>(Arrays.asList("01_samurai_boss",
                "02_cosmonaut_boss",
                "03_futuristic_boss",
                "04_clown_boss",
                "05_pirate_boss",
                "06_superhero_boss",
                "07_viking_boss",
                "08_egypt_boss",
                "09_future_police_boss",
                "10_knight_boss"));

        heavy_skin = new ArrayList<String>(Arrays.asList("01_samurai_heavy",
                "02_cosmonaut_heavy",
                "03_futuristic_heavy",
                "04_clown_heavy",
                "05_superhero_heavy",
                "06_pirate_heavy",
                "07_viking_heavy",
                "08_future_police_heavy",
                "09_egypt_heavy",
                "10_knight_heavy"));

        normal_skin = new ArrayList<String>(Arrays.asList("01_Normal_Zombie",
                "02_Punk_Zombie",
                "03_Metal_Zombie",
                "04_Hobo_Zombie",
                "05_Street_Worker_Zombie",
                "06_Office_Worker_Zombie",
                "07_Superhero_Zombie",
                "08_Police_Zombie",
                "09_Butcher_Zombie",
                "10_Graduate_Zombie"));

        small_skin = new ArrayList<String>(Arrays.asList("01_samurai_small",
                "02_cosmonaut_small",
                "03_futuristic_small",
                "04_clown_small",
                "05_superhero_small",
                "06_pirate_small",
                "07_viking_small",
                "08_future_police_small",
                "09_egypt_small",
                "10_knight_small"));

        camera = new OrthographicCamera();
        viewport = new StretchViewport(540, 960, camera);
        stage = new Stage(viewport);
        batch = new PolygonSpriteBatch();

        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(true);
        debugRenderer = new SkeletonRendererDebug();
        debugRenderer.setBoundingBoxes(false);
        debugRenderer.setRegionAttachments(false);

        // small
        atlasSmall = new TextureAtlas(Gdx.files.internal("spine/Zombie_Small.atlas"));

        SkeletonJson jsonSmall = new SkeletonJson(atlasSmall);
        jsonSmall.setScale(0.15f);

        SkeletonData skeletonDataSmall = jsonSmall.readSkeletonData(Gdx.files.internal("spine/Zombie_Small.json"));

        skeletonSmall = new Skeleton(skeletonDataSmall);
        skeletonSmall.setPosition(170, 330);

        AnimationStateData stateDataSmall = new AnimationStateData(skeletonDataSmall);
        stateDataSmall.setMix("run", "walk", 0.2f);
        stateDataSmall.setMix("walk", "run", 0.2f);

        stateSmall = new AnimationState(stateDataSmall);
        stateSmall.setTimeScale(0.5f);

        stateSmall.setAnimation(0, "run", true);
        // ---------------------------------------------------

        // normal
        atlasNormal = new TextureAtlas(Gdx.files.internal("spine/Zombie_Normal.atlas"));

        SkeletonJson jsonNormal = new SkeletonJson(atlasNormal);
        jsonNormal.setScale(0.15f);

        SkeletonData skeletonDataNormal = jsonNormal.readSkeletonData(Gdx.files.internal("spine/Zombie_Normal.json"));

        skeletonNormal = new Skeleton(skeletonDataNormal);
        skeletonNormal.setPosition(170, 330);

        AnimationStateData stateDataNormal = new AnimationStateData(skeletonDataNormal);
        stateDataNormal.setMix("run", "walk", 0.2f);
        stateDataNormal.setMix("walk", "run", 0.2f);

        stateNormal = new AnimationState(stateDataNormal);
        stateNormal.setTimeScale(0.5f);

        stateNormal.setAnimation(0, "run", true);
        // ---------------------------------------------------

        // heavy
        atlasHeavy = new TextureAtlas(Gdx.files.internal("spine/Zombie_Heavy.atlas"));

        SkeletonJson jsonHeavy = new SkeletonJson(atlasHeavy);
        jsonHeavy.setScale(0.15f);

        SkeletonData skeletonDataHeavy = jsonHeavy.readSkeletonData(Gdx.files.internal("spine/Zombie_Heavy.json"));

        skeletonHeavy = new Skeleton(skeletonDataHeavy);
        skeletonHeavy.setPosition(170, 330);

        AnimationStateData stateDataHeavy = new AnimationStateData(skeletonDataHeavy);
        stateDataHeavy.setMix("run", "walk", 0.2f);
        stateDataHeavy.setMix("walk", "run", 0.2f);

        stateHeavy = new AnimationState(stateDataHeavy);
        stateHeavy.setTimeScale(0.5f);

        stateHeavy.setAnimation(0, "run", true);
        // ---------------------------------------------------

        // Boss
        atlasBoss = new TextureAtlas(Gdx.files.internal("spine/Zombie_Boss.atlas"));

        SkeletonJson json = new SkeletonJson(atlasBoss);
        json.setScale(0.15f);

        SkeletonData skeletonDataBoss = json.readSkeletonData(Gdx.files.internal("spine/Zombie_Boss.json"));

        skeletonBoss = new Skeleton(skeletonDataBoss);
        skeletonBoss.setPosition(170, 330);

        AnimationStateData stateDataBoss = new AnimationStateData(skeletonDataBoss);
        stateDataBoss.setMix("run", "walk", 0.2f);
        stateDataBoss.setMix("walk", "run", 0.2f);

        stateBoss = new AnimationState(stateDataBoss);
        stateBoss.setTimeScale(0.5f);

        stateBoss.setAnimation(0, "run", true);
        // ---------------------------------------------------


//        state.addAnimation(0, "run_attack", false, 2); // Jump after 2 seconds.
//        state.setAnimation(0, "walk", true);
//        state.addAnimation(0, "run", true, 0); // Run after the jump.
        setNewSkin(MathUtils.random(0, 2));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (cur_zombie == 0)
            draw(stateSmall, skeletonSmall);
        if (cur_zombie == 1)
            draw(stateNormal, skeletonNormal);
        if (cur_zombie == 2)
            draw(stateHeavy, skeletonHeavy);
        if (cur_zombie == 3)
            draw(stateBoss, skeletonBoss);
    }

    private void draw(AnimationState state, Skeleton skeleton) {
        state.update(Gdx.graphics.getDeltaTime());

        state.apply(skeleton);
        skeleton.updateWorldTransform();

        camera.update();
        batch.getProjectionMatrix().set(camera.combined);
        debugRenderer.getShapeRenderer().setProjectionMatrix(camera.combined);

        batch.begin();
        renderer.draw(batch, skeleton);
        batch.end();

//        debugRenderer.draw(skeleton);
    }

    public void setNewSkin(int cur_zombie) {

        this.cur_zombie = cur_zombie;

        if (cur_zombie == 0)
            skeletonSmall.setSkin(small_skin.get(MathUtils.random(0, 9)));
        if (cur_zombie == 1)
            skeletonNormal.setSkin(normal_skin.get(MathUtils.random(0, 9)));
        if (cur_zombie == 2)
            skeletonHeavy.setSkin(heavy_skin.get(MathUtils.random(0, 9)));
        if (cur_zombie == 3)
            skeletonBoss.setSkin(boss_skin.get(MathUtils.random(0, 9)));

//        skeleton.setSlotsToSetupPose();
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

    }
}
