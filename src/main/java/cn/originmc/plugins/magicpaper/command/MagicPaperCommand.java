package cn.originmc.plugins.magicpaper.command;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length==0){
            List<String> helpMessage=new ArrayList<>();
            helpMessage.add("&aMagicPaper &7v"+MagicPaper.getVersion());
            helpMessage.add("&7/magicpaper reload &aReload config");
            MagicPaper.getSender().sendToSender(commandSender, helpMessage);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            MagicPaper.getInstance().reloadConfig();
            LangData.load();
            MagicData.load();
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"reload","&aReloaded!"));
        }else if (args[0].equalsIgnoreCase("spells")){
            List<String> spells= MagicDataManager.getSpellsID();
            MagicPaper.getSender().sendToSender(commandSender, spells);
        }else if (args[0].equalsIgnoreCase("words")){
            String words=args[1];
            MagicWords magicWords=new MagicWords(words,MagicPaper.getMagicManager());
            SpellContext spellContext=new SpellContext();
            spellContext.setContextMap(MagicPaper.getContext());
            magicWords.execute(spellContext);
        }else if (args[0].equalsIgnoreCase("spell")) {
            String spellID=args[1];
            if (!MagicDataManager.isSpell(spellID)){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"spell-not-found","&cSpell not found!"));
                return true;
            }
            Spell spell = MagicDataManager.getSpell(spellID);
            spell.execute(MagicPaper.getContext());
        }
        return true;
    }
}
