package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemAttributeSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<6){
            return null;
        }
        FunctionResult item = args.get(0);
        FunctionResult id = args.get(1);
        FunctionResult attribute = args.get(2);
        FunctionResult value = args.get(3);
        FunctionResult slot = args.get(4);
        FunctionResult operation = args.get(5);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a itemStack.");
        }
        if (!(id instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str.");
        }
        if (!(attribute instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str.");
        }
        if (!(value instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str double.");
        }
        if (!(slot instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str.");
        }
        if (!(operation instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String idString = ((StringResult) id).getString();
        String attributeString = ((StringResult) attribute).getString();
        String valueString = ((StringResult) value).getString();
        String slotString = ((StringResult) slot).getString();
        String operationString = ((StringResult) operation).getString();
        if (!VariableUtils.tryDouble(valueString)){
            return new ErrorResult("TYPE_ERROR", "itemAttributeSet need a str double.");
        }
        double valueDouble=Double.parseDouble(valueString);
        EquipmentSlot slotResult=NBTItem.getSlot(slotString);
        AttributeModifier.Operation operationResult= NBTItem.getOperation(operationString);
        NBTItem nbtItem=new NBTItem(itemStack);
        nbtItem.setAttribute(idString, Attribute.valueOf(attributeString), valueDouble, operationResult, slotResult);
        return new ItemStackResult(nbtItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAttributeSet";
    }
}
