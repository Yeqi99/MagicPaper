package cn.originmc.plugins.magicpaper.data.attribute;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class AttributeData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"attribute",true);
    }
}
