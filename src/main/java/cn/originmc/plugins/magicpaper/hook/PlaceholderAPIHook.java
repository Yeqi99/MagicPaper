package cn.originmc.plugins.magicpaper.hook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderAPIHook{
    public static boolean status = false;

    public static String getName() {
        return "PlaceholderAPI";
    }

    public static void hook() {
        status=Hook.hook(getName());
    }


    public static String getPlaceholder(Player player, String inStr){
        if(status){
            return PlaceholderAPI.setPlaceholders(player,inStr);
        }else {
            return inStr;
        }
    }
    public static List<String> getPlaceholder(Player player, List<String> strList){
        List<String> returnList=new ArrayList<>();
        for(String s:strList){
            returnList.add(getPlaceholder(player,s));
        }
        return returnList;
    }
}
