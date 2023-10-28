package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;

public class ProtocolLibHook {
    public static boolean status = false;
    public static com.comphenix.protocol.ProtocolManager pm;

    public static String getName() {
        return "ProtocolLib";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
        if (status){
            pm = com.comphenix.protocol.ProtocolLibrary.getProtocolManager();
        }
    }
}
