package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.util.location.LocationUtil;
import org.bukkit.Location;

import java.util.List;

public class InLocationRangeFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "InLocationRange don't have enough args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        FunctionResult arg3 = args.get(2);
        if (!(arg1 instanceof LocationResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be location.");
        }
        if (!(arg2 instanceof LocationResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be location.");
        }
        if (!(arg3 instanceof LocationResult)){
            return new ErrorResult("TYPE_ERROR", "The third arg must be location.");
        }
        Location location1 = ((LocationResult) arg1).getLocation();
        Location location2 = ((LocationResult) arg2).getLocation();
        Location targetLocation = ((LocationResult) arg3).getLocation();
        return new BooleanResult(LocationUtil.isLocationInRegion(location1, location2, targetLocation));
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "InLocationRange";
    }
}
