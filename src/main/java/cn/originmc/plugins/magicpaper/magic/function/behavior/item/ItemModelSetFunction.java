package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemModelSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemModelSet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult model = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemModelSet need a itemStack.");
        }
        if (!(model instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemModelSet need a string.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String modelString = ((StringResult) model).getString();
        if (!VariableUtil.tryInt(modelString)){
            return new ErrorResult("TYPE_ERROR", "itemModelSet need a int str.");
        }
        int modelInt = Integer.parseInt(modelString);
        ItemMeta itemMeta=itemStack.getItemMeta();
        itemMeta.setCustomModelData(modelInt);
        itemStack.setItemMeta(itemMeta);
        return new ItemStackResult(itemStack);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemModelSet";
    }
}
