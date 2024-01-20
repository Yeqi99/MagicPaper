package cn.originmc.plugins.magicpaper.hook.vault;

import cn.originmc.plugins.magicpaper.hook.VaultHook;
import org.bukkit.entity.Player;

public class VaultManager {
    public static void giveMoney(Player player, double amount){
        if (!VaultHook.status){
            return;
        }
        if (VaultHook.getEconomy()==null){
            return;
        }
        VaultHook.getEconomy().depositPlayer(player,amount);
    }
    public static double getMoney(Player player){
        if (!VaultHook.status){
            return -1;
        }
        if (VaultHook.getEconomy()==null){
            return -1;
        }
        return VaultHook.getEconomy().getBalance(player);
    }
    public static boolean hasMoney(Player player,double amount){
        if (!VaultHook.status){
            return false;
        }
        if (VaultHook.getEconomy()==null){
            return false;
        }
        return getMoney(player)>=amount;
    }
    public static boolean takeMoney(Player player, double amount) {
        if (!VaultHook.status) {
            return false;
        }
        if (VaultHook.getEconomy() == null) {
            return false;
        }
        VaultHook.getEconomy().withdrawPlayer(player, amount);
        return true;
    }
}
