package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.Bukkit;

public class ProtocolLibHook {
    public static boolean status = false;
    public static com.comphenix.protocol.ProtocolManager pm;

    public static String getName() {
        return "ProtocolLib";
    }

    public static boolean hook() {
        if (Bukkit.getPluginManager().getPlugin(getName()) != null) {
            MagicPaper.getSender().sendToLogger("&a成功挂钩"+getName()+"插件");
            pm = com.comphenix.protocol.ProtocolLibrary.getProtocolManager();
            status=true;
            return true;
        } else {
            MagicPaper.getSender().sendToLogger("&c未找到"+getName()+"插件");
            status=false;
            return false;
        }
    }
}
