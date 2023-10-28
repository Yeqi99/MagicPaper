package cn.originmc.plugins.magicpaper.magic.function.trigger;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;

import java.util.List;

public class AddSpellToTriggerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2) {
            return new ErrorResult("ERROR_ARGUMENTS","Arguments are not enough.");
        }
        FunctionResult triggerName = args.get(0);
        FunctionResult spell = args.get(1);
        if(triggerName instanceof StringResult && spell instanceof SpellResult){
            StringResult triggerNameStringResult = (StringResult) triggerName;
            SpellResult spellResult = (SpellResult) spell;
            String triggerNameString = triggerNameStringResult.getString();
            Spell spell1 = spellResult.getSpell();
            if (!MagicPaperTriggerManager.isTrigger(triggerNameString)){
                return new ErrorResult("ERROR_TRIGGER_NOT_EXIST","Trigger does not exist.");
            }
            MagicPaperTriggerManager.register(triggerNameString,spell1);
            return new NullResult();
        }else {
            return new ErrorResult("TYPE_ERROR","Argument type error.");
        }
    }

    @Override
    public String getType() {
        return "TRIGGER";
    }

    @Override
    public String getName() {
        return "addSpellToTrigger";
    }
}
