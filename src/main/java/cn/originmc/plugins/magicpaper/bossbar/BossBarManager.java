package cn.originmc.plugins.magicpaper.bossbar;


import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BossBarManager {
    private Map<String, BossBar> bossBars = new HashMap<>();

    public BossBarManager() {

    }

    public void createBossBar(String id, String title, BarColor color, BarStyle style, BarFlag... flags) {
        if (bossBars.containsKey(id)) {
            bossBars.get(id).removeAll();
        }
        BossBar bossBar = Bukkit.createBossBar(title, color, style, flags);
        bossBars.put(id, bossBar);

    }

    public Map<String, BossBar> getBossBars() {
        return bossBars;
    }

    public void setBossBars(Map<String, BossBar> bossBars) {
        this.bossBars = bossBars;
    }

    public BossBar getBossBar(String id) {
        return bossBars.get(id);
    }

    public void showBossBar(Player player, String id) {
        if (!bossBars.containsKey(id)) {
            return;
        }
        bossBars.get(id).addPlayer(player);
    }

    public void hideBossBar(Player player, String id) {
        if (!bossBars.containsKey(id)) {
            return;
        }
        bossBars.get(id).removePlayer(player);
    }
    public void setBossBarProgress(String id,double progress){
        if (!bossBars.containsKey(id)) {
            return;
        }
        bossBars.get(id).setProgress(progress);
    }
    public void setBossVisibility(String id,boolean flag){
        if (!bossBars.containsKey(id)) {
            return;
        }
        bossBars.get(id).setVisible(flag);
    }
    public boolean hasBossBar(String id) {
        return bossBars.containsKey(id);
    }
    public void removeBossBar(String id){
        if (!bossBars.containsKey(id)) {
            return;
        }
        bossBars.get(id).removeAll();
        bossBars.remove(id);
    }
}
