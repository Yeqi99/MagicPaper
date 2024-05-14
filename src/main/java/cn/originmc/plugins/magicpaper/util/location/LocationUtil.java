package cn.originmc.plugins.magicpaper.util.location;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

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
    public static Location getFacingOffsetLocation(Location location, double horizontalAngle, double verticalAngle, double distance) {

        // 计算水平和垂直偏移的弧度
        double horizontalRadians = Math.toRadians(horizontalAngle);
        double verticalRadians = Math.toRadians(verticalAngle);

        // 获取玩家的方向向量
        Vector direction = location.getDirection();

        // 根据水平和垂直弧度进行旋转
        double x = direction.getX();
        double z = direction.getZ();
        direction.setX(x * Math.cos(horizontalRadians) - z * Math.sin(horizontalRadians));
        direction.setZ(x * Math.sin(horizontalRadians) + z * Math.cos(horizontalRadians));
        direction.setY(Math.sin(verticalRadians));

        // 将方向向量标准化并乘以距离
        Vector offset = direction.normalize().multiply(distance);

        // 添加偏移向量到玩家的位置以获得新的坐标

        return location.clone().add(offset);
    }
    public static Location moveTowardsTarget(Location location, Location targetLocation, double distance) {
        // 计算从玩家位置到目标位置的方向向量
        Vector direction = targetLocation.toVector().subtract(location.toVector()).normalize();

        // 将方向向量乘以距离以获取移动偏移
        Vector offset = direction.multiply(distance);


        return location.clone().add(offset);
    }

    public static int findSafeY(World world, int x, int z) {
        for (int y = world.getMaxHeight() - 1; y > -65; y--) {
            Material materialBelow = world.getBlockAt(x, y - 1, z).getType();
            Material materialAt = world.getBlockAt(x, y, z).getType();
            Material materialAbove = world.getBlockAt(x, y + 1, z).getType();

            // Check if the block below is solid, the block at the location is air, and above is also air.
            if (materialBelow.isSolid() && materialAt == Material.AIR && materialAbove == Material.AIR) {
                return y + 1;  // Return the Y coordinate above the solid block
            }
        }
        return -999;  // Return -1 if no safe location is found
    }
    public static Location moveToSafeLocation(Location location) {
        return getSafeLocation(location.getWorld(), location.getBlockX(), location.getBlockZ());
    }

    public static Location getSafeLocation(World world, int x, int z) {
        int safeY = findSafeY(world, x, z);
        if (safeY != -999) {
            return new Location(world, x + 0.5, safeY, z + 0.5);  // Center the player on the block
        }
        return null;  // Return null if no safe location is found
    }
}
