package cn.originmc.plugins.magicpaper.util.item;


import cn.originmc.plugins.magicpaper.data.manager.ItemFormatDataManager;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
            String formatId = getFormat();
            if (formatId == null) {
                return;
            }
            lore = ItemFormatDataManager.getFormat(formatId);
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

    public void refresh(boolean refreshVar, boolean refreshPapi, Player player, String formatId) {
        List<String> lore = new ArrayList<>();
        if (refreshVar) {
            if (!isRefreshVar()) {
                return;
            }
            if (formatId == null) {
                return;
            }
            lore = ItemFormatDataManager.getFormat(formatId);
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

    public void unlimitedRefresh(boolean refreshVar, boolean refreshPapi, Player player, List<String> format) {
        List<String> lore = format;
        if (refreshVar) {
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

    public boolean hasBore(String boreAddress) {
        return hasSpace(boreAddress);
    }

    public void bore(String boreAddress, int max, String targetAddress, String originAddress) {
        addSpace(boreAddress);
        setBoreCurrent(boreAddress, 0);
        setBoreMax(boreAddress, max);
        setBoreMapping(boreAddress, targetAddress, originAddress);
    }

    public void removeBore(String boreAddress) {
        removeSpace(boreAddress);
    }

    public void setBoreMapping(String boreAddress, String targetAddress, String originAddress) {
        set("target-address", targetAddress, boreAddress);
        set("origin-address", originAddress, boreAddress);
    }

    public String getBoreTargetAddress(String boreAddress) {
        return (String) get("target-address", DataType.STRING, boreAddress);
    }

    public String getBoreOriginAddress(String boreAddress) {
        return (String) get("origin-address", DataType.STRING, boreAddress);
    }

    public void setBoreMax(String boreAddress, int max) {
        set("max", max, boreAddress);
    }

    public int getBoreMax(String boreAddress) {
        return (int) get("max", DataType.INT, boreAddress);
    }

    public void setBoreCurrent(String boreAddress, int current) {
        set("current", current, boreAddress);
    }

    public int getBoreCurrent(String boreAddress) {
        return (int) get("current", DataType.INT, boreAddress);
    }

    public void setSupportBoreAddress(String supportAddress) {
        set("support-bore-address", supportAddress, "/MagicPaper");
    }

    public String getSupportBoreAddress() {
        return (String) get("support-bore-address", DataType.STRING, "/MagicPaper");
    }

    public boolean addItemToBore(ItemStack itemStack) {
        itemStack = itemStack.clone();
        itemStack.setAmount(1);
        MagicItem magicItem = new MagicItem(itemStack);
        String address = magicItem.getSupportBoreAddress();
        if (address == null) {
            return false;
        }
        if (!hasBore(address)) {
            return false;
        }
        int current = getBoreCurrent(address);
        int max = getBoreMax(address);
        if (current >= max) {
            return false;
        }
        addItem(itemStack, getBoreTargetAddress(address), getBoreOriginAddress(address));
        set(current + "", itemStack, address);

        current++;
        setBoreCurrent(address, current);
        return true;
    }

    public ItemStack removeItemFromBore(String boreAddress, int index) {
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
        ItemStack itemStack = (ItemStack) get(index + "", DataType.ITEMSTACK, boreAddress);
        // 依次将后面的物品向前移动覆盖要删除的物品
        for (int i = index; i < totalItems - 1; i++) {
            ItemStack currentItem = (ItemStack) get(i + 1 + "", DataType.ITEMSTACK, boreAddress);
            set(i + "", currentItem, boreAddress);
        }
        removeItem(itemStack, getBoreTargetAddress(boreAddress), getBoreOriginAddress(boreAddress));
        // 移除最后一个物品，相当于删除指定索引的物品
        removeKey((totalItems - 1) + "", boreAddress);
        // 更新物品总数
        setBoreCurrent(boreAddress, totalItems - 1);
        itemStack.setAmount(1);
        return itemStack;
    }

    public void setLeftSpell(String spellName, long coolDown, String slot) {
        set("left-spell", spellName, "/SpellExecute");
        set("left-spell-cool-down", coolDown, "/SpellExecute");
        set("left-spell-slot", slot, "/SpellExecute");
    }

    public boolean hasLeftSpell() {
        return hasKey("left-spell", "/SpellExecute");
    }

    public String getLeftSpell() {
        if (!hasKey("left-spell", "/SpellExecute")) {
            return null;
        }
        return (String) get("left-spell", DataType.STRING, "/SpellExecute");
    }

    public long getLeftSpellCoolDown() {
        return (long) get("left-spell-cool-down", DataType.LONG, "/SpellExecute");
    }

    public String getLeftSpellSlot() {
        return (String) get("left-spell-slot", DataType.STRING, "/SpellExecute");
    }

    public void setRightSpell(String spellName, long coolDown, String slot) {
        set("right-spell", spellName, "/SpellExecute");
        set("right-spell-cool-down", coolDown, "/SpellExecute");
        set("right-spell-slot", slot, "/SpellExecute");
    }

    public boolean hasRightSpell() {
        return hasKey("right-spell", "/SpellExecute");
    }

    public String getRightSpell() {
        if (!hasKey("right-spell", "/SpellExecute")) {
            return null;
        }
        return (String) get("right-spell", DataType.STRING, "/SpellExecute");
    }

    public long getRightSpellCoolDown() {
        return (long) get("right-spell-cool-down", DataType.LONG, "/SpellExecute");
    }

    public String getRightSpellSlot() {
        return (String) get("right-spell-slot", DataType.STRING, "/SpellExecute");
    }

    public void setShiftLeftSpell(String spellName, long coolDown, String slot) {
        set("shift-left-spell", spellName, "/SpellExecute");
        set("shift-left-spell-cool-down", coolDown, "/SpellExecute");
        set("shift-left-spell-slot", slot, "/SpellExecute");
    }

    public boolean hasShiftLeftSpell() {
        return hasKey("shift-left-spell", "/SpellExecute");
    }

    public String getShiftLeftSpell() {
        if (!hasKey("shift-left-spell", "/SpellExecute")) {
            return null;
        }
        return (String) get("shift-left-spell", DataType.STRING, "/SpellExecute");
    }

    public long getShiftLeftSpellCoolDown() {
        return (long) get("shift-left-spell-cool-down", DataType.LONG, "/SpellExecute");
    }

    public String getShiftLeftSpellSlot() {
        return (String) get("shift-left-spell-slot", DataType.STRING, "/SpellExecute");
    }

    public void setShiftRightSpell(String spellName, long coolDown, String slot) {
        set("shift-right-spell", spellName, "/SpellExecute");
        set("shift-right-spell-cool-down", coolDown, "/SpellExecute");
        set("shift-right-spell-slot", slot, "/SpellExecute");
    }

    public boolean hasShiftRightSpell() {
        return hasKey("shift-right-spell", "/SpellExecute");
    }

    public String getShiftRightSpell() {
        if (!hasKey("shift-right-spell", "/SpellExecute")) {
            return null;
        }
        return (String) get("shift-right-spell", DataType.STRING, "/SpellExecute");
    }

    public long getShiftRightSpellCoolDown() {
        return (long) get("shift-right-spell-cool-down", DataType.LONG, "/SpellExecute");
    }

    public String getShiftRightSpellSlot() {
        return (String) get("shift-right-spell-slot", DataType.STRING, "/SpellExecute");
    }

    public void setId(String id) {
        set("id", id, "/MagicPaper");
    }

    public String getId() {
        return (String) get("id", DataType.STRING, "/MagicPaper");
    }

    public boolean hasId() {
        return hasKey("id", "/MagicPaper");
    }

    public void setItemType(String type) {
        set("type", type, "/MagicPaper");
    }

    public String getItemType() {
        return (String) get("type", DataType.STRING, "/MagicPaper");
    }

    public boolean hasItemType() {
        return hasKey("type", "/MagicPaper");
    }

    public String getLeftSpellDisplay() {
        String spellName = getLeftSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDisplayName(spellName);
    }

    public List<String> getLeftSpellDescription() {
        String spellName = getLeftSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDescription(spellName);
    }

    public String getRightSpellDisplay() {
        String spellName = getRightSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDisplayName(spellName);
    }

    public List<String> getRightSpellDescription() {
        String spellName = getRightSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDescription(spellName);
    }

    public String getShiftLeftSpellDisplay() {
        String spellName = getShiftLeftSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDisplayName(spellName);
    }

    public List<String> getShiftLeftSpellDescription() {
        String spellName = getShiftLeftSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDescription(spellName);
    }

    public String getShiftRightSpellDisplay() {
        String spellName = getShiftRightSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDisplayName(spellName);
    }

    public List<String> getShiftRightSpellDescription() {
        String spellName = getShiftRightSpell();
        if (spellName == null) {
            return null;
        }
        return MagicDataManager.getSpellDescription(spellName);
    }

    public double getAttributeValueBySlot(String attributeName, String slot) {
        Object object =get(attributeName,DataType.DOUBLE,"attribute-"+slot);
        if (object==null){
            return 0;
        }else {
            return (double) object;
        }
    }

    public void setAttributeValueBySlot(String attributeName, String slot,double value){
        set(attributeName,value,"attribute-"+slot);
    }


}
