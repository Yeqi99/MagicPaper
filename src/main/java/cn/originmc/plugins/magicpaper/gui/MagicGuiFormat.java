package cn.originmc.plugins.magicpaper.gui;

import cn.originmc.plugins.magicpaper.util.list.ListUtil;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class MagicGuiFormat {
    private final char[][] format;

    private Map<Character, ItemStack> buttonItemMap;

    public MagicGuiFormat(char[][] format, Map<Character, ItemStack> buttonItemMap) {
        this.format = format;
        this.buttonItemMap = buttonItemMap;
    }
    public MagicGuiFormat(List<String> stringList, Map<Character, ItemStack> buttonItemMap) {
        this.format = ListUtil.stringListToCharArray(stringList);
        this.buttonItemMap = buttonItemMap;
    }

    public void apply(Inventory inventory) {
        for (int i = 0; i < format.length; i++) {
            for (int j = 0; j < format[i].length; j++) {
                if (i*format[i].length+j>=inventory.getSize()){
                    break;
                }
                char c = format[i][j];
                if (buttonItemMap.containsKey(c)) {
                    inventory.setItem(i * format[i].length + j, buttonItemMap.get(c));
                }
            }
        }
    }


    public char getIndex(int index) {
        int x = index / format[0].length;
        int y = index % format[0].length;
        return format[x][y];
    }

    public char[][] getFormat() {
        return format;
    }

    public Map<Character, ItemStack> getButtonItemMap() {
        return buttonItemMap;
    }

    public void setButtonItemMap(Map<Character, ItemStack> buttonItemMap) {
        this.buttonItemMap = buttonItemMap;
    }
    public int getButtonAmount(char c){
        int amount=0;
        for (char[] chars : format) {
            for (char aChar : chars) {
                if (aChar==c){
                    amount++;
                }
            }
        }
        return amount;
    }
    public int setButton(char c,ItemStack itemStack,Inventory inventory){
        int amount=0;
        for (int i = 0; i < format.length; i++) {
            for (int j = 0; j < format[i].length; j++) {
                if (format[i][j]==c){
                    inventory.setItem(i*format[i].length+j,itemStack);
                    amount++;
                }
            }
        }
        return amount;
    }
}
