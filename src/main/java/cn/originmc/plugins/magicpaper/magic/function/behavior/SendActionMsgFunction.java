package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.List;

public class SendActionMsgFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "SendActionMsgFunction need 2 args.");
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
        String msg = ((StringResult) arg2).getString();
        player.sendActionBar(Component.text(msg));
        return new NullResult();
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "actionMsg";
    }
}
