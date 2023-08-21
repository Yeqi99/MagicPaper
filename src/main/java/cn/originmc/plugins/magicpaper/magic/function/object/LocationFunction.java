package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.LocationResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.DoubleResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class LocationFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            if (spellContext.getContextMap().hasObject("self")) {
                if (spellContext.getContextMap().getObject("self") instanceof Player){
                    Player player=(Player) spellContext.getContextMap().getObject("self");
                    return new LocationResult(player.getLocation());
                }else {
                    return new ErrorResult("ERROR", "self is not a player.");
                }
            }
        }
        if (args.size()==1){
            if (args.get(0) instanceof PlayerResult){
                PlayerResult playerResult=(PlayerResult) args.get(0);
                return new LocationResult(playerResult.getPlayer().getLocation());
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }
        if (args.size()==3) {
            FunctionResult x = args.get(0);
            FunctionResult y = args.get(1);
            FunctionResult z = args.get(2);
            if (x instanceof DoubleResult) {
                if (y instanceof DoubleResult) {
                    if (z instanceof DoubleResult) {
                        Location location = new Location(null, ((DoubleResult) x).getDouble(), ((DoubleResult) y).getDouble(), ((DoubleResult) z).getDouble());
                        return new LocationResult(location);
                    } else {
                        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                    }
                } else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            } else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }
        if (args.size()==4) {
            FunctionResult x = args.get(0);
            FunctionResult y = args.get(1);
            FunctionResult z = args.get(2);
            FunctionResult world = args.get(3);
            if (x instanceof DoubleResult) {
                if (y instanceof DoubleResult) {
                    if (z instanceof DoubleResult) {
                        if (world instanceof StringResult) {
                            Location location = new Location(null, ((DoubleResult) x).getDouble(), ((DoubleResult) y).getDouble(), ((DoubleResult) z).getDouble());
                            location.setWorld(Bukkit.getWorld(((StringResult) world).getString()));
                            return new LocationResult(location);
                        } else {
                            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                        }
                    } else {
                        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                    }
                } else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            } else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }
        if (args.size()==5){
            FunctionResult x = args.get(0);
            FunctionResult y = args.get(1);
            FunctionResult z = args.get(2);
            FunctionResult yaw = args.get(3);
            FunctionResult pitch = args.get(4);
            if (x instanceof DoubleResult) {
                if (y instanceof DoubleResult) {
                    if (z instanceof DoubleResult) {
                        if (yaw instanceof DoubleResult) {
                            if (pitch instanceof DoubleResult) {
                                Location location = new Location(null, ((DoubleResult) x).getDouble(), ((DoubleResult) y).getDouble(), ((DoubleResult) z).getDouble());
                                location.setYaw((float) ((DoubleResult) yaw).getDouble());
                                location.setPitch((float) ((DoubleResult) pitch).getDouble());
                                return new LocationResult(location);
                            } else {
                                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                            }
                        } else {
                            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                        }
                    } else {
                        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                    }
                } else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            } else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }
        if (args.size()==6){
            FunctionResult x = args.get(0);
            FunctionResult y = args.get(1);
            FunctionResult z = args.get(2);
            FunctionResult yaw = args.get(3);
            FunctionResult pitch = args.get(4);
            FunctionResult world = args.get(5);
            if (x instanceof DoubleResult) {
                if (y instanceof DoubleResult) {
                    if (z instanceof DoubleResult) {
                        if (yaw instanceof DoubleResult) {
                            if (pitch instanceof DoubleResult) {
                                if (world instanceof StringResult) {
                                    Location location = new Location(null, ((DoubleResult) x).getDouble(), ((DoubleResult) y).getDouble(), ((DoubleResult) z).getDouble());
                                    location.setYaw((float) ((DoubleResult) yaw).getDouble());
                                    location.setPitch((float) ((DoubleResult) pitch).getDouble());
                                    location.setWorld(Bukkit.getWorld(((StringResult) world).getString()));
                                    return new LocationResult(location);
                                } else {
                                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                                }
                            } else {
                                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                            }
                        } else {
                            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                        }
                    } else {
                        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                    }
                } else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            } else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }
        return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "location";
    }
}
