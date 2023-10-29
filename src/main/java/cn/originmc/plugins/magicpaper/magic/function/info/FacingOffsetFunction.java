package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.util.location.LocationUtil;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class FacingOffsetFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        if (argsSetting.getId().equalsIgnoreCase("A")){
            Location location= (Location) args.get(0).getObject();
            String horizontalAngle= (String) args.get(1).getObject();
            String verticalAngle= (String) args.get(2).getObject();
            String distance= (String) args.get(3).getObject();
            double horizontalAngle1=Double.parseDouble(horizontalAngle);
            double verticalAngle1=Double.parseDouble(verticalAngle);
            double distance1=Double.parseDouble(distance);
            return new LocationResult(LocationUtil.getFacingOffsetLocation(location,horizontalAngle1,verticalAngle1,distance1));
        }else if (argsSetting.getId().equalsIgnoreCase("B")){
            Location location= (Location) args.get(0).getObject();
            Location targetLocation= (Location) args.get(1).getObject();
            String distance= (String) args.get(2).getObject();
            double distance1=Double.parseDouble(distance);
            return new LocationResult(LocationUtil.moveTowardsTarget(location,targetLocation,distance1));
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                4,
                "Location String String String",
                "location horizontalAngle verticalAngle distance" +
                        "\nGet the location's facing offset location.",
                "Location");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                4,
                "Location Location String",
                "location targetLocation distance" +
                        "\nGet the location's facing offset location.",
                "Location");
        argsSetting2.setId("B");
        return argsSettings;
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }

    @Override
    public String getName() {
        return "facingOffset";
    }
}
