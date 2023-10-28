package cn.originmc.plugins.magicpaper.magic.function.behavior.skill;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class JumpToLocationSkillFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS","JumpSkill function requires at least two arguments.");
        }
        FunctionResult arg0=args.get(0);
        FunctionResult arg1=args.get(1);
        FunctionResult arg2=args.get(2);
        FunctionResult arg3=args.get(3);
        if (!(arg0 instanceof PlayerResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","First argument must be a player.");
        }
        if (!(arg1 instanceof LocationResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","Second argument must be a location.");
        }
        if (!(arg2 instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","Third argument must be a string.");
        }
        if (!(arg3 instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE","Fourth argument must be a string.");
        }
        Player player=((PlayerResult) arg0).getPlayer();
        Location jumpTarget=((LocationResult) arg1).getLocation();
        String jumpPower=((StringResult) arg2).getString();
        String jumpHeight=((StringResult) arg3).getString();
        Vector jumpVector = jumpTarget.toVector().subtract(player.getLocation().toVector()).normalize().multiply(Double.parseDouble(jumpPower));
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
        return "jumpToLocationSkill";
    }
}
