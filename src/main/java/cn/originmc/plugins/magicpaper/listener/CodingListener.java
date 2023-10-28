package cn.originmc.plugins.magicpaper.listener;

import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class CodingListener implements Listener {
    public static ContextMap contextMap=new NormalContext();
    public static final List<String> codingPlayers=new ArrayList<>();
    public static final List<String> wordsList=new ArrayList<>();
    @EventHandler
    public void onAdminCoding(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().isOp() && !event.getPlayer().hasPermission("magicpaper.coding")) {
            return;
        }
        if (codingPlayers.contains(event.getPlayer().getName())){
            String words=event.getMessage();
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&d"+words);
            NormalContext context= (NormalContext) contextMap;
            MagicPaper.importSpell(contextMap);
            if(words.equalsIgnoreCase("go")){
                MagicPaper.importSpell(contextMap);
                context.putVariable("self",new PlayerResult(event.getPlayer()));
                Spell spell=new Spell(wordsList, MagicPaper.getMagicManager());
                SpellContext spellContext = spell.execute(context);
                if (spellContext.hasExecuteError()){
                    MagicPaper
                            .getSender()
                            .sendToPlayer(
                                    event.getPlayer(),
                                    "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
                    MagicPaper.getSender().sendToPlayer(event.getPlayer(), spellContext.getExecuteErrorLocation());
                }
                event.setCancelled(true);
                return;
            }else if(words.equalsIgnoreCase("clear")){
                wordsList.clear();
                event.setCancelled(true);
                return;
            }else if (words.startsWith("save")){
                String origin=words.substring(5);
                String[] value=origin.split(" ");
                MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&d已保存为"+value[0]+".yml");
                MagicDataManager.saveSpell(value[0],wordsList,value[1],value[2]);
                event.setCancelled(true);
                return;
            }else if (words.startsWith("spell")){
                String origin=words.substring(6);
                String[] value=origin.split(" ");
                List<Spell> spells=new ArrayList<>();

                for (String s : value) {
                    Spell spell=MagicDataManager.getSpell(s);
                    if (spell==null){
                        MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&c不存在的spell");
                        return;
                    }
                    spells.add(spell);
                }
                context.putVariable("self",new PlayerResult(event.getPlayer()));
                for (Spell spell : spells) {
                    SpellContext spellContext = spell.execute(context);
                    if (spellContext.hasExecuteError()){
                        MagicPaper
                                .getSender()
                                .sendToPlayer(
                                        event.getPlayer(),
                                        "&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
                        MagicPaper.getSender().sendToPlayer(event.getPlayer(), spellContext.getExecuteErrorLocation());
                    }
                }
                event.setCancelled(true);
                return;
            }else if (words.equalsIgnoreCase("look")){
                MagicPaper.getSender().sendToPlayer(event.getPlayer(), wordsList);
                event.setCancelled(true);
                return;
            }
            wordsList.add(words);
            event.setCancelled(true);
            return;
        }
        if(event.getMessage().startsWith("!m")) {
            String words = event.getMessage().substring(2);
            NormalContext context= (NormalContext) contextMap;
            MagicPaper.importSpell(contextMap);
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
            MagicPaper.importSpell(contextMap);
            MagicPaper.getSender().sendToPlayer(event.getPlayer(), "&dCoding数据已清空");
            event.setCancelled(true);
        }

    }
}
