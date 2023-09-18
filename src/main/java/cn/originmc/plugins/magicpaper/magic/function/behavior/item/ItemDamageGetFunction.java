package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.IntegerResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.List;

public class ItemDamageGetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.isEmpty()){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemDamageGet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        if(!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemDamageGet need a itemStack.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        Damageable damageable=(Damageable) itemStack.getItemMeta();
        if (!damageable.hasDamage()){
            return new IntegerResult(0);
        }
        return new IntegerResult(damageable.getDamage());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemDamageGet";
    }
}
