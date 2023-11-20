package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemAmountSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemAmountSet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult amount = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemAmountSet need a itemStack.");
        }
        if (!(amount instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAmountSet need a int str.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String amountString = ((StringResult) amount).getString();
        if (!VariableUtils.tryInt(amountString)){
            return new ErrorResult("TYPE_ERROR", "itemAmountSet need a int str.");
        }
        int amountInt = Integer.parseInt(amountString);
        itemStack.setAmount(amountInt);
        return new ItemStackResult(itemStack);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAmountSet";
    }
}
