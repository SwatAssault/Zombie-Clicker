package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.math.BigInteger;

public class CraftItem {
    private ZombieClicker zombieClicker;

    private BigInteger item_cost;
    private BigInteger item_value;

    private Stack stack;
    private Table table;
    private Table intable;
    private Table description_table;
    private Image image;

    private Table table_for_item1;
    private Table table_for_item2;
    private Table table_for_item3;
    private Table table_for_item4;

    private Button buy_btn;
    private Button item_1;
    private Button item_2;
    private Button item_3;
    private Button item_4;

    //private Label description_label;
    private Label value_label_1;
    private Label value_label_2;
    private Label value_label_3;
    private Label value_label_4;

    private Skin item_skin1;
    private Skin item_skin2;
    private Skin item_skin3;
    private Skin item_skin4;
    private Skin buy_skin;
    private Skin description_skin;

    // 1 - монеты МАКС
    // 2 - монеты СРЕД
    // 3 - алмазы МАКС
    // 4 - монеты МИН
    // 5 - алмазы МИН
    private int numReward;

    private BigInteger maxGoldReward;
    private BigInteger midGoldReward;
    private BigInteger minGoldReward;
    private int maxDiamondsReward;
    private int minDiamondsReward;

    private int max1, max2, max3, max4;

    public CraftItem(ZombieClicker zc, String texture, final int max_1, int max_2, int max_3, int max_4, int reward) {
        zombieClicker = zc;
        max1 = max_1;
        max2 = max_2;
        max3 = max_3;
        max4 = max_4;

        maxGoldReward = new BigInteger("1000");
        midGoldReward = new BigInteger("50");
        minGoldReward = new BigInteger("10");
        maxDiamondsReward = 100;
        minDiamondsReward = 10;

        this.numReward = reward;

        image = new Image(zombieClicker.get_assets().get_asset_manager().get("separator_inventory.png", Texture.class));

        stack = new Stack();
        table = new Table();
        intable = new Table();
        description_table = new Table();
//        table_for_nums = new Table();

        table_for_item1 = new Table();
        table_for_item2 = new Table();
        table_for_item3 = new Table();
        table_for_item4 = new Table();

        buy_skin = zombieClicker.get_assets().get_asset_manager().get(texture, Skin.class);
        description_skin = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/label_test_skin.json", Skin.class);

        value_label_1 = new Label(zombieClicker.getNumerics().getCountCraftItem_1() + " / " +
                max_1, description_skin);
        value_label_2 = new Label(zombieClicker.getNumerics().getCountCraftItem_2() + " / " +
                max_2, description_skin);
        value_label_3 = new Label(zombieClicker.getNumerics().getCountCraftItem_3() + " / " +
                max_3, description_skin);
        value_label_4 = new Label(zombieClicker.getNumerics().getCountCraftItem_4() + " / " +
                max_4, description_skin);

        buy_btn = new Button(buy_skin);
        buy_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!buy_btn.isDisabled()) {
                    zombieClicker.getNumerics().minusCountCraftItem_1(max1);
                    zombieClicker.getNumerics().minusCountCraftItem_2(max2);
                    zombieClicker.getNumerics().minusCountCraftItem_3(max3);
                    zombieClicker.getNumerics().minusCountCraftItem_4(max4);
                    plusReward(numReward);
                }
            }
        });


        item_skin1 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_1 = new Button(item_skin1);

        item_skin2 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_2 = new Button(item_skin2);

        item_skin3 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_3 = new Button(item_skin3);

        item_skin4 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_4 = new Button(item_skin4);

        table_for_item1.add(item_1).padTop(10);
        table_for_item1.row();
        table_for_item1.add(value_label_1).padTop(20);

        table_for_item2.add(item_2).padTop(10);
        table_for_item2.row();
        table_for_item2.add(value_label_2).padTop(20);

        table_for_item3.add(item_3).padTop(10);
        table_for_item3.row();
        table_for_item3.add(value_label_3).padTop(20);

        table_for_item4.add(item_4).padTop(10);
        table_for_item4.row();
        table_for_item4.add(value_label_4).padTop(20);

        table.add(stack);
        stack.add(image);

        stack.add(intable);
//        intable.add(item_1).padBottom(40);
//        intable.add(item_2).padLeft(10).padBottom(40);
//        intable.add(item_3).padLeft(10).padBottom(40);
//        intable.add(item_4).padLeft(10).padBottom(40);
        intable.add(buy_btn).expandY().expandX().right();

        stack.add(table_for_item1);
        stack.add(table_for_item2);
        stack.add(table_for_item3);
        stack.add(table_for_item4);
        table_for_item1.left();
        table_for_item2.left().padLeft(65);
        table_for_item3.left().padLeft(130);
        table_for_item4.left().padLeft(195);
