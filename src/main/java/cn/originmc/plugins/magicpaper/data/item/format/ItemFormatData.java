package cn.originmc.plugins.magicpaper.data.item.format;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class ItemFormatData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"item-format",true);
    }
}
