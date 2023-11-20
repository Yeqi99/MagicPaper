package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemEnchantmentSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemEnchantmentSet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult key = args.get(1);
        FunctionResult value = args.get(2);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentSet need a itemStack.");
        }
        if (!(key instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentSet need a string.");
        }
        if (!(value instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentSet need a int.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String keyString = ((StringResult) key).getString();
        String valueString = ((StringResult) value).getString();
        if (!VariableUtils.tryInt(valueString)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentSet need a int str.");
        }
        int valueInt = Integer.parseInt(valueString);
        Enchantment enchantment = Enchantment.getByName(keyString);
        if (enchantment==null){
            return new ErrorResult("ARGUMENT_ERROR", "Don't have this enchantment.");
        }
        itemStack.addEnchantment(enchantment,valueInt);
        return new ItemStackResult(itemStack);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemEnchantmentSet";
    }
}
