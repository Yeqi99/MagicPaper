package cn.originmc.plugins.magicpaper.trigger.impl.hook.epiccraftingsplus;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class EpicCraftingsPlaceClickTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "史诗合成台放置时触发";
    }

    @Override
    public String getName() {
        return "EpicCraftingsPlaceClickTrigger";
    }
}
