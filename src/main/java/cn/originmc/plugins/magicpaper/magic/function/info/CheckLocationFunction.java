package cn.originmc.plugins.magicpaper.magic.function.info;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CheckLocationFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id=argsSetting.getId();
        switch (id){
            case "A":{
                Location location1= (Location) args.get(0).getObject();
                Location location2= (Location) args.get(1).getObject();
                if (!location1.getWorld().equals(location2.getWorld())){
                    return new BooleanResult(false);
                }
                if (location1.getX()!=location2.getX()){
                    return new BooleanResult(false);
                }
                if (location1.getY()!=location2.getY()){
                    return new BooleanResult(false);
                }
                if (location1.getZ()!=location2.getZ()){
                    return new BooleanResult(false);
                }
                if (location1.getYaw()!=location2.getYaw()){
                    return new BooleanResult(false);
                }
                if (location1.getPitch()!=location2.getPitch()){
                    return new BooleanResult(false);
                }
                return new BooleanResult(true);
            }
            case "B":{
                Location location1= (Location) args.get(0).getObject();
                Location location2= (Location) args.get(1).getObject();
                String selection= (String) args.get(2).getObject();
                String[] split = selection.split(",");
                for (String s : split) {
                    if (s.equalsIgnoreCase("x")){
                        if (location1.getX()!=location2.getX()){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("y")){
                        if (location1.getY()!=location2.getY()){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("z")){
                        if (location1.getZ()!=location2.getZ()){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("yaw")){
                        if (location1.getYaw()!=location2.getYaw()){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("pitch")){
                        if (location1.getPitch()!=location2.getPitch()){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("world")){
                        if (!location1.getWorld().equals(location2.getWorld())){
                            return new BooleanResult(false);
                        }
                    }
                    if (s.equalsIgnoreCase("block")){
                        if (!location1.getBlock().equals(location2.getBlock())){
                            return new BooleanResult(false);
                        }
                    }
                }
                return new BooleanResult(true);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings=new ArrayList<>();
        ArgsSetting argsSetting1= FunctionUtils.createArgsSetting(
                "Location Location",
                "location location" +
                        "\n Check if the two locations are the same.",
                "Location");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2= FunctionUtils.createArgsSetting(
                "Location Location String",
                "location location selection" +
                        "\n Check if the two locations are the same by the selection.",
                "Location");
        argsSetting2.setId("B");

        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        return argsSettings;
    }

    @Override
    public String getName() {
        return "checkLocation";
    }

    @Override
    public String getType() {
        return "PAPER_INFO";
    }
}
