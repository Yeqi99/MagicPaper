package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class ServerOnDisableTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "服务器关闭时触发";
    }

    @Override
    public String getName() {
        return "ServerOnDisableTrigger";
    }
}
