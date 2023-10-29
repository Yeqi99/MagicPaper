package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import org.bukkit.Location;

public class LocationResult extends ObjectResult {

    public LocationResult(Location location) {
        super(location);
    }


    public Location getLocation() {
        return (Location) getObject();
    }
    public String getName() {
        return "Location";
    }
}
