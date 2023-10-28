package cn.originmc.plugins.magicpaper.magic.function.gui.databuttons;

import cn.origincraft.magic.expression.functions.FunctionResult;
import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.magic.result.ItemStackResult;
import cn.originmc.plugins.magicpaper.util.item.NBTItem;
import cn.originmc.plugins.magicpaper.util.text.Color;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayerButtonsFunction extends NormalFunction {
    @Override
    public FunctionResult whenFunctionCalled(SpellContext spellContext, List<FunctionResult> args) {
        if  (args.size() < 2) {
            return new ErrorResult("ARGS_NOT_ENOUGH","OnlinePlayerButtonsFunction need 3 args");
        }
        FunctionResult arg0=args.get(0);
        FunctionResult arg1=args.get(1);
        if (!(arg0 instanceof StringResult)){
            return new ErrorResult("ARGS_ERROR","OnlinePlayerButtonsFunction first arg must be string");
        }
        if (!(arg1 instanceof ListResult)){
            return new ErrorResult("ARGS_ERROR","OnlinePlayerButtonsFunction second arg must be string list");
        }
        String display=((StringResult) arg0).getString();
        display = Color.toColor(display);
        List<?> stringList=((ListResult) arg1).getList();
        List<Player> players= new ArrayList<>(Bukkit.getOnlinePlayers());
        List<ItemStackResult> itemStacks=new ArrayList<>();
        for (Player player : players) {
            NBTItem nbtItem=new NBTItem(new ItemStack(Material.PLAYER_HEAD));
            ItemStack itemStack=nbtItem.getPlayerHead(player,1);
            ItemMeta itemMeta=itemStack.getItemMeta();
            if (PlaceholderAPIHook.status){
                itemMeta.setDisplayName(
                        PlaceholderAPIHook.getPlaceholder(player,display).replace("~",player.getName())
                );
            }else {
                itemMeta.setDisplayName(display.replace("~",player.getName()));
            }
            List<String> lore=new ArrayList<>();
            for (Object o : stringList) {
                lore.add(o.toString());
            }
            lore = Color.toColor(lore);
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            itemStacks.add(new ItemStackResult(itemStack));
        }
        return new ListResult(itemStacks);
    }

    @Override
    public String getType() {
        return "PAPER_GUI";
    }

    @Override
    public String getName() {
        return "onlinePlayerButtons";
    }
}
