package cn.originmc.plugins.magicpaper;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.function.FunctionRegister;
import cn.origincraft.magic.magicredis.MagicRedisFunctionRegister;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.command.MagicPaperCommand;
import cn.originmc.plugins.magicpaper.command.MagicPaperTabCompleter;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;


import javax.print.DocFlavor;

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
        // 初始化插件实例
        instance=this;
        // 初始化发送器
        sender=new Sender(this);
        // 初始化魔法管理器
        magicManager = new MagicManager();
        // 初始化触发器管理器
        MagicPaperTriggerManager.init();
        // 保存默认配置
        saveRes();
        // 加载数据
        loadData();
        // 加载全局上下文
        loadContext();
        // 注册命令
        registerCommand();
        // 注册监听器
        registerListener();
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
        // 注册魔法函数信息
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerInfo();
        cn.originmc.plugins.magicpaper.magic.FunctionRegister.registerArgsInfo();
        MagicPaperTriggerManager.trigger("server_on_enable",new NormalContext());
    }

    @Override
    public void onDisable() {
        MagicPaperTriggerManager.trigger("server_on_disable",new NormalContext());
    }
    public void loadContext(){
        context=new NormalContext();
    }
    public static String getVersion(){
        return "1.0.13";
    }
    public static String getLang(){
        return getInstance().getConfig().getString("lang");
    }

    public void registerCommand(){
        getCommand("MagicPaper").setExecutor(new MagicPaperCommand());
        getCommand("MagicPaper").setTabCompleter(new MagicPaperTabCompleter());
    }
    public void registerListener(){

    }
    public void saveRes(){
        getInstance().saveDefaultConfig();
        getInstance().saveResource("lang/Chinese.yml",false);
        getInstance().saveResource("magic/example.yml",false);
    }
    public void loadData(){
        // 加载魔咒数据
        MagicData.load();
        // 加载语言文件数据
        LangData.load();
    }
    public static boolean enableExtendedSyntax(String id){
        return getInstance().getConfig().getBoolean("extended-syntax."+id,false);
    }
    public static boolean isDebug(){
        return getInstance().getConfig().getBoolean("debug",false);
    }

}
