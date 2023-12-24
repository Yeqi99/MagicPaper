package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.mcborder.api.McBorderAPI;

public class McBorderHook {
    public static boolean status = false;

    public static String getName() {
        return "McBorder";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }
    public static void rtp(String playerName,String worldName){
        if (!status){
            return;
        }
        McBorderAPI.rtp(playerName,worldName);
    }
}
