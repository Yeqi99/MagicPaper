package cn.originmc.plugins.magicpaper.listener;

import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;


public class ItemTriggerListener implements Listener{
    @EventHandler
    public void onPlayerInteractUseMHItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()){
            return;
        }
        // 检查手持物品是否为空或为 air
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }
        // 处理左键点击事件
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            String id =magicItem.getId();
            if (id==null){
                return;
            }
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+"."+id+".leftClick")){
                CoolDown coolDown = MagicPaper.getCoolDownManager().getCoolDown(player.getUniqueId()+"."+id+".leftClick");
                String message = LangData.get(MagicPaper.getLang(),"item-left-click-cool-down","&c@冷却中...&7(~s)");
                message = message.replace("~",(coolDown.getResultDuration()/1000)+"");
                message = message.replace("@", Objects.requireNonNull(MagicDataManager.getSpellDisplayName(magicItem.getLeftSpell())));
                MagicPaper.getSender().sendToPlayer(player, message);
                return;
            }
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
            Objects.requireNonNull(spell).execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+"."+id+".leftClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }

        // 处理右键点击事件
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            String id =magicItem.getId();
            if (id==null){
                return;
            }
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+"."+id+".rightClick")){
                CoolDown coolDown = MagicPaper.getCoolDownManager().getCoolDown(player.getUniqueId()+"."+id+".rightClick");
                String message = LangData.get(MagicPaper.getLang(),"item-right-click-cool-down","&c@冷却中...&7(~s)");
                message = message.replace("~",(coolDown.getResultDuration()/1000)+"");
                message = message.replace("@", Objects.requireNonNull(MagicDataManager.getSpellDisplayName(magicItem.getRightSpell())));
                MagicPaper.getSender().sendToPlayer(player, message);
                return;
            }
            if (!magicItem.getRightSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getRightSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getRightSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("right"));
            Objects.requireNonNull(spell).execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+"."+id+".rightClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerShiftInteractUseMHItem(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.isSneaking()){
            return;
        }
        // 检查手持物品是否为空或为 air
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            return;
        }

        // 处理蹲下加左键事件
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            String id =magicItem.getId();
            if (id==null){
                return;
            }
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+"."+id+".shiftLeftClick")){
                CoolDown coolDown = MagicPaper.getCoolDownManager().getCoolDown(player.getUniqueId()+"."+id+".shiftLeftClick");
                String message = LangData.get(MagicPaper.getLang(),"item-shift-left-click-cool-down","&c@冷却中...&7(~s)");
                message = message.replace("~",(coolDown.getResultDuration()/1000)+"");
                message = message.replace("@", Objects.requireNonNull(MagicDataManager.getSpellDisplayName(magicItem.getShiftLeftSpell())));
                MagicPaper.getSender().sendToPlayer(player, message);
                return;
            }
            if (!magicItem.getShiftLeftSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getShiftLeftSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getShiftLeftSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("shiftLeft"));
            Objects.requireNonNull(spell).execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+"."+id+".shiftLeftClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
            return;
        }

        // 处理蹲下加右键事件
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
            String id =magicItem.getId();
            if (id==null){
                return;
            }
            if(MagicPaper.getCoolDownManager().isCoolDownActive(player.getUniqueId()+"."+id+".shiftRightClick")){
                CoolDown coolDown = MagicPaper.getCoolDownManager().getCoolDown(player.getUniqueId()+"."+id+".shiftRightClick");
                String message = LangData.get(MagicPaper.getLang(),"item-shift-right-click-cool-down","&c@冷却中...&7(~s)");
                message = message.replace("~",(coolDown.getResultDuration()/1000)+"");
                message = message.replace("@", Objects.requireNonNull(MagicDataManager.getSpellDisplayName(magicItem.getShiftRightSpell())));
                MagicPaper.getSender().sendToPlayer(player, message);
                return;
            }
            if (!magicItem.getShiftRightSpellSlot().equalsIgnoreCase("mh")){
                return;
            }
            String spellName= magicItem.getShiftRightSpell();
            if (spellName==null){
                return;
            }
            if(!MagicDataManager.isSpell(spellName)){
                return;
            }
            long coolDownTime=magicItem.getShiftRightSpellCoolDown();
            Spell spell=MagicDataManager.getSpell(spellName);
            NormalContext context=new NormalContext();
            MagicPaper.importSpell(context);
            context.putVariable("self",new PlayerResult(player));
            context.putVariable("item",new ItemStackResult(player.getInventory().getItemInMainHand()));
            context.putVariable("action",new StringResult("shiftRight"));
            Objects.requireNonNull(spell).execute(context);
            CoolDown coolDown=new CoolDown(player.getUniqueId()+"."+id+".shiftRightClick",coolDownTime);
            MagicPaper.getCoolDownManager().addCoolDown(coolDown);
            event.setCancelled(true);
        }
    }
}
