package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;


public class AnimationManager {

    private final ZombieClicker zombieClicker;
    private Animation zombie_animation;

    private TextureRegion textureRegion;

    private float scale = 0.9f;                  //насколько зомби продавливается при клике
    private float timePassed = 0;

    private ArrayList<Animation> zombie_animations_arr;
    private ArrayList<Animation> boss_animations_arr;

    public AnimationManager(final ZombieClicker zc) {
        zombieClicker = zc;

        zombie_animations_arr = new ArrayList<Animation>(Arrays.asList(
                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie1.atlas", TextureAtlas.class).getRegions()),
                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie2.atlas", TextureAtlas.class).getRegions()),
                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/zombie3.atlas", TextureAtlas.class).getRegions())
        ));

        boss_animations_arr = new ArrayList<Animation>(Arrays.asList(
                new Animation(1 / 3f, zombieClicker.get_assets().get_asset_manager().get("Animations/boss1.atlas", TextureAtlas.class).getRegions())
        ));

        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            zombie_animation = boss_animations_arr.get(0);
        } else
            zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));

    }

    public void render_zombie(SpriteBatch batch) {
        timePassed += Gdx.graphics.getDeltaTime();

        if (!zombieClicker.getNumerics().getCurrent_location().isBossFight())
            if (zombie_animation == boss_animations_arr.get(0))
                zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));

        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {
            zombie_animation = boss_animations_arr.get(0);
        }


        if (zombieClicker.getNumerics().getCurrent_location().isBossFight()) {                                                   //ЕСЛИ ИДЕТ БОССФАЙТ
            if (zombieClicker.getNumerics().getCurrent_location().getBoss_health().compareTo(BigInteger.valueOf(0)) <= 0) {        //УБИЙСТВО БОССА
                zombieClicker.getMainGame().next_level(1);
                System.out.println("boss dead");
                zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));
            }
        } else {
            if (zombieClicker.getNumerics().getCurrent_location().getZombie_health().compareTo(BigInteger.valueOf(0)) <= 0) {          //УБИЙСТВО ЗОМБИ

                zombieClicker.getNumerics().getCurrent_location().setCount_death_zombies_betweenBoss(
                        zombieClicker.getNumerics().getCurrent_location().getCount_death_zombies_betweenBoss() + 1
                );

                zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
                zombieClicker.getNumerics().plus_gold(zombieClicker.getNumerics().getCurrent_location().getZombie_kill_reward());
              //  System.out.println("zombie dead");
                zombieClicker.getNumerics().getCurrent_location().setMaxZombie_health();
                zombie_animation = zombie_animations_arr.get(MathUtils.random(0, 2));

                if (zombieClicker.getNumerics().getCurrent_location().getCount_death_zombies_betweenBoss() == zombieClicker.getNumerics().getCurrent_location().getBetweenBoss()) {
//                    zombieClicker.getNumerics().getCurrent_location().setBossFight(true);
                    zombie_animation = boss_animations_arr.get(0);
                    System.out.println("boss appears");
                }
            }
        }
        if (zombieClicker.getMainGame().get_is_mainButton_pressed()) {
            textureRegion = (TextureRegion) zombie_animation.getKeyFrame(timePassed, true);
            batch.draw(textureRegion, zombieClicker.getMainGame().getMainButton().getX() + zombieClicker.getMainGame().getMainButton().getX() * (1 - scale), zombieClicker.getMainGame().getMainButton().getY() + zombieClicker.getMainGame().getMainButton().getY() * (1 - scale), 0, 0, textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), scale, scale, 0);

        } else
            batch.draw((TextureRegion) zombie_animation.getKeyFrame(timePassed, true), zombieClicker.getMainGame().getMainButton().getX(), zombieClicker.getMainGame().getMainButton().getY());
    }

    public void dispose() {

    }

}
