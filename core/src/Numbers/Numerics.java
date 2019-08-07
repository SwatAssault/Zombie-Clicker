package Numbers;

import com.awprecords.zombieclicker.ZombieClicker;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Numerics {

    private int player_bg;
    private int player_body;
    private int player_ear;
    private int player_face;
    private int player_freckless;
    private int player_eyes;
    private int player_nose;
    private int player_mouth;
    private int player_hair;
    private int player_bread;
    private int player_brows;
    private String player_name;

    private BigInteger global_tap_count;
    private BigInteger gold_from_taps;
    private int diamonds = 100;                 //доп валюта
    private BigInteger zombie_kills;
    private double squads_reward_percent;     //сколько процентов от золота приносит убийство отрядов
    private long boss_kills;
    private BigInteger gold;                   //основная валюта
    private BigInteger punch_power;            //сила одного удара(клика)
    private float how_fast_passive_damage;     //насколько быстро (автоматически) отнимается хп (чем меньше значение, тем быстрее)
    private BigInteger passive_damage;
    private BigInteger oneTrillion;
    private BigInteger oneQuadrillion;
    private BigInteger oneQuintillion;
    private BigInteger oneSextillion;
    private BigInteger oneSeptillion;
    private BigInteger oneOctillion;
    private BigInteger oneNonillion;
    private BigInteger oneDecallion;
    private BigInteger oneAndecallion;
    private BigInteger oneDodecallion;

    private String string_to_cut;
    private String before_dot;
    private String after_dot;

    private int current_location;
    private ArrayList<Location> location;

    private int countCraftItem_1;
    private int countCraftItem_2;
    private int countCraftItem_3;
    private int countCraftItem_4;

    public Numerics(ZombieClicker zc) {


        //TODO загрузить из сохранения
        player_bg = 0;
        player_body = 0;
        player_ear = 0;
        player_face = 0;
        player_freckless = -1;
        player_eyes = 0;
        player_nose = 0;
        player_mouth = 0;
        player_hair = 0;
        player_bread = -1;
        player_brows = 0;
        player_name = "GUSTAV";

        //TODO скорее всего придется это переделать когда появятся сохранения
        location = new ArrayList<Location>(Arrays.asList(
                //                 zombie_health                                            zombie_kills             boss_health                       zombie_kill_reward
                new Location(new BigInteger("10"), 1.195f,1, new BigInteger("0"), new BigInteger("12"), 1, new BigInteger("2"), 1.09f,
                        1, 1,10, 3000, zc),

                new Location(new BigInteger("12"), 1.16f,1, new BigInteger("0"), new BigInteger("102"), 1, new BigInteger("2"), 1.09f,
                        1, 1,10, 3000, zc),

                new Location(new BigInteger("15"), 1.16f,1, new BigInteger("0"), new BigInteger("120"), 1, new BigInteger("2"), 1.09f,
                        1, 1,10, 3000, zc),

                new Location(new BigInteger("20"), 1.16f,1, new BigInteger("0"), new BigInteger("1020"), 1, new BigInteger("2"), 1.09f,
                        1, 1,10, 3000, zc)
        ));

        current_location = 0;

        global_tap_count = new BigInteger("0");
        gold = new BigInteger("0");
        zombie_kills = new BigInteger("0");
        boss_kills = 0;
        punch_power = new BigInteger("1");
        how_fast_passive_damage = 0.1f;
        passive_damage = new BigInteger("0");
        oneTrillion = new BigInteger("1000000000000"); //12
        oneQuadrillion = new BigInteger("1000000000000000");//15
        oneQuintillion = new BigInteger("1000000000000000000"); //18
        oneSextillion = new BigInteger("1000000000000000000000"); //21
        oneSeptillion = new BigInteger("1000000000000000000000000"); //24
        oneOctillion = new BigInteger("1000000000000000000000000000"); //27
        oneNonillion = new BigInteger("1000000000000000000000000000000"); //30
        oneDecallion = new BigInteger("1000000000000000000000000000000000"); //33
        oneAndecallion = new BigInteger("1000000000000000000000000000000000000"); //36
        oneDodecallion = new BigInteger("1000000000000000000000000000000000000000"); //39
        squads_reward_percent = 10.0f;
        gold_from_taps = new BigInteger("0");

        //TODO считывать из сохранения
        countCraftItem_1 = 10;
        countCraftItem_2 = 10;
        countCraftItem_3 = 10;
        countCraftItem_4 = 10;
    }

    //   XXX.XXXM(K,M,B,T,q,Q,s,S,O) FORMAT
    public String bigInteger_to_string(BigInteger x) {
        String y;
        string_to_cut = x.toString();

        if(string_to_cut.length() >= 4 && string_to_cut.length() <= 6){                 //ЕСЛИ ЧИСЛО В ТЫСЯЧАХ
            before_dot = x.divide(BigInteger.valueOf(1000)).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "K";
        } else if (string_to_cut.length() >= 7 && string_to_cut.length() <= 9) {       //ЕСЛИ ЧИСЛО В МИЛЛИОНАХ
            before_dot = x.divide(BigInteger.valueOf(1000000)).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "M";
        } else if (string_to_cut.length() >= 10 && string_to_cut.length() <= 12) {       //ЕСЛИ ЧИСЛО В МИЛЛИАРДАХ
            before_dot = x.divide(BigInteger.valueOf(1000000000)).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "B";
        } else if (string_to_cut.length() >= 13 && string_to_cut.length() <= 15) {       //ЕСЛИ ЧИСЛО В ТРИЛЛИОНАХ
            before_dot = x.divide(oneTrillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "T";
        } else if (string_to_cut.length() >= 16 && string_to_cut.length() <= 18) {       //ЕСЛИ ЧИСЛО В КВАДРИЛЛИОНАХ
            before_dot = x.divide(oneQuadrillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "q";
        } else if (string_to_cut.length() >= 19 && string_to_cut.length() <= 21) {       //ЕСЛИ ЧИСЛО В КВИНТИЛЛИОНАХ
            before_dot = x.divide(oneQuintillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "Q";
        } else if (string_to_cut.length() >= 22 && string_to_cut.length() <= 24) {       //ЕСЛИ ЧИСЛО В СЕКСТИЛЛИОНАХ
            before_dot = x.divide(oneSextillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "s";
        } else if (string_to_cut.length() >= 25 && string_to_cut.length() <= 27) {       //ЕСЛИ ЧИСЛО В СЕПТИЛЛИОНАХ
            before_dot = x.divide(oneSeptillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "S";
        } else if (string_to_cut.length() >= 28 && string_to_cut.length() <= 30) {       //ЕСЛИ ЧИСЛО В ОКТИЛЛИОНАХ
            before_dot = x.divide(oneOctillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "O";
        } else if (string_to_cut.length() >= 31 && string_to_cut.length() <= 33) {       //ЕСЛИ ЧИСЛО В НОНИЛЛИОНАХ
            before_dot = x.divide(oneNonillion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "N";
        } else if (string_to_cut.length() >= 34 && string_to_cut.length() <= 36) {       //ЕСЛИ ЧИСЛО В ДЕКАЛЛИОНАХ
            before_dot = x.divide(oneDecallion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "d";
        } else if (string_to_cut.length() >= 37 && string_to_cut.length() <= 39) {       //ЕСЛИ ЧИСЛО В ЭНДЕКАЛЛИОНАХ
            before_dot = x.divide(oneAndecallion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "Ad";
        } else if (string_to_cut.length() >= 40 && string_to_cut.length() <= 42) {       //ЕСЛИ ЧИСЛО В ДОДЕКАЛЛИОНАХ
            before_dot = x.divide(oneDodecallion).toString();
            after_dot = string_to_cut.substring(before_dot.length(), before_dot.length() + 3);
            y = before_dot + "." + after_dot + "D";
        } else if (string_to_cut.length() >= 43){
            y = string_to_cut.substring(0,1) + "." + string_to_cut.substring(1,3) + "e" + (string_to_cut.length() - 1);
        } else
            y = x.toString();

        return y;
    }

    public void passive_punch() {

    }

    //БОЛЬШИНСТВО СЕТТЕРОВ НУЖНЫ ДЛЯ ОПРЕДЕЛЕНИЯ ЗНАЧЕНИЙ ПРИ ЗАГРУЗКЕ СОХРАНЕНИЯ
    /////////////////SETTERS//////////////////
    public void setGlobal_tap_count(BigInteger x) {
        global_tap_count = x;
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

    public void plus_global_tap_count(BigInteger x) {
        global_tap_count = global_tap_count.add(x);
    }

    public void plus_diamonds(int x) {
        diamonds += x;
    }

    public void minus_Gold(BigInteger x) {
        gold = gold.subtract(x);
    }

    public void minus_diamonds(int x){
        diamonds -= x;
    }

    public void plus_gold(BigInteger x) {
        //формула увеличения выпадения золота с каждым уровнем
        gold = gold.add(x);
    }

    public void plus_zombie_kills(BigInteger x) {
        zombie_kills = zombie_kills.add(x);
    }

    public void plus_boss_kills(long x) {
        boss_kills += x;
    }

    public void plus_punch_power(BigInteger x) {
        //вызывается при покупке улучшения на урон в магазине
        punch_power = punch_power.add(x);
    }

    public void setHow_fast_passive_damage(float x) {
        how_fast_passive_damage = x;
    }

    public void setCurrent_num_location(int x) {
        current_location = x;
    }

    public void minusCountCraftItem_1(int value) {
        countCraftItem_1 -= value;
    }

    public void minusCountCraftItem_2(int value) {
        countCraftItem_2 -= value;
    }

    public void minusCountCraftItem_3(int value) {
        countCraftItem_3 -= value;
    }

    public void minusCountCraftItem_4(int value) {
        countCraftItem_4 -= value;
    }

    public void setPlayer_bg(int value) {
        player_bg = value;
    }

    public void setPlayer_body(int value) {
        player_body = value;
    }

    public void setPlayer_ear(int value) {
        player_ear = value;
    }

    public void setPlayer_face(int value) {
        player_face = value;
    }

    public void setPlayer_freckless(int value) {
        player_freckless = value;
    }

    public void setPlayer_eyes(int value) {
        player_eyes = value;
    }

    public void setPlayer_nose(int value) {
        player_nose = value;
    }

    public void setPlayer_mouth(int value) {
        player_mouth = value;
    }

    public void setPlayer_hair(int value) {
        player_hair = value;
    }

    public void setPlayer_bread(int value) {
        player_bread = value;
    }

    public void setPlayer_brows(int value) {
        player_brows = value;
    }

    public void setPlayer_name(String name) {
        player_name = name;
    }

    public void setSquads_reward_percent(double x){
        // в процентах % 10.0f
        squads_reward_percent = x;
    }

    public void setGold_from_taps(BigInteger x){
        gold_from_taps = x;
    }
    /////////////////SETTERS//////////////////


    ////////////////GETTERS//////////////////
    public BigInteger getGlobal_tap_count() {
        return global_tap_count;
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

    public long getBoss_kills() {
        return boss_kills;
    }

    public BigInteger getPunch_power() {
        return punch_power;
    }

    public float getHow_fast_passive_damage() {
        return how_fast_passive_damage;
    }

    public BigInteger getPassive_damage() {
        return passive_damage;
    }

    public int getCurrent_num_location() {
        return current_location;
    }

    public Location getCurrent_location() {
        return location.get(current_location);
    }

    public Location get_location(int x) {
        return location.get(x - 1);
    }

    public int getCountCraftItem_1() {
        return countCraftItem_1;
    }

    public int getCountCraftItem_2() {
        return countCraftItem_2;
    }

    public int getCountCraftItem_3() {
        return countCraftItem_3;
    }

    public int getCountCraftItem_4() {
        return countCraftItem_4;
    }

    public int getPlayer_bg() {
        return player_bg;
    }

    public int getPlayer_body() {
        return player_body;
    }

    public int getPlayer_ear() {
        return player_ear;
    }

    public int getPlayer_face() {
        return player_face;
    }

    public int getPlayer_freckless() {
        return player_freckless;
    }

    public int getPlayer_eyes() {
        return player_eyes;
    }

    public int getPlayer_nose() {
        return player_nose;
    }

    public int getPlayer_mouth() {
        return player_mouth;
    }

    public int getPlayer_hair() {
        return player_hair;
    }

    public int getPlayer_bread() {
        return player_bread;
    }

    public int getPlayer_brows() {
        return player_brows;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public double getSquads_reward_percent(){
        return squads_reward_percent;
    }

    public BigInteger getGold_from_taps(){
        return gold_from_taps;
    }
    ////////////////GETTERS//////////////////
}
