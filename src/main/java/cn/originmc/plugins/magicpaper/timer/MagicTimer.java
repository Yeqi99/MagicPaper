package cn.originmc.plugins.magicpaper.timer;

import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicTimer {
    private String id;
    private int delay;
    private int period;
    private boolean isRunning;
    private ContextMap contextMap;
    private Runnable runnable;
    private List<Spell> tasks=new ArrayList<>();

    public MagicTimer(String id, int delay, int period, ContextMap contextMap) {
        this.id = id;
        this.delay = delay;
        this.period = period;
        this.isRunning = false;
        this.contextMap = contextMap;
    }
    public void execute(){
        for (Spell task : tasks) {
            getContextMap().putVariable("players", new ArrayList<>(Bukkit.getOnlinePlayers()));
            getContextMap().putVariable("players_amount", Bukkit.getOnlinePlayers().size());
            Bukkit.getOnlinePlayers();
            task.execute(contextMap);
        }
    }
    public abstract void run();
    public void stop(){
        this.isRunning=false;
        this.runnable=null;
    }
    public void start(){
        this.isRunning=true;
        this.run();
    }

    public String getId() {
        return id;
    }

    public int getDelay() {
        return delay;
    }

    public int getPeriod() {
        return period;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    public List<Spell> getTasks() {
        return tasks;
    }

    public void setTasks(List<Spell> tasks) {
        this.tasks = tasks;
    }
    public void addTask(Spell task){
        this.tasks.add(task);
    }
    public void removeTask(Spell task){
        this.tasks.remove(task);
    }
    public void removeAllTask(){
        this.tasks.clear();
    }
    public boolean hasTask(Spell task){
        return this.tasks.contains(task);
    }
    public boolean hasTask(){
        return !this.tasks.isEmpty();
    }
    public int getTaskCount(){
        return this.tasks.size();
    }
    public Spell getTask(int index){
        return this.tasks.get(index);
    }
    public void setTask(int index,Spell task){
        this.tasks.set(index,task);
    }
    public void removeTask(int index){
        this.tasks.remove(index);
    }
    public void addTask(int index,Spell task){
        this.tasks.add(index,task);
    }
    public void addAllTask(List<Spell> tasks){
        this.tasks.addAll(tasks);
    }
    public void addAllTask(int index,List<Spell> tasks){
        this.tasks.addAll(index,tasks);
    }
    public void removeTasks(List<Spell> tasks){
        this.tasks.removeAll(tasks);
    }

    public ContextMap getContextMap() {
        return contextMap;
    }

    public void setContextMap(ContextMap contextMap) {
        this.contextMap = contextMap;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}