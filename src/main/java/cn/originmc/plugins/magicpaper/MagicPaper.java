package cn.originmc.plugins.magicpaper;

import cn.originmc.plugins.magicpaper.utils.text.Sender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicPaper extends JavaPlugin {
    private static JavaPlugin instance;
    private static Sender sender;

    public static JavaPlugin getInstance() {
        return instance;
    }

    public static Sender getSender() {
        return sender;
    }

    @Override
    public void onEnable() {
        instance=this;
        sender=new Sender(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
