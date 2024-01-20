package cn.originmc.plugins.magicpaper.hook;

import fr.xephi.authme.api.v3.AuthMeApi;
import org.bukkit.entity.Player;

public class AuthMeHook {
    public static boolean status = false;

    public static String getName() {
        return "AuthMe";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }

    public static boolean isLogged(Player player){
        if (!status){
            return true;
        }
        return AuthMeApi.getInstance().isAuthenticated(player);
    }
    public static boolean isRegistered(Player player){
        if (!status){
            return true;
        }
        return AuthMeApi.getInstance().isRegistered(player.getName());
    }
}
