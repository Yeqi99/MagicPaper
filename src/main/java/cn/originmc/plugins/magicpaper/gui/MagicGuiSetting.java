package cn.originmc.plugins.magicpaper.gui;

import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlElement;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class MagicGuiSetting {
    private String id;
    private int size;
    private String title;
    private List<Integer> unLimitSlots = new ArrayList<>();
    private Map<Character, List<Spell>> spellMap = new HashMap<>();
    private Map<Character, ItemStack> buttonItemMap = new HashMap<>();
    private Map<Integer, MagicGuiFormat> formatMap = new HashMap<>();
    private List<DataButton> dataButtons=new ArrayList<>();
    private InventoryType inventoryType = null;

    public MagicGuiSetting(YamlManager yamlManager, String id) {
        YamlElement yamlElement = yamlManager.getElement(id);
        Configuration yaml = yamlElement.getYml();
        this.id = id;
        for (String key : yaml.getKeys(false)) {
            if (key.contains("type")) {
                inventoryType = InventoryType.valueOf(yaml.getString("type"));
                continue;
            }
            if (key.equalsIgnoreCase("title")) {
                title = yaml.getString(key);
                continue;
            }
            if (key.equalsIgnoreCase("size")) {
                size = yaml.getInt(key);
                continue;
            }
            if (key.equalsIgnoreCase("unLimitSlots")) {
                unLimitSlots = yaml.getIntegerList(key);
                continue;
            }
            if (key.equalsIgnoreCase("format")) {
                List<?> formatList = yaml.getList(key);
                for (int i = 0; i < Objects.requireNonNull(formatList).size(); i++) {
                    List<String> format = (List<String>) formatList.get(i);
                    formatMap.put(i, new MagicGuiFormat(format, buttonItemMap));
                }
                continue;
            }
            if (key.endsWith("_data")){
                List<String> dataList=yaml.getStringList(key+".dataFrom");
                List<String> spellList=yaml.getStringList(key+".spell");
                Spell data=new Spell(dataList, MagicPaper.getMagicManager());
                Spell spell=new Spell(spellList, MagicPaper.getMagicManager());
                DataButton dataButton=new NormalDataButton(data,key);
                dataButtons.add(dataButton);

                List<Spell> spells = spellMap.get(key.charAt(0));
                if (spells == null) {
                    spells = new ArrayList<>();
                }
                spells.add(spell);
                spellMap.put(key.charAt(0), spells);
                continue;
            }
            ConfigurationSection section = yaml.getConfigurationSection(key);
            ItemStack itemStack = new ItemStack(Material.valueOf(Objects.requireNonNull(section).getString("material")));

            if (section.contains("amount")) {
                itemStack.setAmount(section.getInt("amount"));
            }
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (section.contains("display")) {
                itemMeta.setDisplayName(section.getString("display"));
            }
            if (section.contains("lore")) {
                itemMeta.setLore(section.getStringList("lore"));
            }
            if (section.contains("customModelData")) {
                itemMeta.setCustomModelData(section.getInt("customModelData"));
            }
            if (section.contains("spell")) {
                List<String> spellList = section.getStringList("spell");
                Spell spell = new Spell(spellList, MagicPaper.getMagicManager());
                List<Spell> spells = spellMap.get(key.charAt(0));
                if (spells == null) {
                    spells = new ArrayList<>();
                }
                spells.add(spell);
                spellMap.put(key.charAt(0), spells);
            }
            itemStack.setItemMeta(itemMeta);
            buttonItemMap.put(key.charAt(0), itemStack);
        }

    }

    public MagicGui generate(Player player) {
        if (inventoryType != null) {
            if (inventoryType == InventoryType.CHEST) {
                MagicGui magicGui = new MagicGui(size, title, unLimitSlots,player);
                magicGui.setId(id);
                magicGui.setSpellMap(spellMap);
                magicGui.setButtonItemMap(buttonItemMap);
                magicGui.setFormatMap(formatMap);
                magicGui.setDataButtons(dataButtons);
                return magicGui;
            } else {
                MagicGui magicGui = new MagicGui(title, inventoryType,player);
                magicGui.setId(id);
                magicGui.setSpellMap(spellMap);
                magicGui.setButtonItemMap(buttonItemMap);
                magicGui.setFormatMap(formatMap);
                magicGui.setDataButtons(dataButtons);
                return magicGui;
            }
        }
        MagicGui magicGui = new MagicGui(size, title, unLimitSlots,player);
        magicGui.setId(id);
        magicGui.setSpellMap(spellMap);
        magicGui.setButtonItemMap(buttonItemMap);
        magicGui.setFormatMap(formatMap);
        magicGui.setDataButtons(dataButtons);
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

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }

    public List<DataButton> getDataButtons() {
        return dataButtons;
    }

    public void setDataButtons(List<DataButton> dataButtons) {
        this.dataButtons = dataButtons;
    }
}
