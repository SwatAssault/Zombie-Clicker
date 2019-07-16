package Managers;

import com.awprecords.zombieclicker.ZombieClicker;
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

    public void load_assets_for_anyLocation(ZombieClicker zombieClicker){
        if(zombieClicker.getNumerics().getCurrent_num_location() == 0) {
            load_assets_for_location_1();
            zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
        }

        if(zombieClicker.getNumerics().getCurrent_num_location() == 1) {
            load_assets_for_location_2();
            zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_2_bg.png");
        }

        if(zombieClicker.getNumerics().getCurrent_num_location() == 2) {
            load_assets_for_location_3();
            zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_3_bg.png");
        }

        if(zombieClicker.getNumerics().getCurrent_num_location() == 3) {
            load_assets_for_location_4();
            zombieClicker.getNumerics().getCurrent_location().setBGimage("Background/location_4_bg.png");
        }
    }

    public void load_assets_for_Menu() {
        assetManager.load("Background/menubg.png", Texture.class);
        assetManager.load("Buttons/play_btn.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_Game() {
        assetManager.load("Buttons/back_btn.json", Skin.class);
        assetManager.load("SkinJson/ach_btn.json", Skin.class);
        assetManager.load("SkinJson/shopbtn.json", Skin.class);
        assetManager.load("SkinJson/map_btn.json", Skin.class);
        assetManager.load("Sounds/punch.wav", Sound.class);
        assetManager.load("HP/hp.png", Texture.class);
        assetManager.load("HP/hp_bg.png", Texture.class);
        assetManager.load("BossTime/time.png", Texture.class);

        assetManager.load("Animations/zombie1.atlas", TextureAtlas.class);
        assetManager.load("Animations/zombie2.atlas", TextureAtlas.class);
        assetManager.load("Animations/zombie3.atlas", TextureAtlas.class);
        assetManager.load("Animations/boss1.atlas", TextureAtlas.class);

        assetManager.update();
       assetManager.finishLoading();
    }

    public void load_assets_for_Shop(){

        assetManager.load("Buttons/back_btn.json", Skin.class);
        assetManager.load("Background/shopbg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_settings(){

    }

    public void load_assets_for_map(){
        assetManager.load("Background/mapbg.png", Texture.class);
        assetManager.load("Buttons/back_btn.json", Skin.class);
        assetManager.load("SkinJson/location_btn.json", Skin.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_achievements(){
        assetManager.load("SkinJson/kills.json", Skin.class);
        assetManager.load("SkinJson/clicks.json", Skin.class);
        assetManager.load("Buttons/back_btn.json", Skin.class);
        assetManager.load("Background/achbg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_location_1(){
        assetManager.load("Background/location_1_bg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_location_2(){
        assetManager.load("Background/location_2_bg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_location_3(){
        assetManager.load("Background/location_3_bg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    public void load_assets_for_location_4(){
        assetManager.load("Background/location_4_bg.png", Texture.class);
        assetManager.update();
        assetManager.finishLoading();
    }

    /////////DISPOSE ASSETS/////////
    public void dispose_Menu_assets(){
        assetManager.unload("Background/menubg.png");
        assetManager.unload("Buttons/play_btn.png");
    }

    public void dispose_Game_assets(){
        assetManager.unload("Buttons/back_btn.json");
        assetManager.unload("SkinJson/shopbtn.json");
        assetManager.unload("SkinJson/ach_btn.json");
        assetManager.unload("SkinJson/map_btn.json");
        assetManager.unload("Animations/zombie1.atlas");
        assetManager.unload("Animations/zombie2.atlas");
        assetManager.unload("Animations/zombie3.atlas");
        assetManager.unload("Animations/boss1.atlas");
        assetManager.unload("HP/hp.png");
        assetManager.unload("HP/hp_bg.png");
    }

    public void dispose_Shop_assets(){
        assetManager.unload("Buttons/back_btn.json");
        assetManager.unload("Background/shopbg.png");
    }

    public void dispose_assets_for_settings(){

    }

    public void dispose_assets_for_map(){
        assetManager.unload("Background/mapbg.png");
        assetManager.unload("SkinJson/location_btn.json");
        assetManager.unload("Buttons/back_btn.json");
    }

    public void dispose_assets_for_achiements(){
        assetManager.unload("SkinJson/kills.json");
        assetManager.unload("Buttons/back_btn.json");
        assetManager.unload("SkinJson/clicks.json");
        assetManager.unload("Background/achbg.png");
    }

    public void dispose_assets_for_location_1(){
        assetManager.unload("Background/location_1_bg.png");
    }

    public void dispose_assets_for_location_2(){
        assetManager.unload("Background/location_2_bg.png");
    }

    public void dispose_assets_for_location_3(){
        assetManager.unload("Background/location_3_bg.png");
    }

    public void dispose_assets_for_location_4(){
        assetManager.unload("Background/location_4_bg.png");
    }
    /////////DISPOSE ASSETS/////////


    //ATTENTION
    public void dispose() {
        assetManager.dispose();
    }

}
