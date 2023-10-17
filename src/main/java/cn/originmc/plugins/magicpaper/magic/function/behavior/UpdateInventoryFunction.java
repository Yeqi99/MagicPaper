package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.entity.Player;

import java.util.List;

public class UpdateInventoryFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "UpdateInventory function requires at least one argument.");
        }
        FunctionResult player = args.get(0);
        if (!(player instanceof PlayerResult)) {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        Player p = ((PlayerResult) player).getPlayer();
        p.updateInventory();
        return new NullResult();
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "updateInventory";
    }
}
