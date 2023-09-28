package cn.originmc.plugins.magicpaper.magic.function.hook.itemsadder;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.itemsadder.ItemsAdderManager;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class IAItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "IAItem function requires at least one argument.");
        }
        FunctionResult idResult = args.get(0);
        if (!(idResult instanceof StringResult)){
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
        String id = ((StringResult) idResult).getString();
        ItemStack itemStack= ItemsAdderManager.getItemStack(id);
        if (itemStack==null){
            return new ErrorResult("ERROR", "Failed to get item, need true item id.");
        }
        return new ItemStackResult(itemStack);
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "IAItem";
    }
}
