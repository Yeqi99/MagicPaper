package cn.originmc.plugins.magicpaper.hook.placeholderapi;

import cn.origincraft.magic.utils.ResultUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DataEntityExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "magicdata";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Yeqi";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.5.15";
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String identifier) {
        String[] args= identifier.split("_");
        return ResultUtils.reduction(MagicPaper.dataEntityManager.getData(args[0],args[1])).toString();
    }
}
