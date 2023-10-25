package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicGui implements InventoryHolder {
    private String id;
    private Map<Integer, Inventory> inv = new HashMap<>();
    private List<Integer> unLimitSlots = new ArrayList<>();
    private ContextMap context = new NormalContext();
    private Map<Character, List<Spell>> spellMap = new HashMap<>();
    private Map<Character, ItemStack> buttonItemMap = new HashMap<>();
    private boolean isOpen=false;
    private int page = 0;
    private Map<Integer, MagicGuiFormat> formatMap = new HashMap<>();
    private int size = 54;
    private String title="";

    public MagicGui(int size, String title) {
        this.size=size;
        this.title=title;
    }

    public MagicGui(int size, String title, List<Integer> unLimitSlots) {
        this.size=size;
        this.title=title;
        this.unLimitSlots = unLimitSlots;
    }

    public MagicGui(Inventory inv) {
        this.inv.put(0,inv);
    }

    public MagicGui(String title, InventoryType inventoryType) {
        this.title=title;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv.get(page);
    }

    public void open(Player player) {
        page = page % formatMap.size();
        MagicGuiFormat format = formatMap.get(page);
        if(!inv.containsKey(page)){
            inv.put(page, Bukkit.createInventory(this, size, title));
            format.apply(inv.get(page));
        }
        player.openInventory(inv.get(page));
    }

    public void nextPage(Player player) {
        page++;
        open(player);
    }

    public void previousPage(Player player) {
        if (page == 0) {
            open(player);
        }
        page--;
        open(player);
    }

    public void setInv(int index,Inventory inv) {
        this.inv.put(index,inv);
    }

    public void addUnLimitSlot(int slot) {
        unLimitSlots.add(slot);
    }

    public void removeUnLimitSlot(int slot) {
        unLimitSlots.remove(slot);
    }

    public void clearUnLimitSlot() {
        unLimitSlots.clear();
    }

    public List<Integer> getUnLimitSlots() {
        return unLimitSlots;
    }

    public void setUnLimitSlots(List<Integer> unLimitSlots) {
        this.unLimitSlots = unLimitSlots;
    }

    public void addSpellToButton(char id, Spell spell) {
        if (!spellMap.containsKey(id)) {
            spellMap.put(id, new ArrayList<>());
        }
        spellMap.get(id).add(spell);
    }

    public void removeSpellFromIndex(int index, Spell spell) {
        if (!spellMap.containsKey(index)) {
            return;
        }
        spellMap.get(index).remove(spell);
    }

    public void clearSpellFromIndex(int index) {
        if (!spellMap.containsKey(index)) {
            return;
        }
        spellMap.get(index).clear();
    }

    public void executeSpell(int index, Player player) {
        char buttonId = getButtonId(index, player);
        if (buttonId == ' ') {
            return;
        }
        if (!spellMap.containsKey(buttonId)) {
            return;
        }
        context.putVariable("self", new PlayerResult(player));
        for (Spell spell : spellMap.get(buttonId)) {
            SpellContext spellContext = spell.execute(context);
            if (spellContext.hasExecuteError()) {
                player.sendMessage("§c执行时错误: " + spellContext.getExecuteError());
            }
        }
    }


    public Map<Integer, MagicGuiFormat> getFormatMap() {
        return formatMap;
    }

    public void setFormatMap(Map<Integer, MagicGuiFormat> formatMap) {
        this.formatMap = formatMap;
    }


    public void addButton(char id, ItemStack itemStack) {
        getButtonItemMap().put(id, itemStack);
    }

    public void setPageFormat(int page, MagicGuiFormat format) {
        getFormatMap().put(page, format);
    }

    public void addPageFormat(MagicGuiFormat format) {
        setPageFormat(getFormatMap().size(), format);
    }

    public char getButtonId(int index, Player player) {
        MagicGuiFormat format = getFormatMap().get(page);
        if (format == null) {
            return ' ';
        }
        return format.getIndex(index);
    }

    public Map<Character, ItemStack> getButtonItemMap() {
        return buttonItemMap;
    }

    public void setButtonItemMap(Map<Character, ItemStack> buttonItemMap) {
        this.buttonItemMap = buttonItemMap;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ContextMap getContext() {
        return context;
    }

    public void setContext(ContextMap context) {
        this.context = context;
    }

    public Map<Character, List<Spell>> getSpellMap1() {
        return spellMap;
    }

    public void setSpellMap(Map<Character, List<Spell>> spellMap) {
        this.spellMap = spellMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
