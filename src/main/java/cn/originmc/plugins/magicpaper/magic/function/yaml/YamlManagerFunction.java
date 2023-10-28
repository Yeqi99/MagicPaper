package cn.originmc.plugins.magicpaper.magic.function.yaml;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ObjectResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

import java.util.List;

public class YamlManagerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "YamlManager don't have enough args.");
        }
        FunctionResult pathResult = args.get(0);
        FunctionResult nameResult = args.get(1);
        if (!(pathResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg of YamlManager must be a string.");
        }
        if (!(nameResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg of YamlManager must be a string.");
        }
        String path = ((StringResult) pathResult).getString();
        String name = ((StringResult) nameResult).getString();
        YamlManager yamlManager = new YamlManager(MagicPaper.getInstance(),path,name,true);
        return new ObjectResult(yamlManager);
    }

    @Override
    public String getType() {
        return "YAML";
    }

    @Override
    public String getName() {
        return "yamlManager";
    }
}
