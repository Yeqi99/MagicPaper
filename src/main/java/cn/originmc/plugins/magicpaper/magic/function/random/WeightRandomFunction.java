package cn.originmc.plugins.magicpaper.magic.function.random;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.util.random.RandomStringSelector;

import java.util.ArrayList;
import java.util.List;

public class WeightRandomFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("ARGS_NOT_ENOUGH","Dont have enough args");
        }
        List<String> randomList = new ArrayList<>();
        for (FunctionResult arg : args) {
            if (!(arg instanceof StringResult)){
                continue;
            }
            String random = ((StringResult) arg).getString();
            randomList.add(random);
        }
        RandomStringSelector randomStringSelector = new RandomStringSelector(randomList);
        String str= randomStringSelector.getRandomString();
        return new StringResult(str);
    }

    @Override
    public String getType() {
        return "RANDOM";
    }

    @Override
    public String getName() {
        return "weightRandom";
    }
}
