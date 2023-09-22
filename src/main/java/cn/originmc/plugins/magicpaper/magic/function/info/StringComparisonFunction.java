package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class StringComparisonFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "StringComparison don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be string.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        String str1 = ((StringResult) arg1).getString();
        String str2 = ((StringResult) arg2).getString();
        boolean result = str1.equals(str2);
        return new BooleanResult(result);
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "stringComparison";
    }
}
