package cn.originmc.plugins.magicpaper.util.item;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemVariable {

    public static List<String> parse(ItemStack itemStack,List<String> lines,char sign){
        List<String> newLines=new ArrayList<>();
        for (String s : lines) {
            if (s.equalsIgnoreCase("!info")){
                MagicItem magicItem=new MagicItem(itemStack);
                List<String> info = magicItem.getInfo();
                if (info==null){
                    continue;
                }
                newLines.addAll(info);
                continue;
            }
            String vs=parse(itemStack,s,sign);
            if (vs.isEmpty()){
                continue;
            }
            newLines.add(vs);
        }
        return newLines;
    }

    public static String parse(ItemStack itemStack,String line,char sign){
        VariableString variableString=new VariableString(line,sign);
        NBTItem nbtItem=new NBTItem(itemStack);
        for (String s : variableString.getAllVariable()) {
            if (s.contains("!")){
                String str = s.replace("!", "");
                if (nbtItem.hasSpace(str)){
                    variableString.setVariable(s,"");
                    continue;
                }else {
                    return "";
                }
            }
            String[] split = s.split(":");
            String path=split[0];
            String key=split[1];
            String dataType=split[2];
            if (nbtItem.hasKey(key,path)){
                String value= nbtItem.get(key,DataType.valueOf(dataType),path)+"";
                variableString.setVariable(s,value);
            }else {
                return "";
            }
        }
        return variableString.getResultString();
    }

}
