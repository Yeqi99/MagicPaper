package lol.imc.minecraft.respawn;


import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.Location;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    public void onPlayerFirsJoin(PlayerJoinEvent event){
        if (RespawnManager.hasRespawnPoint(event.getPlayer())){
            return;
        }else {
            while(true){
                Location location = RespawnManager.getNextPoint();
                if (location!=null){
                    RespawnManager.setRespawnPoint(event.getPlayer(), location.getWorld().getName(), location.getBlockX(), location.getBlockZ());
                    break;
                }
            }
        }
    }
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.isAnchorSpawn() || event.isBedSpawn()){
            return;
        }
        if (RespawnManager.hasRespawnPoint(event.getPlayer())){
            event.setRespawnLocation(RespawnManager.getRespawnPoint(event.getPlayer()));
        }else {
            MagicPaper.getSender().sendToLogger("&c玩家&e" + event.getPlayer().getName() + "&c重生点错误");
            return;
        }
    }
}
