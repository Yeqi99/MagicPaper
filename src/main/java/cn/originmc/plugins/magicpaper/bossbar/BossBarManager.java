package cn.originmc.plugins.magicpaper.bossbar;


import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BossBarManager {
    private Map<String, BossBar> bossBars = new HashMap<>();

    public BossBarManager() {

    }

    public boolean createBossBar(String id, Component name, float progress, BossBar.Color color, BossBar.Overlay overlay) {
        if (bossBars.containsKey(id)) {
            return false;
        }
        BossBar bossBar = BossBar.bossBar(name, progress, color, overlay);
        bossBars.put(id, bossBar);
        return true;
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
        if (!bossBars.containsKey(id)){
            return;
        }
        bossBars.get(id).addViewer(player);
    }
    public void hideBossBar(Player player, String id) {
        if (!bossBars.containsKey(id)){
            return;
        }
        bossBars.get(id).removeViewer(player);
    }
    public boolean hasBossBar(String id) {
        return bossBars.containsKey(id);
    }
}
