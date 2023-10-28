package cn.originmc.plugins.magicpaper.hook;

import cn.originmc.plugins.magicpaper.MagicPaper;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultHook {
    public static boolean status = false;
    public static String getName() {
        return "Vault";
    }

    public static void hook() {
        status=Hook.hook(getName());
        MagicPaper.getSender().sendToLogger(Hook.getLog(getName(),status));
    }
    public static Economy getEconomy(){
        RegisteredServiceProvider<Economy> rsp = MagicPaper.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return null;
        }
        return  rsp.getProvider();
    }

}
