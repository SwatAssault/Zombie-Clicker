package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.math.MathUtils;

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
                Thread.sleep(300);


            } catch (InterruptedException e){
                System.out.println("Thread Problem");
            }

         }
    }



}
