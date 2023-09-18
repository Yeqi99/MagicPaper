package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import dev.rgbmc.expression.results.StringResult;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemSetPlayerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<3) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "ItemSetPlayer function requires at least three arguments.");
        }
        FunctionResult player = args.get(0);
        FunctionResult item = args.get(1);
        FunctionResult slot = args.get(2);
        if (player instanceof PlayerResult){
            if (item instanceof ItemStackResult){
                if (slot instanceof StringResult){
                    PlayerResult playerResult=(PlayerResult) player;
                    ItemStackResult itemStackResult=(ItemStackResult) item;
                    StringResult stringResult=(StringResult) slot;
                    Player p = playerResult.getPlayer();
                    ItemStack i = itemStackResult.getItemStack();
                    String s = stringResult.getString();
                    if (s.equalsIgnoreCase("mh")){
                        p.getInventory().setItemInMainHand(i);
                    }
                    if (s.equalsIgnoreCase("oh")){
                        p.getInventory().setItemInOffHand(i);
                    }
                    if (s.equalsIgnoreCase("h")){
                        p.getInventory().setHelmet(i);
                    }
                    if (s.equalsIgnoreCase("c")){
                        p.getInventory().setChestplate(i);
                    }
                    if (s.equalsIgnoreCase("l")){
                        p.getInventory().setLeggings(i);
                    }
                    if (s.equalsIgnoreCase("b")){
                        p.getInventory().setBoots(i);
                    }
                    if(VariableUtil.tryInt(s)){
                        p.getInventory().setItem(Integer.parseInt(s),i);
                    }
                    return new NullResult();
                }else {
                    return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
                }
            }else {
                return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
            }
        }else {
            return new ErrorResult("UNKNOWN_ARGUMENT_TYPE", "Unsupported argument type.");
        }
    }

    @Override
    public String getType() {
        return "BEHAVIOR";
    }

    @Override
    public String getName() {
        return "itemSetPlayer";
    }
}
