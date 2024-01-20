package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemNBTAddFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                ItemStack itemStack = (ItemStack) list.get(0).getObject();
                String path = list.get(1).toString();
                String key = list.get(2).toString();
                Object value = list.get(3).getObject();
                NBTItem nbtItem = new NBTItem(itemStack);
                nbtItem.set(key, value, path);
                return new ItemStackResult(nbtItem.getItemStack());
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("ItemStack").addArgType("String").addArgType("String").addArgType(".")
                        .addInfo("item path key value")
                        .addInfo("Add nbt value to item")
                        .addInfo("path format:/a/b/c, /a/b,/")
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
        return "itemNBTAdd";
    }
}
