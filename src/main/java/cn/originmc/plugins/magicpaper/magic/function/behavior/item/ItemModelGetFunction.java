package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemModelGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemModelGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        if(!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemModelGet need a itemStack.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        ItemMeta itemMeta=itemStack.getItemMeta();
        if(!itemMeta.hasCustomModelData()){
            return new IntegerResult(-1);
        }
        return new IntegerResult(itemMeta.getCustomModelData());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemModelGet";
    }
}
