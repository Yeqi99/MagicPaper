package cn.originmc.plugins.magicpaper.trigger.impl;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class PlayerKeyboardTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "玩家按下按键时触发";
    }

    @Override
    public String getName() {
        return "PlayerKeyboardTrigger";
    }
}
