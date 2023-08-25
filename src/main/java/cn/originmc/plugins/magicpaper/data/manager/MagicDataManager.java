package cn.originmc.plugins.magicpaper.data.manager;

import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.data.config.MagicData;

import java.util.ArrayList;
import java.util.List;

public class MagicDataManager {
    public static List<String> getSpellsID(){
        List<String> result=new ArrayList<>();
        MagicData.yamlManager.forEach(yamlElement -> {
            result.add(yamlElement.getId());
        });
        return result;
    }
    public static Spell getSpell(String id){
        return (Spell) MagicData.yamlManager.get(id,"spell");
    }
    public static boolean isSpell(String id){
        return MagicData.yamlManager.has(id,"spell");
    }
}
