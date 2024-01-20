package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import org.bukkit.potion.PotionEffect;

public class PotionResult extends ObjectResult {
    public PotionResult(Object object) {
        super(object);
    }
    public PotionEffect getPlayer() {
        return (PotionEffect) getObject();
    }
    public String getName() {
        return "Potion";
    }
}
