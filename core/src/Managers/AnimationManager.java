package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.Arrays;


public class AnimationManager {

    private final ZombieClicker zombieClicker;
    private Animation zombie_animation;

    private TextureRegion textureRegion;

    private float scale = 0.9f;
    private float timePassed = 0;
    private boolean flag;
    private boolean for_death;
    private boolean for_deathBoss;

//    private ArrayList<Animation> zombie_animations_arr;
//    private ArrayList<Animation> boss_animations_arr;


    public AnimationManager(final ZombieClicker zc) {
        zombieClicker = zc;

        flag = true;
        for_death = true;
        for_deathBoss = true;

//        zombie_animations_arr = new ArrayList<Animation>(Arrays.asList(
//                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie1.atlas", TextureAtlas.class).getRegions()),
//                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie2.atlas", TextureAtlas.class).getRegions()),
//                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie3.atlas", TextureAtlas.class).getRegions())
//        ));
//
//        boss_animations_arr = new ArrayList<Animation>(Arrays.asList(
//                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/boss1.atlas", TextureAtlas.class).getRegions())
//        ));


//        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
////            zombie_animation = boss_animations_arr.get(0);
//            zombieClicker.getMainGame().getTestSpine().setNewSkin(3);
//        } else
////            zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));
//            zombieClicker.getMainGame().getTestSpine().setNewSkin(MathUtils.random(0, 2));

    }

    public void setFlag(boolean x) {
        flag = x;
    }

    public void render_zombie(SpriteBatch batch) {
        timePassed += Gdx.graphics.getDeltaTime();

//        if (!zombieClicker.getNumerics().getCurrent_location().isBossFight())
//            if (zombie_animation == boss_animations_arr.get(0))
//                zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));
//
//        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
//            zombie_animation = boss_animations_arr.get(0);
//        }

        if (zombieClicker.getNumerics().getCurrent_location().isBossFight() && flag) {
            zombieClicker.getNumerics().getTestSpine().setNewSkin(3);
            flag = false;
        }

        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {                                                   //ЕСЛИ ИДЕТ БОССФАЙТ
            if (zombieClicker.getNumerics().getCurrent_location().getBoss_health().compareTo(BigDecimal.valueOf(0)) <= 0) {        //УБИЙСТВО БОССА
                if ((zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("zombie_walk") ||
                        zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("hurt")) && for_deathBoss) {
                    zombieClicker.getNumerics().getTestSpine().setAnimation("death_01");
                    System.out.println(1);
                    for_deathBoss = false;
                } else {
                    if(!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01")) {
                        zombieClicker.getMainGame().next_level(1);
                        //   System.out.println("boss dead");
//                zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));
//                zombieClicker.getMainGame().getTestSpine().setNewSkin(3);
                        flag = true;
                        zombieClicker.getNumerics().getTestSpine().setNewSkin(MathUtils.random(0, 2));
                        for_deathBoss = true;
                    }
                }
            }
        } else {

            if (zombieClicker.getNumerics().getCurrent_location().getZombie_health().compareTo(BigDecimal.valueOf(0)) <= 0) {          //УБИЙСТВО ЗОМБИ

                if ((zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("zombie_walk") ||
                        zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("hurt")) && for_death) {
                    zombieClicker.getNumerics().getTestSpine().setAnimation("death_01");
                    for_death = false;
                } else {

                    if(!zombieClicker.getNumerics().getTestSpine().getAnimation().getAnimation().getName().equals("death_01")) {
                        zombieClicker.getNumerics().getCurrent_location().setCount_death_zombies_betweenBoss(
                                zombieClicker.getNumerics().getCurrent_location().getCount_death_zombies_betweenBoss() + 1
                        );

                        zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
                        zombieClicker.getNumerics().plus_gold(zombieClicker.getNumerics().getCurrent_location().getZombie_kill_reward());
                        //  System.out.println("zombie dead");
                        zombieClicker.getNumerics().getCurrent_location().setMaxZombie_health();

                        zombieClicker.getNumerics().getTestSpine().setNewSkin(MathUtils.random(0, 2));

                        if (zombieClicker.getNumerics().getCurrent_location().getCount_death_zombies_betweenBoss() == zombieClicker.getNumerics().getCurrent_location().getBetweenBoss()) {
//                    zombieClicker.getNumerics().getCurrent_location().setBossFight(true);
//                    zombie_animation = boss_animations_arr.get(0);
                            //  System.out.println("boss appears");
                        }

                        for_death = true;
                    }
                }
            }
        }
//        if (zombieClicker.getMainGame().get_is_mainButton_pressed()) {
//            textureRegion = (TextureRegion) zombie_animation.getKeyFrame(timePassed, true);
//            batch.draw(textureRegion, zombieClicker.getMainGame().getMainButton().getX() + zombieClicker.getMainGame().getMainButton().getX() * (1 - scale), zombieClicker.getMainGame().getMainButton().getY() + zombieClicker.getMainGame().getMainButton().getY() * (1 - scale), 0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), scale, scale, 0);
//
//        } else
//            batch.draw((TextureRegion) zombie_animation.getKeyFrame(timePassed, true), zombieClicker.getMainGame().getMainButton().getX(), zombieClicker.getMainGame().getMainButton().getY());
    }

    public void dispose() {

    }

}
