package cn.originmc.plugins.magicpaper.magic.function.control.execute;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class PaperSpellAsyncExecuteFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {

        if (args.isEmpty()){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PaperSpellAsyncExecute function requires at least one argument.");
        }
        int count = 0;

        for (FunctionResult arg : args) {
            if (arg instanceof SpellResult){
                SpellResult spellResult = (SpellResult) arg;
                Spell spell = spellResult.getSpell();
                Object o= new BukkitRunnable() {
                    @Override
                    public void run() {
                        spell.execute(spellContext.getContextMap());
                    }
                }.runTaskAsynchronously(MagicPaper.getInstance());
                count++;
            }
        }
        // 返回执行统计
        return new IntegerResult(count);
    }

    @Override
    public String getType() {
        return "PAPER_SYSTEM";
    }

    @Override
    public String getName() {
        return "paperSpellAsyncExecute";
    }
}
