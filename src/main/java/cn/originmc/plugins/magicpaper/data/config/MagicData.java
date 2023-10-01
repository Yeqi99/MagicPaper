package cn.originmc.plugins.magicpaper.data.config;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class MagicData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"magic",true);
    }
}
