package cn.originmc.plugins.magicpaper.buff;

import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
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
                if (VariableUtils.tryInt(setting)) {
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
        if (MagicPaper.getMagicBuffManager().isBuffActive(id, "mute")){
            return;
        }
        if (MagicPaper.getMagicBuffManager().isBuffActive(id, "oracle")) {
            MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "oracle");
            String setting = magicBuff.getBuffSetting();
            if (VariableUtils.tryInt(setting)) {
                int times = Integer.parseInt(setting);
                if (times > 0) {
                    magicBuff.getContextMap().putVariable("message", event.getMessage());
                    magicBuff.getContextMap().putVariable("self",new PlayerResult(event.getPlayer()));
                    magicBuff.execute(event.getPlayer());
                    event.setCancelled(true);
                    times--;
                    magicBuff.setBuffSetting(times + "");
                } else {
                    MagicPaper.getMagicBuffManager().removeBuff(id, "oracle");
                }
            } else {
                magicBuff.getContextMap().putVariable("message", event.getMessage());
                magicBuff.execute(event.getPlayer());
                event.setCancelled(true);
            }
        }
    }
}
