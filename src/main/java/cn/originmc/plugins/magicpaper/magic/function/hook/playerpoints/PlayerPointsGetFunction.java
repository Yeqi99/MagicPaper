package cn.originmc.plugins.magicpaper.magic.function.hook.playerpoints;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.playerpoints.PlayerPointsManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerPointsGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PlayerPointsGet don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        if (!(arg1 instanceof PlayerResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        Player player=((PlayerResult) arg1).getPlayer();
        return new IntegerResult(PlayerPointsManager.lookPoints(player.getUniqueId()));
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "playerpointsGet";
    }
}
