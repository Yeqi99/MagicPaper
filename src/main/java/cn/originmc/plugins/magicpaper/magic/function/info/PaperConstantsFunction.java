package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class PaperConstantsFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PaperConstants don't have enough args.");
        }
        FunctionResult key = args.get(0);
        if (!(key instanceof StringResult)) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PaperConstants don't have enough args.");
        }
        String keyStr = ((StringResult) key).getString();
        String value = MagicPaper.getInstance().getConfig().getString(keyStr,"");
        if (value.isEmpty()){
            return new NullResult();
        }else {
            return new StringResult(value);
        }
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "paperConstants";
    }
}
