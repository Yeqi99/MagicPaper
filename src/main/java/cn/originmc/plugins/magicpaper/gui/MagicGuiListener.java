package cn.originmc.plugins.magicpaper.gui;

import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class MagicGuiListener implements Listener {
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent e){
        if(e.getInventory().getHolder()==null){
            return;
        }

        if(e.getInventory().getHolder() instanceof MagicGui) {
            if (e.getAction()== InventoryAction.MOVE_TO_OTHER_INVENTORY){
                e.setCancelled(true);
            }
            MagicGui gui = (MagicGui) e.getInventory().getHolder();
            int slot = e.getSlot();
            if (e.getClickedInventory().getHolder() instanceof MagicGui){
                if (!gui.getUnLimitSlots().contains(slot)) {
                    e.setCancelled(true);
                }
            }
            gui.getContext().putVariable("slot", slot);
            gui.getContext().putVariable("item", new ItemStackResult(e.getCurrentItem()));
            gui.getContext().putVariable("click", e.getClick().name());
            gui.getContext().putVariable("action", e.getAction().name());
            gui.executeSpell(slot, (Player) e.getWhoClicked());
        }
    }
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onDrag(InventoryDragEvent e){
        if(e.getInventory().getHolder()==null)
            return;
        if(e.getInventory().getHolder() instanceof MagicGui) {
            e.setCancelled(true);
        }
    }
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().getHolder()==null)
            return;
        if(e.getInventory().getHolder() instanceof MagicGui) {
            MagicGui gui = (MagicGui) e.getInventory().getHolder();
            gui.setOpen(false);
        }
    }

    @EventHandler(priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onOpen(InventoryOpenEvent e){
        if(e.getInventory().getHolder()==null)
            return;
        if(e.getInventory().getHolder() instanceof MagicGui) {
            MagicGui gui = (MagicGui) e.getInventory().getHolder();
            gui.setOpen(true);
        }
    }
}
