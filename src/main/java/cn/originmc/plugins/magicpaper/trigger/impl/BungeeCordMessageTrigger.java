package cn.originmc.plugins.magicpaper.trigger.impl;


import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class BungeeCordMessageTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "当收到BungeeCord消息时触发";
    }

    @Override
    public String getName() {
        return "BungeeCordMessageTrigger";
    }
}
