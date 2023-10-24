package cn.originmc.plugins.magicpaper.util.location;

import org.bukkit.Location;

public class LocationUtil {
    public static boolean isLocationInRegion(Location location1, Location location2, Location targetLocation) {
        double x1 = Math.min(location1.getX(), location2.getX());
        double x2 = Math.max(location1.getX(), location2.getX());
        double y1 = Math.min(location1.getY(), location2.getY());
        double y2 = Math.max(location1.getY(), location2.getY());
        double z1 = Math.min(location1.getZ(), location2.getZ());
        double z2 = Math.max(location1.getZ(), location2.getZ());

        double targetX = targetLocation.getX();
        double targetY = targetLocation.getY();
        double targetZ = targetLocation.getZ();

        return targetX >= x1 && targetX <= x2
                && targetY >= y1 && targetY <= y2
                && targetZ >= z1 && targetZ <= z2;
    }
}
