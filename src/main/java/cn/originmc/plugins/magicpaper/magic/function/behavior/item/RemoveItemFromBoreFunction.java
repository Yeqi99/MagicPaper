package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RemoveItemFromBoreFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGUMENTS_ERROR","RemoveItemFromBoreFunction need 2 arguments");
        }
        FunctionResult itemResult = args.get(0);
        FunctionResult addressResult = args.get(1);
        FunctionResult indexResult = args.get(2);
        if (!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","RemoveItemFromBoreFunction need itemStack as first argument");
        }
        if (!(addressResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","RemoveItemFromBoreFunction need String as second argument");
        }
        if (!(indexResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","RemoveItemFromBoresFunction need String as second argument");
        }

        String index = ((StringResult) indexResult).getString();
        if (VariableUtil.tryInt(index)){
            return new ErrorResult("ARGUMENTS_ERROR","RemoveItemFromBoreFunction need String int as second argument");
        }
        ItemStack itemStack = ((ItemStackResult) itemResult).getItemStack();
        String address = ((StringResult) addressResult).getString();
        MagicItem magicItem = new MagicItem(itemStack);
        if (!magicItem.hasBore(address)){
            return new ItemStackResult(itemStack);
        }
        ItemStack removeItem= magicItem.removeItemFromBore(address,Integer.parseInt(index));
        if (removeItem==null){
            return new ItemStackResult(itemStack);
        }
        spellContext.getContextMap().putVariable("removeItem",new ItemStackResult(removeItem));
        return new ItemStackResult(magicItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "removeItemFromBore";
    }
}
