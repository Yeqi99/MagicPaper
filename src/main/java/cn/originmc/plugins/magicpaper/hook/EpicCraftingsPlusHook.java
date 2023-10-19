package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.Bukkit;

public class EpicCraftingsPlusHook {
    public static boolean status = false;

    public static String getName() {
        return "EpicCraftingsPlus";
    }

    public static boolean hook() {
        if (Bukkit.getPluginManager().getPlugin(getName()) != null) {
            MagicPaper.getSender().sendToLogger("&a成功挂钩"+getName()+"插件");
            status=true;
            return true;
        } else {
            MagicPaper.getSender().sendToLogger("&c未找到"+getName()+"插件");
            status=false;
            return false;
        }
    }

}
