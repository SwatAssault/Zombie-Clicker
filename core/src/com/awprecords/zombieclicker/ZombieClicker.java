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

    private BitmapFont bitmapFont;
    private BitmapFont font_for_plus;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font_for_description;
    private final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";

    public ZombieClicker() {
        instance = this;
        assets = new Assets();
        numerics = new Numerics(instance);
        soundManager = new SoundManager(instance);
        fontManager = new FontManager(instance);
        shopNumerics = new ShopNumerics();
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
        mainGame = new MainGame(instance, numerics.getCurrent_location());
        setScreen(mainGame);
    }

    public void setMenuScreen(){
        mainGame = null;
        //System.gc();
        menuScreen = new MenuScreen(instance);
        setScreen(menuScreen);
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
