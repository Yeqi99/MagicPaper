package cn.originmc.plugins.magicpaper.api;

import cn.origincraft.magic.object.ContextMap;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.MagicTimer;

public abstract class APITimer extends MagicTimer {
    public APITimer(String id, int delay, int period, ContextMap contextMap) {
        super(id, delay, period, contextMap);
    }
}
