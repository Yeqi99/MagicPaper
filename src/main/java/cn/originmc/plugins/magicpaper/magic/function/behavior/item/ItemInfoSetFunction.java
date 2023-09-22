package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemInfoSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size() < 2) {
            return new ErrorResult("ARGS_NOT_ENOUGH","Dont have enough args");
        }
        FunctionResult arg0 = args.get(0);
        FunctionResult arg1 = args.get(1);
        if(!(arg0 instanceof ItemStackResult)){
            return new ErrorResult("ARGS_ERROR","The first arg must be itemstack");
        }
        if(!(arg1 instanceof StringResult)){
            return new ErrorResult("ARGS_ERROR","The second arg must be string");
        }
        ItemStack itemStack = ((ItemStackResult) arg0).getItemStack();
        String info = ((StringResult) arg1).getString();
        MagicItem magicItem=new MagicItem(itemStack);
        magicItem.setInfo(info);
        return new ItemStackResult(magicItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemInfoSet";
    }
}
