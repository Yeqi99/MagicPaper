package cn.originmc.plugins.magicpaper.util.item;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Item;
import org.bukkit.inventory.EquipmentSlot;
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
            if (s.contains("^")){
                String str = s.replace("^", "");
                String[] split = str.split(":");
                String address=split[0];
                String key=split[1];
                String itemAddress=split[2];
                String itemKey=split[3];
                String type=split[4];
                String defaultValue=split[5];
                if (nbtItem.hasKey(key,address)) {
                    ItemStack item = (ItemStack) nbtItem.get(key, DataType.ITEMSTACK,address);
                    if (item==null){
                        variableString.setVariable(s,defaultValue);
                        continue;
                    }
                    NBTItem inItem=new NBTItem(item);
                    if (!inItem.hasKey(itemKey,itemAddress)){
                        variableString.setVariable(s,defaultValue);
                        continue;
                    }
                    String value = inItem.get(itemKey, DataType.valueOf(type),itemAddress)+"";
                    variableString.setVariable(s,value);
                    continue;
                }else {
                    return "";
                }
            }
            // 获取某个key上存的物品名字
            if (s.contains("$")){
                String str = s.replace("$", "");
                String[] split = str.split(":");
                String address=split[0];
                String key=split[1];
                if (nbtItem.hasKey(key,address)) {
                    ItemStack item = (ItemStack) nbtItem.get(key, DataType.ITEMSTACK,address);
                    if (item==null){
                        return "";
                    }
                    variableString.setVariable(s,item.getItemMeta().getDisplayName());
                    continue;
                }
            }
            // 附魔变量
            if (s.contains("@")){
                String str = s.replace("@", "");
                int eLevel= nbtItem.getEnchantmentLevel(str);
                if (eLevel>0){
                    variableString.setVariable(s,eLevel+"");
                    continue;
                }else {
                    return "";
                }
            }
            // 原版属性变量
            if (s.contains("#")){
                String str = s.replace("#", "");
                String[] split = str.split(":");
                String id=split[0];
                Attribute attribute=Attribute.valueOf(split[1]);
                AttributeModifier.Operation operation=NBTItem.getOperation(split[2]);
                EquipmentSlot equipmentSlot= NBTItem.getSlot(split[3]);
                double aValue= nbtItem.getAttributeValue(id,attribute,operation,equipmentSlot);
                if (aValue>0) {
                    variableString.setVariable(s, aValue + "");
                    continue;
                }else {
                    return "";
                }
            }
            if (s.contains("%")){
                String str = s.replace("%", "");
                String[] split = str.split(":");
                String path=split[0];
                String key=split[1];
                if (nbtItem.hasKey(key,path)){
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
