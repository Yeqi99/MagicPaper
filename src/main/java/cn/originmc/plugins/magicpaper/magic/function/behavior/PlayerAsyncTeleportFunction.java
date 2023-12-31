package cn.originmc.plugins.magicpaper.magic.function.behavior;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.List;

public class PlayerAsyncTeleportFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PlayerTeleport function requires at least two arguments.");
        }
        FunctionResult player=args.get(0);
        FunctionResult location=args.get(1);
        if (player instanceof PlayerResult){
            if (location instanceof LocationResult){
                PlayerResult playerResult=(PlayerResult) player;
                LocationResult locationResult=(LocationResult) location;
                Player p=playerResult.getPlayer();
                p.teleportAsync(locationResult.getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);
                return new NullResult();
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_BEHAVIOR";
    }

    @Override
    public String getName() {
        return "playerAsyncTeleport";
    }
}
