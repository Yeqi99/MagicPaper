package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.util.error.PaperErrorUtils;
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
    private final Map<Integer, Inventory> inv = new HashMap<>();
    private List<Integer> unLimitSlots = new ArrayList<>();
    private ContextMap context = new NormalContext();
    private Map<Character, List<Spell>> spellMap = new HashMap<>();
    private Map<Character, ItemStack> buttonItemMap = new HashMap<>();
    private List<DataButton> dataButtons = new ArrayList<>();
    private boolean isOpen=false;
    private int page = 0;
    private Map<Integer, MagicGuiFormat> formatMap = new HashMap<>();
    private int size = 54;
    private String title;
    private InventoryType inventoryType = null;
    private Player player;

    public MagicGui(int size, String title,Player player) {
        this.size=size;
        this.title=title;
        this.player=player;
    }

    public MagicGui(int size, String title, List<Integer> unLimitSlots,Player player) {
        this.size=size;
        this.title=title;
        this.player=player;
        this.unLimitSlots = unLimitSlots;
    }


    public MagicGui(String title, InventoryType inventoryType,Player player) {
        this.title=title;
        this.inventoryType = inventoryType;
        this.player=player;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv.get(page);
    }

    public void update(Player player) {
        context.putVariable("self", new PlayerResult(player));
        if (inventoryType!=null && inventoryType!=InventoryType.CHEST){
            for (int i = 0;i<getPageSize();i++){
                inv.put(i, Bukkit.createInventory(this, inventoryType, title));
            }
            return;
        }
        dataButtonApply();
        for (Map.Entry<Integer, MagicGuiFormat> entry : formatMap.entrySet()) {
            if (!inv.containsKey(entry.getKey())) {
                inv.put(entry.getKey(), Bukkit.createInventory(this, size, title));
            }
            entry.getValue().apply(inv.get(entry.getKey()));
        }
    }
    public void open(Player player) {
        page = getPage();
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

    public void removeSpellFromIndex(char index, Spell spell) {
        if (!spellMap.containsKey(index)) {
            return;
        }
        spellMap.get(index).remove(spell);
    }

    public void clearSpellFromIndex(char index) {
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
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log= PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToPlayer(player, log);
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
        if (page<0){
            page=0;
        }
        if (page>=getPageSize()){
            page=0;
        }
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

    public int getPageSize(){
        return formatMap.size();
    }
    public Map<Integer, Inventory> getInv() {
        return inv;
    }

    public List<DataButton> getDataButtons() {
        return dataButtons;
    }

    public void setDataButtons(List<DataButton> dataButtons) {
        this.dataButtons = dataButtons;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void putVariable(String key,Object value){
        context.putVariable(key,value);
    }
    public void putInv(int index,Inventory inventory){
        inv.put(index,inventory);
    }

    public void dataButtonApply(){
        for (DataButton dataButton : dataButtons) {
            List<ItemStack> buttons = dataButton.getButtons(context);
            int pageSize = getSize();
            int index = 0;
            format:for (int i = 0; i < pageSize; i++) {
                MagicGuiFormat magicGuiFormat = getFormatMap().get(i);
                if (magicGuiFormat == null) {
                    break;
                }
                if (!inv.containsKey(i)){
                    getInv().put(i, Bukkit.createInventory(this, getSize(), getTitle()));
                }
                Inventory inventory = inv.get(i);
                char[][] format = magicGuiFormat.getFormat();
                for (int j = 0; j < format.length; j++) {
                    for (int k = 0; k < format[j].length; k++) {
                        int slot=j * format[j].length + k;
                        if (slot >= getSize()) {
                            continue format;
                        }
                        NormalDataButton normalDataButton= (NormalDataButton) dataButton;
                        if (format[j][k] == normalDataButton.getButtonId().charAt(0)) {
                            if (index >= buttons.size()) {
                                inv.put(i, inventory);
                                break format;
                            }
                            inventory.setItem(slot, buttons.get(index));
                            index++;
                        }
                        inv.put(i,inventory);
                    }
                }
            }
        }

    }
}
