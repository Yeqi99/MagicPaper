package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
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
        String message= normalContext.getVariable("join_message").toString();
        e.setJoinMessage(message);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("action",new StringResult(e.getAction().name()));
        normalContext.putVariable("interaction_point",new LocationResult(e.getInteractionPoint()));
        normalContext.putVariable("block_face",new StringResult(e.getBlockFace().name()));
        normalContext.putVariable("item", new ItemStackResult(e.getItem()));
        normalContext.putVariable("name",new StringResult(e.getMaterial().name()));
        normalContext.putVariable("cancelled",new BooleanResult(e.isCancelled()));
        normalContext.putVariable("is_block_in_hand",new BooleanResult(e.isBlockInHand()));
        normalContext.putVariable("has_block",new BooleanResult(e.hasBlock()));
        normalContext.putVariable("has_item",new BooleanResult(e.hasItem()));
        normalContext.putVariable("interacted_block",new StringResult(e.useInteractedBlock().name()));
        normalContext.putVariable("use_item_in_hand",new StringResult(e.useItemInHand().name()));
        MagicPaperTriggerManager.trigger("PlayerInteractTrigger", normalContext);
        e.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
        e.setUseItemInHand(Event.Result.valueOf(normalContext.getVariable("use_item_in_hand").toString()));
        e.setUseInteractedBlock(Event.Result.valueOf(normalContext.getVariable("interacted_block").toString()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDrop(PlayerDropItemEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(event.getEventName()));
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        normalContext.putVariable("item",new ItemStackResult(event.getItemDrop().getItemStack()));
        normalContext.putVariable("cancelled",new BooleanResult(event.isCancelled()));
        MagicPaperTriggerManager.trigger("PlayerDropTrigger", normalContext);
        event.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBreak(BlockBreakEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("btype",new StringResult(e.getBlock().getType().name()));
        normalContext.putVariable("cancelled",new BooleanResult(e.isCancelled()));
        MagicPaperTriggerManager.trigger("PlayerBreakTrigger", normalContext);
        e.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));

    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPlace(BlockPlaceEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(e.getPlayer()));
        normalContext.putVariable("btype",new StringResult(e.getBlock().getType().name()));
        normalContext.putVariable("cancelled",new BooleanResult(e.isCancelled()));
        MagicPaperTriggerManager.trigger("PlayerPlaceTrigger", normalContext);
        e.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockDrop(BlockDropItemEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        for (Item item : event.getItems()) {
            normalContext.putVariable("item",new ItemStackResult(item.getItemStack()));
            MagicPaperTriggerManager.trigger("ItemDropTrigger", normalContext);
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            NormalContext normalContext=new NormalContext();
            normalContext.putVariable("event_name",new StringResult(event.getEventName()));
            normalContext.putVariable("self",new EntityResult(event.getDamager()));
            normalContext.putVariable("entity",new EntityResult(event.getEntity()));
            normalContext.putVariable("damage",new NumberResult(event.getDamage()));
            normalContext.putVariable("cause",new StringResult(event.getCause().name()));
            normalContext.putVariable("cancelled",new BooleanResult(event.isCancelled()));
            normalContext.putVariable("is_critical",new BooleanResult(event.isCritical()));
            MagicPaperTriggerManager.trigger("EntityDamageTrigger", normalContext);
            event.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
            event.setDamage(((NumberResult) normalContext.getVariable("damage")).toDouble());
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(event.getEventName()));
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        normalContext.putVariable("from",new LocationResult(event.getFrom()));
        normalContext.putVariable("to",new LocationResult(event.getTo()));
        normalContext.putVariable("to_world_str",new StringResult(event.getTo().getWorld().getName()));
        normalContext.putVariable("from_world_str",new StringResult(event.getFrom().getWorld().getName()));
        normalContext.putVariable("cause",new StringResult(event.getCause().name()));
        normalContext.putVariable("cancelled",new BooleanResult(event.isCancelled()));
        MagicPaperTriggerManager.trigger("PlayerTeleportTrigger", normalContext);
        event.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(event.getEventName()));
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        normalContext.putVariable("message",new StringResult(event.getMessage()));
        normalContext.putVariable("format",new StringResult(event.getFormat()));
        normalContext.putVariable("cancelled",new BooleanResult(event.isCancelled()));
        MagicPaperTriggerManager.trigger("AsyncPlayerChatTrigger", normalContext);
        event.setCancelled(((BooleanResult) normalContext.getVariable("cancelled")).toBoolean(false));
        event.setFormat(normalContext.getVariable("format").toString());
        event.setMessage(normalContext.getVariable("message").toString());
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(event.getEventName()));
        normalContext.putVariable("self",new PlayerResult(event.getPlayer()));
        normalContext.putVariable("location",new LocationResult(event.getRespawnLocation()));
        MagicPaperTriggerManager.trigger("PlayerRespawnTrigger", normalContext);
        event.setRespawnLocation(((LocationResult) normalContext.getVariable("location")).getLocation());
    }

}
