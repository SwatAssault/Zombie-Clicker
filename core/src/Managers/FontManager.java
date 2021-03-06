package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

public class FontManager {

    private final ZombieClicker zombieClicker;

    private BitmapFont hud_font;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;

    private BitmapFont font_border;
    private Label.LabelStyle description_labelStyle;

    private GlyphLayout layout;

    public FontManager(final ZombieClicker zc) {
        zombieClicker = zc;
        layout = new GlyphLayout();

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Rubik.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 22;
        fontParameter.color = Color.WHITE;
        hud_font = fontGenerator.generateFont(fontParameter);
        hud_font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        font_border = new BitmapFont(
                Gdx.files.internal("Fonts/rubik_main.fnt"),
                Gdx.files.internal("Fonts/rubik_main.png"), false);
        font_border.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        font_border.getData().setScale(.28f);
        description_labelStyle = new Label.LabelStyle(font_border, Color.WHITE);
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

    public void draw_text_forMissionInfo(SpriteBatch batch, String mission, String rank, String time) {
        font_border.getData().setScale(.40f);
        zombieClicker.getFontManager().getLayout().setText(font_border, rank);
        font_border.draw(batch, rank, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 650);
        font_border.getData().setScale(.35f);
        font_border.draw(batch, mission, 80, 500,
                400, Align.left, true);
        zombieClicker.getFontManager().getLayout().setText(font_border, "Time: " + time);
        font_border.draw(batch, "Time: " + time, 540 / 2 - zombieClicker.getFontManager().getLayout().width / 2, 390);
    }

    public GlyphLayout getLayout() {
        return layout;
    }

    public BitmapFont getFont_border() {
        return font_border;
    }

    public BitmapFont getHud_font() {
        return hud_font;
    }

    public void setBitmapFont_invent_Scale(float x) {
        font_border.getData().setScale(x);
    }

    public Label.LabelStyle getDescription_labelStyle(){
        return description_labelStyle;
    }

    public void dispose() {

    }

}
