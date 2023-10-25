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
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicGui implements InventoryHolder {
    private Inventory inv;
    private List<Integer> unLimitSlots = new ArrayList<>();
    private Map<String,ContextMap> context=new HashMap<>();
    private Map<Integer,List<Spell>> spellMap=new HashMap<>();

    public MagicGui(int size, String title) {
        inv = Bukkit.createInventory(this, size, title);
    }

    public MagicGui(int size, String title, List<Integer> unLimitSlots) {
        inv = Bukkit.createInventory(this, size, title);
        this.unLimitSlots = unLimitSlots;
    }

    public MagicGui(Inventory inv) {
        this.inv = inv;
    }

    public MagicGui(String title, InventoryType inventoryType) {
        inv = Bukkit.createInventory(this, inventoryType, title);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inv;
    }

    public void open(Player player) {
        player.openInventory(inv);
    }

    public ContextMap getPlayerContext(Player player){
        if(!context.containsKey(player.getUniqueId().toString())){
            context.put(player.getUniqueId().toString(),new NormalContext());
        }
        return context.get(player.getUniqueId().toString());
    }


    public void setInv(Inventory inv) {
        this.inv = inv;
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

    public Map<String, ContextMap> getContext() {
        return context;
    }

    public void setContext(Map<String, ContextMap> context) {
        this.context = context;
    }

    public Map<Integer, List<Spell>> getSpellMap() {
        return spellMap;
    }

    public void setSpellMap(Map<Integer, List<Spell>> spellMap) {
        this.spellMap = spellMap;
    }
    public void addSpellToIndex(int index,Spell spell){
        if (!spellMap.containsKey(index)){
            spellMap.put(index,new ArrayList<>());
        }
        spellMap.get(index).add(spell);
    }
    public void removeSpellFromIndex(int index,Spell spell){
        if (!spellMap.containsKey(index)){
            return;
        }
        spellMap.get(index).remove(spell);
    }
    public void clearSpellFromIndex(int index){
        if (!spellMap.containsKey(index)){
            return;
        }
        spellMap.get(index).clear();
    }
    public void executeSpell(int index,Player player){
        if (!spellMap.containsKey(index)){
            return;
        }
        ContextMap contextMap=getPlayerContext(player);
        contextMap.putVariable("self",new PlayerResult(player));
        for (Spell spell : spellMap.get(index)) {
            SpellContext spellContext = spell.execute(contextMap);
            if (spellContext.hasExecuteError()){
                player.sendMessage("§c执行时错误: "+spellContext.getExecuteError());
            }
        }
    }
}
