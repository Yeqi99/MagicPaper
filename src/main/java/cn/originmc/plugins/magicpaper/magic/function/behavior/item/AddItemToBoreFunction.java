package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class AddItemToBoreFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGUMENTS_ERROR","AddItemToBoreFunction need 2 arguments");
        }
        FunctionResult itemResult = args.get(0);
        FunctionResult addItemsResult = args.get(1);
        if (!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","AddItemToBoreFunction need itemStack as first argument");
        }
        if (!(addItemsResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","AddItemToBoreFunction need itemStack as second argument");
        }
        ItemStack itemStack = ((ItemStackResult) itemResult).getItemStack();
        ItemStack addItems = ((ItemStackResult) addItemsResult).getItemStack();
        MagicItem magicItem = new MagicItem(itemStack);
        boolean flag= magicItem.addItemToBore(addItems);
        if (flag){
            return new ItemStackResult(magicItem.getItemStack());
        }else {
            return new ItemStackResult(itemStack);
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "addItemToBore";
    }
}
