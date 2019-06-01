package Other;

import com.awprecords.zombieclicker.ZombieClicker;

import java.math.BigInteger;

public class KeepTrackAch { //КЛАСС НУЖЕН ДЛЯ ОТСЛЕЖИВАНИЯ ДОСТИЖЕНИЙ, СОЗДАЕТСЯ В САМОМ НАЧАЛЕ

    final ZombieClicker zombieClicker;
    private boolean[] achievement_mass;

    // [0] - 100 КЛИКОВ
    // [1] - 500 КЛИКОВ
    // [2] - 1000 КЛИКОВ
    // [3] - 5000 КЛИКОВ
    // [4] - 10000 КЛИКОВ
    // [5] - 100000 КЛИКОВ
    // [6] - 10 УБИЙСТВ
    // [7] - 100 УБИЙСТВ
    // [8] - 500 УБИЙСТВ
    // [9] - 1000 УБИЙСТВ
    // [10] - 10000 УБИЙСТВ
    // [11] - 100000 УБИЙСТВ
    // [12] - 500000 УБИЙСТВ

    public KeepTrackAch(final ZombieClicker zc){
        zombieClicker = zc;
        achievement_mass = new boolean[]{true, true, true, true, true, true, true, true, true, true, true, true, true};

    }

    public void keep_track_of_achievements(){
        if(zombieClicker.getNumerics().getGlobal_tap_count().compareTo(BigInteger.valueOf(100)) == 0){
            achievement_mass[7] = true;
        }
        if(zombieClicker.getNumerics().getGlobal_tap_count().compareTo(BigInteger.valueOf(500)) == 0){
            achievement_mass[8] = true;
        }
        if(zombieClicker.getNumerics().getGlobal_tap_count().compareTo(BigInteger.valueOf(1000)) == 0){
            achievement_mass[9] = true;
        }
        if(zombieClicker.getNumerics().getGlobal_tap_count().compareTo(BigInteger.valueOf(10000)) == 0){
            achievement_mass[10] = true;
        }
        if(zombieClicker.getNumerics().getGlobal_tap_count().compareTo(BigInteger.valueOf(100000)) == 0){
            achievement_mass[11] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(10)) == 0){
            achievement_mass[0] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(100)) == 0){
            achievement_mass[1] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(500)) == 0){
            achievement_mass[2] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(1000)) == 0){
            achievement_mass[3] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(10000)) == 0){
            achievement_mass[4] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(100000)) == 0){
            achievement_mass[5] = true;
        }
        if(zombieClicker.getNumerics().getZombie_kills().compareTo(BigInteger.valueOf(500000)) == 0){
            achievement_mass[6] = true;
        }

    }

    public boolean[] get_achievement_mass(){
        return achievement_mass;
    }

}
