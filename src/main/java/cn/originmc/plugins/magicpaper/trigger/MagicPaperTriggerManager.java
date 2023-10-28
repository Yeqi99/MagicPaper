package cn.originmc.plugins.magicpaper.trigger;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.hook.EpicCraftingsPlusHook;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.*;
import cn.originmc.plugins.magicpaper.trigger.impl.hook.epiccraftingsplus.EpicCraftingsCraftTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.hook.epiccraftingsplus.EpicCraftingsPlaceClickTrigger;
import cn.originmc.plugins.magicpaper.trigger.impl.hook.epiccraftingsplus.EpicCraftingsPreCraftTrigger;
import cn.originmc.plugins.magicpaper.trigger.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperTriggerManager {
    public static final List<MagicPaperTrigger> magicPaperTriggers=new ArrayList<>();
    public static void init(){
        // 将需要的触发器加入到触发器列表
        registerDefaultTrigger();

        // 注册监听器
        registerListener(MagicPaper.getInstance());
    }
    public static void registerDefaultTrigger(){
        magicPaperTriggers.add(new ServerOnEnableTrigger());
        magicPaperTriggers.add(new ServerOnLoadTrigger());
        magicPaperTriggers.add(new PlayerJoinTrigger());
        magicPaperTriggers.add(new ServerOnDisableTrigger());
        magicPaperTriggers.add(new PlayerInteractTrigger());
        magicPaperTriggers.add(new PlayerKeyboardTrigger());
        magicPaperTriggers.add(new PlayerDropTrigger());
        magicPaperTriggers.add(new TimerTrigger());
        magicPaperTriggers.add(new PlayerBreakTrigger());
        magicPaperTriggers.add(new PlayerPlaceTrigger());
        magicPaperTriggers.add(new EntityDamageTrigger());
        magicPaperTriggers.add(new ItemDropTrigger());
        magicPaperTriggers.add(new PlayerTeleportTrigger());
        magicPaperTriggers.add(new AsyncPlayerChatTrigger());
        magicPaperTriggers.add(new PlayerRespawnTrigger());
        if (EpicCraftingsPlusHook.status){
            magicPaperTriggers.add(new EpicCraftingsCraftTrigger());
            magicPaperTriggers.add(new EpicCraftingsPreCraftTrigger());
            magicPaperTriggers.add(new EpicCraftingsPlaceClickTrigger());
        }
    }
    public static void registerTrigger(MagicPaperTrigger trigger){
        magicPaperTriggers.add(trigger);
    }
    public static void registerListener(JavaPlugin plugin){
        new PlayerListener(plugin);
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
    public static void register(String name,List<Spell> spell){
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
    public static void clear(String name){
        for(MagicPaperTrigger trigger:magicPaperTriggers){
            if (trigger.getName().equalsIgnoreCase(name)){
                trigger.getSpells().clear();
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
