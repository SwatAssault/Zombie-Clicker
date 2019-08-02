package Numbers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.math.BigInteger;

public class Location {

    private final ZombieClicker zombieClicker;

    private BigInteger zombie_health;          //в начале каждого уровня := max_zombie_health
    private int level_count;              //левел на текущей локации!!!
    private BigInteger zombie_kills;
    private BigInteger boss_health;            //в начале каждого уровня := max_boss_health
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

    public Location(BigInteger zombie_health, int level_count, BigInteger zombie_kills, BigInteger boss_health, BigInteger zombie_kill_reward,
                    int boss_kill_reward, long betweenBoss, double durationBossFight, final ZombieClicker zc) {

        zombieClicker = zc;

        this.zombie_health = zombie_health;
        max_zombie_health = zombie_health;
        this.boss_health = boss_health;
        max_boss_health = boss_health;
        this.zombie_kills = zombie_kills;
        this.zombie_kill_reward = zombie_kill_reward;
        this.boss_kill_reward = boss_kill_reward;
        this.level_count = level_count;
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
    }

    public void minus_Boss_health(BigInteger x) {
        boss_health = boss_health.subtract(x);
    }

    public void minusHealth(BigInteger x) {
        zombie_health = zombie_health.subtract(x);
    }

    public void passive_punch() {
        if (bossFight) {
            boss_health = boss_health.subtract(zombieClicker.getNumerics().getPassive_damage());
        } else
            zombie_health = zombie_health.subtract(zombieClicker.getNumerics().getPassive_damage());
    }

    public void plus_zombie_health() {
        //формула увеличения хп зомби (пока с каждым уровнем увеличивается в полтора раза)
        max_zombie_health = max_zombie_health.add(max_zombie_health.divide(BigInteger.valueOf(2)));
    }

    public void plus_Boss_health() {
        //формула увеличения хп босса (пока с каждым уровнем увеличивается в полтора раза)
        max_boss_health = max_boss_health.add(max_boss_health.divide(BigInteger.valueOf(2)));
    }

    public void plus_zombie_kills(BigInteger x) {
        zombie_kills = zombie_kills.add(x);
    }

    ////////////////////// GETTER ///////////////////////////
    public boolean getPlayer_on_location(){
        return player_on_location;
    }

    public BigInteger getZombie_health() {
        return zombie_health;
    }

    public int getLevel_count() {
        return level_count;
    }

    public BigInteger getZombie_kills() {
        return zombie_kills;
    }

    public BigInteger getBoss_health() {
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

    public boolean getLoseBoss(){
        return loseBoss;
    }
    ////////////////////// GETTER ///////////////////////////

    ////////////////////// SETTER ///////////////////////////
    public void setMaxZombie_health() {
        this.zombie_health = max_zombie_health;
    }

    public void upLevel(long level_count) {
        this.level_count += level_count;
    }

    public void setZombie_kills(BigInteger zombie_kills) {
        this.zombie_kills = zombie_kills;
    }

    public void setMaxBoss_health() {
        this.boss_health = max_boss_health;
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

    public void setPlayer_on_location(boolean x){
        player_on_location = x;
    }
    ////////////////////// SETTER ///////////////////////////
}
