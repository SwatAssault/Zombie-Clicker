package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.ApplicationListener;

import Numbers.Location;

public class SquadsActivity implements ApplicationListener {  //класс будет реализовывать закулисные действия отрядов на локациях

    private final ZombieClicker zombieClicker;
    private Location location;
    private SquadItem squad;

    public SquadsActivity(ZombieClicker zc, SquadItem squadItem, Location location){
        zombieClicker = zc;
        this.location = location;
        this.squad = squadItem;

    }

    public void do_DPS_damage(){
        location.minusHealth(squad.getDps());
    }

    @Override
    public void create() {
        do_DPS_damage();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
