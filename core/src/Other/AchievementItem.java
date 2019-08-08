package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AchievementItem {

    private final ZombieClicker zombieClicker;
    private String name;
    private String description;
    private Image icon;

    public AchievementItem(ZombieClicker zc, String name, String description){
        zombieClicker = zc;

        this.name = name;
        this.description = description;

    }

    public Image getAchievementItem(){
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void dispose(){

    }

}
