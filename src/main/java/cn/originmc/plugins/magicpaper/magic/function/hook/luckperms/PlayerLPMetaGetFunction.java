package cn.originmc.plugins.magicpaper.magic.function.hook.luckperms;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.luckperms.LuckPermsManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class PlayerLPMetaGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list) {
        if (list.size() < 2) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PlayerLPMetaGet function requires at least two arguments.");
        }
        if (list.get(0) instanceof PlayerResult) {
            if (list.get(1) instanceof StringResult) {
                PlayerResult playerResult = (PlayerResult) list.get(0);
                StringResult keyResult = (StringResult) list.get(1);
                String keyString = keyResult.getString();
                return new StringResult(LuckPermsManager.getMeta(playerResult.getPlayer(), keyString));
            } else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        } else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "playerLPMetaGet";
    }
}
