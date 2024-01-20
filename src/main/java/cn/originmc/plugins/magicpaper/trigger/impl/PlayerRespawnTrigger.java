package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerRespawnTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家重生时触发";
    }

    @Override
    public String getName() {
        return "PlayerRespawnTrigger";
    }
}
