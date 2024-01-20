package cn.originmc.plugins.magicpaper.hook;

public class MythicMobsHook {
    public static boolean status = false;

    public static String getName() {
        return "MythicMobs";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }
}
