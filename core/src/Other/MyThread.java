package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.utils.Array;

import java.math.BigDecimal;
import java.math.BigInteger;


public class MyThread extends Thread {
    private final ZombieClicker zombieClicker;
    private BigInteger s;
    private int m; //во сколько раз увилечивается награда



    private Array<SquadItem> location1_squads;

    private Array<Array<SquadItem>> allSquads;

    public MyThread(final ZombieClicker zc){
        zombieClicker = zc;
        s = new BigInteger("0");
        m = 0;

        location1_squads = new Array<SquadItem>();


        allSquads = new Array<Array<SquadItem>>();

        allSquads.add(location1_squads);
    }

    public void run(){

        update();

    }

    public void update(){
        for(;true;){
            try{
                Thread.sleep(300);
                if(!zombieClicker.getNumerics().get_location(1).getPlayer_on_location())
                    location_activity(1);



            } catch (InterruptedException e){
                System.out.println("Thread Problem");
            }

         }
    }

    public BigInteger sum_location_DMG(int which_location){
        s = BigInteger.valueOf(0);
        for (SquadItem x : allSquads.get(which_location - 1)) {
            s = s.add(x.getDps());
        }
        return s;
    }

    public void location_activity(int which_location){
        zombieClicker.getNumerics().get_location(which_location).minusHealth(sum_location_DMG(which_location));
        if (zombieClicker.getNumerics().get_location(which_location).getZombie_health().compareTo(BigInteger.valueOf(0)) <= 0) {          //УБИЙСТВО ЗОМБИ

            zombieClicker.getNumerics().plus_zombie_kills(BigInteger.valueOf(1));
            System.out.println("zombie dead");
            zombieClicker.getNumerics().get_location(which_location).setMaxZombie_health();

            if (zombieClicker.getNumerics().get_location(which_location).getCount_death_zombies_betweenBoss() < zombieClicker.getNumerics().get_location(which_location).getBetweenBoss()) {
                zombieClicker.getNumerics().get_location(which_location).setCount_death_zombies_betweenBoss(
                        zombieClicker.getNumerics().get_location(which_location).getCount_death_zombies_betweenBoss() + 1);
            }

            //выдача награды
            pay_reward(which_location);

        }

    }


    public void remove_squad_from_location(SquadItem x, int which_location){
        allSquads.get(which_location - 1).removeValue(x, true);
    }

    public void pay_reward(int which_location){
        zombieClicker.getNumerics().plus_gold(BigDecimal.valueOf(zombieClicker.getNumerics().get_location(which_location).getZombie_kill_reward().floatValue() *
                zombieClicker.getNumerics().getSquads_reward_percent() / 100 * allSquads.get(which_location - 1).size).toBigInteger());
    }

    //////////////////////////////GETTERS///////////////////////////////
    public Array<SquadItem> getLocation1_squads(){
        return location1_squads;
    }

    public Array<Array<SquadItem>> getAllSquads(){
        return allSquads;
    }
    //////////////////////////////GETTERS///////////////////////////////


}
