package cn.originmc.plugins.magicpaper.magic.function.yaml;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ObjectResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

import java.util.List;

public class HasYamlFunction extends NormalFunction{

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGUMENTS_EMPTY", "HasYaml don't have enough args.");
        }
        FunctionResult arg0=args.get(0);
        FunctionResult arg1=args.get(1);
        if (arg0 instanceof ObjectResult) {
            ObjectResult objectResult = (ObjectResult) arg0;
            Object object = objectResult.getObject();
            if (!(object instanceof YamlManager)) {
                return new ErrorResult("TYPE_ERROR", "The first arg of HasYaml must be a YamlManager.");
            }
            if (!(arg1 instanceof StringResult)) {
                return new ErrorResult("TYPE_ERROR", "The second arg of HasYaml must be a string.");
            }
            YamlManager yamlManager = (YamlManager) object;
            String key = ((StringResult) arg1).getString();
            if (yamlManager.hasElement(key)) {
                return new BooleanResult(true);
            } else {
                return new BooleanResult(false);
            }
        }else {
            return new ErrorResult("TYPE_ERROR", "The first arg of HasYaml must be a YamlManager.");
        }
    }

    @Override
    public String getType() {
        return "YAML";
    }

    @Override
    public String getName() {
        return "hasyaml";
    }
}
