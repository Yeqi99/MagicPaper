package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import org.bukkit.inventory.ItemStack;

public class ItemStackResult extends ObjectResult {

    public ItemStackResult(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemStack getItemStack() {
        return (ItemStack) getObject();
    }
    public String getName() {
        return "ItemStack";
    }
}
