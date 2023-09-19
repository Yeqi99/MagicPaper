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

public class ItemFormatSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "ItemEnableRefresh function requires two arguments.");
        }
        FunctionResult item = args.get(0);
        FunctionResult formatName = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        if (!(formatName instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String formatNameString = ((StringResult) formatName).getString();
        MagicItem magicItem=new MagicItem(itemStack);
        magicItem.setFormat(formatNameString);
        return new ItemStackResult(magicItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemFormatSet";
    }
}
