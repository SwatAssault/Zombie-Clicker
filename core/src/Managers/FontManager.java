package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class FontManager {

    private final ZombieClicker zombieClicker;

//    private BitmapFont bitmapFont;
//    private FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
//    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont font_border;

    private GlyphLayout layout;

    public FontManager(final ZombieClicker zc) {
        zombieClicker = zc;
        layout = new GlyphLayout();
        //ПОЧЕМУ НУЛЛ??
//     //   fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
//        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        fontParameter.size = 24;
//        fontParameter.color = Color.WHITE;
//        bitmapFont = fontGenerator.generateFont(fontParameter);

//        fontGenerator_invent;
//        fontParameter_invent;

        font_border = new BitmapFont(
                Gdx.files.internal("Fonts/rubik_main.fnt"),
                Gdx.files.internal("Fonts/rubik_main.png"), false);
        font_border.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void draw_text_forGame(SpriteBatch batch) {

    }

    public void draw_text_forShop() {

    }

    public void draw_text_forInvent(SpriteBatch batch) {
        font_border.getData().setScale(.6f);
        font_border.draw(batch, zombieClicker.getNumerics().getPlayer_name(), 70, 605);
        font_border.getData().setScale(.9f);
        font_border.draw(batch, "INVENTORY", 115, 905);

        font_border.getData().setScale(.28f);

        font_border.draw(batch, "TAP DAMAGE: \n" +
                        "DPS: \n" +
                        "ZOMBIES \nDESTROYED: \n" +
                        "BOSSES \nDESTROYED: \n" +
                        "TOTAL \nCLICKS: \n" +
                        "COLLECTED \n ITEMS: \n" +
                        "ACTIVE \nSKILL: \n",
                370, 813, 1, Align.topRight, false);

        font_border.draw(batch, "    " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getPunch_power()) + "\n" +
                         "    " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getPassive_damage()) + "\n" +
                        "\n    " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getZombie_kills()) + "\n" +
                        "\n    " + zombieClicker.getNumerics().getBoss_kills() + "\n" +
                        "\n    " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGlobal_tap_count()) + "\n" +
                        "\n    " + "null" + "\n" +
                        "\n    " + "null",
                370, 813, 1, Align.topLeft, false);
    }

    public GlyphLayout getLayout() {
        return layout;
    }

    public BitmapFont getFont_border(){
        return font_border;
    }

    public void setBitmapFont_invent_Scale(float x){
        font_border.getData().setScale(x);
    }

    public void dispose() {
//        bitmapFont.dispose();
    }

}
