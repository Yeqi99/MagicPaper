package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class PlayerListener implements Listener {
    public PlayerListener(JavaPlugin plugin){
        // bukkit 注册监听器
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        MagicPaper.getSender().sendToAllPlayer(e.getJoinMessage());
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",e.getEventName());
        normalContext.putVariable("self",e.getPlayer());
        normalContext.putVariable("join_message",e.getJoinMessage());
        normalContext.putVariable("event",e);
        MagicPaperTriggerManager.trigger("player_join", normalContext);
        String message= (String) normalContext.getVariable("join_message");
        message= Color.toColor(message);
        e.setJoinMessage(message);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",e.getEventName());
        normalContext.putVariable("self",e.getPlayer());
        normalContext.putVariable("action",e.getAction().name());
        normalContext.putVariable("interaction_point",e.getInteractionPoint());
        normalContext.putVariable("block_face",e.getBlockFace());
        normalContext.putVariable("item",e.getItem());
        normalContext.putVariable("name",e.getMaterial().name());
        normalContext.putVariable("cancelled",e.isCancelled());
        normalContext.putVariable("is_block_in_hand",e.isBlockInHand());
        normalContext.putVariable("has_block",e.hasBlock());
        normalContext.putVariable("has_item",e.hasItem());
        normalContext.putVariable("interacted_block",e.useInteractedBlock().name());
        normalContext.putVariable("use_item_in_hand",e.useItemInHand().name());
        MagicPaperTriggerManager.trigger("player_interact", normalContext);
        e.setCancelled((boolean) normalContext.getVariable("cancelled"));
        e.setUseItemInHand(Event.Result.valueOf((String) normalContext.getVariable("use_item_in_hand")));
        e.setUseInteractedBlock(Event.Result.valueOf((String) normalContext.getVariable("interacted_block")));
    }
}
