package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemFlagFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemFlag don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult flag = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemFlag need a itemStack.");
        }
        if (!(flag instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemFlag need a string int.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String flagString = ((StringResult) flag).getString();
        if (!VariableUtils.tryInt(flagString)){
            return new ErrorResult("TYPE_ERROR", "itemFlag need a string int.");
        }
        int flagInt = Integer.parseInt(flagString);
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setHideFlags(flagInt);
        return new ItemStackResult(nbtItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemFlag";
    }
}
