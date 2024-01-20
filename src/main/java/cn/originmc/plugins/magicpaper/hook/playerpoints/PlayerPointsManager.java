package cn.originmc.plugins.magicpaper.hook.playerpoints;

import cn.originmc.plugins.magicpaper.hook.PlayerPointsHook;

import java.util.UUID;

public class PlayerPointsManager {
    public static int lookPoints(UUID playerUUID){
        if (!PlayerPointsHook.status) {
            return -1;
        }
        return PlayerPointsHook.getPlayerPointsAPI().look(playerUUID);
    }
    public static boolean takePoints(UUID playerUUID,int amount){
        if (!PlayerPointsHook.status) {
            return false;
        }
        return PlayerPointsHook.getPlayerPointsAPI().take(playerUUID, amount);
    }
    public static boolean hasPoints(UUID playerUUID,int amount){
        if (!PlayerPointsHook.status) {
            return false;
        }
        return lookPoints(playerUUID) >= amount;
    }
    public static boolean givePoints(UUID playerUUID,int amount){
        if (!PlayerPointsHook.status) {
            return false;
        }
        return PlayerPointsHook.getPlayerPointsAPI().give(playerUUID,amount);
    }
    public static boolean setPoints(UUID playerUUID,int amount){
        if (!PlayerPointsHook.status) {
            return false;
        }
        return PlayerPointsHook.getPlayerPointsAPI().set(playerUUID,amount);
    }
}
