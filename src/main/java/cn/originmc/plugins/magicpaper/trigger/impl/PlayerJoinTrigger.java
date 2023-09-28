package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerJoinTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家加入服务器时触发";
    }

    @Override
    public String getName() {
        return "PlayerJoinTrigger";
    }
}
