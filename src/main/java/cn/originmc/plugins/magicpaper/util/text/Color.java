package cn.originmc.plugins.magicpaper.util.text;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Color {
    public static String toColor(String instr){
        return ChatColor.translateAlternateColorCodes('&',instr);
    }
    public static List<String> toColor(List<String> inList){
        List<String> list = new ArrayList<>();
        for (String str : inList) {
            list.add(ChatColor.translateAlternateColorCodes('&', str));
        }
        return list;
    }
    public static String returnColor(String instr){
        return instr.replace("§","&");
    }
    public static List<String> returnColor(List<String> inList){
        List<String> list = new ArrayList<>();
        for (String str : inList) {
            list.add(str.replace("§","&"));
        }
        return list;
    }
    public static String removeColor(String inStr){
        String returnStr="";
        for (int i=0;i<inStr.length();i++){
            char c=inStr.charAt(i);
            if (c=='§'){
                i++;
            }else {
                returnStr+=c;
            }
        }
        return returnStr;
    }
    public static List<String> removeColor(List<String> inList){
        List<String> returnList=new ArrayList<>();
        for (String s:inList){
            returnList.add(removeColor(s));
        }
        return returnList;
    }
}
