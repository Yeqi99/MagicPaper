package cn.originmc.plugins.magicpaper.data.manager;

import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;

import java.util.List;

public class ItemFormatManager {
    public static List<String> getFormat(String id){
        return (List<String>) ItemFormatData.yamlManager.get(id,"format",null);
    }
}
