package cn.originmc.plugins.magicpaper.listener;

import cn.origincraft.magic.function.NormalFunction;
import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.*;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class CodingListener implements Listener {
    public static ContextMap contextMap=new NormalContext();
    @EventHandler
    public void onAdminCoding(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("magicpaper.coding")) {
            return;
        }
        if(event.getMessage().startsWith("!m")) {
            String words = event.getMessage().substring(2);
            NormalContext context= (NormalContext) contextMap;
            List<String> wordsList=new ArrayList<>();
            wordsList.add(words);
            Spell spell=new Spell(wordsList, MagicPaper.getMagicManager());
            context.putVariable("self",new PlayerResult(event.getPlayer()));
            context.putVariable("players", new ListResult(new ArrayList<>(Bukkit.getOnlinePlayers())));
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&d"+words);
            SpellContext spellContext = spell.execute(context);

            if (spellContext.hasExecuteError()){
                MagicPaper
                        .getSender()
                        .sendToPlayer(
                                event.getPlayer(),
                                "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
            event.setCancelled(true);
        }else if(event.getMessage().startsWith("!pm")){
            String words = event.getMessage().substring(3);
            NormalContext context=MagicPaper.getContext();
            List<String> wordsList=new ArrayList<>();
            wordsList.add(words);
            Spell spell=new Spell(wordsList, MagicPaper.getMagicManager());
            context.putVariable(event.getPlayer().getName()+".self",new PlayerResult(event.getPlayer()));
            context.putVariable("players", new ListResult(new ArrayList<>(Bukkit.getOnlinePlayers())));
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&d"+words);
            SpellContext spellContext = spell.execute(context);
            if (spellContext.hasExecuteError()){
                MagicPaper
                        .getSender()
                        .sendToPlayer(
                                event.getPlayer(),
                                "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
            event.setCancelled(true);
        }else if(event.getMessage().startsWith("!clear")){
            contextMap=new NormalContext();
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&dCoding数据已清空");
            event.setCancelled(true);
        }

    }
}
