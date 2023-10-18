package cn.originmc.plugins.magicpaper.magic.function.object;


import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.DoubleResult;
import dev.rgbmc.expression.results.IntegerResult;
import dev.rgbmc.expression.results.ObjectResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class PlayerFunction extends NormalFunction {


    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()) {
            if (spellContext.getContextMap().hasVariable("self")) {
                if (spellContext.getContextMap().getVariable("self") instanceof PlayerResult){
                    return (FunctionResult) spellContext.getContextMap().getVariable("self");
                }else {
                    return new ErrorResult("ERROR", "self is not a player.");
                }
            }
        }
        FunctionResult firstArg=args.get(0);
        if (firstArg instanceof StringResult) {
            StringResult name = (StringResult) firstArg;
            Player player = Bukkit.getPlayer(name.getString());
            if (player == null) {
                return new ErrorResult("PLAYER_NOT_FOUND", "Player not found.");
            }
            return new PlayerResult(player);
        }else if(firstArg instanceof EntityResult){
            Entity entity = ((EntityResult) firstArg).getEntity();
            if (entity instanceof Player) {
                return new PlayerResult((Player) entity);
            }else {
                return firstArg;
            }
        } else if (firstArg instanceof PlayerResult) {
            return firstArg;
        } else if (firstArg instanceof LocationResult) {
            Location location = ((LocationResult) firstArg).getLocation();
            FunctionResult redis = args.get(1);
            FunctionResult amount = args.get(2);
            if (redis instanceof DoubleResult){
                if (amount instanceof IntegerResult) {
                    double r=((DoubleResult)redis).getDouble();
                    int a=((IntegerResult)amount).getInteger();
                    Collection<Player> players = location.getNearbyPlayers(r);
                    Player[] playersArray = players.toArray(new Player[0]);
                    List<Object> playersList = new ArrayList<>(Arrays.asList(playersArray).subList(0, a));
                    return new ListResult(playersList);
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }if (firstArg instanceof ObjectResult){
            Object o = ((ObjectResult) firstArg).getObject();
            if (o instanceof Player) {
                return new PlayerResult((Player) o);
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        } else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "player";
    }
}
