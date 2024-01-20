package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MergeNBTFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "MergeNBTFunction need 2 args.");
        }
        FunctionResult arg1 = args.get(0);
        FunctionResult arg2 = args.get(1);
        if (!(arg1 instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg must be itemstack.");
        }
        if (!(arg2 instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg must be itemstack.");
        }
        ItemStack itemStack1 = ((ItemStackResult) arg1).getItemStack();
        ItemStack itemStack2 = ((ItemStackResult) arg2).getItemStack();
        NBTItem nbtItem=new NBTItem(itemStack1);
        nbtItem.mergeItemNBT(itemStack2);
        return new ItemStackResult(nbtItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "mergeNBT";
    }
}
