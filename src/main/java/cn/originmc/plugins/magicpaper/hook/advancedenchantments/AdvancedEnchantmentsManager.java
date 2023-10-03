package cn.originmc.plugins.magicpaper.hook.advancedenchantments;

import net.advancedplugins.ae.api.AEAPI;
import org.bukkit.inventory.ItemStack;


import java.util.List;
import java.util.Map;

public class AdvancedEnchantmentsManager {
    public static List<String> getEnchantments() {
        return AEAPI.getAllEnchantments();
    }
    public static boolean isAEEnchantment(String enchantment) {
        return AEAPI.getAllEnchantments().contains(enchantment);
    }
    public static List<String> getAEEnchantmentMaterials(String enchantment) {
        return AEAPI.getMaterialsForEnchantment(enchantment);
    }
    public static Map<String, Integer> getAEEnchantmentOnItem(ItemStack itemStack) {
        return AEAPI.getEnchantmentsOnItem(itemStack);
    }
    public static boolean hasAECustomEnchant(ItemStack itemStack,String enchantment){
        return AEAPI.hasCustomEnchant(enchantment,itemStack);
    }
    public static void removeAEEnchantment(ItemStack itemStack,String enchantment){
        AEAPI.removeEnchantment(itemStack,enchantment);
    }
    public static ItemStack createAEEnchantmentBook(String enchantment,int level,int success,int failure){
        return AEAPI.createEnchantmentBook(enchantment,level,success,failure);
    }
    public static List<String> getAEEnchantmentLore(String enchantment,int level){
        return AEAPI.getEnchantLore(enchantment,level);
    }
    public static int getAEEnchantmentLevel(ItemStack itemStack,String enchantment){
        return AEAPI.getEnchantLevel(enchantment,itemStack);
    }
    public static int getAEEnchantmentMaxLevel(String enchantment){
        return AEAPI.getHighestEnchantmentLevel(enchantment);
    }
    public static void applyAEEnchantment(ItemStack itemStack,String enchantment,int level){
        AEAPI.applyEnchant(enchantment,level,itemStack);
    }
    public static boolean isEnchantLine(String s){
        return AEAPI.isEnchantLine(s);
    }

}
