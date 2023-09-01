package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class ServerOnLoadTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "服务器加载时触发";
    }

    @Override
    public String getName() {
        return "server_on_load";
    }
}
