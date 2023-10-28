package cn.originmc.plugins.magicpaper.magic.function.magictimer;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ContextMapResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.timer.MagicTimerManager;

import java.util.List;

public class PaperTimerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PaperTimer function requires at least four arguments.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        FunctionResult arg3 = args.get(2);
        FunctionResult arg4 = args.get(3);
        if(arg1 instanceof StringResult && arg2 instanceof StringResult && arg3 instanceof StringResult && arg4 instanceof ContextMapResult) {
            StringResult stringResult1 = (StringResult) arg1;
            StringResult stringResult2 = (StringResult) arg2;
            StringResult stringResult3 = (StringResult) arg3;
            ContextMapResult contextMapResult = (ContextMapResult) arg4;
            String id = stringResult1.getString();
            String delay = stringResult2.getString();
            String period = stringResult3.getString();
            ContextMap contextMap= contextMapResult.getContextMap();
            MagicTimerManager.registerPaperTimer(id, Integer.parseInt(delay), Integer.parseInt(period), contextMap);
            return new NullResult();
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_SYSTEM";
    }

    @Override
    public String getName() {
        return "paperTimer";
    }
}
