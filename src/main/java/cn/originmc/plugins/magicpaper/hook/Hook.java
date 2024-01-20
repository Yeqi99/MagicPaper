package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import org.bukkit.Bukkit;

public class Hook {

    public static boolean hook(String name) {
        if (Bukkit.getPluginManager().getPlugin(name) != null) {
            MagicPaper.getSender().sendToLogger(getLog(name, true));
            return true;
        } else {
            MagicPaper.getSender().sendToLogger(getLog(name, false));
            return false;
        }
    }

    public static String getLog(String name, boolean status){
        if (status){
            return LangData.get(MagicPaper.getLang(),
                            "hook-success","§a[§bMagicPaper§a] §e成功挂钩 §a~ §e插件")
                    .replace("~",name);
        }else {
            return LangData.get(MagicPaper.getLang(),
                            "hook-fail","§a[§bMagicPaper§a] §e未找到 §a~ §e插件")
                    .replace("~",name);
        }

    }


}
