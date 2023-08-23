package cn.originmc.plugins.magicpaper;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.magic.FunctionRegister;
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
    public void onEnable() {
        instance=this;
        sender=new Sender(this);
        magicManager = new MagicManager();
        MagicData.load();
        LangData.load();
        loadContext();
        FunctionRegister.register(getMagicManager());
    }

    @Override
    public void onDisable() {

    }
    public void loadContext(){
        context=new NormalContext();
    }
    public static String getVersion(){
        return "1.0.0";
    }
}
