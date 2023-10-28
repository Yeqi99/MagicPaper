package cn.originmc.plugins.magicpaper.hook;


public class AbolethplusHook{
    public static boolean status = false;

    public static String getName() {
        return "Abolethplus";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }

}
