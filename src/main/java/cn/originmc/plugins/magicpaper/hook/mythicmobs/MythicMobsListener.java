package cn.originmc.plugins.magicpaper.hook.mythicmobs;

import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent;
import io.lumine.mythic.bukkit.events.MythicDropLoadEvent;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class MythicMobsListener implements Listener {
    @EventHandler
    public void onMythicDropLoad(MythicDropLoadEvent event) {

        if (event.getDropName().equalsIgnoreCase("MagicItemDrop")) {
            event.register(new MagicItemDrop(event.getConfig(), event.getArgument()));
        }
    }

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event) {

        if (event.getMechanicName().equalsIgnoreCase("MagicMechanic")) {
            event.register(new MagicMechanic(event.getConfig()));
        }
    }

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent event) {
        if (event.getConditionName().equalsIgnoreCase("MagicCondition")) {
            event.register(new MagicCondition(event.getConfig()));
        }
    }
}
