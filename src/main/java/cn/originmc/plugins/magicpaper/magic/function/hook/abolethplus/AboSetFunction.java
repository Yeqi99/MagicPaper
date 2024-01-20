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

public class AboSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboSet don't have enough args.");
        }
        FunctionResult uuid = args.get(0);
        FunctionResult key = args.get(1);
        if (uuid instanceof StringResult && key instanceof StringResult) {
            String uuidString = ((StringResult) uuid).getString();
            String keyString = ((StringResult) key).getString();
            if (args.size()==3){
                FunctionResult value = args.get(2);
                if (value instanceof StringResult){
                    String valueString = ((StringResult) value).getString();
                    AbolethPlusAdder.set(UUID.fromString(uuidString),keyString,valueString);
                }
                return new NullResult();
            }else{
                return new ErrorResult("FUNCTION_ARGS_ERROR", "AboSet don't have enough args.");
            }
        }
        return null;
    }

    @Override
    public String getType() {
        return "HOOK";
    }

    @Override
    public String getName() {
        return "aboSet";
    }
}
