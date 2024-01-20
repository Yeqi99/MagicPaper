package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemTypeGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemTypeGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        if (item instanceof ItemStackResult){
            ItemStack itemStack = ((ItemStackResult) item).getItemStack();
            return new StringResult(itemStack.getType().toString());
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemTypeGet don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemTypeGet";
    }
}
