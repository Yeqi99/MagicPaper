package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemTypeSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemTypeSet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult type = args.get(1);
        if (item instanceof ItemStackResult){
            if (type instanceof StringResult){
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                String typeString = ((StringResult) type).getString();
                itemStack.setType(Material.valueOf(typeString));
                return new ItemStackResult(itemStack);
            }else {
                return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemTypeSet don't have enough args.");
            }
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemTypeSet don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemTypeSet";
    }
}
