package cn.originmc.plugins.magicpaper.hook;


import cn.originmc.plugins.magicpaper.MagicPaper;

public class AbolethplusHook{
    public static boolean status = false;

    public static String getName() {
        return "Abolethplus";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
    }

}
