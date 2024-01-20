package cn.originmc.plugins.magicpaper.magic.function.hook.mythicmobs;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.mythicmobs.MythicMobsManager;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;

public class SpawnMobFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "SpawnMob function requires at least two arguments.");
        }
        FunctionResult mobNameResult = args.get(0);
        FunctionResult locationResult = args.get(1);
        if (!(mobNameResult instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        if (!(locationResult instanceof LocationResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        String mobName = ((StringResult) mobNameResult).getString();
        Location location = ((LocationResult) locationResult).getLocation();
        Entity entity= MythicMobsManager.spawnMobGetEntity(mobName,location);
        if (entity==null){
            return new ErrorResult("ERROR", "Failed to spawn mob, need true mob name.");
        }
        return new EntityResult(entity);
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "spawnMob";
    }
}
