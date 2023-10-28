package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Objects;

public class LuckPermsHook {
    public static boolean status = false;
    private static LuckPerms api;

    public static String getName() {
        return "LuckPerms";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
        if (status){
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            api = Objects.requireNonNull(provider).getProvider();
        }
    }
    public static LuckPerms getApi() {
        return api;
    }

    public static void setApi(LuckPerms api) {
        LuckPermsHook.api = api;
    }
}
