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

public class ItemSupportBoreAddressSetFunction extends NormalFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGUMENTS_ERROR","ItemSupportBoreAddressSetFunction need 2 arguments");
        }
        FunctionResult itemResult = args.get(0);
        FunctionResult addressResult = args.get(1);
        if (!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemSupportBoreAddressSetFunction need itemStack as first argument");
        }
        if (!(addressResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemSupportBoreAddressSetFunction need String as second argument");
        }
        ItemStack itemStack = ((ItemStackResult) itemResult).getItemStack();
        String address = ((StringResult) addressResult).getString();
        MagicItem magicItem = new MagicItem(itemStack);
        magicItem.setSupportBoreAddress(address);
        return new ItemStackResult(magicItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemSupportBoreAddressSet";
    }
}
