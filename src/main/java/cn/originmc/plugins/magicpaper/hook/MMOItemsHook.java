package cn.originmc.plugins.magicpaper.hook;

public class MMOItemsHook {
    public static boolean status = false;

    public static String getName() {
        return "MMOItems";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }
}
