package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.util.text.Color;
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
        normalContext.putVariable("self",e.getPlayer());
        normalContext.putVariable("action",e.getAction().name());
        normalContext.putVariable("interaction_point",e.getInteractionPoint());
        normalContext.putVariable("block_face",e.getBlockFace());
        normalContext.putVariable("is_hand", Objects.requireNonNull(e.getHand()).isHand());
        normalContext.putVariable("is_armor",e.getHand().isArmor());
        MagicPaperTriggerManager.trigger("player_interact", normalContext);

    }
}
