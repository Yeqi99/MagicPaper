package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ObjectResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemNBTAddFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTAdd don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult path = args.get(1);
        FunctionResult key= args.get(2);
        FunctionResult value = args.get(3);
        if (item instanceof ItemStackResult){
            if (path instanceof StringResult && key instanceof StringResult && value instanceof ObjectResult){
                ItemStack itemStack = ((ItemStackResult) item).getItemStack();
                String pathString = ((StringResult) path).getString();
                String keyString = ((StringResult) key).getString();
                Object valueObject = ((ObjectResult) value).getObject();
                NBTItem nbtItem = new NBTItem(itemStack);
                nbtItem.set(keyString,valueObject,pathString);
                return new ItemStackResult(nbtItem.getItemStack());
            }else {
                return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTAdd don't have enough args.");
            }
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemNBTAdd don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemNBTAdd";
    }
}
