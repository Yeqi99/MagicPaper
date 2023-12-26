package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.*;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.DataType;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemNBTGetFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> list, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                ItemStack itemStack = (ItemStack) list.get(0).getObject();
                if (itemStack == null || itemStack.isEmpty() || itemStack.getType() == Material.AIR) {
                    return new NullResult();
                }
                String path = list.get(1).toString();
                String key = list.get(2).toString();
                String dataType = list.get(3).toString();
                NBTItem nbtItem = new NBTItem(itemStack);
                if (!nbtItem.hasKey(key, path)) {
                    return new NullResult();
                }
                Object value = nbtItem.get(key, DataType.valueOf(dataType), path);
                switch (dataType) {
                    case "STRING": {
                        return new StringResult((String) value);
                    }
                    case "ITEM": {
                        return new ItemStackResult((ItemStack) value);
                    }
                    case "INT": {
                        return new NumberResult((int) value);
                    }
                    case "LONG": {
                        return new NumberResult((long) value);
                    }
                    case "FLOAT": {
                        return new NumberResult((float) value);
                    }
                    case "DOUBLE": {
                        return new NumberResult((double) value);
                    }
                    case "BOOLEAN": {
                        return new BooleanResult((boolean) value);
                    }
                }
                return new ObjectResult(value);
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        argsSettings.add(
                new ArgsSetting("A")
                        .addArgType("ItemStack").addArgType("String").addArgType("String").addArgType("String")
                        .addInfo("item path key dataType")
                        .addInfo("Get nbt value from item")
                        .addInfo("path format:/a/b/c, /a/b,/")
                        .addInfo("dataType: STRING, ITEM, INT, LONG, FLOAT, DOUBLE, BOOLEAN")
                        .addInfo("if dont have value, return null")
                        .setResultType(".")
        );
        return argsSettings;
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemNBTGet";
    }
}
