package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.object.ContextMap;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface DataButton {
    List<ItemStack> getButtons(ContextMap contextMap);
    char getButtonChar();

}
