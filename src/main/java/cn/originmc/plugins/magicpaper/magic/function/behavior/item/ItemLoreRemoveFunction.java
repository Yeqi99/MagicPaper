package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.IntegerResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemLoreRemoveFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemLoreRemove don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult index = args.get(1);
        if (item instanceof ItemStackResult){
            if (index instanceof StringResult){
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                if (!itemStack.hasItemMeta()){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove item don't have lore.");
                }
                String indexString = ((StringResult) index).getString();
                if (!VariableUtil.isInt(indexString)){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove index must be a number.");
                }
                int indexInt = Integer.parseInt(indexString);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (!itemMeta.hasLore()){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove item don't have lore.");
                }
                List<String> lore = itemMeta.getLore();
                if (lore.size()<=indexInt){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove index out of range.");
                }
                lore.remove(indexInt);
                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }else {
                return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemLoreRemove don't have enough args.");
            }
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemLoreRemove don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemLoreRemove";
    }
}
