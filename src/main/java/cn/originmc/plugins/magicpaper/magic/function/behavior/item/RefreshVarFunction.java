package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.data.manager.ItemFormatManager;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RefreshVarFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("ARGUMENTS_FUNCTION_ARGS_ERROR", "Arguments don't have enough args.");
        }
        FunctionResult itemResult=args.get(0);
        FunctionResult formatNameResult=args.get(1);
        if (!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_FUNCTION_ARGS_ERROR", "Arguments type error.");
        }
        if (!(formatNameResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_FUNCTION_ARGS_ERROR", "Arguments type error.");
        }
        ItemStack item=((ItemStackResult) itemResult).getItemStack();
        String formatName=((StringResult) formatNameResult).getString();
        NBTItem nbtItem=new NBTItem(item);
        List<String> format= ItemFormatManager.getFormat(formatName);
        nbtItem.refreshVar(format,'*');
        return new ItemStackResult(nbtItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "refreshVar";
    }
}
