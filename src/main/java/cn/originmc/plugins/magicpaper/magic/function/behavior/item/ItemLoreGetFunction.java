package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemLoreGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemLoreGet don't have enough args.");
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
                int indexInt = Integer.parseInt(indexString);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (!itemMeta.hasLore()){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove item don't have lore.");
                }
                List<String> lore = itemMeta.getLore();
                if (Objects.requireNonNull(lore).size()<=indexInt){
                    return new ErrorResult("TYPE_ERROR", "ItemLoreRemove index out of range.");
                }
                return new StringResult(lore.get(indexInt));
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
        return "itemLoreGet";
    }
}
