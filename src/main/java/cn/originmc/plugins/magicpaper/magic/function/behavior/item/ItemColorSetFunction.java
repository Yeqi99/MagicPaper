package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ColorableArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemColorSetFunction extends ArgsFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id =argsSetting.getId();
        switch (id){
            case "A":{
                ItemStack itemStack = (ItemStack) args.get(0).getObject();

                ColorableArmorMeta colorableArmorMeta= (ColorableArmorMeta) itemStack.getItemMeta();
                colorableArmorMeta.setColor(Color.fromARGB());
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(new ArgsSetting("A")
                .addArgType("ItemStack").addArgType("Number").addArgType("Number").addArgType("Number").addArgType("Number")
                .addInfo("item color")
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
