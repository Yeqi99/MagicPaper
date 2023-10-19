package cn.originmc.plugins.magicpaper.trigger.impl.hook.epiccraftingsplus;

import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;

public class EpicCraftingsCraftTrigger extends MagicPaperTrigger {
    @Override
    public String getDefaultInfo() {
        return "史诗合成时触发";
    }

    @Override
    public String getName() {
        return "EpicCraftingsCraftTrigger";
    }
}
