package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class MagicLocationFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "MagicLocation function requires at least one argument.");
        }
        if (args.size()==4){
            FunctionResult functionResult1=args.get(0);
            FunctionResult functionResult2=args.get(1);
            FunctionResult functionResult3=args.get(2);
            FunctionResult functionResult4=args.get(3);
            String x= ((StringResult) functionResult1).getString();
            String y= ((StringResult) functionResult2).getString();
            String z= ((StringResult) functionResult3).getString();
            String worldName= ((StringResult) functionResult4).getString();
            World world= Bukkit.getWorld(worldName);
            if (world==null){
                return new ErrorResult("WORLD_NOT_FOUND", "World not found.");
            }
            return new LocationResult(new Location(world,Double.parseDouble(x),Double.parseDouble(y),Double.parseDouble(z)));
        }else if (args.size()==6){
            FunctionResult functionResult1=args.get(0);
            FunctionResult functionResult2=args.get(1);
            FunctionResult functionResult3=args.get(2);
            FunctionResult functionResult4=args.get(3);
            FunctionResult functionResult5=args.get(4);
            FunctionResult functionResult6=args.get(5);
            String x= ((StringResult) functionResult1).getString();
            String y= ((StringResult) functionResult2).getString();
            String z= ((StringResult) functionResult3).getString();
            String worldName= ((StringResult) functionResult4).getString();
            String yaw= ((StringResult) functionResult5).getString();
            String pitch= ((StringResult) functionResult6).getString();
            World world= Bukkit.getWorld(worldName);
            if (world==null){
                return new ErrorResult("WORLD_NOT_FOUND", "World not found.");
            }
            return new LocationResult(new Location(world,Double.parseDouble(x),Double.parseDouble(y),Double.parseDouble(z),Float.parseFloat(yaw),Float.parseFloat(pitch)));
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "PAPER_OBJECT";
    }

    @Override
    public String getName() {
        return "magicLocation";
    }
}
