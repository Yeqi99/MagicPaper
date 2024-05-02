package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.function.results.NumberResult;
import cn.origincraft.magic.object.SpellContext;

import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;


import java.util.ArrayList;
import java.util.List;

public class ItemColorSetFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                ItemStack itemStack = (ItemStack) args.get(0).getObject();

                if (itemStack.getItemMeta() instanceof ColorableArmorMeta) {
                    NumberResult alpha = (NumberResult) args.get(1);
                    NumberResult red = (NumberResult) args.get(2);
                    NumberResult green = (NumberResult) args.get(3);
                    NumberResult blue = (NumberResult) args.get(4);
                    ColorableArmorMeta colorableArmorMeta = (ColorableArmorMeta) itemStack.getItemMeta();
                    colorableArmorMeta.setColor(Color.fromARGB(alpha.toInteger(), red.toInteger(), green.toInteger(), blue.toInteger()));
                    itemStack.setItemMeta(colorableArmorMeta);
                    return new ItemStackResult(itemStack);
                } else {
                    return new ItemStackResult(itemStack);
                }

            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("ItemStack").addArgType("Number").addArgType("Number").addArgType("Number").addArgType("Number")
                .addInfo("item alpha red green blue")
                .setResultType("ItemStack")
        );
        return argsSettings;
    }

    @Override
    public String getName() {
        return "itemColorSet";
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

}