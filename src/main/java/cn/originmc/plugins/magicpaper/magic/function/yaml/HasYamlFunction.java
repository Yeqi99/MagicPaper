package cn.originmc.plugins.magicpaper.magic.function.yaml;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import dev.rgbmc.expression.functions.FunctionResult;

import java.util.List;

public class HasYamlFunction extends NormalFunction{

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("ARGUMENTS_EMPTY", "Arguments cannot be empty");
        }
        FunctionResult arg0=args.get(0);

        return null;
    }

    @Override
    public String getType() {
        return "hasyaml";
    }

    @Override
    public String getName() {
        return "hasyaml";
    }
}
