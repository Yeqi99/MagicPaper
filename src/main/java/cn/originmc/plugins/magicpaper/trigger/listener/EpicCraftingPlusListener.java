package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import ecp.ajneb97.api.EpicCraftingsCraftEvent;
import ecp.ajneb97.api.EpicCraftingsPlaceClickEvent;
import ecp.ajneb97.api.EpicCraftingsPreCraftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EpicCraftingPlusListener implements Listener {
    public EpicCraftingPlusListener(JavaPlugin plugin){
        // bukkit 注册监听器
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler
    public void onEpicCraftingPlusPreCraft(EpicCraftingsPreCraftEvent event) {
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",event.getEventName());
        normalContext.putVariable("self",event.getPlayer());
        MagicPaperTriggerManager.trigger("EpicCraftingsPreCraftTrigger",normalContext);
    }

    @EventHandler
    public void onEpicCraftingPlusCraft(EpicCraftingsCraftEvent event) {
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",event.getEventName());
        normalContext.putVariable("self",event.getPlayer());
        MagicPaperTriggerManager.trigger("EpicCraftingsCraftTrigger",normalContext);
    }

    @EventHandler
    public void onEpicCraftingPlaceClick(EpicCraftingsPlaceClickEvent event) {
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",event.getEventName());
        normalContext.putVariable("self",event.getPlayer());
        MagicPaperTriggerManager.trigger("EpicCraftingsPlaceClickTrigger",normalContext);
    }
}
