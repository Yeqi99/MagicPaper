package cn.originmc.plugins.magicpaper.magic.function.behavior.skill;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class JumpSkillFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS","JumpSkill function requires at least two arguments.");
        }
        FunctionResult arg0=args.get(0);
        FunctionResult arg1=args.get(1);
        FunctionResult arg2=args.get(2);
        if (!(arg0 instanceof PlayerResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","First argument must be a player.");
        }
        if (!(arg1 instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","Second argument must be a string.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","Third argument must be a string.");
        }
        Player player=((PlayerResult) arg0).getPlayer();
        String jumpPower=((StringResult) arg1).getString();
        String jumpHeight=((StringResult) arg2).getString();
        Vector playerDirection = player.getLocation().getDirection();
        Vector jumpVector = playerDirection.multiply(Double.parseDouble(jumpPower));
        jumpVector.setY(Double.parseDouble(jumpHeight));
        player.setVelocity(jumpVector);
        return new NullResult();
    }

    @Override
    public String getType() {
        return "SKILL";
    }

    @Override
    public String getName() {
        return "JumpSkill";
    }
}
