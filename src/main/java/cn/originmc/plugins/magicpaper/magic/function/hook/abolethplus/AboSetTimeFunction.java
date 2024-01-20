package cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.LongResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import ray.maplex.top.abolethplusadder.AbolethPlusAdder;

import java.util.List;
import java.util.UUID;

public class AboSetTimeFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboSetTime don't have enough args.");
        }
        FunctionResult uuid = args.get(0);
        FunctionResult key = args.get(1);
        FunctionResult time = args.get(2);
        if (uuid instanceof StringResult && key instanceof StringResult && time instanceof LongResult) {
            String uuidString = ((StringResult) uuid).getString();
            String keyString = ((StringResult) key).getString();
            long timeString = ((LongResult) time).getLong();
            AbolethPlusAdder.setOverTime(UUID.fromString(uuidString),keyString,timeString);
            return new NullResult();
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboSetTime don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "aboSetTime";
    }
}
