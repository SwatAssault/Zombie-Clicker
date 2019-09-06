package com.awprecords.zombieclicker;

import com.badlogic.gdx.Game;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Managers.Assets;
import Managers.FontManager;
import Managers.PreferencesManager;
import Managers.SoundManager;
import Numbers.Numerics;
import Other.HUD;
import Other.KeepTrackAch;
import Other.MyThread;
import Other.SquadItem;
import Screens.Achievements;
import Screens.EveryDayRewardScreen;
import Screens.Inventory;
import Screens.MainGame;
import Screens.MapScreen;
import Screens.MiniGame;
import Screens.Missions;
import Screens.PlayerCreate;
import Screens.RewardScreen;
import Screens.Shop;
import Screens.SquadSelectionScreen;
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
    private SquadSelectionScreen squadSelectionScreen;
    private HUD hud;
    private PreferencesManager preferencesManager;
    private EveryDayRewardScreen everyDayRewardScreen;

    private int lastLaunch_Month;
    private int lastLaunch_Day;
    private SimpleDateFormat simpleDateFormat;
    private Date game_launch_date;
    private Date last_launch_date;
    private Calendar calendar;

    private boolean veryFirstLaunch;
    private boolean firstLaunchToday;
    private int days_in_aRow;

    public ZombieClicker() {
        instance = this;
        calendar = Calendar.getInstance();

    }

    //////////GETTERS FOR SCREENS//////////
    public MainGame getMainGame() {
        return mainGame;
    }

    public Shop getShop() {
        return shop;
    }
    //////////GETTERS FOR SCREENS//////////

    /////////////OTHER GETTERS/////////////
    public MyThread getMyThread() {
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

    public FontManager getFontManager() {
        return fontManager;
    }

    public KeepTrackAch getKeepTrackAch() {
        return keepTrackAch;
    }

    public Achievements getAchievements() {
        return achievements;
    }

    public HUD getHud() {
        return hud;
    }

    public Date getGame_launch_date() {
        return game_launch_date;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Missions getMissions() {
        return missions;
    }

    public SquadSelectionScreen getSquadSelectionScreen(){
        return squadSelectionScreen;
    }

    public PreferencesManager getPreferencesManager(){
        return preferencesManager;
    }
    /////////////OTHER GETTERS/////////////

    public ZombieClicker get_ZombieClicker() {
        return instance;
    }

    public void dates(){
        days_in_aRow = preferencesManager.getSettings().getInteger("days_in_aRow", 0);
        lastLaunch_Month = preferencesManager.getSettings().getInteger("lastLaunch_Month", calendar.get(Calendar.MONTH) + 1);
        //lastLaunch_Month = 8;
        lastLaunch_Day = preferencesManager.getSettings().getInteger("lastLaunch_Day", calendar.get(Calendar.DAY_OF_MONTH));
       // lastLaunch_Day = 31;

        if(lastLaunch_Day < calendar.get(Calendar.DAY_OF_MONTH) || lastLaunch_Month != calendar.get(Calendar.MONTH) + 1 || veryFirstLaunch){
            firstLaunchToday = true;
        } else
            firstLaunchToday = false;

        if(firstLaunchToday && ((calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day == 1) || (calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day == -29 || calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day == -30))){
            days_in_aRow++;
        }

//        if(firstLaunchToday && ((calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day > 1) || (calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day != -29 || calendar.get(Calendar.DAY_OF_MONTH) - lastLaunch_Day != -30))){
//            days_in_aRow = 0;
//        }

        preferencesManager.getSettings().putInteger("days_in_aRow", days_in_aRow);
        preferencesManager.getSettings().flush();

        lastLaunch_Month = calendar.get(Calendar.MONTH) + 1;
        lastLaunch_Day = calendar.get(Calendar.DAY_OF_MONTH);
        preferencesManager.getSettings().putInteger("lastLaunch_Month", lastLaunch_Month);
        preferencesManager.getSettings().putInteger("lastLaunch_Day", lastLaunch_Day);
        preferencesManager.getSettings().flush();

        if(firstLaunchToday){
            veryFirstLaunch = false;
            preferencesManager.getSettings().putBoolean("veryFirstLaunch", veryFirstLaunch);
            preferencesManager.getSettings().flush();
            switch(days_in_aRow){

            }
            setEveryDayRewardScreen(days_in_aRow);
        } else {
            _begin();
        }

        //TODO
     //   setEveryDayRewardScreen(days_in_aRow);
    }

    public void _begin(){
        get_assets().load_assets_for_location_1();
        getNumerics().setCurrent_num_location(0);
        getNumerics().getCurrent_location().setPlayer_on_location(true);
        getNumerics().getCurrent_location().setBGimage("Background/location_1_bg.png");
        setMainGame();
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
        hud = new HUD(instance);
        shop = new Shop(instance);

        preferencesManager = new PreferencesManager(instance);
        veryFirstLaunch = preferencesManager.getSettings().getBoolean("veryFirstLaunch", true);
        dates();

    }

    ////////////SET SCREEN METHODS/////////////
    public void setMainGame() {
        mainGame = new MainGame(instance, numerics.getCurrent_location());
        setScreen(mainGame);
    }

    public void setSquadsDistScreen(ZombieClicker zc, SquadItem squadItem) {
        squadsDistScreen = new SquadsDistScreen(zc, squadItem);
        setScreen(squadsDistScreen);
    }

    public void setShopScreen() {
        if (shop == null) {
            shop = new Shop(instance);
        }
        shop.getStage().addActor(hud.getGold_icon());
        shop.getStage().addActor(hud.getDiamond_icon());
        shop.getStage().addActor(hud.getPlus_gold_btn());
        shop.getStage().addActor(hud.getPlus_diamonds_btn());
        setScreen(shop);
    }

    public void setAchievementScreen() {
        achievements = null;
        achievements = new Achievements(instance);
        setScreen(achievements);
    }

    public void setMapScreen() {
        mapScreen = null;
        mapScreen = new MapScreen(instance);
        setScreen(mapScreen);
    }

    public void setInventoryScreen() {
        inventory = null;
        inventory = new Inventory(instance);
        setScreen(inventory);
    }

    public void setPlayerCreateScreen() {
        playerCreate = null;
        playerCreate = new PlayerCreate(instance);
        setScreen(playerCreate);
    }

    public void setMissionsScreen() {
        missions = null;
        missions = new Missions(instance);
        setScreen(missions);
    }

    public void setRewardScreen(String what, BigInteger amount, String lastScreen) {
        rewardScreen = null;
        rewardScreen = new RewardScreen(instance, what, amount, lastScreen);
        setScreen(rewardScreen);
    }

    public void setMiniGameScreen() {
        miniGame = null;
        miniGame = new MiniGame(instance);
        setScreen(miniGame);
    }

    public void setWheelOfFortuneScreen() {
        wheelOfFortune = null;
        wheelOfFortune = new WheelOfFortune(instance);
        setScreen(wheelOfFortune);
    }

    public void setTipScreen(String header, String mainText, String lastScreen, int whichMission) {
        tipScreen = null;
        tipScreen = new TipScreen(instance, header, mainText, lastScreen, whichMission);
        setScreen(tipScreen);
    }

    public void setSquadSelectionScreen(int whichMission) {
        squadSelectionScreen = null;
        if (shop == null)
            shop = new Shop(instance);
        squadSelectionScreen = new SquadSelectionScreen(instance, whichMission);
        setScreen(squadSelectionScreen);
    }

    public void setEveryDayRewardScreen(int _days_in_aRow){
        everyDayRewardScreen = null;
        everyDayRewardScreen = new EveryDayRewardScreen(instance, _days_in_aRow);
        setScreen(everyDayRewardScreen);
    }
    ////////////SET SCREEN METHODS/////////////

    @Override
    public void dispose() {
        fontManager.dispose();
        assets.dispose();
    }
}
