package cn.originmc.plugins.magicpaper.magic.function.object;


import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class PlayerFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                String name= (String) args.get(0).getObject();
                Player player = Bukkit.getPlayer(name);
                if (player == null) {
                    return new ErrorResult("PLAYER_NOT_FOUND", "Player not found.");
                }
                return new PlayerResult(player);
            }
            case "B":{
                Entity entity = ((EntityResult) args.get(0)).getEntity();
                if (entity instanceof Player) {
                    return new PlayerResult((Player) entity);
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Entity is not a player.");
                }
            }
            case "C":{
                Location location= (Location) args.get(0).getObject();
                String redis= (String) args.get(1).getObject();
                String amount= (String) args.get(2).getObject();
                if (!VariableUtil.tryDouble(redis)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be double str");
                }
                if (!VariableUtil.tryInt(amount)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be int str");
                }
                double r=Double.parseDouble(redis);
                int a=Integer.parseInt(amount);
                Location location1=location.clone();
                Collection<Player> players = location1.getNearbyPlayers(r);
                Player[] playersArray = players.toArray(new Player[0]);
                List<Player> playerList = new ArrayList<>(Arrays.asList(playersArray).subList(0, a));
                return new ListResult(playerList);
            }
            case "D":{
                Object o = args.get(0).getObject();
                if (o instanceof Player) {
                    return new PlayerResult((Player) o);
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Object is not a player.");
                }
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "String",
                "name" +
                        "\nGet a player by name.",
                "Player");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "Entity",
                "entity" +
                        "\nGet a player by entity.",
                "Player");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3= FunctionUtils.createArgsSetting(
                "Location String String",
                "location redis amount" +
                        "\nGet playerList nearby location",
                "List");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4= FunctionUtils.createArgsSetting(
                "Object",
                "object" +
                        "\nGet a player by object.",
                "Player");
        argsSetting4.setId("D");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }

    @Override
    public String getName() {
        return "player";
    }
}
