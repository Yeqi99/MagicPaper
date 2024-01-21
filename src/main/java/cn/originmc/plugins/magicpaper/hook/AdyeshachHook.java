package cn.originmc.plugins.magicpaper.hook;

public class AdyeshachHook {
    public static boolean status = false;

    public static String getName() {
        return "Adyeshach";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }

}
