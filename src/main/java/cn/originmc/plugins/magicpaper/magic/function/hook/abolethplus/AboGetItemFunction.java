package cn.originmc.plugins.magicpaper.magic.function.hook.abolethplus;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Item;
import ray.maplex.top.abolethplusadder.AbolethDtoAdp;
import ray.maplex.top.abolethplusadder.AbolethPlusAdder;

import java.util.List;
import java.util.UUID;

public class AboGetItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "AboGet don't have enough args.");
        }
        FunctionResult uuid = args.get(0);
        FunctionResult key = args.get(1);
        if (uuid instanceof StringResult && key instanceof StringResult) {
            String uuidString = ((StringResult) uuid).getString();
            String keyString = ((StringResult) key).getString();
            AbolethDtoAdp abolethDtoAdp=null;
            if (args.size()==3){
                FunctionResult defaultValue = args.get(2);
                if (defaultValue instanceof StringResult){
                    String defaultValueString = ((StringResult) defaultValue).getString();
                    abolethDtoAdp= AbolethPlusAdder.get(UUID.fromString(uuidString),keyString,defaultValueString);
                }
            }else{
                abolethDtoAdp=AbolethPlusAdder.get(UUID.fromString(uuidString),keyString);
            }
            spellContext.getContextMap().putVariable(uuidString+"."+keyString,abolethDtoAdp.getValue());
            spellContext.getContextMap().putVariable(uuidString+"."+keyString+".default",abolethDtoAdp.getDefaultValue());
            spellContext.getContextMap().putVariable(uuidString+"."+keyString+".over",abolethDtoAdp.getOverTime());
            spellContext.getContextMap().putVariable(uuidString+"."+keyString+".value-data",abolethDtoAdp.getValueData());
            return new ItemStackResult(NBTItem.toItemStack(abolethDtoAdp.getValue()));
        }else {
            return new ErrorResult("TYPE_ERROR", "AboGet args type error.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER_HOOK";
    }

    @Override
    public String getName() {
        return "aboGetItem";
    }
}
