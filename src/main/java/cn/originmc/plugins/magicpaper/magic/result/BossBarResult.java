package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import org.bukkit.boss.BossBar;

public class BossBarResult extends ObjectResult {
    public BossBarResult(BossBar bossBar) {
        super(bossBar);
    }

    public BossBar getBossBar() {
        return (BossBar) getObject();
    }
    public String getName() {
        return "BossBar";
    }
}
