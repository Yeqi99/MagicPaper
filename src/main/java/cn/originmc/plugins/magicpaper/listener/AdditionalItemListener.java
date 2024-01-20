package cn.originmc.plugins.magicpaper.listener;


import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;



public class AdditionalItemListener implements Listener {
    @EventHandler
    public void onUseItemClickItem(InventoryClickEvent event){
        if (event.getCursor().getType() == Material.AIR){
            return;
        }
        if (event.getClickedInventory() != null) {
            ItemStack beAddedItem = event.getCursor();
            if (beAddedItem.getType() == Material.AIR){
                return;
            }
            ItemStack itemStack = event.getCurrentItem();
            if (itemStack == null || itemStack.getType() == Material.AIR){
                return;
            }
            Player player = (Player) event.getWhoClicked();
            if (checkCoolDown(player)) {
                MagicPaper.getSender().sendToPlayer(player, getCoolDownMessageFromConfig());
                return;
            }
            MagicItem magicItem = new MagicItem(itemStack);
            boolean flag= magicItem.addItemToBore(beAddedItem);
            if (flag){
                magicItem.refresh(true, PlaceholderAPIHook.status,player);
                beAddedItem.setAmount(beAddedItem.getAmount()-1);
                event.getClickedInventory().addItem(magicItem.getItemStack());
                itemStack.setAmount(0);
                event.setCancelled(true);
                addCoolDown(player, getCoolDownTimeFromConfig());
            }
        }
    }
    public static boolean checkCoolDown(Player player) {
        String coolDownID = player.getUniqueId()+".boreAdd";
        return MagicPaper.getCoolDownManager().isCoolDownActive(coolDownID);
    }
    public static void addCoolDown(Player player, long time) {
        String coolDownID = player.getUniqueId()+".boreAdd";
        MagicPaper.getCoolDownManager().addCoolDown(new CoolDown(coolDownID, time));
    }
    public static long getCoolDownTimeFromConfig() {
        return MagicPaper.getInstance().getConfig().getLong("cool-down.bore-add.time",1000);
    }
    public static String getCoolDownMessageFromConfig() {
        return MagicPaper.getInstance().getConfig().getString("cool-down.bore-add.message","&c你的操作过于频繁，请稍后再试");
    }
}
