package cn.originmc.plugins.magicpaper.util.item;

import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.data.manager.ItemFormatManager;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MagicItem extends NBTItem {
    public MagicItem(ItemStack itemStack) {
        super(itemStack);
    }

    public MagicItem(String itemStack) {
        super(itemStack);
    }

    public void enableRefreshVar() {
        set("refresh-var", true, "/MagicPaper");
    }

    public void disableRefreshVar() {
        set("refresh-var", false, "/MagicPaper");
    }

    public boolean isRefreshVar() {
        if (!hasKey("refresh-var", "/MagicPaper")) {
            return false;
        }
        return (boolean) get("refresh-var", DataType.BOOLEAN, "/MagicPaper");
    }

    public void setFormat(String id) {
        set("lore-format", id, "/MagicPaper");
    }

    public String getFormat() {
        return (String) get("lore-format", DataType.STRING, "/MagicPaper");
    }

    public void refreshVar() {
        if (!isRefreshVar()) {
            return;
        }
        String format_id = getFormat();
        if (format_id == null) {
            return;
        }
        List<String> format = ItemFormatManager.getFormat(format_id);
        if (format == null) {
            return;
        }
        refreshVar(format, '*');
    }

    public void refreshPapi(Player player) {
        if (!PlaceholderAPIHook.status) {
            return;
        }
        String format_id = getFormat();
        if (format_id == null) {
            return;
        }
        List<String> format = ItemFormatManager.getFormat(format_id);
        if (format == null) {
            return;
        }
        refreshPapi(format, player);
    }


}
