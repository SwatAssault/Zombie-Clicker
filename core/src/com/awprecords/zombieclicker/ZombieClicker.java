package com.awprecords.zombieclicker;

import com.badlogic.gdx.Game;

import Managers.Assets;
import Managers.FontManager;
import Managers.SoundManager;
import Numbers.Numerics;
import Numbers.ShopNumerics;
import Other.Achievements;
import Screens.MainGame;
import Screens.MenuScreen;
import Screens.Shop;


public class ZombieClicker extends Game {

    private ZombieClicker instance;
    private Assets assets;
    private Numerics numerics;
    private SoundManager soundManager;
    private FontManager fontManager;
    private ShopNumerics shopNumerics;
    private Achievements achievements;

    private MainGame mainGame;
    private MenuScreen menuScreen;
    private Shop shop;

    public ZombieClicker() {
        instance = this;
        assets = new Assets();
        numerics = new Numerics();
        soundManager = new SoundManager(instance);
        fontManager = new FontManager(instance);
        shopNumerics = new ShopNumerics();
        achievements = new Achievements(instance);
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
    ////////////SET SCREEN METHODS/////////////

    @Override
    public void dispose() {
        fontManager.dispose();
        assets.dispose();
    }
}
