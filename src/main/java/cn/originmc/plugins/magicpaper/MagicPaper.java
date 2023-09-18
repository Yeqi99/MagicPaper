package cn.originmc.plugins.magicpaper;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.function.FunctionRegister;
import cn.origincraft.magic.magicredis.MagicRedisFunctionRegister;
import cn.origincraft.magic.manager.MagicInstance;
import cn.origincraft.magic.manager.MagicPackage;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.command.MagicPaperCommand;
import cn.originmc.plugins.magicpaper.command.MagicPaperTabCompleter;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.hook.AbolethplusHook;
import cn.originmc.plugins.magicpaper.hook.LuckPermsHook;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.listener.CodingListener;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicPaper extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;
    private static MagicManager magicManager;
    private static NormalContext context;

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

    @Override
    public void onLoad() {
        MagicPaperTriggerManager.trigger("server_on_enable",new NormalContext());
    }
    @Override
    public void onEnable() {
        int pluginId = 19713; // 替换为您的插件ID
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

        // 加载全局上下文
        loadContext();
        // 加载数据
        loadData();
        // 注册命令
        registerCommand();
        // 注册监听器
        registerListener();

        // 注册魔法函数信息
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerInfo();
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerArgsInfo();
        MagicPaperTriggerManager.trigger("server_on_enable",new NormalContext());
        importSpell(getContext());
        onLoadSpell();
    }
    public static void initMagicManager(){
        // 初始化魔法管理器
        magicManager = new MagicManager();
        // 注册魔法函数
        if (enableExtendedSyntax("system")){
            FunctionRegister.regDefault(getMagicManager());
        }
        if (enableExtendedSyntax("paper")){
            cn.originmc.plugins.magicpaper.magic.FunctionRegister.register(getMagicManager());
        }
        if (enableExtendedSyntax("redis")){
            MagicRedisFunctionRegister.reg(getMagicManager());
        }
    }
    @Override
    public void onDisable() {
        MagicPaperTriggerManager.trigger("server_on_disable",new NormalContext());
    }
    public void loadContext(){
        context=new NormalContext();
    }
    public static String getVersion(){
        return "1.0.34";
    }
    public static String getLang(){
        return getInstance().getConfig().getString("lang");
    }

    public void registerCommand(){
        getCommand("MagicPaper").setExecutor(new MagicPaperCommand());
        getCommand("MagicPaper").setTabCompleter(new MagicPaperTabCompleter());
    }
    public void registerListener(){
        if (getConfig().getBoolean("coding",false)){
            getServer().getPluginManager().registerEvents(new CodingListener(),this);

        }
    }
    public void saveRes(){
        getInstance().saveDefaultConfig();
        getInstance().saveResource("lang/Chinese.yml",false);
        getInstance().saveResource("magic/HelloWorld.yml",false);
        getInstance().saveResource("onload/register.m",false);
        getInstance().saveResource("import/HelloWorld.m",false);
        getInstance().saveResource("import/aitem.m",false);
        getInstance().saveResource("import/nbt.m",false);
        getInstance().saveResource("item-format/default.yml",false);
    }
    public void hook(){
        PlaceholderAPIHook.hook();
        LuckPermsHook.hook();
        AbolethplusHook.hook();
    }
    public void loadData(){
        // 加载魔咒数据
        MagicData.load();
        // 加载语言文件数据
        LangData.load();
        // 加载物品格式数据
        ItemFormatData.load();
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
        MagicPackage magicPackage=new MagicPackage("paper.import");
        magicPackage.loadFiles(getInstance().getDataFolder()+"/import");
        magicPackage.importPackage(contextMap,getMagicManager());
    }
}
