package cn.originmc.plugins.magicpaper.trigger.listener.timer.impl;

import cn.origincraft.magic.object.ContextMap;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.MagicTimer;
import io.papermc.paper.threadedregions.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class FoliaTimer extends MagicTimer {

    public FoliaTimer(String id, int delay, int period, ContextMap contextMap) {
        super(id, delay, period, contextMap);
    }

    @Override
    public void run() {
        Runnable runnable= new BukkitRunnable() {
            @Override
            public void run() {
                if (!isRunning()){
                    cancel();
                }
                execute();
            }
        };
        Bukkit.getAsyncScheduler().runAtFixedRate(MagicPaper.getInstance(),scheduledTask -> runnable.run(),getDelay(),getPeriod(), TimeUnit.DAYS);
        setRunnable(runnable);
    }

}