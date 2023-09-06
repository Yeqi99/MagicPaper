package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.FunctionRegister;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.DataType;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.ObjectResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemNBTGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult path = args.get(1);
        FunctionResult key= args.get(2);
        FunctionResult dataType = args.get(3);
        if (item instanceof ItemStackResult) {
            if (path instanceof StringResult && key instanceof StringResult && dataType instanceof StringResult) {
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                String pathString = ((StringResult) path).getString();
                String keyString = ((StringResult) key).getString();
                String dataTypeString = ((StringResult) dataType).getString();
                NBTItem nbtItem = new NBTItem(itemStack);
                Object value = nbtItem.get(keyString, DataType.valueOf(dataTypeString),pathString);
                return new ObjectResult(value);
            } else {
                return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTGet don't have enough args.");
            }
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTGet don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemNBTGet";
    }
}
