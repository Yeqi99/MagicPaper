package cn.originmc.plugins.magicpaper.data.manager;

import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
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
        return new Spell((List<String>) MagicData.yamlManager.get(id,"spell"), MagicPaper.getMagicManager());
    }
    public static boolean isSpell(String id){
        return MagicData.yamlManager.has(id,"spell");
    }
    public static String getSpellDisplayName(String id){
        if (!MagicData.yamlManager.has(id,"display")){
            return null;
        }
        return (String) MagicData.yamlManager.get(id,"display");
    }
    public static List<String> getSpellDescription(String id){
        return (List<String>) MagicData.yamlManager.get(id,"description");
    }
    public static List<Spell> getSpell(List<String> spellNames){
        List<Spell> result=new ArrayList<>();
        for (String spellName : spellNames) {
            if (!isSpell(spellName)){
                continue;
            }
            Spell spell=getSpell(spellName);
            result.add(spell);
        }
        return result;
    }
}
