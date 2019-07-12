package Other;


import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class SquadItem {

    private ZombieClicker zombieClicker;

    private Stack stack;
    private Table table;
    private Table intable;
    private Image image;
    private TextButton buy_squad_btn;
    private TextButton upgrade_squad_btn;
    private Label name_of_squad;
    private Label descriotion_of_squad;

    public SquadItem(ZombieClicker zc){
        zombieClicker = zc;

        stack = new Stack();
        table = new Table();
        intable = new Table();

        zombieClicker.get_assets().get_asset_manager().load("Shop/squad_item_bg.png", Texture.class);
        zombieClicker.get_assets().get_asset_manager().update();
        zombieClicker.get_assets().get_asset_manager().finishLoading();

        image = new Image(zombieClicker.get_assets().get_asset_manager().get("Shop/squad_item_bg.png", Texture.class));




        table.add(stack);
        stack.add(image);


    }

    //////////////////GETTERS////////////////////////
    public Table get_table(){
        return table;
    }


    //////////////////GETTERS////////////////////////


    //////////////////SETTERS////////////////////////

    //////////////////SETTERS////////////////////////

    public void dispose(){
        zombieClicker.get_assets().get_asset_manager().unload("Shop/squad_item_bg.png");
    }

}
