package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Objects;

public class ItemLoreHasLineNoColorFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemLoreHasLine don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult line = args.get(1);
        if (item instanceof ItemStackResult){
            if (line instanceof StringResult){
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                if (!itemStack.hasItemMeta()){
                    return new BooleanResult(false);
                }
                if (!itemStack.getItemMeta().hasLore()){
                    return new BooleanResult(false);
                }
                String lineString = ((StringResult) line).getString();
                ItemMeta itemMeta = itemStack.getItemMeta();
                List<String> lore = Color.removeColor(Objects.requireNonNull(itemMeta.getLore()));
                if (lore.contains(lineString)){
                    return new BooleanResult(true);
                }else {
                    return new BooleanResult(false);
                }
            }else {
                return new ErrorResult("TYPE_ERROR", "The second arg of ItemLoreHasLine must be a string.");
            }
        }else {
            return new ErrorResult("TYPE_ERROR", "The first arg of ItemLoreHasLine must be an item.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemLoreHasLineNoColor";
    }
}
