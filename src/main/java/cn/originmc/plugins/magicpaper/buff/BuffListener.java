package cn.originmc.plugins.magicpaper.buff;

import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.MagicPaper;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class BuffListener implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            String id = event.getEntity().getUniqueId().toString();
            if (MagicPaper.getMagicBuffManager().isBuffActive(id, "fall_immunity")) {
                MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "fall_immunity");
                String setting = magicBuff.getBuffSetting();
                if (VariableUtil.tryInt(setting)) {
                    int times = Integer.parseInt(setting);
                    if (times > 0) {
                        event.setCancelled(true);
                        times--;
                    } else {
                        MagicPaper.getMagicBuffManager().removeBuff(id, "fall_immunity");
                    }
                    magicBuff.setBuffSetting(times + "");
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerChatMute(AsyncPlayerChatEvent event) {
        String id = event.getPlayer().getUniqueId().toString();
        if (MagicPaper.getMagicBuffManager().isBuffActive(id, "mute")) {
            MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "mute");
            if (!magicBuff.getBuffSetting().isEmpty()) {
                MagicPaper.getSender().sendToPlayer(event.getPlayer(), magicBuff.getBuffSetting());
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerChatOracle(AsyncPlayerChatEvent event) {
        String id = event.getPlayer().getUniqueId().toString();
        if (MagicPaper.getMagicBuffManager().isBuffActive(id, "oracle")) {
            MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "oracle");
            magicBuff.getContextMap().putVariable("message", event.getMessage());
            magicBuff.execute(event.getPlayer());
        }
    }
}
