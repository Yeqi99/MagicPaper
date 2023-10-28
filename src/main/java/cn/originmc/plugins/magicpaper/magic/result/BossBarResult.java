package cn.originmc.plugins.magicpaper.magic.result;



import cn.origincraft.magic.expression.functions.FunctionResult;
import org.bukkit.boss.BossBar;

public class BossBarResult extends FunctionResult {
    private final BossBar bossBar;
    public BossBarResult(BossBar bossBar) {
        this.bossBar = bossBar;
    }

    public BossBar getBossBar() {
        return bossBar;
    }
}
