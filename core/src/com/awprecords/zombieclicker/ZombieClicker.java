package com.awprecords.zombieclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import Managers.Assets;
import Managers.FontManager;
import Managers.SoundManager;
import Numbers.Location;
import Numbers.Numerics;
import Other.KeepTrackAch;
import Other.SquadItem;
import Screens.Achievements;
import Screens.MainGame;
import Screens.MapScreen;
import Screens.Shop;
import Screens.SquadsDistScreen;


public class ZombieClicker extends Game {

    private ZombieClicker instance;
    private Assets assets;
    private Numerics numerics;
    private SoundManager soundManager;
    private FontManager fontManager;
    private KeepTrackAch keepTrackAch;
    private SquadsDistScreen squadsDistScreen;

    private MainGame mainGame;
    private Shop shop;
    private Achievements achievements;
    private MapScreen mapScreen;

    public ZombieClicker() {
        instance = this;
        assets = new Assets();
        numerics = new Numerics(instance);
        soundManager = new SoundManager(instance);
        fontManager = new FontManager(instance);
        keepTrackAch = new KeepTrackAch(instance);

      //  shop = new Shop(instance);
    }

    //////////GETTERS FOR SCREENS//////////
    public MainGame getMainGame() {
        return mainGame;
    }

    public Shop getShop(){
        return shop;
    }
    //////////GETTERS FOR SCREENS//////////


    /////////////OTHER GETTERS/////////////
    public Numerics getNumerics() {
        return numerics;
    }

    public Assets get_assets() {
        return assets;
    }

    public SoundManager getSoundManager() {
        return soundManager;
    }

    public FontManager getFontManager(){
        return fontManager;
    }

    public KeepTrackAch getKeepTrackAch(){
        return keepTrackAch;
    }

    public Achievements getAchievements(){
        return achievements;
    }

    /////////////OTHER GETTERS/////////////

    public ZombieClicker get_ZombieClicker() {
        return instance;
    }

    @Override
    public void create() {

        get_assets().load_assets_for_location_1();
        getNumerics().setCurrent_num_location(0);
        getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
        setMainGame();
        // setMenuScreen();

    }

    ////////////SET SCREEN METHODS/////////////
    public void setMainGame(){
        //System.gc();
        mainGame = new MainGame(instance, numerics.getCurrent_location());
        setScreen(mainGame);
    }

    public void setSquadsDistScreen(ZombieClicker zc, SquadItem squadItem){
        squadsDistScreen = new SquadsDistScreen(zc, squadItem);
        setScreen(squadsDistScreen);
    }

    public void setShopScreen(){
        if(shop == null){
            shop = new Shop(instance);
        }
        setScreen(shop);
    }

    public void setAchievementScreen(){
        achievements = null;
        achievements = new Achievements(instance);
        setScreen(achievements);
    }

    public void setMapScreen(){
        mapScreen = null;
        mapScreen = new MapScreen(instance);
        setScreen(mapScreen);
    }
    ////////////SET SCREEN METHODS/////////////

    @Override
    public void dispose() {
        fontManager.dispose();
        assets.dispose();
    }
}
