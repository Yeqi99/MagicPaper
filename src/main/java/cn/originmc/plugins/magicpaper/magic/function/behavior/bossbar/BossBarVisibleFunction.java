package cn.originmc.plugins.magicpaper.magic.function.behavior.bossbar;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.BossBarResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.boss.BossBar;

import java.util.List;

public class BossBarVisibleFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "BossBarVisibleFunction don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof BossBarResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be BossBar.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be str boolean.");
        }
        BossBar bossBar = ((BossBarResult) arg1).getBossBar();
        String visible = ((StringResult) arg2).getString();
        if (!VariableUtil.tryBoolean(visible)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be str boolean.");
        }
        bossBar.setVisible(Boolean.parseBoolean(visible));
        return new BossBarResult(bossBar);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "bossbarVisible";
    }
}
