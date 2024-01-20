package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemAmountSetFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                ItemStack itemStack = (ItemStack) list.get(0).getObject();
                NumberResult numberResult = (NumberResult) list.get(1);
                itemStack.setAmount(numberResult.toInteger());
                return new ItemStackResult(itemStack);
            }
            case "B": {
                ItemStack itemStack = (ItemStack) list.get(0).getObject();
                String amount = list.get(1).toString();
                itemStack.setAmount(new NumberResult(amount).toInteger());
                return new ItemStackResult(itemStack);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("ItemStack").addArgType("Number")
                        .addInfo("item amount")
                        .addInfo("Set item amount")
                        .setResultType("ItemStack")
        );
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("ItemStack").addArgType("String")
                        .addInfo("item amount")
                        .addInfo("Set item amount")
                        .setResultType("ItemStack")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemAmountSet";
    }
}
