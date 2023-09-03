package cn.originmc.plugins.magicpaper.listener;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class CodingListener implements Listener {
    @EventHandler
    public void onAdminCoding(AsyncChatEvent event) {
        if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("magicpaper.coding")) {
            return;
        }
        if(event.message().toString().startsWith("!m")) {
            String words = event.message().toString().substring(3);
            MagicWords magicWords=new MagicWords(words, MagicPaper.getMagicManager());
            NormalContext context=new NormalContext();
            SpellContext spellContext=new SpellContext();
            spellContext.setContextMap(context);
            spellContext.getContextMap().putVariable("self",event.getPlayer());
            spellContext.getContextMap().putVariable("players", new ArrayList<>(Bukkit.getOnlinePlayers()));
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), words);
            magicWords.execute(spellContext);
            if (spellContext.hasExecuteError()){
                MagicPaper
                        .getSender()
                        .sendToPlayer(
                                event.getPlayer(),
                                "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
            event.setCancelled(true);
        }else if(event.message().toString().startsWith("!pm")){
            String words = event.message().toString().substring(3);
            MagicWords magicWords=new MagicWords(words, MagicPaper.getMagicManager());
            SpellContext spellContext=new SpellContext();
            spellContext.setContextMap(MagicPaper.getContext());
            spellContext.getContextMap().putVariable("self",event.getPlayer());
            spellContext.getContextMap().putVariable("players", new ArrayList<>(Bukkit.getOnlinePlayers()));
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), words);
            magicWords.execute(spellContext);
            if (spellContext.hasExecuteError()){
                MagicPaper
                        .getSender()
                        .sendToPlayer(
                                event.getPlayer(),
                                "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
            event.setCancelled(true);
        }

    }
}
