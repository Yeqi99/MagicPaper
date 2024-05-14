package lol.imc.minecraft.respawn;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.util.location.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class RespawnManager {
    public static boolean hasRespawnPoint(Player player) {
        String uuid = player.getUniqueId().toString();
        return RespawnData.yamlManager.hasElement(uuid);
    }
    public static void setRespawnPoint(Player player, String world, int x, int z) {
        String uuid = player.getUniqueId().toString();
        RespawnData.yamlManager.create(uuid);
        RespawnData.yamlManager.set(uuid, "respawn_location.world", world);
        RespawnData.yamlManager.set(uuid, "respawn_location.x", x);
        RespawnData.yamlManager.set(uuid, "respawn_location.z", z);
        RespawnData.yamlManager.save(uuid);
    }

    public static Location getRespawnPoint(Player player) {
        String uuid = player.getUniqueId().toString();
        if (RespawnData.yamlManager.hasElement(uuid)) {
            String worldString = (String) RespawnData.yamlManager.get(uuid, "respawn_location.world");
            World world= Bukkit.getWorld(worldString);
            if (world==null){
                return null;
            }
            int x = (int) RespawnData.yamlManager.get(uuid, "respawn_location.x");
            int z = (int) RespawnData.yamlManager.get(uuid, "respawn_location.z");
            return LocationUtil.getSafeLocation(world, x, z);
        }else {
            return null;
        }
    }
    public static Location getCenter() {
        String worldString = MagicPaper.getInstance().getConfig().getString("respawn-control.center.world","world");
        World world= Bukkit.getWorld(worldString);
        if (world==null){
            return null;
        }
        int x = MagicPaper.getInstance().getConfig().getInt("respawn-control.center.x",0);
        int z = MagicPaper.getInstance().getConfig().getInt("respawn-control.center.z",0);
        return LocationUtil.getSafeLocation(world, x, z);
    }

    public static Location getNextPoint(){
        int radius = MagicPaper.getInstance().getConfig().getInt("respawn-control.radius",20);
        int latest = MagicPaper.getInstance().getConfig().getInt("respawn-control.latest",1);
        int basic = (latest / 4) * radius;
        int mode = latest % 4;
        Location center = getCenter();
        Location result = null;
        switch (mode) {
            case 0:{
                result= center.clone().add(basic,0,0);
            }
            case 1:{
                result= center.clone().add(0,0,basic);
            }
            case 2:{
                result= center.clone().add(-basic,0,0);
            }
            case 3:{
                result= center.clone().add(0,0,-basic);
            }
        }
        if (result!=null){
            result=LocationUtil.moveToSafeLocation(result);
            MagicPaper.getInstance().getConfig().set("respawn-control.latest",latest+1);
            return result;
        }
        return null;
    }
}
