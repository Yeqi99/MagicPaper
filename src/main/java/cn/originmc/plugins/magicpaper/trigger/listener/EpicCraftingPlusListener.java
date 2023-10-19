package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import ecp.ajneb97.api.EpicCraftingsCraftEvent;
import ecp.ajneb97.api.EpicCraftingsPlaceClickEvent;
import ecp.ajneb97.api.EpicCraftingsPreCraftEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EpicCraftingPlusListener implements Listener {
    @EventHandler
    public void onEpicCraftingPlusPreCraft(EpicCraftingsPreCraftEvent event) {
        NormalContext normalContext=new NormalContext();
        MagicPaperTriggerManager.trigger("EpicCraftingsPreCraftTrigger",normalContext);
    }

    @EventHandler
    public void onEpicCraftingPlusCraft(EpicCraftingsCraftEvent event) {
        NormalContext normalContext=new NormalContext();
        MagicPaperTriggerManager.trigger("EpicCraftingsCraftTrigger",normalContext);
    }

    @EventHandler
    public void onEpicCraftingPlaceClick(EpicCraftingsPlaceClickEvent event) {
        NormalContext normalContext=new NormalContext();
        MagicPaperTriggerManager.trigger("EpicCraftingsPlaceClickTrigger",normalContext);
    }
}
