package Numbers;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ShopNumerics {

    //СТОИМОСТЬ КАЖДОГО ПРЕДМЕТА
    private BigInteger item1_cost;
    private BigInteger item2_cost;
    private BigInteger item3_cost;
    private BigInteger item4_cost;
    private BigInteger item5_cost;
    private BigInteger item6_cost;
    private BigInteger item7_cost;
    private BigInteger item8_cost;
    private BigInteger item9_cost;
    private BigInteger item10_cost;
    private BigInteger item11_cost;

    //НАСКОЛЬКО КАЖДЫЙ ПРЕДМЕТ УВЕЛИЧИВАЕТ СИЛУ УДАРА(ИЛИ ЧТО-ТО ДРУГОЕ)
    private BigInteger item1_value;
    private BigInteger item2_value;
    private BigInteger item3_value;
    private BigInteger item4_value;
    private BigInteger item5_value;
    private BigInteger item6_value;
    private BigInteger item7_value;
    private BigInteger item8_value;
    private BigInteger item9_value;
    private BigInteger item10_value;
    private BigInteger item11_value;

    //СКОЛЬКО РАЗ МОЖНО КУПИТЬ КАЖДЫЙ ПРЕДМЕТ
    private int item1_number;
    private int item2_number;
    private int item3_number;
    private int item4_number;
    private int item5_number;
    private int item6_number;
    private int item7_number;
    private int item8_number;
    private int item9_number;
    private int item10_number;
    private int item11_number;

    public ShopNumerics() {

        item1_cost = new BigInteger("100");
        item2_cost = new BigInteger("200");
        item3_cost = new BigInteger("300");
        item4_cost = new BigInteger("400");
        item5_cost = new BigInteger("500");
        item6_cost = new BigInteger("600");
        item7_cost = new BigInteger("700");
        item8_cost = new BigInteger("800");
        item9_cost = new BigInteger("900");
        item10_cost = new BigInteger("1000");
        item11_cost = new BigInteger("1001");

        item1_value = new BigInteger("10");
        item2_value = new BigInteger("20");
        item3_value = new BigInteger("30");
        item4_value = new BigInteger("40");
        item5_value = new BigInteger("50");
        item6_value = new BigInteger("60");
        item7_value = new BigInteger("70");
        item8_value = new BigInteger("80");
        item9_value = new BigInteger("90");
        item10_value = new BigInteger("100");
        item11_value = new BigInteger("110");

        item1_number = 100;
        item2_number = 100;
        item3_number = 100;
        item4_number = 100;
        item5_number = 100;
        item6_number = 100;
        item7_number = 100;
        item8_number = 100;
        item9_number = 100;
        item10_number = 100;
        item11_number = 100;

    }

    //////////////SETTERS///////////////
    public void setItem1_cost(BigInteger x){
        item1_cost = x;
    }
    public void setItem2_cost(BigInteger x){
        item2_cost = x;
    }
    public void setItem3_cost(BigInteger x){
        item3_cost = x;
    }
    public void setItem4_cost(BigInteger x){
        item4_cost = x;
    }
    public void setItem5_cost(BigInteger x){
        item5_cost = x;
    }
    public void setItem6_cost(BigInteger x){
        item6_cost = x;
    }
    public void setItem7_cost(BigInteger x){
        item7_cost = x;
    }
    public void setItem8_cost(BigInteger x){
        item8_cost = x;
    }
    public void setItem9_cost(BigInteger x){
        item9_cost = x;
    }
    public void setItem10_cost(BigInteger x){
        item10_cost = x;
    }
    public void setItem11_cost(BigInteger x){
        item11_cost = x;
    }

    public void plusItem1_cost(){
        //формула увеличения цены предмета
        item1_cost = item1_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem2_cost(){
        //формула увеличения цены предмета
        item2_cost = item2_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem3_cost(){
        //формула увеличения цены предмета
        item3_cost = item3_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem4_cost(){
        //формула увеличения цены предмета
        item4_cost = item4_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem5_cost(){
        //формула увеличения цены предмета
        item5_cost = item5_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem6_cost(){
        //формула увеличения цены предмета
        item6_cost = item6_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem7_cost(){
        //формула увеличения цены предмета
        item7_cost = item7_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem8_cost(){
        //формула увеличения цены предмета
        item8_cost = item8_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem9_cost(){
        //формула увеличения цены предмета
        item9_cost = item9_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem10_cost(){
        //формула увеличения цены предмета
        item10_cost = item10_cost.add(BigInteger.valueOf(100));
    }

    public void plusItem11_cost(){
        //формула увеличения цены предмета
        item11_cost = item11_cost.add(BigInteger.valueOf(1000));
    }

    public void setItem1_value(BigInteger x){
        item1_value = x;
    }
    public void setItem2_value(BigInteger x){
        item2_value = x;
    }
    public void setItem3_value(BigInteger x){
        item3_value = x;
    }
    public void setItem4_value(BigInteger x){
        item4_value = x;
    }
    public void setItem5_value(BigInteger x){
        item5_value = x;
    }
    public void setItem6_value(BigInteger x){
        item6_value = x;
    }
    public void setItem7_value(BigInteger x){
        item7_value = x;
    }
    public void setItem8_value(BigInteger x){
        item8_value = x;
    }
    public void setItem9_value(BigInteger x){
        item9_value = x;
    }
    public void setItem10_value(BigInteger x){
        item10_value = x;
    }
    public void setItem11_value(BigInteger x){
        item11_value = x;
    }

    public void plus_Item1_value(){
        //формула увеличения значения, которое дает покупка предмета
        item1_value = item1_value.add(item1_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item2_value(){
        //формула увеличения значения, которое дает покупка предмета
        item2_value = item2_value.add(item2_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item3_value(){
        //формула увеличения значения, которое дает покупка предмета
        item3_value = item3_value.add(item3_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item4_value(){
        //формула увеличения значения, которое дает покупка предмета
        item4_value = item4_value.add(item4_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item5_value(){
        //формула увеличения значения, которое дает покупка предмета
        item5_value = item5_value.add(item5_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item6_value(){
        //формула увеличения значения, которое дает покупка предмета
        item6_value = item6_value.add(item6_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item7_value(){
        //формула увеличения значения, которое дает покупка предмета
        item7_value = item7_value.add(item7_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item8_value(){
        //формула увеличения значения, которое дает покупка предмета
        item8_value = item8_value.add(item8_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item9_value(){
        //формула увеличения значения, которое дает покупка предмета
        item9_value = item9_value.add(item9_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item10_value(){
        //формула увеличения значения, которое дает покупка предмета
        item10_value = item10_value.add(item10_value.divide(BigInteger.valueOf(2)));
    }

    public void plus_Item11_value(){
        //формула увеличения значения, которое дает покупка предмета
        item11_value = item11_value.add(item11_value.divide(BigInteger.valueOf(2)));
    }

    public void setItem1_number(int x){
        item1_number = x;
    }
    public void setItem2_number(int x){
        item2_number = x;
    }
    public void setItem3_number(int x){
        item3_number = x;
    }
    public void setItem4_number(int x){
        item4_number = x;
    }
    public void setItem5_number(int x){
        item5_number = x;
    }
    public void setItem6_number(int x){
        item6_number = x;
    }
    public void setItem7_number(int x){
        item7_number = x;
    }
    public void setItem8_number(int x){
        item8_number = x;
    }
    public void setItem9_number(int x){
        item9_number = x;
    }

    public void setItem10_number(int x){
        item10_number = x;
    }

    public void setItem11_number(int x){
        item11_number = x;
    }
    //////////////SETTERS///////////////

    //////////////GETTERS///////////////
    public BigInteger getItem1_cost(){
        return item1_cost;
    }
    public BigInteger getItem2_cost(){
        return item2_cost;
    }
    public BigInteger getItem3_cost(){
        return item3_cost;
    }
    public BigInteger getItem4_cost(){
        return item4_cost;
    }
    public BigInteger getItem5_cost(){
        return item5_cost;
    }
    public BigInteger getItem6_cost(){
        return item6_cost;
    }
    public BigInteger getItem7_cost(){
        return item7_cost;
    }
    public BigInteger getItem8_cost(){
        return item8_cost;
    }
    public BigInteger getItem9_cost(){
        return item9_cost;
    }

    public BigInteger getItem10_cost(){
        return item10_cost;
    }

    public BigInteger getItem11_cost(){
        return item11_cost;
    }

    public BigInteger getItem1_value(){
        return item1_value;
    }
    public BigInteger getItem2_value(){
        return item2_value;
    }
    public BigInteger getItem3_value(){
        return item3_value;
    }
    public BigInteger getItem4_value(){
        return item4_value;
    }
    public BigInteger getItem5_value(){
        return item5_value;
    }
    public BigInteger getItem6_value(){
        return item6_value;
    }
    public BigInteger getItem7_value(){
        return item7_value;
    }
    public BigInteger getItem8_value(){
        return item8_value;
    }
    public BigInteger getItem9_value(){
        return item9_value;
    }

    public BigInteger getItem10_value(){
        return item10_value;
    }

    public BigInteger getItem11_value(){
        return item11_value;
    }

    public int getItem1_number(){
        return item1_number;
    }
    public int getItem2_number(){
        return item2_number;
    }
    public int getItem3_number(){
        return item3_number;
    }
    public int getItem4_number(){
        return item4_number;
    }
    public int getItem5_number(){
        return item5_number;
    }
    public int getItem6_number(){
        return item6_number;
    }
    public int getItem7_number(){
        return item7_number;
    }
    public int getItem8_number(){
        return item8_number;
    }
    public int getItem9_number(){
        return item9_number;
    }

    public int getItem10_number(){
        return item10_number;
    }

    public int getItem11_number(){
        return item11_number;
    }
    //////////////GETTERS///////////////

}
