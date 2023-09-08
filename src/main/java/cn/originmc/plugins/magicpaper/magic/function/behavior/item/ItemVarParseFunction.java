package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.ItemVariable;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemVarParseFunction extends NormalFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "ItemVarParse don't have enough args.");
        }
        FunctionResult itemResult = args.get(0);
        FunctionResult varResult = args.get(1);
        FunctionResult signResult = args.get(2);
        if(!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "The first arg of ItemVarParse must be an item.");
        }
        if(!(varResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The second arg of ItemVarParse must be a string.");
        }
        if(!(signResult instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "The third arg of ItemVarParse must be a string.");
        }
        ItemStack itemStack = ((ItemStackResult) itemResult).getItemStack();
        String var = ((StringResult) varResult).getString();
        String sign = ((StringResult) signResult).getString();
        String result= ItemVariable.parse(itemStack,var,sign.charAt(0));
        return new StringResult(result);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemVarParse";
    }
}
