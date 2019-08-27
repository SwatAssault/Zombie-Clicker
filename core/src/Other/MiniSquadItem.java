package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MiniSquadItem {

    private final ZombieClicker zombieClicker;

    private Stack stack;
    private Image back_image;
    private Image front_image;

    private int which_squad;

    public MiniSquadItem(ZombieClicker zc, final Texture back_img, final int whichSquad){
        zombieClicker = zc;
        this.which_squad = whichSquad - 1;

        stack = new Stack();
        front_image = new Image(zombieClicker.get_assets().get_asset_manager().get("SquadIcons/front.png", Texture.class));
        back_image = new Image(back_img);

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

        stack.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(zombieClicker.getShop().getSquadItems_array().get(which_squad).getStatus() == 0 && zombieClicker.getShop().getSquadItems_array().get(which_squad).isBought())
                     back_image.setScale(0.9f);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                back_image.setScale(1f);
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
