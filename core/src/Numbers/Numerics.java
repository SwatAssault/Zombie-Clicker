package Numbers;

import java.math.BigInteger;

public class Numerics {

    private BigInteger zombie_health;          //в начале каждого уровня := max_zombie_health
    private BigInteger global_tap_count;
    private int level_count;              //левел на текущей локации!!!
    private int level_count1 = 1;             //левел на 1 локации!!!
    private int level_count2 = 1;             //левел на 2 локации!!!
    private int level_count3 = 1;             //левел на 3 локации!!!
    private int level_count4 = 1;             //левел на 4 локации!!!
    private int level_count5 = 1;             //левел на 5 локации!!!
    private int diamonds = 0;                 //доп валюта
    private BigInteger zombie_kills;
    private BigInteger gold;                   //основная валюта
    private BigInteger punch_power;            //сила одного удара(клика)
    private BigInteger boss_health;            //в начале каждого уровня := max_boss_health
    private BigInteger zombie_kill_reward;     //награда за убийство зомби(золото)
    private int boss_kill_reward = 1;          //нагарада за убийство босса(алмазы)
    private BigInteger max_boss_health;        //увеличивается с каждым уровнем
    private BigInteger max_zombie_health;      //увеличивается с каждым уровнем
    private BigInteger last_kills;             //последнее убийство на предыдущем уровне
    private float how_fast_passive_damage;     //насколько быстро (автоматически) отнимается хп (чем меньше значение, тем быстрее)
    private BigInteger passive_damage;
    private BigInteger oneTrillion;
    private BigInteger oneQuadrillion;

    private boolean bossFight;
    private String string_to_cut;
    private String before_dot;
    private String after_dot;

    private String selected_level;

    public Numerics() {
        //ЧТОБЫ ПОМЕНЯТЬ ХП ЗОМБИ ИЛИ БОССА, МЕНЯТЬ И МАКС ХП ТОЖЕ!!!
        zombie_health = new BigInteger("10");
        max_zombie_health = new BigInteger("10");
        boss_health = new BigInteger("20");
        max_boss_health = new BigInteger("20");
        global_tap_count = new BigInteger("0");
        gold = new BigInteger("0");
        zombie_kills = new BigInteger("0");
        punch_power = new BigInteger("2");
        zombie_kill_reward = new BigInteger("50000000");
        last_kills = new BigInteger("0");
        how_fast_passive_damage = 0.1f;
        passive_damage = new BigInteger("0");
        oneTrillion = new BigInteger("1000000000000");
        oneQuadrillion = new BigInteger("1000000000000000");

        selected_level = "level_1";
        bossFight = false;
    }

    //   XXX.XXXM(B,T,Q) FORMAT
    public String bigInteger_to_string(BigInteger x){
        String y;
        string_to_cut = x.toString();
        if(string_to_cut.length() >= 7 && string_to_cut.length() <= 9){       //ЕСЛИ ЧИСЛО В МИЛЛИОНАХ
            before_dot = x.divide(BigInteger.valueOf(1000000)).toString();
            if(before_dot.length() == 1){
                before_dot = "00" + before_dot;
            } else if(before_dot.length() == 2){
                before_dot = "0" + before_dot;
            }
            after_dot = string_to_cut.substring(3, 6);
            y = before_dot + "." + after_dot + "M";
        } else
        if(string_to_cut.length() >= 10 && string_to_cut.length() <= 12){       //ЕСЛИ ЧИСЛО В МИЛЛИАРДАХ
            before_dot = x.divide(BigInteger.valueOf(1000000000)).toString();
            if(before_dot.length() == 1){
                before_dot = "00" + before_dot;
            } else if(before_dot.length() == 2){
                before_dot = "0" + before_dot;
            }
            after_dot = string_to_cut.substring(3, 6);
            y = before_dot + "." + after_dot + "B";
        } else
        if(string_to_cut.length() >= 13 && string_to_cut.length() <= 15){       //ЕСЛИ ЧИСЛО В ТРИЛЛИОНАХ
            before_dot = x.divide(oneTrillion).toString();
            if(before_dot.length() == 1){
                before_dot = "00" + before_dot;
            } else if(before_dot.length() == 2){
                before_dot = "0" + before_dot;
            }
            after_dot = string_to_cut.substring(3, 6);
            y = before_dot + "." + after_dot + "T";
        } else
        if(string_to_cut.length() >= 16 && string_to_cut.length() <= 18){       //ЕСЛИ ЧИСЛО В КВАДРИЛЛИОНАХ
            before_dot = x.divide(oneQuadrillion).toString();
            if(before_dot.length() == 1){
                before_dot = "00" + before_dot;
            } else if(before_dot.length() == 2){
                before_dot = "0" + before_dot;
            }
            after_dot = string_to_cut.substring(3, 6);
            y = before_dot + "." + after_dot + "Q";
        } else
            y = x.toString();

        return y;
    }

