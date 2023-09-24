package cn.originmc.plugins.magicpaper.util.item;


import cn.originmc.plugins.magicpaper.data.manager.ItemFormatManager;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


import java.util.*;

import static org.apache.commons.lang3.StringUtils.remove;

public class MagicItem extends NBTItem {
    public MagicItem(ItemStack itemStack) {
        super(itemStack);
    }

    public MagicItem(String itemStack) {
        super(itemStack);
    }

    public void enableRefreshVar() {
        set("refresh-var", true, "/MagicPaper");
    }

    public void disableRefreshVar() {
        set("refresh-var", false, "/MagicPaper");
    }

    public boolean isRefreshVar() {
        if (!hasKey("refresh-var", "/MagicPaper")) {
            return false;
        }
        return (boolean) get("refresh-var", DataType.BOOLEAN, "/MagicPaper");
    }

    public void setFormat(String id) {
        set("lore-format", id, "/MagicPaper");
    }

    public String getFormat() {
        return (String) get("lore-format", DataType.STRING, "/MagicPaper");
    }

    public void addInfo(String info) {
        if (!hasKey("info", "/ItemInfo")) {
            setInfo(info);
            return;
        }
        String s = (String) get("info", DataType.STRING, "/ItemInfo");
        s = s + "|" + info;
        set("info", s, "/ItemInfo");
    }

    public void setInfo(String info) {
        set("info", info, "/ItemInfo");
    }

    public List<String> getInfo() {
        if (!hasKey("info", "/ItemInfo")) {
            return null;
        }
        String s = (String) get("info", DataType.STRING, "/ItemInfo");
        String[] strs = s.split("\\|");
        List<String> list = new ArrayList<>();
        for (String str : strs) {
            list.add(Color.toColor(str));
        }
        return list;
    }

    public void refresh(boolean refreshVar, boolean refreshPapi, Player player) {
        List<String> lore = new ArrayList<>();
        if (refreshVar) {
            if (!isRefreshVar()) {
                return;
            }
            String format_id = getFormat();
            if (format_id == null) {
                return;
            }
            lore = ItemFormatManager.getFormat(format_id);
            if (lore == null) {
                return;
            }
            lore = refreshVar(lore, '*');
        }
        if (refreshPapi) {
            if (!PlaceholderAPIHook.status) {
                return;
            }
            if (lore == null) {
                return;
            }
            refreshPapi(lore, player);
        }
    }
    public boolean hasBore(String boreAddress){
        return hasSpace(boreAddress);
    }
    public void bore(String boreAddress,int max,String targetAddress,String originAddress){
        addSpace(boreAddress);
        setBoreCurrent(boreAddress,0);
        setBoreMax(boreAddress,max);
        setBoreMapping(boreAddress,targetAddress,originAddress);
    }
    public void removeBore(String boreAddress){
        removeSpace(boreAddress);
    }
    public void setBoreMapping(String boreAddress,String targetAddress,String originAddress){
        set("target-address",targetAddress,boreAddress);
        set("origin-address",originAddress,boreAddress);
    }
    public String getBoreTargetAddress(String boreAddress){
        return (String) get("target-address",DataType.STRING,boreAddress);
    }
    public String getBoreOriginAddress(String boreAddress){
        return (String) get("origin-address",DataType.STRING,boreAddress);
    }
    public void setBoreMax(String boreAddress,int max){
        set("max",max,boreAddress);
    }
    public int getBoreMax(String boreAddress){
        return (int) get("max",DataType.INT,boreAddress);
    }
    public void setBoreCurrent(String boreAddress,int current){
        set("current",current,boreAddress);
    }
    public int getBoreCurrent(String boreAddress){
        return (int) get("current",DataType.INT,boreAddress);
    }
    public void setSupportBoreAddress(String supportAddress){
        set("support-bore-address",supportAddress,"/MagicPaper");
    }
    public String getSupportBoreAddress(){
        return (String) get("support-bore-address",DataType.STRING,"/MagicPaper");
    }
    public boolean addItemToBore(ItemStack itemStack){
        String address = getSupportBoreAddress();
        if (address == null){
            return false;
        }
        if(!hasBore(address)){
            return false;
        }
        int current = getBoreCurrent(address);
        int max = getBoreMax(address);
        if (current >= max){
            return false;
        }
        addItem(itemStack,getBoreTargetAddress(address),getBoreOriginAddress(address));
        set(current+"",itemStack,address);

        current++;
        setBoreCurrent(address,current);
        return true;
    }
    public ItemStack removeItemFromBore(String boreAddress,int index){
        if (boreAddress == null || !hasBore(boreAddress)) {
            return null;
        }

        // 获取当前物品总数
        int totalItems = getBoreCurrent(boreAddress);

        // 检查索引是否合法
        if (index < 0 || index >= totalItems) {
            // 索引无效，不执行任何操作
            return null;
        }
        ItemStack itemStack = (ItemStack) get(index + "",DataType.ITEMSTACK, boreAddress);
        // 依次将后面的物品向前移动覆盖要删除的物品
        for (int i = index; i < totalItems - 1; i++) {
            ItemStack currentItem = (ItemStack) get(i + 1 + "",DataType.ITEMSTACK, boreAddress);
            set(i + "", currentItem, boreAddress);
        }
        removeItem(itemStack,getBoreTargetAddress(boreAddress),getBoreOriginAddress(boreAddress));
        // 移除最后一个物品，相当于删除指定索引的物品
        removeKey((totalItems - 1)+"", boreAddress);
        // 更新物品总数
        setBoreCurrent(boreAddress, totalItems - 1);
        return itemStack;
    }
}
