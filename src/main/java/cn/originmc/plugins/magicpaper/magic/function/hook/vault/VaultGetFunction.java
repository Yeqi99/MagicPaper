package cn.originmc.plugins.magicpaper.magic.function.hook.vault;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.VaultHook;
import cn.originmc.plugins.magicpaper.hook.vault.VaultManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.DoubleResult;
import org.bukkit.entity.Player;

import java.util.List;

public class VaultGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "VaultGet don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        if (!(arg1 instanceof PlayerResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        Player player=((PlayerResult) arg1).getPlayer();
        return new DoubleResult(VaultManager.getMoney(player));
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "vaultGet";
    }
}
