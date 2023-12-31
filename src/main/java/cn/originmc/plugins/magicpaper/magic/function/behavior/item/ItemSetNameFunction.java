package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemSetNameFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemSetName don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult name = args.get(1);
        if (item instanceof ItemStackResult){
            if (name instanceof StringResult){
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                String nameString = ((StringResult) name).getString();
                nameString = Color.toColor(nameString);
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(nameString);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }else {
                return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemSetName don't have enough args.");
            }
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemSetName don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemSetName";
    }
}
