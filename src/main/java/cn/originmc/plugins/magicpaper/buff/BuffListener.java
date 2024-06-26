package cn.originmc.plugins.magicpaper.buff;

import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.util.error.PaperErrorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

    @EventHandler
    public void onLeftCharging(PlayerInteractEvent event) {
        if (!event.getAction().isLeftClick()){
            return;
        }
        if (event.getPlayer().isSneaking()){
            return;
        }
        String id = event.getPlayer().getUniqueId().toString();
        if (MagicPaper.getMagicBuffManager().hasBuff(id, "leftCharging")) {
            MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "leftCharging");

            // 检查Buff是否结束
            if (magicBuff.isEnd()) {
                // Buff结束，执行并移除
                magicBuff.execute(event.getPlayer());
                MagicPaper.getMagicBuffManager().removeBuff(id, "leftCharging");
            } else {
                // Buff未结束，更新tick和开始时间
                magicBuff.setTick(magicBuff.getTick() + 1);
                magicBuff.setStartTime(System.currentTimeMillis());

                // 检查并执行Buff中的spell
                if (!magicBuff.getBuffSetting().isEmpty()) {
                    Spell spell = MagicDataManager.getSpell(magicBuff.getBuffSetting());
                    if (spell != null) {
                        SpellContext spellContext = spell.execute(magicBuff.getContextMap());
                        if (spellContext.hasExecuteError()) {;
                            MagicPaper.getSender().sendToPlayer(event.getPlayer(), PaperErrorUtils.getErrorAllLog(spellContext,"&c"));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onRightCharging(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()){
            return;
        }
        if (event.getPlayer().isSneaking()){
            return;
        }
        String id = event.getPlayer().getUniqueId().toString();
        if (MagicPaper.getMagicBuffManager().hasBuff(id, "rightCharging")) {
            MagicBuff magicBuff = MagicPaper.getMagicBuffManager().getMagicBuff(id, "rightCharging");

            // 检查Buff是否结束
            if (magicBuff.isEnd()) {
                // Buff结束，执行并移除
                magicBuff.execute(event.getPlayer());
                MagicPaper.getMagicBuffManager().removeBuff(id, "rightCharging");
            } else {
                // Buff未结束，更新tick和开始时间
                magicBuff.setTick(magicBuff.getTick() + 1);
                magicBuff.setStartTime(System.currentTimeMillis());

                // 检查并执行Buff中的spell
                if (!magicBuff.getBuffSetting().isEmpty()) {
                    Spell spell = MagicDataManager.getSpell(magicBuff.getBuffSetting());
                    if (spell != null) {
                        SpellContext spellContext = spell.execute(magicBuff.getContextMap());
                        if (spellContext.hasExecuteError()) {;
                            MagicPaper.getSender().sendToPlayer(event.getPlayer(), PaperErrorUtils.getErrorAllLog(spellContext,"&c"));
                        }
                    }
                }
            }
        }
    }
}
