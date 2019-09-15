package Numbers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.TimeUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Location {

    private final ZombieClicker zombieClicker;

    private BigDecimal zombie_health;          //в начале каждого уровня := max_zombie_health
    private int level_count;              //левел на текущей локации!!!
    private BigInteger zombie_kills;
    private BigDecimal boss_health;            //в начале каждого уровня := max_boss_health
    private BigInteger zombie_kill_reward;     //награда за убийство зомби(золото)
    private int boss_kill_reward;          //нагарада за убийство босса(алмазы)
    private BigInteger max_boss_health;        //увеличивается с каждым уровнем
    private BigInteger max_zombie_health;      //увеличивается с каждым уровнем
    private BigInteger last_kills;             //последнее убийство на предыдущем уровне

    private boolean bossFight;
    private double durationBossFight;          // длительность мочилова с боссом. (ms)

    private BigInteger count_kill_zombies;     // кол-во убитых зомби на локации
    private BigInteger count_kill_boss;        // кол-во убитых боссов на локации
    private Image BGimage;

    private int count_death_zombies_betweenBoss;
    private long betweenBoss;
    private boolean loseBoss;
    private boolean player_on_location;           //находится игрок на локации или нет   0 - нет, 1 - да

    private double multiplier_zombie_HP;                    //коэффициент для ХП зомби
    private double multiplier_BOSS_HP;                    //коэффициент для ХП Боссов
    private double multiplier_zombie_kill_reward;                    //коэффициент для золота с убийства зомби
    private double multiplier_boss_kill_reward;

    private BigInteger base_zombie_HP;   //начальное ХП зомби (не изменно)
    private BigInteger base_BOSS_HP;
    private BigInteger base_zombie_reward;
    private int base_boss_reward;
    private long passivePunch_time;
    private String name;

    public Location(BigInteger zombie_health, double zombie_koeff, int level_count, BigInteger zombie_kills, BigInteger boss_health, double boss_koeff, BigInteger zombie_kill_reward, double zombie_reward_koeff,
                    int boss_kill_reward, double boss_reward_koeff, long betweenBoss, double durationBossFight, String _name, final ZombieClicker zc) {

        zombieClicker = zc;
        base_zombie_HP = zombie_health;
        max_zombie_health = base_zombie_HP;
        this.zombie_health = new BigDecimal(max_zombie_health);
        this.boss_health = new BigDecimal(boss_health);
        this.base_zombie_reward = zombie_kill_reward;
        max_boss_health = boss_health;
        this.zombie_kills = zombie_kills;
        this.zombie_kill_reward = zombie_kill_reward;
        this.boss_kill_reward = boss_kill_reward;
        this.level_count = level_count;
        this.name = _name;
        last_kills = new BigInteger("0");
        player_on_location = false;

        this.betweenBoss = betweenBoss;
        loseBoss = false;
        bossFight = false;
        this.durationBossFight = durationBossFight;
        //TODO Загрузить из сохранения
        count_kill_zombies = new BigInteger("0");
        count_kill_boss = new BigInteger("0");
        count_death_zombies_betweenBoss = 0;

        passivePunch_time = 0;

        this.multiplier_zombie_HP = zombie_koeff;
        this.multiplier_BOSS_HP = boss_koeff;
        this.multiplier_zombie_kill_reward = zombie_reward_koeff;
        this.multiplier_boss_kill_reward = boss_reward_koeff;
    }

    public void minus_Boss_health(BigInteger x) {
        if (boss_health.compareTo(BigDecimal.ZERO) > 0)
            if (zombie_health.toBigInteger().compareTo(x) > 0) {
                boss_health = boss_health.subtract(new BigDecimal(x));
            } else {
                boss_health = BigDecimal.ZERO;
            }
    }

    public void minusHealth(BigInteger x) {
        if (zombie_health.compareTo(BigDecimal.ZERO) > 0)
            if (zombie_health.toBigInteger().compareTo(x) > 0) {
                zombie_health = zombie_health.subtract(new BigDecimal(x));
            } else {
                zombie_health = BigDecimal.ZERO;
            }
    }

    public void passive_punch() {
//        if(TimeUtils.timeSinceMillis(passivePunch_time) > 500) {
//            if (bossFight) {
//                boss_health = boss_health.subtract(zombieClicker.getNumerics().getPassive_damage());
//            } else
//                zombie_health = zombie_health.subtract(zombieClicker.getNumerics().getPassive_damage());
//            passivePunch_time = TimeUtils.millis();
//        }

        if (bossFight) {
            if (boss_health.compareTo(BigDecimal.ZERO) > 0) {
                boss_health = boss_health.subtract(
                        new BigDecimal(zombieClicker.getNumerics().getPassive_damage()).divide(BigDecimal.valueOf(60), MathContext.DECIMAL128));
                if(boss_health.compareTo(BigDecimal.ZERO) < 0) boss_health = BigDecimal.ZERO;
            }
        } else if (zombie_health.compareTo(BigDecimal.ZERO) > 0) {
            zombie_health = zombie_health.subtract(
                    new BigDecimal(zombieClicker.getNumerics().getPassive_damage()).divide(BigDecimal.valueOf(60), MathContext.DECIMAL128));
            if(zombie_health.compareTo(BigDecimal.ZERO) < 0) zombie_health = BigDecimal.ZERO;
        }
    }

    public void plus_zombie_health() {
        //формула увеличения хп зомби
        // HP * multiplier ^ level
        //   max_zombie_health = BigDecimal.valueOf(base_zombie_HP.floatValue() * Math.pow(multiplier_zombie_HP, level_count)).toBigInteger();
        max_zombie_health = BigDecimal.valueOf(base_zombie_HP.floatValue() * (level_count + Math.pow(multiplier_zombie_HP, level_count))).toBigInteger();
    }

    public void plus_Boss_health() {
        //формула увеличения хп босса
        max_boss_health = BigDecimal.valueOf(max_boss_health.floatValue() * Math.pow(multiplier_BOSS_HP, level_count)).toBigInteger();
    }

    public void plus_zombie_kills(BigInteger x) {
        zombie_kills = zombie_kills.add(x);
    }

    ////////////////////// GETTER ///////////////////////////
    public boolean getPlayer_on_location() {
        return player_on_location;
    }

    public BigDecimal getZombie_health() {
        return zombie_health;
    }

    public int getLevel_count() {
        return level_count;
    }

    public BigInteger getZombie_kills() {
        return zombie_kills;
    }

    public BigDecimal getBoss_health() {
        return boss_health;
    }

    public BigInteger getZombie_kill_reward() {
        return zombie_kill_reward;
    }

    public int getBoss_kill_reward() {
        return boss_kill_reward;
    }

    public BigInteger getMax_boss_health() {
        return max_boss_health;
    }

    public BigInteger getMax_zombie_health() {
        return max_zombie_health;
    }

    public Image getBGimage() {
        return BGimage;
    }

    public BigInteger getLast_kills() {
        return last_kills;
    }

    public int getCount_death_zombies_betweenBoss() {
        return count_death_zombies_betweenBoss;
    }

    public long getBetweenBoss() {
        return betweenBoss;
    }

    public boolean isBossFight() {
        return bossFight;
    }

    public double getDurationBossFight() {
        return durationBossFight;
    }

    public boolean getLoseBoss() {
        return loseBoss;
    }

    public double getMultiplier_zombie_HP() {
        return multiplier_zombie_HP;
    }

    public double getMultiplier_BOSS_HP() {
        return multiplier_BOSS_HP;
    }

    public BigInteger getBase_zombie_HP() {
        return base_zombie_HP;
    }

    public String getName() {
        return name;
    }
    ////////////////////// GETTER ///////////////////////////

    ////////////////////// SETTER ///////////////////////////
    public void setMaxZombie_health() {
        this.zombie_health = new BigDecimal(max_zombie_health);
    }

    public void upLevel(long level_count) {
        this.level_count += level_count;
    }

    public void setZombie_kills(BigInteger zombie_kills) {
        this.zombie_kills = zombie_kills;
    }

    public void setMaxBoss_health() {
        this.boss_health = new BigDecimal(max_boss_health);
    }

    public void plus_zombie_kill_reward(){
        zombie_kill_reward = BigDecimal.valueOf(base_zombie_reward.floatValue() * Math.pow(multiplier_zombie_kill_reward, level_count)).toBigInteger();
    }

    public void setZombie_kill_reward(BigInteger zombie_kill_reward) {
        this.zombie_kill_reward = zombie_kill_reward;
    }

    public void setBoss_kill_reward(int boss_kill_reward) {
        this.boss_kill_reward = boss_kill_reward;
    }

    public void setMax_boss_health(BigInteger max_boss_health) {
        this.max_boss_health = max_boss_health;
    }

    public void setMax_zombie_health(BigInteger max_zombie_health) {
        this.max_zombie_health = max_zombie_health;
    }

    public void setBossFight(boolean bossFight) {
        this.bossFight = bossFight;
    }

    public void setBGimage(String bg_texture) {
        BGimage = new Image(zombieClicker.get_assets().get_asset_manager().get(bg_texture, Texture.class));
    }

    public void setCount_death_zombies_betweenBoss(int x) {
        count_death_zombies_betweenBoss = x;
        if (!bossFight)
            count_kill_zombies = count_kill_zombies.add(BigInteger.ONE);
    }

    public void plus_count_kill_boss(long x) {
        count_kill_boss = count_kill_boss.add(BigInteger.valueOf(x));
    }

    public void setLoseBoss(boolean value) {
        loseBoss = value;
    }

    public void setPlayer_on_location(boolean x) {
        player_on_location = x;
    }

    public void setMultiplier_zombie_HP(double x) {
        multiplier_zombie_HP = x;
    }

    public void setMultiplier_BOSS_HP(double x) {
        multiplier_BOSS_HP = x;
    }
    ////////////////////// SETTER ///////////////////////////
}
