package cn.originmc.plugins.magicpaper.magic.function.hook.itemsadder;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.itemsadder.ItemsAdderManager;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.BooleanResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Location;

import java.util.List;

public class PlaceIABlockFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "PlaceIABlock function requires at least two arguments.");
        }
        FunctionResult idResult = args.get(0);
        FunctionResult locationResult = args.get(1);
        if (!(idResult instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        if (!(locationResult instanceof LocationResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        String id = ((StringResult) idResult).getString();
        Location location = ((LocationResult) locationResult).getLocation();
        boolean result= ItemsAdderManager.placeCustomBlock(id,location);
        return new BooleanResult(result);
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "placeIABlock";
    }
}
