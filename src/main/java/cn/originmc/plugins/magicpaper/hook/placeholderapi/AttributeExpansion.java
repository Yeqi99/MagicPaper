package cn.originmc.plugins.magicpaper.hook.placeholderapi;

import cn.origincraft.magic.Magic;
import cn.originmc.plugins.magicpaper.MagicPaper;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class AttributeExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "magicattribute";
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
        String uuid = player.getUniqueId().toString();
        if (!MagicPaper.attributeCache.attributeMap.containsKey(uuid)){
            return "0";
        }
        Map<String,Double> attributes = MagicPaper.attributeCache.attributeMap.get(uuid);
        if (!attributes.containsKey(identifier)){
            return "0";
        }
        return attributes.get(identifier)+"";
    }
}
