package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;

import java.util.List;

public class PlayerNameFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PlayerName don't have enough args.");
        }
        FunctionResult player = args.get(0);
        if (player instanceof PlayerResult){
            return new StringResult(((PlayerResult) player).getPlayer().getName());
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PlayerName don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "playerName";
    }
}
