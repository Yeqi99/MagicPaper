package cn.originmc.plugins.magicpaper.magic.function.behavior.potion;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class PotionAddFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "PotionAdd don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        FunctionResult arg3 = args.get(2);
        FunctionResult arg4 = args.get(3);
        if (!(arg1 instanceof PlayerResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be player.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be string.");
        }
        if (!(arg3 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The third arg must be string int.");
        }
        if (!(arg4 instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The fourth arg must be string int.");
        }
        Player player = ((PlayerResult) arg1).getPlayer();
        String potion = ((StringResult) arg2).getString();
        String level = ((StringResult) arg3).getString();
        String time = ((StringResult) arg4).getString();
        PotionEffectType potionEffectType=PotionEffectType.getByName(potion);
        if (potionEffectType==null){
            return new ErrorResult("TYPE_ERROR", "The potion type is not exist.");
        }
        PotionEffect potionEffect = new PotionEffect(potionEffectType,Integer.parseInt(time),Integer.parseInt(level));
        player.addPotionEffect(potionEffect);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "potionAdd";
    }
}
