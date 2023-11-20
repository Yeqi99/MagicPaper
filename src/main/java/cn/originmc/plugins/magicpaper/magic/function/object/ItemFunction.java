package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.function.ArgsSetting;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.FunctionUtils;
import cn.origincraft.magic.utils.VariableUtils;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemFunction extends ArgsFunction {

    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args, ArgsSetting argsSetting) {
        String id = argsSetting.getId();
        switch (id) {
            case "A": {
                Player player = (Player) args.get(0).getObject();
                String s = (String) args.get(1).getObject();
                if (s.equalsIgnoreCase("mh")) {
                    return new ItemStackResult(player.getInventory().getItemInMainHand());
                } else if (s.equalsIgnoreCase("oh")) {
                    return new ItemStackResult(player.getInventory().getItemInOffHand());
                } else if (s.equalsIgnoreCase("h")) {
                    return new ItemStackResult(player.getInventory().getHelmet());
                } else if (s.equalsIgnoreCase("c")) {
                    return new ItemStackResult(player.getInventory().getChestplate());
                } else if (s.equalsIgnoreCase("l")) {
                    return new ItemStackResult(player.getInventory().getLeggings());
                } else if (s.equalsIgnoreCase("b")) {
                    return new ItemStackResult(player.getInventory().getBoots());
                } else if (VariableUtils.tryInt(s)) {
                    return new ItemStackResult(player.getInventory().getItem(Integer.parseInt(s)));
                }
            }
            case "B":{
                String s = (String) args.get(0).getObject();
                Material material = Material.getMaterial(s);
                return new ItemStackResult(new ItemStack(Objects.requireNonNullElse(material, Material.AIR)));
            }
            case "C":{
                String s = (String) args.get(0).getObject();
                String s1 = (String) args.get(1).getObject();
                Material material = Material.getMaterial(s);
                ItemStack itemStack = new ItemStack(Objects.requireNonNullElse(material, Material.AIR));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(s1);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }
            case "D":{
                String s = (String) args.get(0).getObject();
                String s1 = (String) args.get(1).getObject();
                String s2 = (String) args.get(2).getObject();
                Material material = Material.getMaterial(s);
                ItemStack itemStack = new ItemStack(Objects.requireNonNullElse(material, Material.AIR),Integer.parseInt(s2));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(s1);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }
            case "E":{
                String s = (String) args.get(0).getObject();
                String s1 = (String) args.get(1).getObject();
                String s2 = (String) args.get(2).getObject();
                List<?> s3 = (List<?>) args.get(3).getObject();
                Material material = Material.getMaterial(s);
                ItemStack itemStack = new ItemStack(Objects.requireNonNullElse(material, Material.AIR),Integer.parseInt(s2));
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.setDisplayName(s1);
                List<String> s4=new ArrayList<>();
                for (Object o : s3) {
                    s4.add((String) o);
                }
                itemMeta.setLore(s4);
                itemStack.setItemMeta(itemMeta);
                return new ItemStackResult(itemStack);
            }
            case "F":{
                Object object = args.get(0).getObject();
                if (object instanceof ItemStack) {
                    return new ItemStackResult((ItemStack) object);
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Object is not a item.");
                }
            }
            case "G":{
                ItemStack itemStack = (ItemStack) args.get(0).getObject();
                return new ItemStackResult(itemStack.clone());
            }
        }
        return new NullResult();
    }

    @Override
    public List<ArgsSetting> getArgsSetting() {
        List<ArgsSetting> argsSettings = new ArrayList<>();
        ArgsSetting argsSetting1 = FunctionUtils.createArgsSetting(
                "Player String",
                "player index" +
                        "\nGet a item from player." +
                        "\nindex: mh,oh,h,c,l,b,0-35",
                "ItemStack");
        argsSetting1.setId("A");
        ArgsSetting argsSetting2 = FunctionUtils.createArgsSetting(
                "String",
                "material" +
                        "\nGet a item from material.",
                "ItemStack");
        argsSetting2.setId("B");
        ArgsSetting argsSetting3 = FunctionUtils.createArgsSetting(
                "String String",
                "display material" +
                        "\nGet a itemStack from material and set display name.",
                "ItemStack");
        argsSetting3.setId("C");
        ArgsSetting argsSetting4 = FunctionUtils.createArgsSetting(
                "String String String",
                "display material amount" +
                        "\nGet a itemStack from material and set display name and amount.",
                "ItemStack");
        argsSetting4.setId("D");
        ArgsSetting argsSetting5 = FunctionUtils.createArgsSetting(
                "String String String List",
                "display material amount lore" +
                        "\nGet a itemStack from material and set display name and amount and lore.",
                "ItemStack");
        argsSetting5.setId("E");
        ArgsSetting argsSetting6 = FunctionUtils.createArgsSetting(
                "Object",
                "object" +
                        "\nGet a itemStack from object.",
                "ItemStack");
        argsSetting6.setId("F");
        ArgsSetting argsSetting7 = FunctionUtils.createArgsSetting(
                "ItemStack",
                "itemStack" +
                        "\nget a itemStack clone from item.",
                "ItemStack");
        argsSetting7.setId("G");
        argsSettings.add(argsSetting1);
        argsSettings.add(argsSetting2);
        argsSettings.add(argsSetting3);
        argsSettings.add(argsSetting4);
        argsSettings.add(argsSetting5);
        argsSettings.add(argsSetting6);
        argsSettings.add(argsSetting7);
        return argsSettings;
    }

    @Override
    public String getType() {
        return "SUPER_OBJECT";
    }

    @Override
    public String getName() {
        return "item";
    }
}
