package cn.originmc.plugins.magicpaper.data.manager;


import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.trigger.TriggerData;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;

import java.util.List;

public class TriggerDataManager {
    public static List<String> getTriggerSpellNames(String id){
        return (List<String>) TriggerData.yamlManager.get(id,"execute-spell",null);
    }
    public static void registerTrigger(String id){
        if(!MagicPaperTriggerManager.isTrigger(id)){
            return;
        }
        List<String> spellNames=getTriggerSpellNames(id);
        if (spellNames==null){
            return;
        }
        if (spellNames.isEmpty()){
            return;
        }
        List<Spell> spells= MagicDataManager.getSpell(spellNames);
        if (spells.isEmpty()){
            return;
        }
        MagicPaperTriggerManager.register(id,spells);
    }
    public static void initDefaultTrigger(){
        registerTrigger("PlayerJoinTrigger");
        registerTrigger("ServerOnEnableTrigger");
        registerTrigger("ServerOnLoadTrigger");
        registerTrigger("ServerOnDisableTrigger");
        registerTrigger("PlayerInteractTrigger");
        MagicPaper.getSender().sendToLogger("§a[§bMagicPaper§a] §e触发器已加载");
    }
    public static void reInit(){
        MagicPaperTriggerManager.magicPaperTriggers.clear();
        MagicPaperTriggerManager.registerDefaultTrigger();
        initDefaultTrigger();
    }
}
