package cn.originmc.plugins.magicpaper.magic.function.hook.luckperms;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.LongResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.luckperms.LuckPermsManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerLPTempMetaSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 4) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PlayerLPTempMetaSet function requires at least four arguments.");
        }
        FunctionResult p = args.get(0);
        FunctionResult key = args.get(1);
        FunctionResult value = args.get(2);
        FunctionResult duration = args.get(3);
        if (p instanceof PlayerResult) {
            if (key instanceof StringResult) {
                if (value instanceof StringResult) {
                    if (duration instanceof LongResult){
                        PlayerResult playerResult = (PlayerResult) p;
                        StringResult keyResult = (StringResult) key;
                        StringResult valueResult = (StringResult) value;
                        LongResult durationResult = (LongResult) duration;
                        Player player = playerResult.getPlayer();
                        String keyString = keyResult.getString();
                        String valueString = valueResult.getString();
                        LuckPermsManager.setMeta(player, keyString, valueString, durationResult.getLong());
                        return new NullResult();
                    }else {
                        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                    }
                } else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
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
        return "playerLPTempMetaSet";
    }
}
