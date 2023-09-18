package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.DoubleResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemAttributeGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<4){
            return null;
        }
        FunctionResult item = args.get(0);
        FunctionResult id = args.get(1);
        FunctionResult attribute = args.get(2);
        FunctionResult slot = args.get(3);
        FunctionResult operation = args.get(4);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeAdd need a itemStack.");
        }
        if (!(id instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeAdd need a str.");
        }
        if (!(attribute instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeAdd need a str.");
        }
        if (!(slot instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeAdd need a str.");
        }
        if (!(operation instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeAdd need a str.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String idString = ((StringResult) id).getString();
        String attributeString = ((StringResult) attribute).getString();
        String slotString = ((StringResult) slot).getString();
        String operationString = ((StringResult) operation).getString();
        EquipmentSlot slotResult= NBTItem.getSlot(slotString);
        AttributeModifier.Operation operationResult= NBTItem.getOperation(operationString);
        NBTItem nbtItem=new NBTItem(itemStack);
        double result= nbtItem.getAttributeValue(idString, Attribute.valueOf(attributeString),operationResult,slotResult);
        return new DoubleResult(result);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAttributeGet";
    }
}
