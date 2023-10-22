package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerTeleportTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家传送时触发";
    }

    @Override
    public String getName() {
        return "PlayerTeleportTrigger";
    }
}
