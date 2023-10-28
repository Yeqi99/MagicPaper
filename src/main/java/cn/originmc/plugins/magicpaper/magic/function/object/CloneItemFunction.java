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
import java.util.Objects;

public class CloneItemFunction extends NormalFunction {
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
                    return new ItemStackResult(p.getInventory().getItemInMainHand().clone());
                }
                if (s.equalsIgnoreCase("oh")){
                    return new ItemStackResult(p.getInventory().getItemInOffHand().clone());
                }
                if (s.equalsIgnoreCase("h")){
                    return new ItemStackResult(Objects.requireNonNull(p.getInventory().getHelmet()).clone());
                }
                if (s.equalsIgnoreCase("c")){
                    return new ItemStackResult(Objects.requireNonNull(p.getInventory().getChestplate()).clone());
                }
                if (s.equalsIgnoreCase("l")){
                    return new ItemStackResult(Objects.requireNonNull(p.getInventory().getLeggings()).clone());
                }
                if (s.equalsIgnoreCase("b")){
                    return new ItemStackResult(Objects.requireNonNull(p.getInventory().getBoots()).clone());
                }
                if(VariableUtil.tryInt(s)){
                    return new ItemStackResult(Objects.requireNonNull(p.getInventory().getItem(Integer.parseInt(s))).clone());
                }
            }
            return new ErrorResult("ERROR", "Item don't have enough args.");
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
        return "cloneItem";
    }
}
