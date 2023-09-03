package cn.originmc.plugins.magicpaper.trigger;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.PlayerJoinTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.ServerOnEnableTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.ServerOnLoadTrigger;


import java.util.ArrayList;
import java.util.List;

public class MagicPaperTriggerManager {
    public static List<MagicPaperTrigger> magicPaperTriggers=new ArrayList<>();
    public static void init(){
        // 将需要的触发器加入到触发器列表
        magicPaperTriggers.add(new ServerOnEnableTrigger());
        magicPaperTriggers.add(new ServerOnLoadTrigger());
        magicPaperTriggers.add(new PlayerJoinTrigger());
        magicPaperTriggers.add(new PlayerJoinTrigger());

        // 注册监听器
        registerListener();
    }
    public static void registerTrigger(MagicPaperTrigger trigger){
        magicPaperTriggers.add(trigger);
    }
    public static void registerListener(){
        new PlayerJoinTrigger();
    }
    public static void trigger(String name, ContextMap contextMap){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                trigger.onTrigger(contextMap);
                return;
            }
        }
    }
    public static void register(String name, Spell spell){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                trigger.register(spell);
                return;
            }
        }
    }
    public static void unregister(String name, Spell spell){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                trigger.unregister(spell);
                return;
            }
        }
    }
    public static void unregisterAll(Spell spell){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            trigger.unregister(spell);
        }
    }
    public static void registerAll(Spell spell){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            trigger.register(spell);
        }
    }
    public static List<String> getTriggerNames(){
        List<String> names=new ArrayList<>();
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            names.add(trigger.getName());
        }
        return names;
    }
    public static List<MagicPaperTrigger> getMagicPaperTriggers(){
        return magicPaperTriggers;
    }
    public static boolean isTrigger(String name){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static List<Spell> getSpells(String name){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                return trigger.getSpells();
            }
        }
        return null;
    }

}