    public void passive_punch(){
            if(bossFight){
                boss_health = boss_health.subtract(passive_damage);
            } else
                zombie_health = zombie_health.subtract(passive_damage);
    }

    //БОЛЬШИНСТВО СЕТТЕРОВ НУЖНЫ ДЛЯ ОПРЕДЕЛЕНИЯ ЗНАЧЕНИЙ ПРИ ЗАГРУЗКЕ СОХРАНЕНИЯ
    /////////////////SETTERS//////////////////
    public void setZombie_health(BigInteger x) {
        zombie_health = x;
    }

    public void setMax_zombie_health(BigInteger x) {
        max_zombie_health = x;
    }

    public void setMax_boss_health(BigInteger x) {
        max_boss_health = x;
    }

    public void setGlobal_tap_count(BigInteger x) {
        global_tap_count = x;
    }

    public void setLevel_count(int x) {
        level_count = x;
    }

    public void setLevel_count1(int x){
        level_count1 = x;
    }

    public void setLevel_count2(int x){
        level_count2 = x;
    }

    public void setLevel_count3(int x){
        level_count3 = x;
    }

    public void setLevel_count4(int x){
        level_count4 = x;
    }

    public void setLevel_count5(int x){
        level_count5 = x;
    }

    public void setDiamonds(int x) {
        diamonds = x;
    }

    public void setGold(BigInteger x) {
        gold = x;
    }

    public void setZombie_kills(BigInteger x) {
        zombie_kills = x;
    }

    public void setPunch_power(BigInteger x) {
        punch_power = x;
    }

    public void setBoss_health(BigInteger x) {
        boss_health = x;
    }

    public void setZombie_kill_reward(BigInteger x) {
        zombie_kill_reward = x;
    }

    public void setBoss_kill_reward(int x) {
        boss_kill_reward = x;
    }

    public void minusHealth(BigInteger x) {
        zombie_health = zombie_health.subtract(x);
    }

    public void plus_global_tap_count(BigInteger x) {
        global_tap_count = global_tap_count.add(x);
    }

    public void plus_level_count(long x) {
        level_count += x;
    }

    public void plus_diamonds(int x) {
        diamonds += x;
    }

    public void minus_Gold(BigInteger x){
        gold = gold.subtract(x);
    }

    public void plus_gold(BigInteger x) {
        //формула увеличения выпадения золота с каждым уровнем
        gold = gold.add(x);
    }

    public void plus_zombie_kills(BigInteger x) {
        zombie_kills = zombie_kills.add(x);
    }

    public void plus_punch_power(BigInteger x) {
        //вызывается при покупке улучшения на урон в магазине
        punch_power = punch_power.add(x);
    }

    public void minus_Boss_health(BigInteger x) {
        boss_health = boss_health.subtract(x);
    }

    public void plus_Zombie_kill_reward(BigInteger x) {
        zombie_kills = zombie_kills.add(x);
    }

    public void plus_Boss_health() {
        //формула увеличения хп босса (пока с каждым уровнем увеличивается в полтора раза)
        max_boss_health = max_boss_health.add(max_boss_health.divide(BigInteger.valueOf(2)));
    }

    public void plus_zombie_health() {
        //формула увеличения хп зомби (пока с каждым уровнем увеличивается в полтора раза)
        max_zombie_health = max_zombie_health.add(max_zombie_health.divide(BigInteger.valueOf(2)));
    }

    public void setLast_kills(BigInteger x) {
        last_kills = x;
    }

    public void set_bossFight(boolean x) {
        bossFight = x;
    }

    public void setHow_fast_passive_damage(float x){
        how_fast_passive_damage = x;
    }

    public void setSelected_level(String x){
        selected_level = x;
    }
    /////////////////SETTERS//////////////////


    ////////////////GETTERS//////////////////
    public BigInteger getZombie_health() {
        return zombie_health;
    }

    public BigInteger getGlobal_tap_count() {
        return global_tap_count;
    }

    public int getLevel_count() {
        return level_count;
    }

    public int getLevel_count1() {
        return level_count1;
    }

    public int getLevel_count2() {
        return level_count2;
    }

    public int getLevel_count3() {
        return level_count3;
    }

    public int getLevel_count4() {
        return level_count4;
    }

    public int getLevel_count5() {
        return level_count5;
    }

    public int getDiamonds() {
        return diamonds;
    }

    public BigInteger getGold() {
        return gold;
    }

    public BigInteger getZombie_kills() {
        return zombie_kills;
    }

    public BigInteger getPunch_power() {
        return punch_power;
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

    public BigInteger getLast_kills() {
        return last_kills;
    }

    public boolean get_bossFight() {
        return bossFight;
    }

    public float getHow_fast_passive_damage(){
        return how_fast_passive_damage;
    }

    public String getSelected_level(){
        return selected_level;
    }
    ////////////////GETTERS//////////////////
}
