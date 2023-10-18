package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class LuckPermsHook {
    public static boolean status = false;
    private static LuckPerms api;

    public static String getName() {
        return "LuckPerms";
    }

    public static boolean hook() {
        if (Bukkit.getPluginManager().getPlugin(getName()) != null) {
            MagicPaper.getSender().sendToLogger("&a成功挂钩"+getName()+"插件");
            RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
            api = provider.getProvider();
            status=true;
            return true;
        } else {
            MagicPaper.getSender().sendToLogger("&c未找到"+getName()+"插件");
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
