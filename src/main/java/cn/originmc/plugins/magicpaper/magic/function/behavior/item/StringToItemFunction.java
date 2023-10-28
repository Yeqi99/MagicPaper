package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;

import java.util.List;

public class StringToItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "StringToItem don't have enough args.");
        }
        FunctionResult string = args.get(0);
        if (string instanceof StringResult) {
            StringResult stringResult = (StringResult) string;
            return new ItemStackResult(NBTItem.toItemStack(stringResult.getString()));
        }else {
            return new ErrorResult("FUNCTION_ARGS_ERROR", "StringToItem don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "stringToItem";
    }
}
