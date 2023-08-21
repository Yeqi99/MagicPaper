package cn.originmc.plugins.magicpaper.hook;

import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;

public class LuckPermsHook {
    public static boolean status = false;
    private static LuckPerms api;

    public static String getName() {
        return "LuckPerms";
    }

    public static boolean hook() {
        if (Bukkit.getPluginManager().getPlugin(getName()) != null) {
            status=true;
            return true;
        } else {
            status=false;
            return false;
        }
    }
    public static LuckPerms getApi() {
        return api;
    }

    public static void setApi(LuckPerms api) {
        LuckPermsHook.api = api;
    }
}
