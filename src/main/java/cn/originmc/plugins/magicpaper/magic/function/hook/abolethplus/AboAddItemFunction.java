package cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import ray.maplex.top.abolethplusadder.AbolethPlusAdder;

import java.util.List;
import java.util.UUID;

public class AboAddItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboAdd don't have enough args.");
        }
        FunctionResult uuid = args.get(0);
        FunctionResult key = args.get(1);
        FunctionResult item = args.get(2);
        if (uuid instanceof StringResult && key instanceof StringResult && item instanceof ItemStackResult) {
            StringResult uuidString = (StringResult) uuid;
            StringResult keyString = (StringResult) key;
            ItemStackResult itemStackResult = (ItemStackResult) item;
            String itemString = NBTItem.toString(itemStackResult.getItemStack());
            AbolethPlusAdder.add(UUID.fromString(uuidString.getString()), keyString.getString(), itemString);
            return new NullResult();
        }else {
            return new ErrorResult("TYPE_ERROR", "AboAdd args type error.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "aboAddItem";
    }
}
