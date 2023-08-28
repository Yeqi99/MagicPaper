package cn.originmc.plugins.magicpaper.magic.function.control.execute;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.IntegerResult;
import dev.rgbmc.expression.results.ObjectResult;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PaperSpellTimer extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {

        if (args.isEmpty()){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "SpellExecute function requires at least one argument.");
        }
        FunctionResult spellResult = args.get(0);
        FunctionResult delayResult = args.get(1);
        FunctionResult timeResult = args.get(2);
        if (spellResult instanceof SpellResult && delayResult instanceof IntegerResult && timeResult instanceof IntegerResult){
            Spell spell = ((SpellResult) spellResult).getSpell();
            int delay = ((IntegerResult) delayResult).getInteger();
            int time = ((IntegerResult) timeResult).getInteger();
            Object o= new BukkitRunnable() {
                @Override
                public void run() {
                    spell.execute(spellContext.getContextMap());
                }
            }.runTaskTimer(MagicPaper.getInstance(),delay,time);
            return new ObjectResult(o);
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
        return "paperSpellTimer";
    }
}
