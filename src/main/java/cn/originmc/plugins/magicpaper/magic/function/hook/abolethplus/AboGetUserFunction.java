package cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import ray.maplex.top.abolethplusadder.AbolethPlusAdder;

import java.util.List;

public class AboGetUserFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("IF_FUNCTION_ARGS_ERROR", "If don't have enough args.");
        }
        FunctionResult userName = args.get(0);
        if (userName instanceof StringResult) {
            StringResult userNameString = (StringResult) userName;
            return new StringResult(AbolethPlusAdder.getUser(userNameString.getString()).toString());
        }else {
            return new ErrorResult("IF_FUNCTION_ARGS_ERROR", "If don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "aboGetUser";
    }
}
