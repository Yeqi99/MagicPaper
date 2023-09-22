package cn.originmc.plugins.magicpaper.util.item;

import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.data.manager.ItemFormatManager;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
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

    public void addInfo(String info){
        if (!hasKey("info", "/ItemInfo")){
            setInfo(info);
            return;
        }
        String s= (String) get("info",DataType.STRING,"/ItemInfo");
        s=s+"|"+info;
        set("info", s, "/ItemInfo");
    }
    public void setInfo(String info) {
        set("info", info, "/ItemInfo");
    }
    public List<String> getInfo(){
        if (!hasKey("info", "/ItemInfo")){
            return null;
        }
        String s= (String) get("info",DataType.STRING,"/ItemInfo");
        String[] strs= s.split("\\|");
        List<String> list=new ArrayList<>();
        for (String str : strs) {
            list.add(Color.toColor(str));
        }
        return list;
    }

    public void refresh(boolean refreshVar, boolean refreshPapi, Player player) {
        List<String> lore=new ArrayList<>();
        if (refreshVar) {
            if (!isRefreshVar()) {
                return ;
            }
            String format_id = getFormat();
            if (format_id == null) {
                return;
            }
            lore = ItemFormatManager.getFormat(format_id);
            if (lore == null) {
                return;
            }
            lore = refreshVar(lore, '*');
        }
        if (refreshPapi) {
            if (!PlaceholderAPIHook.status) {
                return;
            }
            if (lore == null) {
                return;
            }
            refreshPapi(lore, player);
        }
    }



}
