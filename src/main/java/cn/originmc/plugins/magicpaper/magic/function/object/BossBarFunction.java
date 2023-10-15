package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.BossBarResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.List;

public class BossBarFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 4) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "BossBarFunction need 3 args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        FunctionResult arg3 = args.get(2);
        FunctionResult arg4 = args.get(3);
        if (!(arg1 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The first arg must be string.");
        }
        if (!(arg2 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        if (!(arg3 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The third arg must be string.");
        }
        if (!(arg4 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The fourth arg must be string.");
        }
        String title = ((StringResult) arg1).getString();
        String color = ((StringResult) arg2).getString();
        String style = ((StringResult) arg3).getString();
        String key = ((StringResult) arg4).getString();
        NamespacedKey namespacedKey = new NamespacedKey(MagicPaper.getInstance(), key);
        BossBar bossBar = Bukkit.createBossBar(namespacedKey, title, BarColor.valueOf(color), BarStyle.valueOf(style));
        return new BossBarResult(bossBar);
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "bossbar";
    }
}
