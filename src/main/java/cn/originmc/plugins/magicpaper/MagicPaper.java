package cn.originmc.plugins.magicpaper;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.function.FunctionRegister;
import cn.origincraft.magic.manager.MagicInstance;
import cn.origincraft.magic.manager.MagicPackage;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.buff.BuffListener;
import cn.originmc.plugins.magicpaper.buff.MagicBuffManager;
import cn.originmc.plugins.magicpaper.command.MagicPaperCommand;
import cn.originmc.plugins.magicpaper.command.MagicPaperTabCompleter;
import cn.originmc.plugins.magicpaper.cooldown.CoolDownManager;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.data.manager.TimerDataManager;
import cn.originmc.plugins.magicpaper.data.manager.TriggerDataManager;
import cn.originmc.plugins.magicpaper.data.timer.TimerData;
import cn.originmc.plugins.magicpaper.data.trigger.TriggerData;
import cn.originmc.plugins.magicpaper.gui.MagicGuiListener;
import cn.originmc.plugins.magicpaper.hook.*;
import cn.originmc.plugins.magicpaper.hook.placeholderapi.SpellExpansion;
import cn.originmc.plugins.magicpaper.listener.AdditionalItemListener;
import cn.originmc.plugins.magicpaper.listener.CodingListener;
import cn.originmc.plugins.magicpaper.listener.ItemTriggerListener;
import cn.originmc.plugins.magicpaper.listener.ItemVariableRefreshListener;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.trigger.listener.EpicCraftingPlusListener;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicPaper extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;
    private static MagicManager magicManager;
    private static NormalContext context;
    private static CoolDownManager coolDownManager;
    private static MagicBuffManager magicBuffManager;
    public static MagicPackage magicPackage;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static Sender getSender() {
        return sender;
    }

    public static MagicManager getMagicManager() {
        return magicManager;
    }

    public static NormalContext getContext() {
        return context;
    }

    public static void setContext(NormalContext context) {
        MagicPaper.context = context;
    }

    public static CoolDownManager getCoolDownManager() {
        return coolDownManager;
    }

    public static void setCoolDownManager(CoolDownManager coolDownManager) {
        MagicPaper.coolDownManager = coolDownManager;
    }

    public static MagicBuffManager getMagicBuffManager() {
        return magicBuffManager;
    }

    public static void setMagicBuffManager(MagicBuffManager magicBuffManager) {
        MagicPaper.magicBuffManager = magicBuffManager;
    }

    @Override
    public void onLoad() {
        MagicPaperTriggerManager.trigger("ServerOnLoad",new NormalContext());
    }
    @Override
    public void onEnable() {
        int pluginId = 19713;
        Metrics metrics = new Metrics(this, pluginId);
        // 初始化插件实例
        instance=this;
        // 初始化发送器
        sender=new Sender(this);
        // 保存默认配置
        saveRes();
        // 初始化魔法管理器
        initMagicManager();
        hook();
        // 初始化触发器管理器
        MagicPaperTriggerManager.init();
        // 初始化冷却管理器
        coolDownManager=new CoolDownManager();
        sender.sendToLogger("§a[§bMagicPaper§a] §e冷却管理器初始化完成");
        // 初始化Buff管理器
        magicBuffManager=new MagicBuffManager();
        sender.sendToLogger("§a[§bMagicPaper§a] §eBuff管理器初始化完成");
        // 加载全局上下文
        loadContext();
        sender.sendToLogger("§a[§bMagicPaper§a] §e全局上下文加载完成");
        // 加载数据
        loadData();
        // 注册命令
        registerCommand();
        // 注册监听器
        registerListener();
        // 注册魔法函数信息
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerInfo();
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerArgsInfo();
        MagicPaperTriggerManager.trigger("ServerOnEnable",new NormalContext());
        importSpell(getContext());
        sender.sendToLogger("§a[§bMagicPaper§a] §e载入import魔咒完成");
        onLoadSpell();
        sender.sendToLogger("§a[§bMagicPaper§a] §e执行onload魔咒完成");
        // 载入触发器魔咒
        sender.sendToLogger("§a[§bMagicPaper§a] §e启动触发器...");
        TriggerDataManager.initDefaultTrigger();
        // 启动计时器
        sender.sendToLogger("§a[§bMagicPaper§a] §e启动计时器...");
        TimerDataManager.initConfigTimer();
        sender.sendOnEnableMsgToLogger("MagicPaper","Yeqi",getVersion(),"Public");

    }
    public static void initMagicManager(){
        // 初始化魔法管理器
        magicManager = new MagicManager();
        // 注册魔法函数
        if (enableExtendedSyntax("system")){
            FunctionRegister.regDefault(getMagicManager());
            sender.sendToLogger("§a[§bMagicPaper§a] §eMagic系统语义注册完成");
        }
        if (enableExtendedSyntax("paper")){
            cn.originmc.plugins.magicpaper.magic.FunctionRegister.register(getMagicManager());
            sender.sendToLogger("§a[§bMagicPaper§a] §eMagicPaper语义注册完成");
        }
        /*
        if (enableExtendedSyntax("redis")){
            MagicRedisFunctionRegister.reg(getMagicManager());
        }
         */
        sender.sendToLogger("§a[§bMagicPaper§a] §eMagic管理器初始化完成");
    }
    @Override
    public void onDisable() {
        MagicPaperTriggerManager.trigger("ServerOnDisable",new NormalContext());
        sender.sendOnDisableMsgToLogger("MagicPaper","Yeqi",getVersion(),"Public");
    }
    public void loadContext(){
        context=new NormalContext();
    }
    public static String getVersion(){
        return "1.3.0";
    }
    public static String getLang(){
        return getInstance().getConfig().getString("lang");
    }

    public void registerCommand(){
        getCommand("MagicPaper").setExecutor(new MagicPaperCommand());
        getCommand("MagicPaper").setTabCompleter(new MagicPaperTabCompleter());
        sender.sendToLogger("§a[§bMagicPaper§a] §e命令注册完成");
    }
    public void registerListener(){
        if (getConfig().getBoolean("buff-system-enable")){
            getServer().getPluginManager().registerEvents(new BuffListener(),this);
        }
        if (getConfig().getBoolean("coding",false)){
            getServer().getPluginManager().registerEvents(new CodingListener(),this);
        }
        if (getConfig().getBoolean("gui-listener",false)){
            getServer().getPluginManager().registerEvents(new MagicGuiListener(),this);
        }
        if (PlaceholderAPIHook.status){
            new SpellExpansion().register();
        }
        if (EpicCraftingsPlusHook.status){
            getServer().getPluginManager().registerEvents(new EpicCraftingPlusListener(), this);
        }
        // protocolLib修改物品发包解析监听器
        if (ProtocolLibHook.status){
            ProtocolLibHook.pm.addPacketListener(new ItemVariableRefreshListener(this));
        }
        if (getConfig().getBoolean("bore-listener",false)){
            getServer().getPluginManager().registerEvents(new AdditionalItemListener(), this);
        }
        if (getConfig().getBoolean("item-trigger-listener",false)){
            getServer().getPluginManager().registerEvents(new ItemTriggerListener(), this);
        }
        sender.sendToLogger("§a[§bMagicPaper§a] §e监听器注册完成");
    }
    public void saveRes(){
        getInstance().saveDefaultConfig();
        if (getConfig().getBoolean("default-file",true)){
            getInstance().saveResource("lang/Chinese.yml",false);
            getInstance().saveResource("lang/English.yml",false);
            getInstance().saveResource("magic/HelloWorld.yml",false);
            getInstance().saveResource("magic/nothing.yml",false);
            getInstance().saveResource("onload/register.m",false);
            getInstance().saveResource("import/HelloWorld.m",false);
            getInstance().saveResource("import/aitem.m",false);
            getInstance().saveResource("import/nbt.m",false);
            getInstance().saveResource("item-format/default.yml",false);
            getInstance().saveResource("trigger/PlayerJoinTrigger.yml",false);
            getInstance().saveResource("trigger/ServerOnDisableTrigger.yml",false);
            getInstance().saveResource("trigger/ServerOnEnableTrigger.yml",false);
            getInstance().saveResource("trigger/ServerOnLoadTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerInteractTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerDropTrigger.yml",false);
            getInstance().saveResource("trigger/TimerTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerBreakTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerPlaceTrigger.yml",false);
            getInstance().saveResource("trigger/EntityDamageTrigger.yml",false);
            getInstance().saveResource("trigger/ItemDropTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerTeleportTrigger.yml",false);
            getInstance().saveResource("trigger/AsyncPlayerChatTrigger.yml",false);
            getInstance().saveResource("trigger/PlayerRespawnTrigger.yml",false);
            if (RemoteKeyboardBukkitHook.status){
                getInstance().saveResource("trigger/PlayerKeyboardTrigger.yml",false);
            }
            if (EpicCraftingsPlusHook.status){
                getInstance().saveResource("trigger/EpicCraftingsPreCraftTrigger.yml",false);
                getInstance().saveResource("trigger/EpicCraftingsCraftTrigger.yml",false);
                getInstance().saveResource("trigger/EpicCraftingsPlaceClickTrigger.yml",false);
            }
            getInstance().saveResource("timer/default.yml",false);
        }
        sender.sendToLogger("§a[§bMagicPaper§a] §e默认配置保存完成");
    }
    public void hook(){
        sender.sendToLogger("§a[§bMagicPaper§a] §e正在挂钩插件");
        sender.sendToLogger("§8挂钩仅用于提供更多功能,不挂钩不影响插件正常使用");
        ProtocolLibHook.hook();
        PlaceholderAPIHook.hook();
        LuckPermsHook.hook();
        AbolethplusHook.hook();
        MythicMobsHook.hook();
        ItemsAdderHook.hook();
        PlayerPointsHook.hook();
        VaultHook.hook();
        RemoteKeyboardBukkitHook.hook();
        EpicCraftingsPlusHook.hook();
        sender.sendToLogger("§a[§bMagicPaper§a] §e插件挂钩完成");
    }
    public static void loadData(){
        // 加载魔咒数据
        MagicData.load();
        // 加载语言文件数据
        LangData.load();
        // 加载物品格式数据
        ItemFormatData.load();
        // 加载触发器数据
        TriggerData.load();
        // 加载计时器数据
        TimerData.load();
        sender.sendToLogger("§a[§bMagicPaper§a] §e数据加载完成");
    }
    public static boolean enableExtendedSyntax(String id){
        return getInstance().getConfig().getBoolean("extended-syntax."+id,false);
    }
    public static boolean isDebug(){
        return getInstance().getConfig().getBoolean("debug",false);
    }

    public static void onLoadSpell(){
        MagicPackage magicPackage=new MagicPackage("paper.onload");
        magicPackage.loadFiles(getInstance().getDataFolder()+"/onload");
        for (MagicInstance value : magicPackage.getMagicInstances().values()) {
            value.getSpell(getMagicManager()).execute(getContext());
        }
    }
    public static void importSpell(ContextMap contextMap){
        magicPackage=new MagicPackage("paper.import");
        magicPackage.loadFiles(getInstance().getDataFolder()+"/import");
        magicPackage.importPackage(contextMap,getMagicManager());
    }

}
