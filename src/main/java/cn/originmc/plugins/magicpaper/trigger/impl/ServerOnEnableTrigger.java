package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class ServerOnEnableTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "服务器启动时触发";
    }

    @Override
    public String getName() {
        return "ServerOnEnableTrigger";
    }
}
