package cn.originmc.plugins.magicpaper.data.trigger;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class TriggerData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"trigger",true);
    }
}
