package cn.originmc.plugins.magicpaper.magic.function.cooldown;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.cooldown.CoolDown;

import java.text.DecimalFormat;
import java.util.List;

public class GETCDFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("ARGS_NOT_ENOUGH", "Function addCoolDown need 1 args");
        }
        FunctionResult arg0 = args.get(0);
        if (arg0 instanceof StringResult) {
            String id = ((StringResult) arg0).getString();
            CoolDown coolDown = MagicPaper.getCoolDownManager().getCoolDown(id);
            if (coolDown==null){
                return new StringResult("0");
            }
            long nowToEnd = coolDown.getNowToEnd();
            // 毫秒转化为秒，并取两位小数
            double nowToEndSecond = ((double) nowToEnd) / 1000.0;
            DecimalFormat df = new DecimalFormat("#.0");
            return new StringResult(df.format(nowToEndSecond));
        } else {
            return new ErrorResult("ARGS_TYPE_ERROR", "Function addCoolDown need 1 to be String");
        }
    }

    @Override
    public String getType() {
        return "COOLDOWN";
    }

    @Override
    public String getName() {
        return "getcd";
    }
}
