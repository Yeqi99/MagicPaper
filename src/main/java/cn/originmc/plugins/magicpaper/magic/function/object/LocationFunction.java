package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LocationFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();

        switch (id) {
            case "A":{
                Player player= (Player) args.get(0).getObject();
                return new LocationResult(player.getLocation());
            }
            case "B":{
                String x= (String) args.get(0).getObject();
                String y= (String) args.get(1).getObject();
                String z= (String) args.get(2).getObject();
                if (!VariableUtils.tryDouble(x)){
                    return new ErrorResult("ARGS_ERROR","The first arg must be double str");
                }
                if (!VariableUtils.tryDouble(y)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be double str");
                }
                if (!VariableUtils.tryDouble(z)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be double str");
                }
                double x1=Double.parseDouble(x);
                double y1=Double.parseDouble(y);
                double z1=Double.parseDouble(z);
                return new LocationResult(new Location(null,x1,y1,z1));
            }
            case "C":{
                String x= (String) args.get(0).getObject();
                String y= (String) args.get(1).getObject();
                String z= (String) args.get(2).getObject();
                String world= (String) args.get(3).getObject();
                if (!VariableUtils.tryDouble(x)){
                    return new ErrorResult("ARGS_ERROR","The first arg must be double str");
                }
                if (!VariableUtils.tryDouble(y)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be double str");
                }
                if (!VariableUtils.tryDouble(z)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be double str");
                }
                double x1=Double.parseDouble(x);
                double y1=Double.parseDouble(y);
                double z1=Double.parseDouble(z);
                World world1= Bukkit.getWorld(world);
                if (world1==null){
                    return new ErrorResult("ARGS_ERROR","The world is not exist.");
                }
                return new LocationResult(new Location(world1,x1,y1,z1));
            }
            case "D":{
                String x= (String) args.get(0).getObject();
                String y= (String) args.get(1).getObject();
                String z= (String) args.get(2).getObject();
                String yaw= (String) args.get(3).getObject();
                String pitch= (String) args.get(4).getObject();
                String world= (String) args.get(5).getObject();
                if (!VariableUtils.tryDouble(x)){
                    return new ErrorResult("ARGS_ERROR","The first arg must be double str");
                }
                if (!VariableUtils.tryDouble(y)){
                    return new ErrorResult("ARGS_ERROR","The second arg must be double str");
                }
                if (!VariableUtils.tryDouble(z)){
                    return new ErrorResult("ARGS_ERROR","The third arg must be double str");
                }
                if (!VariableUtils.tryDouble(yaw)){
                    return new ErrorResult("ARGS_ERROR","The fourth arg must be double str");
                }
                if (!VariableUtils.tryDouble(pitch)){
                    return new ErrorResult("ARGS_ERROR","The fifth arg must be double str");
                }
                double x1=Double.parseDouble(x);
                double y1=Double.parseDouble(y);
                double z1=Double.parseDouble(z);
                double yaw1=Double.parseDouble(yaw);
                double pitch1=Double.parseDouble(pitch);
                World world1= Bukkit.getWorld(world);
                if (world1==null){
                    return new ErrorResult("ARGS_ERROR","The world is not exist.");
                }
                return new LocationResult(new Location(world1,x1,y1,z1,(float)yaw1,(float)pitch1));
            }
            case "E":{
                Object object=args.get(0).getObject();
                if (object instanceof Player){
                    Player player= (Player) object;
                    return new LocationResult(player.getLocation());
                }else if(object instanceof Location){
                    return new LocationResult((Location) object);
                }else {
                    return new ErrorResult("ERROR", "object is not a player or location.");
                }
            }
            case "F":{
                Location location= (Location) args.get(0).getObject();
                Location location1=
                        new Location(
                                location.getWorld()
                                ,location.getX(),location.getY()
                                ,location.getZ(),location.getYaw()
                                ,location.getPitch());
                return new LocationResult(location1);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "Player",
                "player" +
                        "\nGet the location of the player.",
                "Location");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "String String String",
                "x y z" +
                        "\nGet the location",
                "Location");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3= FunctionUtils.createArgsSetting(
                "String String String String",
                "x y z world" +
                        "\nGet the location",
                "Location");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4= FunctionUtils.createArgsSetting(
                "String String String String String String",
                "x y z yaw pitch world" +
                        "\nGet the location",
                "Location");
        argsSetting4.setId("D");

        ArgsSetting argsSetting5= FunctionUtils.createArgsSetting(
                "Object",
                "object" +
                        "\nGet the location of the object.",
                "Location");
        argsSetting5.setId("E");
        ArgsSetting argsSetting6= FunctionUtils.createArgsSetting(
                "Location",
                "location" +
                        "\nGet the location clone from location.",
                "Location");
        argsSetting6.setId("F");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        argsSettings.add(argsSetting5);
        argsSettings.add(argsSetting6);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }

    @Override
    public String getName() {
        return "location";
    }
}
