package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemEnchantmentGetFunction extends NormalFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemEnchantmentGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult key = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentGet need a itemStack.");
        }
        if (!(key instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemEnchantmentGet need a string.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String keyString = ((StringResult) key).getString();
        Enchantment enchantment= Enchantment.getByName(keyString);
        NBTItem nbtItem=new NBTItem(itemStack);
        if (enchantment==null){
            return new ErrorResult("ARGUMENT_ERROR", "Don't have this enchantment.");
        }
        return new IntegerResult(nbtItem.getEnchantmentLevel(enchantment));
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemEnchantmentGet";
    }
}
