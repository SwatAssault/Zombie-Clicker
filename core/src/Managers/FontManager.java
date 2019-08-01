package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Align;

public class FontManager {

    private final ZombieClicker zombieClicker;

//    private BitmapFont bitmapFont;
//    private FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Ubuntu-Regular.ttf"));
//    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont bitmapFont_invent;

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

        bitmapFont_invent = new BitmapFont(
                Gdx.files.internal("Fonts/rubik_main.fnt"),
                Gdx.files.internal("Fonts/rubik_main.png"), false);
        bitmapFont_invent.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void draw_text_forGame(SpriteBatch batch) {

    }

    public void draw_text_forShop() {

    }

    public void draw_text_forInvent(SpriteBatch batch) {
        bitmapFont_invent.getData().setScale(.6f);
        bitmapFont_invent.draw(batch, zombieClicker.getNumerics().getPlayer_name(), 70, 605);
        bitmapFont_invent.getData().setScale(.9f);
        bitmapFont_invent.draw(batch, "INVENTORY", 115, 905);

        bitmapFont_invent.getData().setScale(.28f);

        bitmapFont_invent.draw(batch, "TAP DAMAGE: \n" +
                        "DPS: \n" +
                        "ZOMBIES \nDESTROYED: \n" +
                        "BOSSES \nDESTROYED: \n" +
                        "TOTAL \nCLICKS: \n" +
                        "COLLECTED \n ITEMS: \n" +
                        "ACTIVE \nSKILL: \n",
                370, 813, 1, Align.topRight, false);

        bitmapFont_invent.draw(batch, "    " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getPunch_power()) + "\n" +
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

    public BitmapFont getBitmapFont_invent(){
        return bitmapFont_invent;
    }

    public void setBitmapFont_invent_Scale(float x){
        bitmapFont_invent.getData().setScale(x);
    }

    public void dispose() {
//        bitmapFont.dispose();
    }

}
