package cn.originmc.plugins.magicpaper.data.gui;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.gui.MagicGuiManager;
import cn.originmc.plugins.magicpaper.gui.MagicGuiSetting;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class GuiData {
    public static YamlManager yamlManager;

    public static void load() {
        yamlManager = new YamlManager(MagicPaper.getInstance(), "gui", true);
        for (String s : yamlManager.getIdList()) {
            MagicGuiSetting setting = new MagicGuiSetting(yamlManager, s);
            MagicPaper.getMagicGuiManager().register(setting);
        }

    }
}
