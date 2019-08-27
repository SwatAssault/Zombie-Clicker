package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MiniSquadItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Image back_image;
    private Image front_image;


    private int which_squad;

    public MiniSquadItem(ZombieClicker zc, Texture back_img, final int whichSquad){
        zombieClicker = zc;
        this.which_squad = whichSquad - 1;

        stack = new Stack();
        front_image = new Image(zombieClicker.get_assets().get_asset_manager().get("SquadIcons/front.png", Texture.class));
        back_image = new Image(back_img);
        back_img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        front_image.setVisible(false);

        stack.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(zombieClicker.getShop().getSquadItems_array().get(which_squad).getStatus() == 0 && zombieClicker.getShop().getSquadItems_array().get(which_squad).isBought()){  // если отряд свободен
                    zombieClicker.getShop().getSquadItems_array().get(which_squad).setStatus(-1);   // значит на задании
                    //тут нужно засечь время и по его истечении вернуть отряду статус 0


                }
            }
        });

        stack.setHeight(130);
        stack.setWidth(130);


        stack.add(back_image);
        stack.add(front_image);

    }

    public Stack getStack(){
        return stack;
    }

    public void show_front_image(boolean x){
        front_image.setVisible(x);
    }
}
