package cn.originmc.plugins.magicpaper.magic.function.hook.playerpoints;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.hook.playerpoints.PlayerPointsManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerPointsSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PlayerPointsSet don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof PlayerResult)) {
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        if (!(arg2 instanceof StringResult)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        Player player = ((PlayerResult) arg1).getPlayer();
        String money = ((StringResult) arg2).getString();
        if (!VariableUtil.tryInt(money)) {
            return new ErrorResult("TYPE_ERROR", "The second arg must be number.");
        }
        return new BooleanResult(PlayerPointsManager.setPoints(player.getUniqueId(), Integer.parseInt(money)));
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "playerpointsSet";
    }
}
