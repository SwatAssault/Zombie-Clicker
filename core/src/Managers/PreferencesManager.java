package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {

    private final ZombieClicker zombieClicker;
    private Preferences settings;

    public PreferencesManager(ZombieClicker zc){
        zombieClicker = zc;
        settings = Gdx.app.getPreferences("settingsZombieClicker");


    }


    public Preferences getSettings(){
        return settings;
    }

}
