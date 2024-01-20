package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class AsyncPlayerChatTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家聊天时触发";
    }

    @Override
    public String getName() {
        return "AsyncPlayerChatTrigger";
    }
}
