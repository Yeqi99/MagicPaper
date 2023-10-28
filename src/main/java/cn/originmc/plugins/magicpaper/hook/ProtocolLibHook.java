package cn.originmc.plugins.magicpaper.hook;

public class ProtocolLibHook {
    public static boolean status = false;
    public static com.comphenix.protocol.ProtocolManager pm;

    public static String getName() {
        return "ProtocolLib";
    }

    public static void hook() {
        status=Hook.hook(getName());
        if (status){
            pm = com.comphenix.protocol.ProtocolLibrary.getProtocolManager();
        }
    }
}
