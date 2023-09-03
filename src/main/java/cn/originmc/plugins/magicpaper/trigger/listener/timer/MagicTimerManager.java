package cn.originmc.plugins.magicpaper.trigger.listener.timer;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.impl.FoliaTimer;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.impl.PaperTimer;
import org.checkerframework.checker.index.qual.PolyUpperBound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MagicTimerManager {
    public static Map<String,MagicTimer> magicTimerMap=new HashMap<>();
    public static void registerPaperTimer(String id, int delay, int period, ContextMap contextMap){
        PaperTimer paperTimer=new PaperTimer(id,delay,period,contextMap);
        magicTimerMap.put(id,paperTimer);
    }
    public static void registerFoliaTimer(String id, int delay, int period, ContextMap contextMap){
        PaperTimer paperTimer=new PaperTimer(id,delay,period,contextMap);
        magicTimerMap.put(id,paperTimer);
    }
    public static void registerTimer(String id, MagicTimer magicTimer){
        magicTimerMap.put(id,magicTimer);
    }
    public static void addTask(String id, Spell task){
        MagicTimer magicTimer=magicTimerMap.get(id);
        magicTimer.addTask(task);
    }
    public static void removeTask(String id, Spell task){
        MagicTimer magicTimer=magicTimerMap.get(id);
        magicTimer.removeTask(task);
    }
    public static void removeAllTask(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        magicTimer.removeAllTask();
    }
    public static int getTaskAmount(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        return magicTimer.getTasks().size();
    }
    public static List<String> getAllOnStartTimer(){
        List<String> list=null;
        for (MagicTimer magicTimer : magicTimerMap.values()) {
            if (magicTimer.isRunning()){
                list.add(magicTimer.getId());
            }
        }
        return list;
    }
    public static boolean isTimer(String id){
        return magicTimerMap.containsKey(id);
    }
    public static boolean isFoliaTimer(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        return magicTimer instanceof FoliaTimer;
    }
    public static boolean isPaperTimer(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        return magicTimer instanceof PaperTimer;
    }
    public static void startTimer(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        magicTimer.start();
    }
    public static void stopTimer(String id){
        MagicTimer magicTimer=magicTimerMap.get(id);
        magicTimer.stop();
    }
    public static void stopAllTimer(){
        for (MagicTimer magicTimer : magicTimerMap.values()) {
            magicTimer.stop();
        }
    }
    public static void startAllTimer(){
        for (MagicTimer magicTimer : magicTimerMap.values()) {
            magicTimer.start();
        }
    }
    public static void removeTimer(String id){
        magicTimerMap.remove(id);
    }
    public static void removeAllTimer(){
        magicTimerMap.clear();
    }
    public static MagicTimer getTimer(String id){
        return magicTimerMap.get(id);
    }
    public static Map<String,MagicTimer> getMagicTimerMap(){
        return magicTimerMap;
    }

}
