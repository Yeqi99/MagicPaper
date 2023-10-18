package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerPlaceTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家放置方块时触发";
    }

    @Override
    public String getName() {
        return "PlayerPlaceTrigger";
    }
}
