package cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import ray.maplex.top.abolethplusadder.AbolethPlusAdder;

import java.util.List;
import java.util.UUID;

public class AboEditFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboEdit don't have enough args.");
        }
        FunctionResult uuid = args.get(0);
        FunctionResult key = args.get(1);
        FunctionResult value = args.get(2);
        FunctionResult action = args.get(3);
        if (uuid instanceof StringResult && key instanceof StringResult && value instanceof StringResult && action instanceof StringResult) {
            String uuidString = ((StringResult) uuid).getString();
            String keyString = ((StringResult) key).getString();
            String valueString = ((StringResult) value).getString();
            String actionString = ((StringResult) action).getString();
            AbolethPlusAdder.edit(UUID.fromString(uuidString), keyString, valueString, actionString);
            return new NullResult();
        } else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboEdit don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "aboEdit";
    }
}
