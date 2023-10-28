package cn.originmc.plugins.magicpaper.magic.function.behavior.bossbar;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.BossBarResult;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import java.util.List;

public class BossBarGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "BossBarGetFunction don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        if (!(arg1 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be string.");
        }
        String key = ((StringResult) arg1).getString();
        return new BossBarResult(Bukkit.getBossBar(new NamespacedKey(MagicPaper.getInstance(), key)));
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "bossbarGet";
    }
}
