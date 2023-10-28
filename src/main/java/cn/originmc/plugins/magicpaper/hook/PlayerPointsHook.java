package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;

public class PlayerPointsHook {
    public static boolean status = false;
    public static String getName() {
        return "PlayerPoints";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
    }

    public static PlayerPointsAPI getPlayerPointsAPI() {
        return PlayerPoints.getInstance().getAPI();
    }
}