//        stack.add(table_for_nums);
//        table_for_nums.left();
//        table_for_nums.add(value_label_1).padTop(70).padLeft(1).padRight(15);
//        table_for_nums.add(value_label_2).padTop(70).padRight(14);
//        table_for_nums.add(value_label_3).padTop(70).padRight(13);
//        table_for_nums.add(value_label_4).padTop(70).padRight(15);

    }

    public CraftItem(ZombieClicker zc, String texture, int max_1, int max_2, int max_3, int reward) {
        zombieClicker = zc;
        max1 = max_1;
        max2 = max_2;
        max3 = max_3;
        max4 = -1;
        maxGoldReward = new BigInteger("1000");
        midGoldReward = new BigInteger("50");
        minGoldReward = new BigInteger("10");
        maxDiamondsReward = 100;
        minDiamondsReward = 10;
        numReward = reward;

        image = new Image(zombieClicker.get_assets().get_asset_manager().get("separator_inventory.png", Texture.class));

        stack = new Stack();
        table = new Table();
        intable = new Table();
        description_table = new Table();
//        table_for_nums = new Table();

        table_for_item1 = new Table();
        table_for_item2 = new Table();
        table_for_item3 = new Table();

        buy_skin = zombieClicker.get_assets().get_asset_manager().get(texture, Skin.class);
        description_skin = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/label_test_skin.json", Skin.class);

        value_label_1 = new Label(zombieClicker.getNumerics().getCountCraftItem_1() + " / " +
                max_1, description_skin);
        value_label_2 = new Label(zombieClicker.getNumerics().getCountCraftItem_2() + " / " +
                max_2, description_skin);
        value_label_3 = new Label(zombieClicker.getNumerics().getCountCraftItem_3() + " / " +
                max_3, description_skin);

        buy_btn = new Button(buy_skin);
        buy_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!buy_btn.isDisabled()) {
                    zombieClicker.getNumerics().minusCountCraftItem_1(max1);
                    zombieClicker.getNumerics().minusCountCraftItem_2(max2);
                    zombieClicker.getNumerics().minusCountCraftItem_3(max3);
                    plusReward(numReward);
                }
            }
        });

        item_skin1 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_1 = new Button(item_skin1);

        item_skin2 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_2 = new Button(item_skin2);

        item_skin3 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_3 = new Button(item_skin3);

        table_for_item1.add(item_1).padTop(10);
        table_for_item1.row();
        table_for_item1.add(value_label_1).padTop(20);

        table_for_item2.add(item_2).padTop(10);
        table_for_item2.row();
        table_for_item2.add(value_label_2).padTop(20);

        table_for_item3.add(item_3).padTop(10);
        table_for_item3.row();
        table_for_item3.add(value_label_3).padTop(20);

        table.add(stack);
        stack.add(image);

        stack.add(intable);
//        intable.add(item_1).padBottom(40);
//        intable.add(item_2).padLeft(10).padBottom(40);
//        intable.add(item_3).padLeft(10).padBottom(40);
        intable.add(buy_btn).expandY().expandX().right();

        stack.add(table_for_item1);
        stack.add(table_for_item2);
        stack.add(table_for_item3);
        table_for_item1.left();
        table_for_item2.left().padLeft(65);
        table_for_item3.left().padLeft(130);

//        stack.add(table_for_nums);
//        table_for_nums.left();
//        table_for_nums.add(value_label_1).padTop(70).padLeft(1).padRight(15);
//        table_for_nums.add(value_label_2).padTop(70).padRight(14);
//        table_for_nums.add(value_label_3).padTop(70).padRight(13);
    }

    public CraftItem(ZombieClicker zc, String texture, int max_1, int max_2, int reward) {
        zombieClicker = zc;
        max1 = max_1;
        max2 = max_2;
        max3 = -1;
        max4 = -1;

        maxGoldReward = new BigInteger("1000");
        midGoldReward = new BigInteger("50");
        minGoldReward = new BigInteger("10");
        maxDiamondsReward = 100;
        minDiamondsReward = 10;
        numReward = reward;

        image = new Image(zombieClicker.get_assets().get_asset_manager().get("separator_inventory.png", Texture.class));

        stack = new Stack();
        table = new Table();
        intable = new Table();
        description_table = new Table();
//        table_for_nums = new Table();

        table_for_item1 = new Table();
        table_for_item2 = new Table();

        buy_skin = zombieClicker.get_assets().get_asset_manager().get(texture, Skin.class);
        description_skin = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/label_test_skin.json", Skin.class);

        value_label_1 = new Label(zombieClicker.getNumerics().getCountCraftItem_1() + " / " +
                max_1, description_skin);
        value_label_2 = new Label(zombieClicker.getNumerics().getCountCraftItem_2() + " / " +
                max_2, description_skin);

        buy_btn = new Button(buy_skin);
        buy_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!buy_btn.isDisabled()) {
                    zombieClicker.getNumerics().minusCountCraftItem_1(max1);
                    zombieClicker.getNumerics().minusCountCraftItem_2(max2);
                    plusReward(numReward);
                }
            }
        });

        item_skin1 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_1 = new Button(item_skin1);

        item_skin2 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_2 = new Button(item_skin2);

        table_for_item1.add(item_1).padTop(10);
        table_for_item1.row();
        table_for_item1.add(value_label_1).padTop(20);

        table_for_item2.add(item_2).padTop(10);
        table_for_item2.row();
        table_for_item2.add(value_label_2).padTop(20);

        table.add(stack);
        stack.add(image);

        stack.add(intable);
