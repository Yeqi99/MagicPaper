package cn.originmc.plugins.magicpaper.magic.function.behavior.bossbar;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.BossBarResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.List;

public class HideBossBarFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "HideBossBarFunction don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof PlayerResult)) {
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        if (!(arg2 instanceof BossBarResult)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be BossBar.");
        }
        Player player = ((PlayerResult) arg1).getPlayer();
        BossBar bossBar = ((BossBarResult) arg2).getBossBar();
        bossBar.removePlayer(player);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "hideBossBar";
    }
}
