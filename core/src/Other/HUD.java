package Other;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HUD {

    private final ZombieClicker zombieClicker;

    private Button plus_gold_btn;
    private Button plus_diamonds_btn;

    private TextureAtlas hud_icons_atlas;

    private Image gold_icon;
    private Image diamond_icon;

    private int gold_x = 155;
    private int gold_y = 945;
    private int diamond_x = 480;

    public HUD(ZombieClicker zc){
        zombieClicker = zc;

        hud_icons_atlas = zombieClicker.get_assets().get_asset_manager().get("Texture Atlases/hud_atlas.atlas");

        gold_icon = new Image(hud_icons_atlas.createSprite("gold"));
        gold_icon.setPosition(gold_x - 145, 920);
        diamond_icon = new Image(hud_icons_atlas.createSprite("diamond"));
        diamond_icon.setPosition(diamond_x - 145,920);

        plus_gold_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/plus_btn.json", Skin.class));
        plus_gold_btn.setPosition(gold_x + 10, gold_y - 28);
        plus_gold_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

        plus_diamonds_btn = new Button(zombieClicker.get_assets().get_asset_manager().get("Buttons/plus_btn.json", Skin.class));
        plus_diamonds_btn.setPosition(diamond_x + 10, gold_y - 28);
        plus_diamonds_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        });

    }

    public void render(SpriteBatch batch){
        zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()));
        zombieClicker.getFontManager().getHud_font().draw(batch, zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), gold_x - zombieClicker.getFontManager().getLayout().width, gold_y);
        zombieClicker.getFontManager().getLayout().setText(zombieClicker.getFontManager().getHud_font(), "" + zombieClicker.getNumerics().getDiamonds());
        zombieClicker.getFontManager().getHud_font().draw(batch, "" + zombieClicker.getNumerics().getDiamonds(), diamond_x - zombieClicker.getFontManager().getLayout().width, gold_y);
    }

    public Button getPlus_gold_btn(){
        return plus_gold_btn;
    }

    public Button getPlus_diamonds_btn(){
        return plus_diamonds_btn;
    }

    public Image getGold_icon() {
        return gold_icon;
    }

    public Image getDiamond_icon() {
        return diamond_icon;
    }

    public TextureAtlas getHud_icons_atlas(){
        return hud_icons_atlas;
    }

    public void dispose(){

    }

}
