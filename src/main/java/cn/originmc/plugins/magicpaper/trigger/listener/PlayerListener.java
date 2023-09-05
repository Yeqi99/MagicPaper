package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Color;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;
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
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("join_message",new StringResult(e.getJoinMessage()));
        MagicPaperTriggerManager.trigger("player_join", normalContext);
        String message= ((StringResult) normalContext.getVariable("join_message")).getString();
        message= Color.toColor(message);
        e.setJoinMessage(message);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("action",new StringResult(e.getAction().name()));
        normalContext.putVariable("interaction_point",new LocationResult(e.getInteractionPoint()));
        normalContext.putVariable("block_face",e.getBlockFace());
        normalContext.putVariable("item", new ItemStackResult(e.getItem()));
        normalContext.putVariable("name",new StringResult(e.getMaterial().name()));
        normalContext.putVariable("cancelled",new BooleanResult(e.isCancelled()));
        normalContext.putVariable("is_block_in_hand",new BooleanResult(e.isBlockInHand()));
        normalContext.putVariable("has_block",new BooleanResult(e.hasBlock()));
        normalContext.putVariable("has_item",new BooleanResult(e.hasItem()));
        normalContext.putVariable("interacted_block",new StringResult(e.useInteractedBlock().name()));
        normalContext.putVariable("use_item_in_hand",new StringResult(e.useItemInHand().name()));
        MagicPaperTriggerManager.trigger("player_interact", normalContext);
        e.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).getBoolean());
        e.setUseItemInHand(Event.Result.valueOf(((StringResult) normalContext.getVariable("use_item_in_hand")).getString()));
        e.setUseInteractedBlock(Event.Result.valueOf(((StringResult) normalContext.getVariable("interacted_block")).getString()));
    }
}
