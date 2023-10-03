package cn.originmc.plugins.magicpaper.hook.advancedenchantments;

import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import net.advancedplugins.ae.api.AEAPI;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

public class AEItem extends MagicItem {
    public AEItem(ItemStack itemStack) {
        super(itemStack);
    }

    public AEItem(String itemStack) {
        super(itemStack);
    }
    public Map<String,Integer> getAEEnchantments(){
        return AEAPI.getEnchantmentsOnItem(getItemStack());
    }
    public Set<String> getAEEnchantmentKeys(){
        return AEAPI.getEnchantmentsOnItem(getItemStack()).keySet();
    }
    public  boolean hasAEEnchantment(String enchantment){
        return AEAPI.hasCustomEnchant(enchantment,getItemStack());
    }
    public void removeAEEnchantment(String enchantment){
        ItemStack itemStack = getItemStack().clone();
        AEAPI.removeEnchantment(itemStack,enchantment);
        updateOrigin(itemStack);
    }
    public void applyAEEnchantment(String enchantment,int level){
        ItemStack itemStack = getItemStack().clone();
        AEAPI.applyEnchant(enchantment,level,itemStack);
        updateOrigin(itemStack);
    }
    public int getAEEnchantmentLevel(String enchantment){
        return AEAPI.getEnchantLevel(enchantment,getItemStack());
    }

}
