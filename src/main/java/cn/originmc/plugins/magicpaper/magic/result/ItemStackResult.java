package cn.originmc.plugins.magicpaper.magic.result;

import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.inventory.ItemStack;

public class ItemStackResult extends FunctionResult {
    private final ItemStack itemStack;

    public ItemStackResult(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
