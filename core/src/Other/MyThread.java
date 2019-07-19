package Other;

import com.awprecords.zombieclicker.ZombieClicker;

import java.math.BigInteger;

public class MyThread extends Thread {

    private final ZombieClicker zombieClicker;

    public MyThread(final ZombieClicker zc){
        zombieClicker = zc;

    }

    public void run(){

        update();

    }

    public void update(){
        for(;true;){
            try{
              //  System.out.println("Thread working");
                Thread.sleep(1000);
                zombieClicker.getNumerics().get_location(1).minusHealth(BigInteger.valueOf(1));
                System.out.println(zombieClicker.getNumerics().get_location(1).getZombie_health());
            } catch (InterruptedException e){
                System.out.println("Thread Problem");
            }

         }
    }



}
