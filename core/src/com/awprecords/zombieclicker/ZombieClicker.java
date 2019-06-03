package com.awprecords.zombieclicker;

import com.badlogic.gdx.Game;

import Managers.Assets;
import Managers.FontManager;
import Managers.SoundManager;
import Numbers.Numerics;
import Numbers.ShopNumerics;
import Other.KeepTrackAch;
import Screens.Achievements;
import Screens.MainGame;
import Screens.MapScreen;
import Screens.MenuScreen;
import Screens.Shop;


public class ZombieClicker extends Game {

    private ZombieClicker instance;
    private Assets assets;
    private Numerics numerics;
    private SoundManager soundManager;
    private FontManager fontManager;
    private ShopNumerics shopNumerics;
    private KeepTrackAch keepTrackAch;

    private MainGame mainGame;
    private MenuScreen menuScreen;
    private Shop shop;
    private Achievements achievements;
    private MapScreen mapScreen;

    public ZombieClicker() {
        instance = this;
        assets = new Assets();
        numerics = new Numerics();
        soundManager = new SoundManager(instance);
        fontManager = new FontManager(instance);
        shopNumerics = new ShopNumerics();
        keepTrackAch = new KeepTrackAch(instance);
    }

    //////////GETTERS FOR SCREENS//////////
    public MainGame getMainGame() {
        return mainGame;
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

    public ShopNumerics getShopNumerics(){
        return shopNumerics;
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

        setMenuScreen();

    }

    ////////////SET SCREEN METHODS/////////////
    public void setMainGame(){
        menuScreen = null;
        //System.gc();
        mainGame = new MainGame(instance);
        setScreen(mainGame);
    }

    public void setMenuScreen(){
        mainGame = null;
        //System.gc();
        menuScreen = new MenuScreen(instance);
        setScreen(menuScreen);
    }

    public void setShopScreen(){
        shop = null;
        //System.gc();
        shop = new Shop(instance);
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
