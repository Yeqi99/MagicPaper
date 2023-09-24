package cn.originmc.plugins.magicpaper.listener;


import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdditionalItemListener implements Listener {
    @EventHandler
    public void onUseItemClickItem(InventoryClickEvent event){
        if (event.getWhoClicked().getItemOnCursor().getType() == Material.AIR){
            return;
        }
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
            ItemStack beAddedItem = event.getWhoClicked().getItemOnCursor();
            if (beAddedItem.getType() == Material.AIR){
                return;
            }
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack == null || itemStack.getType() == Material.AIR){
                return;
            }
            MagicItem magicItem = new MagicItem(itemStack);
            magicItem.addItem(beAddedItem,"attribute_tag","add_tag");
//            magicItem.additionalItem(beAddedItem,"attribute_tag","add_tag");
            event.getWhoClicked().getInventory().addItem(magicItem.getItemStack());
            event.setCurrentItem(magicItem.getItemStack());
        }
    }

}
