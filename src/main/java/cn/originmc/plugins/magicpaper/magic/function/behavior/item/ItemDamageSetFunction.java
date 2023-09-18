package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemDamageSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2){
            return new ErrorResult("FUNCTION_ARGS_ERROR", "itemDamageSet don't have enough args.");
        }
        FunctionResult item = args.get(0);
        FunctionResult damage = args.get(1);
        if (!(item instanceof ItemStackResult)){
            return new ErrorResult("TYPE_ERROR", "itemDamageSet need a itemStack.");
        }
        if (!(damage instanceof StringResult)){
            return new ErrorResult("TYPE_ERROR", "itemDamageSet need a int str.");
        }
        ItemStack itemStack = ((ItemStackResult) item).getItemStack();
        String damageString = ((StringResult) damage).getString();
        if (!VariableUtil.tryInt(damageString)){
            return new ErrorResult("TYPE_ERROR", "itemDamageSet need a int str.");
        }
        int damageInt = Integer.parseInt(damageString);
        if (damageInt == -1){
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setUnbreakable(true);
            itemStack.setItemMeta(itemMeta);
            return new ItemStackResult(itemStack);
        }
        if (damageInt == -2){
            ItemMeta itemMeta=itemStack.getItemMeta();
            itemMeta.setUnbreakable(false);
            itemStack.setItemMeta(itemMeta);
            return new ItemStackResult(itemStack);
        }
        Damageable damageable=(Damageable) itemStack.getItemMeta();
        damageable.setDamage(damageInt);
        itemStack.setItemMeta(damageable);
        return new ItemStackResult(itemStack);
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemDamageSet";
    }
}
