package lol.imc.minecraft.respawn;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class RespawnData {
    public static YamlManager yamlManager;
    public static void load(){
        yamlManager=new YamlManager(MagicPaper.getInstance(),"respawn",true);
    }
}
