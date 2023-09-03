package cn.originmc.plugins.magicpaper.trigger.listener.timer.impl;

import cn.origincraft.magic.object.ContextMap;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.MagicTimer;
import org.bukkit.scheduler.BukkitRunnable;

public class PaperTimer extends MagicTimer {


    public PaperTimer(String id, int delay, int period, ContextMap contextMap) {
        super(id, delay, period, contextMap);
    }

    @Override
    public void run() {
        BukkitRunnable bukkitRunnable= new BukkitRunnable() {
            @Override
            public void run() {
                if (!isRunning()){
                    cancel();
                }
                execute();
            }
        };
        bukkitRunnable.runTaskTimerAsynchronously(MagicPaper.getInstance(),getDelay(),getPeriod());
        setRunnable(bukkitRunnable);
    }
}
