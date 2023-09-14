package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerClickPlayerTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家点击玩家时触发";
    }

    @Override
    public String getName() {
        return "player_click_player";
    }
}
