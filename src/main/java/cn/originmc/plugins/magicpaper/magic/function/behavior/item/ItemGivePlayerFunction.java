package cn.originmc.plugins.magicpaper.magic.function.behavior.item;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.NullResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemGivePlayerFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if (args.size()<2) {
            return new ErrorResult("INSUFFICIENT_ARGUMENTS", "ItemGivePlayer function requires at least two arguments.");
        }
        FunctionResult player = args.get(0);
        FunctionResult item = args.get(1);
        if (player instanceof PlayerResult) {
            if (item instanceof ItemStackResult) {
                PlayerResult playerResult = (PlayerResult) player;
                ItemStackResult itemStackResult = (ItemStackResult) item;
                Player p = playerResult.getPlayer();
                ItemStack i = itemStackResult.getItemStack();
                p.getInventory().addItem(i);
                return new NullResult();
            } else {
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
        return "itemGivePlayer";
    }
}
