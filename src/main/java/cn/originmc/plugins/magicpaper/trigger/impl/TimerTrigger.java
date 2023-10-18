package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class TimerTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "定时器触发";
    }

    @Override
    public String getName() {
        return "TimerTrigger";
    }

}
