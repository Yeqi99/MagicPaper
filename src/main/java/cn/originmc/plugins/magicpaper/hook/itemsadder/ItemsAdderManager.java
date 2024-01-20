package cn.originmc.plugins.magicpaper.hook.itemsadder;

import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.CustomMob;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemsAdderManager {
    public static ItemStack getItemStack(String id){
        CustomStack customStack=CustomStack.getInstance(id);
        if (customStack!=null){
            return null;
        }
        return Objects.requireNonNull(CustomStack.getInstance(id)).getItemStack();
    }
    public static boolean isCustomStack(ItemStack itemStack){
        return getCustomStack(itemStack) != null;
    }
    public static CustomStack getCustomStack(String id){
        return CustomStack.getInstance(id);
    }
    public static CustomStack getCustomStack(ItemStack itemStack){
        return CustomStack.byItemStack(itemStack);
    }
    public static CustomBlock getCustomBlock(String id){
        return CustomBlock.getInstance(id);
    }
    public static boolean isCustomBlock(Block block){
        CustomBlock customBlock=CustomBlock.byAlreadyPlaced(block);
        return customBlock != null;
    }
    public static boolean placeCustomBlock(String id, Location location){
        CustomBlock customBlock=getCustomBlock(id);
        if (customBlock==null){
            return false;
        }
        customBlock.place(location);
        return true;
    }
    public static CustomMob spawnCustomMob(String id, Location location){
        return CustomMob.spawn(id,location);
    }
    public static CustomMob getCustomMob(Entity entity){
        return CustomMob.byAlreadySpawned(entity);
    }
}
