package cn.originmc.plugins.magicpaper.util.item;

import org.bukkit.inventory.ItemStack;

public class ItemVariable {
    public static String parse(String text, ItemStack itemStack,char sign){
        VariableString vString=new VariableString(text,sign);
        NBTItem nbtItem=new NBTItem(itemStack);
        return text;
    }

}