//        intable.add(item_1).padBottom(40);
//        intable.add(item_2).padLeft(10).padBottom(40);
        intable.add(buy_btn).expandY().expandX().right();

        stack.add(table_for_item1);
        stack.add(table_for_item2);
        table_for_item1.left();
        table_for_item2.left().padLeft(65);

//        stack.add(table_for_nums);
//        table_for_nums.left();
//        table_for_nums.add(value_label_1).padTop(70).padLeft(1).padRight(15);
//        table_for_nums.add(value_label_2).padTop(70).padRight(14);
    }

    public CraftItem(ZombieClicker zc, String texture, int max_1, int reward) {
        zombieClicker = zc;
        max1 = max_1;
        max2 = -1;
        max3 = -1;
        max4 = -1;

        maxGoldReward = new BigInteger("1000");
        midGoldReward = new BigInteger("50");
        minGoldReward = new BigInteger("10");
        maxDiamondsReward = 100;
        minDiamondsReward = 10;

        numReward = reward;

        image = new Image(zombieClicker.get_assets().get_asset_manager().get("separator_inventory.png", Texture.class));

        stack = new Stack();
        table = new Table();
        intable = new Table();
        description_table = new Table();
//        table_for_nums = new Table();

        table_for_item1 = new Table();

        buy_skin = zombieClicker.get_assets().get_asset_manager().get(texture, Skin.class);
        description_skin = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/label_test_skin.json", Skin.class);

        value_label_1 = new Label(zombieClicker.getNumerics().getCountCraftItem_1() + " / " +
                max_1, description_skin);

        buy_btn = new Button(buy_skin);
        buy_btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!buy_btn.isDisabled()) {
                    zombieClicker.getNumerics().minusCountCraftItem_1(max1);
                    plusReward(numReward);
                }
            }
        });

        item_skin1 = zombieClicker.get_assets().get_asset_manager().get("ButtonsInventory/craftItem_1.json", Skin.class);
        item_1 = new Button(item_skin1);

        table_for_item1.add(item_1).padTop(10);
        table_for_item1.row();
        table_for_item1.add(value_label_1).padTop(20);

        table.add(stack);
        stack.add(image);

        stack.add(intable);
//        intable.add(item_1).padBottom(40);
        intable.add(buy_btn).expandY().expandX().right();

        stack.add(table_for_item1);
        table_for_item1.left();

//        stack.add(table_for_nums);
//        table_for_nums.left();
//        table_for_nums.add(value_label_1).padTop(70).align(Align.center);
    }

    public void update() {
        if (zombieClicker.getNumerics().getCountCraftItem_1() < max1) buy_btn.setDisabled(true);
        value_label_1.setText(zombieClicker.getNumerics().getCountCraftItem_1() + " / " + max1);
        if (max2 != -1) {
            if (zombieClicker.getNumerics().getCountCraftItem_2() < max2) buy_btn.setDisabled(true);
            value_label_2.setText(zombieClicker.getNumerics().getCountCraftItem_2() + " / " + max2);
        }
        if (max3 != -1) {
            value_label_3.setText(zombieClicker.getNumerics().getCountCraftItem_3() + " / " + max3);
            if (zombieClicker.getNumerics().getCountCraftItem_3() < max3) buy_btn.setDisabled(true);
        }
        if (max4 != -1) {
            value_label_4.setText(zombieClicker.getNumerics().getCountCraftItem_4() + " / " + max4);
            if (zombieClicker.getNumerics().getCountCraftItem_4() < max4) buy_btn.setDisabled(true);
        }
    }

    public void plusReward(int numReward) {
        if (numReward == 1) {
            zombieClicker.getNumerics().plus_gold(maxGoldReward);
        }
        if (numReward == 2) {
            zombieClicker.getNumerics().plus_gold(midGoldReward);
        }
        if (numReward == 3) {
            zombieClicker.getNumerics().plus_diamonds(maxDiamondsReward);
        }
        if (numReward == 4) {
            zombieClicker.getNumerics().plus_gold(minGoldReward);
        }
        if (numReward == 5) {
            zombieClicker.getNumerics().plus_diamonds(minDiamondsReward);
        }
    }

    ////////////////////GETTERS////////////////////////
    public Table getTable() {
        return table;
    }

    public Button getBuy_btn() {
        return buy_btn;
    }

    public BigInteger getItem_cost() {
        return item_cost;
    }
    ////////////////////GETTERS////////////////////////


    public void dispose() {
//        zombieClicker.get_assets().get_asset_manager().unload("Buttons/buybtn.json");
    }
}
