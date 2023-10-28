package cn.originmc.plugins.magicpaper.magic.function.cooldown;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.LongResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;

import java.util.List;

public class AddCoolDownFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("ARGS_NOT_ENOUGH","Function addCoolDown need 2 args");
        }
        FunctionResult arg0 = args.get(0);
        FunctionResult arg1 = args.get(1);
        FunctionResult arg2 = args.get(2);
        FunctionResult arg3 = args.get(3);
        if (!(arg0 instanceof StringResult)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 1 to be String");
        }
        if (!(arg1 instanceof LongResult)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 2 to be Long");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 3 to be String");
        }
        if (!(arg3 instanceof StringResult)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 4 to be String");
        }
        String id = ((StringResult) arg0).getString();
        long duration = ((LongResult) arg1).getLong();
        String reduction = ((StringResult) arg2).getString();
        String fixedReduction = ((StringResult) arg3).getString();
        if (!VariableUtil.tryDouble(reduction)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 3 to be Double String");
        }
        if (!VariableUtil.tryInt(fixedReduction)){
            return new ErrorResult("ARGS_TYPE_ERROR","Function addCoolDown need 4 to be Int String");
        }
        double reductionDouble = Double.parseDouble(reduction);
        int fixedReductionDouble = Integer.parseInt(fixedReduction);
        CoolDown coolDown = new CoolDown(id,duration,(float) reductionDouble,fixedReductionDouble);
        MagicPaper.getCoolDownManager().addCoolDown(coolDown);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "COOLDOWN";
    }

    @Override
    public String getName() {
        return "addCoolDown";
    }
}
