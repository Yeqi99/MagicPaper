package cn.originmc.plugins.magicpaper.magic.result;

import org.bukkit.entity.Player;

public class PlayerResult extends EntityResult {
    public PlayerResult(Player player) {
        super(player);
    }

    public Player getPlayer() {
        return (Player) getObject();
    }
    public String getName() {
        return "Player";
    }
}
