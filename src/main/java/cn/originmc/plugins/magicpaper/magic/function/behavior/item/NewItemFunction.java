package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class NewItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "Item don't have enough args.");
        }
        FunctionResult typeResult = args.get(0);
        if (!(typeResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg of Item must be a string.");
        }
        String type = ((StringResult) typeResult).getString();
        return new ItemStackResult(new ItemStack(Material.valueOf(type.toUpperCase())));
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "newItem";
    }
}
