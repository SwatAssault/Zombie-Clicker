package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class FontManager {

    private final ZombieClicker zombieClicker;

    private BitmapFont bitmapFont;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private GlyphLayout layout;

    public FontManager(final ZombieClicker zc) {
        zombieClicker = zc;

        layout = new GlyphLayout();
        //ПОЧЕМУ НУЛЛ??
//        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Pangolin-Regular.ttf"));
//        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        fontParameter.size = 24;
//        fontParameter.color = Color.WHITE;
//        bitmapFont = fontGenerator.generateFont(fontParameter);

    }

    public void draw_text_forGame(SpriteBatch batch) {
        bitmapFont.draw(batch, "LEVEL : " + zombieClicker.getNumerics().getLevel_count(), 100, 800);
        if (zombieClicker.getNumerics().get_bossFight()) {
            bitmapFont.draw(batch, "HP : " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getBoss_health()), 200, 180);
        } else
            bitmapFont.draw(batch, "HP : " + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getZombie_health()), 200, 180);
        bitmapFont.draw(batch, "" + zombieClicker.getNumerics().bigInteger_to_string(zombieClicker.getNumerics().getGold()), 300, 700);
        bitmapFont.draw(batch, "DIAMONDS : " + zombieClicker.getNumerics().getDiamonds(), 300, 600);
    }

    public void draw_text_forShop(){

    }

    public GlyphLayout getLayout(){
        return layout;
    }

    public void dispose() {
//        bitmapFont.dispose();
    }

}
