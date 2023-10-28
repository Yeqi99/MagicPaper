package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemAmountGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemAmountGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        if(!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemAmountGet need a itemStack.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        return new IntegerResult(itemStack.getAmount());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAmountGet";
    }
}
