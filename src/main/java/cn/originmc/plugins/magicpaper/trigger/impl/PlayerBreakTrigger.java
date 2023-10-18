package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerBreakTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家破坏方块时触发";
    }

    @Override
    public String getName() {
        return "PlayerBreakTrigger";
    }
}
