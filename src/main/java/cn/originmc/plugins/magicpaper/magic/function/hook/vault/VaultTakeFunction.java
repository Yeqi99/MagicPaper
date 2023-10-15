package cn.originmc.plugins.magicpaper.magic.function.hook.vault;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.hook.vault.VaultManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;

import java.util.List;

import static io.lumine.mythic.bukkit.utils.text.Text.DefaultFontInfo.V;

public class VaultTakeFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "VaultTake don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof PlayerResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        Player player=((PlayerResult) arg1).getPlayer();
        String money = ((StringResult) arg2).getString();
        if (!VariableUtil.tryDouble(money)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be number.");
        }
        return new BooleanResult(VaultManager.takeMoney(player,Double.parseDouble(money)));
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "vaultTake";
    }
}
