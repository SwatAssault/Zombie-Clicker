package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

import java.math.BigDecimal;
import java.math.BigInteger;


public class MyThread extends Thread {
    private final ZombieClicker zombieClicker;
    private BigInteger s1;
    private int m; //во сколько раз увилечивается награда



    private Array<SquadItem> location1_squads;

    public MyThread(final ZombieClicker zc){
        zombieClicker = zc;
        s1 = new BigInteger("0");
        m = 0;

        location1_squads = new Array<SquadItem>();

    }

    public void run(){

        update();

    }

    public void update(){
        for(;true;){
            try{
                Thread.sleep(300);
                if(!zombieClicker.getNumerics().get_location(1).getPlayer_on_location())
                    location1_activity();



            } catch (InterruptedException e){
                System.out.println("Thread Problem");
            }

         }
    }

    public BigInteger sum_location1_DMG(){
        s1 = BigInteger.valueOf(0);
        for (SquadItem x : location1_squads) {
            s1 = s1.add(x.getDps());
        }
        return s1;
    }

    public void location1_activity(){
        zombieClicker.getNumerics().get_location(1).minusHealth(sum_location1_DMG());
        if (zombieClicker.getNumerics().get_location(1).getZombie_health().compareTo(BigInteger.valueOf(0)) <= 0) {          //УБИЙСТВО ЗОМБИ

            zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
            System.out.println("zombie dead");
            zombieClicker.getNumerics().get_location(1).setMaxZombie_health();

            if (zombieClicker.getNumerics().get_location(1).getCount_death_zombies_betweenBoss() < zombieClicker.getNumerics().get_location(1).getBetweenBoss()) {
                zombieClicker.getNumerics().get_location(1).setCount_death_zombies_betweenBoss(
                        zombieClicker.getNumerics().get_location(1).getCount_death_zombies_betweenBoss() + 1);
            }

            //выдача награды
            pay_reward(1);

        }

    }


    public void remove_squad_from_location(SquadItem x, int which_location){
        switch (which_location){
            case 1:
                location1_squads.removeValue(x, true);
            break;

            default:
                System.out.println("хуй");
            break;
        }
    }

    public void pay_reward(int which_location){
        switch (which_location){
            case 1:
                m = location1_squads.size;
            break;

            default:
                m = 0;
            break;
        }
        zombieClicker.getNumerics().plus_gold(BigDecimal.valueOf(zombieClicker.getNumerics().get_location(which_location).getZombie_kill_reward().floatValue() *
                zombieClicker.getNumerics().getSquads_reward_percent() / 100 * m).toBigInteger());
    }

    //////////////////////////////GETTERS///////////////////////////////
    public Array<SquadItem> getLocation1_squads(){
        return location1_squads;
    }
    //////////////////////////////GETTERS///////////////////////////////


}
