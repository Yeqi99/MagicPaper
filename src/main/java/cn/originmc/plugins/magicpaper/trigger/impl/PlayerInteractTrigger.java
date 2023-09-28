package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerInteractTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家点击时触发";
    }

    @Override
    public String getName() {
        return "PlayerInteractTrigger";
    }
}
