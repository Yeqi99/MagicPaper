package cn.originmc.plugins.magicpaper.magic.function.trigger;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class TriggerClearSpellFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("ARGS_EMPTY","Args is empty");
        }
        FunctionResult triggerName = args.get(0);
        if (triggerName instanceof StringResult){
            StringResult triggerNameStringResult = (StringResult) triggerName;
            String triggerNameString = triggerNameStringResult.getString();
            MagicPaperTriggerManager.clear(triggerNameString);
            return new NullResult();
        }else {
            return new ErrorResult("TYPE_ERROR","Argument type error.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_SYSTEM";
    }

    @Override
    public String getName() {
        return "triggerClearSpell";
    }
}
