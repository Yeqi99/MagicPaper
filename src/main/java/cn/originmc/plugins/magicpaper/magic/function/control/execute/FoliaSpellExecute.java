package cn.originmc.plugins.magicpaper.magic.function.control.execute;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.SpellResult;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.IntegerResult;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FoliaSpellExecute extends NormalFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "SpellExecute function requires at least one argument.");
        }
        int count = 0;

        for (FunctionResult arg : args) {
            if (arg instanceof SpellResult){
                SpellResult spellResult = (SpellResult) arg;
                Spell spell = spellResult.getSpell();
                Runnable runnable= new BukkitRunnable() {
                    @Override
                    public void run() {
                        spell.execute(spellContext.getContextMap());
                    }
                };
                Bukkit.getGlobalRegionScheduler().run(MagicPaper.getInstance(), scheduledTask -> runnable.run());
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
        return "foliaSpellExecute";
    }
}
