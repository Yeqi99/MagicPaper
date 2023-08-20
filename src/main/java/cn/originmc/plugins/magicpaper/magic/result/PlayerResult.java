package cn.originmc.plugins.magicpaper.magic.result;

import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.entity.Player;

public class PlayerResult extends FunctionResult {
    private final Player player;
    public PlayerResult(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
