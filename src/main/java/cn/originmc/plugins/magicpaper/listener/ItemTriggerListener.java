package cn.originmc.plugins.magicpaper.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;



public class ItemTriggerListener implements Listener{
    @EventHandler
    public void onPlayerInteractUseMHItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // 检查手持物品是否为空或为 air
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }

        // 处理左键点击事件
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+".leftClick")){
                return;
            }
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            if (!magicItem.getLeftSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getLeftSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getLeftSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("left"));
            spell.execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+".leftClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }

        // 处理右键点击事件
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+".rightClick")){
                return;
            }
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            if (!magicItem.getRightSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getLeftSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getLeftSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("right"));
            spell.execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+".rightClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }

        // 处理蹲下加左键事件
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK && player.isSneaking()) {
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+".shiftLeftClick")){
                return;
            }
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            if (!magicItem.getShiftLeftSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getLeftSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getLeftSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("shiftLeft"));
            spell.execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+".shiftLeftClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }

        // 处理蹲下加右键事件
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK && player.isSneaking()) {
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+".shiftRightClick")){
                return;
            }
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            if (!magicItem.getShiftRightSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getLeftSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getLeftSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("shiftRight"));
            spell.execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+".shiftRightClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }
    }
}
