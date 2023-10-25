package cn.originmc.plugins.magicpaper.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MagicGuiListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getClickedInventory()==null || e.getClickedInventory().getHolder()==null)
            return;
        if(e.getClickedInventory().getHolder() instanceof MagicGui) {
            MagicGui gui = (MagicGui) e.getClickedInventory().getHolder();
            int slot = e.getSlot();
            if (!gui.getUnLimitSlots().contains(slot)) {
                e.setCancelled(true);
            }
            gui.executeSpell(slot, (Player) e.getWhoClicked());
        }
    }
}
