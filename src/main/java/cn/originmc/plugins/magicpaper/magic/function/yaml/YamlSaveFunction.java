package cn.originmc.plugins.magicpaper.magic.function.yaml;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.ObjectResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class YamlSaveFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "YamlSave don't have enough args.");
        }
        FunctionResult yamlManagerResult = args.get(0);
        FunctionResult nameResult = args.get(1);
        if (!(yamlManagerResult instanceof ObjectResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg of YamlManager must be a object.");
        }
        Object yamlManager = ((ObjectResult) yamlManagerResult).getObject();
        if (!(yamlManager instanceof YamlManager)){
            return new ErrorResult("TYPE_ERROR", "The first arg of YamlManager must be a yamlManager.");
        }
        if (!(nameResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg of YamlManager must be a string.");
        }
        String name = ((StringResult) nameResult).getString();
        YamlManager newYamlManager = (YamlManager) yamlManager;
        newYamlManager.save(name);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "YAML";
    }

    @Override
    public String getName() {
        return "yamlSave";
    }
}
