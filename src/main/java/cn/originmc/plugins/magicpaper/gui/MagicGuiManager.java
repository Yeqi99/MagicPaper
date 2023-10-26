package cn.originmc.plugins.magicpaper.gui;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO 完善方法并封装为语义
public class MagicGuiManager {
    private Map<String, List<MagicGui>> playerGuiMap = new HashMap<>();
    private List<MagicGuiSetting> magicGuiSettings = new ArrayList<>();

    public MagicGuiManager() {

    }
    public void register(MagicGuiSetting magicGuiSetting) {
        magicGuiSettings.add(magicGuiSetting);
    }
    public void clearRegister() {
        magicGuiSettings.clear();
    }
    public MagicGui getGui(Player player, String id) {
        List<MagicGui> magicGuis = playerGuiMap.get(player.getUniqueId().toString());
        if (magicGuis == null) {
            magicGuis=new ArrayList<>();
        }
        for (MagicGui magicGui : magicGuis) {
            if (magicGui.getId().equalsIgnoreCase(id)){
                return magicGui;
            }
        }
        MagicGuiSetting magicGuiSetting = getGuiSetting(id);
        if (magicGuiSetting == null) {
            return null;
        }
        MagicGui magicGui = magicGuiSetting.generate();
        magicGuis.add(magicGui);
        playerGuiMap.put(player.getUniqueId().toString(), magicGuis);
        return magicGui;
    }


    public MagicGuiSetting getGuiSetting(String id) {
        for (MagicGuiSetting magicGuiSetting : magicGuiSettings) {
            if (magicGuiSetting.getId().equals(id)) {
                return magicGuiSetting;
            }
        }
        return null;
    }


    public List<MagicGuiSetting> getMagicGuiSettings() {
        return magicGuiSettings;
    }

    public void setMagicGuiSettings(List<MagicGuiSetting> magicGuiSettings) {
        this.magicGuiSettings = magicGuiSettings;
    }

    public Map<String, List<MagicGui>> getPlayerGuiMap() {
        return playerGuiMap;
    }

    public void setPlayerGuiMap(Map<String, List<MagicGui>> playerGuiMap) {
        this.playerGuiMap = playerGuiMap;
    }
}
