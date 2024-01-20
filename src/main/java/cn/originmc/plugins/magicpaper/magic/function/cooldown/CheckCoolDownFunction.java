package cn.originmc.plugins.magicpaper.magic.function.cooldown;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;

import java.util.List;

public class CheckCoolDownFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("ARGS_NOT_ENOUGH","Function addCoolDown need 1 args");
        }
        FunctionResult arg0 = args.get(0);

        if (!(arg0 instanceof StringResult)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 1 to be String");
        }
        String id = ((StringResult) arg0).getString();
        return new BooleanResult(MagicPaper.getCoolDownManager().isCoolDownActive(id));
    }

    @Override
    public String getType() {
        return "COOLDOWN";
    }

    @Override
    public String getName() {
        return "checkCoolDown";
    }
}
