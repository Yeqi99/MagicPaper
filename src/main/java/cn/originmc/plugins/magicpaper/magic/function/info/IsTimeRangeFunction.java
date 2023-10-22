package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.util.time.TimeUtil;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class IsTimeRangeFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("ARGS_NOT_ENOUGH", "Function addCoolDown need 1 args");
        }
        FunctionResult arg0 = args.get(0);
        if (!(arg0 instanceof StringResult)) {
            return new ErrorResult("ARGS_TYPE_ERROR", "Function addCoolDown need 1 to be String");
        }
        String timeRange = ((StringResult) arg0).getString();
        return new BooleanResult(TimeUtil.isCurrentTimeInTimeRange(timeRange));
    }

    @Override
    public String getType() {
        return "INFO";
    }

    @Override
    public String getName() {
        return "isTimeRange";
    }
}
