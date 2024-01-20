package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class EntityDamageTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "实体造成伤害时触发";
    }

    @Override
    public String getName() {
        return "EntityDamageTrigger";
    }
}
