package cn.originmc.plugins.magicpaper.buff;

import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class BuffListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            String id = event.getEntity().getUniqueId().toString();
            if (MagicPaper.getMagicBuffManager().isBuffActive(id, "fall_immunity")) {
                event.setCancelled(true);
            }
        }
    }
}
