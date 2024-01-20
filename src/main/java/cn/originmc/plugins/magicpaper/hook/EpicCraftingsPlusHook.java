package cn.originmc.plugins.magicpaper.hook;

public class EpicCraftingsPlusHook {
    public static boolean status = false;

    public static String getName() {
        return "EpicCraftingsPlus";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }

}
