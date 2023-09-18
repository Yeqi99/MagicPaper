package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.text.Color;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemLoreAddLineFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "ItemLoreAddLine function requires at least two arguments.");
        }
        FunctionResult item = args.get(0);
        FunctionResult line = args.get(1);
        if (item instanceof ItemStackResult){
            ItemStack itemStack=((ItemStackResult) item).getItemStack();
            if (!itemStack.hasItemMeta()){
                itemStack.setItemMeta(itemStack.getItemMeta());
            }
            if (line instanceof StringResult){
                ItemMeta itemMeta=itemStack.getItemMeta();
                String string=((StringResult) line).getString();
                string= Color.toColor(string);
                List<String> lore=null;
                if (!itemStack.getItemMeta().hasLore()){
                    lore=new ArrayList<>();
                    lore.add(string);
                }else {
                    lore=itemStack.getItemMeta().getLore();
                    lore.add(string);
                }
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }

    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemLoreAddLine";
    }
}
