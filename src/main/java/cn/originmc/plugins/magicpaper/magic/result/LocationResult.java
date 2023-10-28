package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.expression.functions.FunctionResult;
import org.bukkit.Location;

public class LocationResult extends FunctionResult {
    private final Location location;

    public LocationResult(Location location) {
        this.location = location;
    }


    public Location getLocation() {
        return location;
    }

}
