package cn.originmc.plugins.magicpaper.data.config;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class LangData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"lang",true);
    }
    public static String get(String lang,String key,String def){
        return (String) yamlManager.get(lang,key,def);
    }
}
