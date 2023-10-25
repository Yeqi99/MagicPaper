package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlElement;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicGuiSetting {
    private String id;
    private int size;
    private String title;
    private List<Integer> unLimitSlots = new ArrayList<>();
    private Map<Character, List<Spell>> spellMap = new HashMap<>();
    private Map<Character, ItemStack> buttonItemMap = new HashMap<>();
    private Map<Integer, MagicGuiFormat> formatMap = new HashMap<>();

    public MagicGuiSetting(YamlManager yamlManager, String id) {
        YamlElement yamlElement=yamlManager.getElement(id);
        Configuration yaml=yamlElement.getYml();
        this.id = id;
        for (String key : yaml.getKeys(false)) {
            if (key.equalsIgnoreCase("title")){
                title=yaml.getString(key);
                continue;
            }
            if (key.equalsIgnoreCase("size")){
                size=yaml.getInt(key);
                continue;
            }
            if (key.equalsIgnoreCase("unLimitSlots")){
                unLimitSlots=yaml.getIntegerList(key);
                continue;
            }
            if(key.equalsIgnoreCase("format")){
                List<?> formatList = yaml.getList(key);
                for(int i=0;i<formatList.size();i++){
                    List<String> format = (List<String>) formatList.get(i);
                    formatMap.put(i,new MagicGuiFormat(format,buttonItemMap));
                }
                continue;
            }

            ConfigurationSection section= yaml.getConfigurationSection(key);
            ItemStack itemStack = new ItemStack(Material.valueOf(section.getString("material")));

            if (section.contains("amount")){
                itemStack.setAmount(section.getInt("amount"));
            }
            ItemMeta itemMeta=itemStack.getItemMeta();
            if (section.contains("display")){
                itemMeta.setDisplayName(section.getString("display"));
            }
            if (section.contains("lore")){
                itemMeta.setLore(section.getStringList("lore"));
            }
            if (section.contains("customModelData")){
                itemMeta.setCustomModelData(section.getInt("customModelData"));
            }
            if (section.contains("spell")){
                List<String> spellList = section.getStringList("spell");
                Spell spell=new Spell(spellList, MagicPaper.getMagicManager());
                List<Spell> spells=spellMap.get(key.charAt(0));
                if (spells==null){
                    spells=new ArrayList<>();
                }
                spells.add(spell);
                spellMap.put(key.charAt(0),spells);
            }
            itemStack.setItemMeta(itemMeta);
            buttonItemMap.put(key.charAt(0),itemStack);
        }

    }
    public MagicGui generate() {
        MagicGui magicGui = new MagicGui(size, title, unLimitSlots);
        magicGui.setId(id);
        magicGui.setSpellMap(spellMap);
        magicGui.setButtonItemMap(buttonItemMap);
        magicGui.setFormatMap(formatMap);
        return magicGui;
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

    public List<Integer> getUnLimitSlots() {
        return unLimitSlots;
    }

    public void setUnLimitSlots(List<Integer> unLimitSlots) {
        this.unLimitSlots = unLimitSlots;
    }

    public Map<Character, List<Spell>> getSpellMap() {
        return spellMap;
    }

    public void setSpellMap(Map<Character, List<Spell>> spellMap) {
        this.spellMap = spellMap;
    }

    public Map<Character, ItemStack> getButtonItemMap() {
        return buttonItemMap;
    }

    public void setButtonItemMap(Map<Character, ItemStack> buttonItemMap) {
        this.buttonItemMap = buttonItemMap;
    }

    public Map<Integer, MagicGuiFormat> getFormatMap() {
        return formatMap;
    }

    public void setFormatMap(Map<Integer, MagicGuiFormat> formatMap) {
        this.formatMap = formatMap;
    }
}
