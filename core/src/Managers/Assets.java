package Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private AssetManager assetManager = new AssetManager();

    public AssetManager get_asset_manager() {
        return assetManager;
    }

    public void load_assets_for_Menu() {
        assetManager.load("Background/menubg.png", Texture.class);
        assetManager.load("Buttons/play_btn.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_Game() {
        assetManager.load("Background/gamebg.png", Texture.class);
        assetManager.load("Buttons/back.png", Texture.class);
        assetManager.load("Buttons/shop_btn.png", Texture.class);
        assetManager.load("Sounds/punch.wav", Sound.class);
        assetManager.load("HP/hp.png", Texture.class);
        assetManager.load("HP/hp_bg.png", Texture.class);

        assetManager.load("Animations/zombie1.atlas", TextureAtlas.class);
        assetManager.load("Animations/zombie2.atlas", TextureAtlas.class);
        assetManager.load("Animations/zombie3.atlas", TextureAtlas.class);
        assetManager.load("Animations/boss1.atlas", TextureAtlas.class);

        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_Shop(){
        assetManager.load("SkinJson/buybtn.json", Skin.class);
        assetManager.load("Background/shopbg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    /////////DISPOSE ASSETS/////////
    public void dispose_Menu_assets(){
        assetManager.unload("Background/menubg.png");
        assetManager.unload("Buttons/play_btn.png");
    }

    public void dispose_Game_assets(){
        assetManager.unload("Buttons/back.png");
        assetManager.unload("Buttons/shop_btn.png");
        assetManager.unload("Background/gamebg.png");
        assetManager.unload("Animations/zombie1.atlas");
        assetManager.unload("Animations/zombie2.atlas");
        assetManager.unload("Animations/zombie3.atlas");
        assetManager.unload("Animations/boss1.atlas");
        assetManager.unload("HP/hp.png");
        assetManager.unload("HP/hp_bg.png");
    }

    public void dispose_Shop_assets(){
        assetManager.unload("Background/shopbg.png");
        assetManager.unload("SkinJson/buybtn.json");
    }
    /////////DISPOSE ASSETS/////////


    //ATTENTION
    public void dispose() {
        assetManager.dispose();
    }

}
