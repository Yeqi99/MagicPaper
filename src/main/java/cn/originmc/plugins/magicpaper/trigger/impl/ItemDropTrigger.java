package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class ItemDropTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "一个物品掉落时触发";
    }

    @Override
    public String getName() {
        return "ItemDropTrigger";
    }
}
