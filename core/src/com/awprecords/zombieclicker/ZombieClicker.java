package com.awprecords.zombieclicker;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.math.BigInteger;

import Managers.Assets;
import Managers.FontManager;
import Managers.SoundManager;
import Numbers.Numerics;
import Other.KeepTrackAch;
import Other.MyThread;
import Other.SquadItem;
import Screens.Achievements;
import Screens.Inventory;
import Screens.MainGame;
import Screens.MapScreen;
import Screens.MiniGame;
import Screens.Missions;
import Screens.PlayerCreate;
import Screens.RewardScreen;
import Screens.Shop;
import Screens.SquadsDistScreen;
import Screens.TipScreen;
import Screens.WheelOfFortune;


public class ZombieClicker extends Game {

    private MyThread myThread;

    private ZombieClicker instance;
    private Assets assets;
    private Numerics numerics;
    private SoundManager soundManager;
    private FontManager fontManager;
    private KeepTrackAch keepTrackAch;
    private SquadsDistScreen squadsDistScreen;
    private RewardScreen rewardScreen;
    private MainGame mainGame;
    private Shop shop;
    private Achievements achievements;
    private MapScreen mapScreen;
    private Inventory inventory;
    private PlayerCreate playerCreate;
    private Missions missions;
    private MiniGame miniGame;
    private WheelOfFortune wheelOfFortune;
    private TipScreen tipScreen;

    public ZombieClicker() {
        instance = this;
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
    public MyThread getMyThread(){
        return myThread;
    }

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

        assets = new Assets();
        numerics = new Numerics(instance);
        soundManager = new SoundManager(instance);
        fontManager = new FontManager(instance);
        keepTrackAch = new KeepTrackAch(instance);

        myThread = new MyThread(instance);
        myThread.start();

        get_assets().load_popular();

        get_assets().load_assets_for_location_1();
        getNumerics().setCurrent_num_location(0);
        getNumerics().getCurrent_location().setPlayer_on_location(true);
        getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
        setMainGame();
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

    public void setInventoryScreen(){
        inventory = null;
        inventory = new Inventory(instance);
        setScreen(inventory);
    }

    public void setPlayerCreateScreen(){
        playerCreate = null;
        playerCreate = new PlayerCreate(instance);
        setScreen(playerCreate);
    }

    public void setMissionsScreen(){
        missions = null;
        missions = new Missions(instance);
        setScreen(missions);
    }

    public void setRewardScreen(String what, BigInteger amount, String lastScreen){
        rewardScreen = null;
        rewardScreen = new RewardScreen(instance, what, amount, lastScreen);
        setScreen(rewardScreen);
    }

    public void setMiniGameScreen(){
        miniGame = null;
        miniGame = new MiniGame(instance);
        setScreen(miniGame);
    }

    public void setWheelOfFortuneScreen(){
        wheelOfFortune = null;
        wheelOfFortune = new WheelOfFortune(instance);
        setScreen(wheelOfFortune);
    }

    public void setTipScreen(String header, String mainText, String lastScreen){
        tipScreen = null;
        tipScreen = new TipScreen(instance, header, mainText, lastScreen);
        setScreen(tipScreen);
    }
    ////////////SET SCREEN METHODS/////////////

    @Override
    public void dispose() {
        fontManager.dispose();
        assets.dispose();
    }
}
