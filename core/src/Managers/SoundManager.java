package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    private final ZombieClicker zombieClicker;

    public SoundManager(final ZombieClicker zc) {
        zombieClicker = zc;
    }

    public void play_punch_sound() {
        //zombieClicker.get_assets().get_asset_manager().get("Sounds/punch.wav", Sound.class).play();
    }


}
