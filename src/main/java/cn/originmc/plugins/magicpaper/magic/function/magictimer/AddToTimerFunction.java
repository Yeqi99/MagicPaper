package cn.originmc.plugins.magicpaper.magic.function.magictimer;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.trigger.listener.timer.MagicTimerManager;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class AddToTimerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "AddToTimer function requires at least two arguments.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (arg1 instanceof StringResult&&arg2 instanceof SpellResult){
            StringResult stringResult = (StringResult) arg1;
            SpellResult spellResult = (SpellResult) arg2;
            String id = stringResult.getString();
            Spell spell= spellResult.getSpell();
            MagicTimerManager.addTask(id, spell);
            return new NullResult();
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }

    }

    @Override
    public String getType() {
        return "PAPER_SYSTEM";
    }

    @Override
    public String getName() {
        return "addToTimer";
    }
}
