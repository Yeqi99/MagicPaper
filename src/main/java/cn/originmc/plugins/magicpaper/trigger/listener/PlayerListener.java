package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Color;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.DoubleResult;
import dev.rgbmc.expression.results.IntegerResult;
import dev.rgbmc.expression.results.StringResult;
import dev.rgbmc.remotekeyboard.events.KeyInputEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
        MagicPaperTriggerManager.trigger("PlayerJoinTrigger", normalContext);
        String message= ((StringResult) normalContext.getVariable("join_message")).getString();
        message= Color.toColor(message);
        e.setJoinMessage(message);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("action",new StringResult(e.getAction().name()));
        normalContext.putVariable("interaction_point",new LocationResult(e.getInteractionPoint()));
        normalContext.putVariable("block_face",e.getBlockFace());
        normalContext.putVariable("item", new ItemStackResult(e.getItem()));
        normalContext.putVariable("name",new StringResult(e.getMaterial().name()));
        normalContext.putVariable("cancelled",e.isCancelled());
        normalContext.putVariable("is_block_in_hand",new BooleanResult(e.isBlockInHand()));
        normalContext.putVariable("has_block",new BooleanResult(e.hasBlock()));
        normalContext.putVariable("has_item",new BooleanResult(e.hasItem()));
        normalContext.putVariable("interacted_block",new StringResult(e.useInteractedBlock().name()));
        normalContext.putVariable("use_item_in_hand",new StringResult(e.useItemInHand().name()));
        MagicPaperTriggerManager.trigger("PlayerInteractTrigger", normalContext);
        e.setCancelled((Boolean) normalContext.getVariable("cancelled"));
        e.setUseItemInHand(Event.Result.valueOf(((StringResult) normalContext.getVariable("use_item_in_hand")).getString()));
        e.setUseInteractedBlock(Event.Result.valueOf(((StringResult) normalContext.getVariable("interacted_block")).getString()));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKeyboard(KeyInputEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(Bukkit.getPlayer(e.getPlayer())));
        normalContext.putVariable("key",new IntegerResult(e.getKeycode()));
        normalContext.putVariable("mod",new IntegerResult(e.getMods()));
        normalContext.putVariable("isPressed",new BooleanResult(e.isPressed()));
        normalContext.putVariable("isRelease",new BooleanResult(e.isRelease()));
        MagicPaperTriggerManager.trigger("PlayerKeyboardTrigger", normalContext);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDrop(PlayerDropItemEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(event.getEventName()));
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        normalContext.putVariable("item",new ItemStackResult(event.getItemDrop().getItemStack()));
        normalContext.putVariable("cancelled",event.isCancelled());
        MagicPaperTriggerManager.trigger("PlayerDropTrigger", normalContext);
        event.setCancelled((Boolean) normalContext.getVariable("cancelled"));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBreak(BlockBreakEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("block",new LocationResult(e.getBlock().getLocation()));
        normalContext.putVariable("cancelled",e.isCancelled());
        MagicPaperTriggerManager.trigger("PlayerBreakTrigger", normalContext);
        e.setCancelled((Boolean) normalContext.getVariable("cancelled"));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPlace(BlockPlaceEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("block",new LocationResult(e.getBlock().getLocation()));
        normalContext.putVariable("cancelled",e.isCancelled());
        MagicPaperTriggerManager.trigger("PlayerPlaceTrigger", normalContext);
        e.setCancelled((Boolean) normalContext.getVariable("cancelled"));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            NormalContext normalContext=new NormalContext();
            normalContext.putVariable("event_name",new StringResult(event.getEventName()));
            normalContext.putVariable("self",new EntityResult(event.getDamager()));
            normalContext.putVariable("entity",new EntityResult(event.getEntity()));
            normalContext.putVariable("damage",event.getDamage());
            normalContext.putVariable("cause",new StringResult(event.getCause().name()));
            normalContext.putVariable("cancelled",event.isCancelled());
            normalContext.putVariable("is_critical",new BooleanResult(event.isCritical()));
            MagicPaperTriggerManager.trigger("EntityDamageTrigger", normalContext);
            event.setCancelled((Boolean) normalContext.getVariable("cancelled"));
            event.setDamage((Double) normalContext.getVariable("damage"));
        }
    }
}
