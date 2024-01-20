package cn.originmc.plugins.magicpaper.magic.function.hook.mythicmobs;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.hook.mythicmobs.MythicMobsManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.List;

public class CastSkillFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "CastSkill function requires at least three arguments.");
        }
        FunctionResult playerResult = args.get(0);
        FunctionResult skillNameResult = args.get(1);
        FunctionResult powerResult = args.get(2);
        if (!(playerResult instanceof PlayerResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        if (!(skillNameResult instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        if (!(powerResult instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        Player player = ((PlayerResult) playerResult).getPlayer();
        String skillName = ((StringResult) skillNameResult).getString();
        String power = ((StringResult) powerResult).getString();
        if (!VariableUtils.tryDouble(power)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        float powerFloat = Float.parseFloat(power);
        MythicMobsManager.castSkill(player,skillName,powerFloat);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "castSkill";
    }
}
