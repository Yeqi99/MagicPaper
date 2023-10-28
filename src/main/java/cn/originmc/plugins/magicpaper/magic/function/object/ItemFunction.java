package cn.originmc.plugins.magicpaper.magic.function.object;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.VariableUtil;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.entity.Player;

import java.util.List;

public class ItemFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2) {
            return new ErrorResult("ERROR", "Item don't have enough args.");
        }
        FunctionResult player=args.get(0);
        FunctionResult index=args.get(1);
        if(player instanceof PlayerResult){
            if (index instanceof StringResult){
                PlayerResult playerResult=(PlayerResult) player;
                StringResult stringResult=(StringResult) index;
                Player p = playerResult.getPlayer();
                String s = stringResult.getString();
                if (s.equalsIgnoreCase("mh")){
                    return new ItemStackResult(p.getInventory().getItemInMainHand());
                }
                if (s.equalsIgnoreCase("oh")){
                    return new ItemStackResult(p.getInventory().getItemInOffHand());
                }
                if (s.equalsIgnoreCase("h")){
                    return new ItemStackResult(p.getInventory().getHelmet());
                }
                if (s.equalsIgnoreCase("c")){
                    return new ItemStackResult(p.getInventory().getChestplate());
                }
                if (s.equalsIgnoreCase("l")){
                    return new ItemStackResult(p.getInventory().getLeggings());
                }
                if (s.equalsIgnoreCase("b")){
                    return new ItemStackResult(p.getInventory().getBoots());
                }
                if(VariableUtil.tryInt(s)){
                    return new ItemStackResult(p.getInventory().getItem(Integer.parseInt(s)));
                }
                return new ErrorResult("ERROR", "Item don't have enough args.");
            }else {
                return new ErrorResult("ERROR", "Item don't have enough args.");
            }
        }else {
            return new ErrorResult("ERROR", "Item don't have enough args.");
        }
    }

    @Override
    public String getType() {
        return "MAGIC_PAPER";
    }

    @Override
    public String getName() {
        return "item";
    }
}
