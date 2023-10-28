package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;

public class EpicCraftingsPlusHook {
    public static boolean status = false;

    public static String getName() {
        return "EpicCraftingsPlus";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
    }

}
