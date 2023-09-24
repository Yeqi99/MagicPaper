package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemBoreSetFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<5){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need 5 arguments");
        }
        FunctionResult itemResult = args.get(0);
        FunctionResult boreNameResult = args.get(1);
        FunctionResult maxResult = args.get(2);
        FunctionResult targetAddressResult = args.get(3);
        FunctionResult originAddressResult = args.get(4);
        if(!(itemResult instanceof ItemStackResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need itemStack as first argument");
        }
        if(!(boreNameResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need String as second argument");
        }
        if(!(maxResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need String as third argument");
        }
        if(!(targetAddressResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need String as fourth argument");
        }
        if(!(originAddressResult instanceof StringResult)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need String as fifth argument");
        }
        ItemStack itemStack = ((ItemStackResult) itemResult).getItemStack();
        String boreName = ((StringResult) boreNameResult).getString();
        String max = ((StringResult) maxResult).getString();
        if (!VariableUtil.tryInt(max)){
            return new ErrorResult("ARGUMENTS_ERROR","ItemBoreSetFunction need String int as third argument");
        }
        String targetAddress = ((StringResult) targetAddressResult).getString();
        String originAddress = ((StringResult) originAddressResult).getString();

        MagicItem magicItem=new MagicItem(itemStack);
        magicItem.bore(boreName, Integer.parseInt(max),targetAddress,originAddress);
        return new ItemStackResult(magicItem.getItemStack());
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemBoreSet";
    }
}
