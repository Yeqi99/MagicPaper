package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.entity.Player;

import java.util.List;

public class EntityFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "EntityFunction don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        if (!(arg1 instanceof PlayerResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be itemStack.");
        }
        Player player = ((PlayerResult) arg1).getPlayer();
        return new EntityResult(player);
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "entity";
    }
}
