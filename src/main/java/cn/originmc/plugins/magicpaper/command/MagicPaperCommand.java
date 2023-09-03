package cn.originmc.plugins.magicpaper.command;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.FunctionRegister;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length==0){
            List<String> helpMessage=new ArrayList<>();
            helpMessage.add("&aMagicPaper &7v"+MagicPaper.getVersion());
            helpMessage.add("&a/magicpaper reload &7- &fReload config");
            helpMessage.add("&a/magicpaper spells &7- &fList all spells");
            helpMessage.add("&a/magicpaper words <words> &7- &fExecute words");
            helpMessage.add("&a/magicpaper spell <spell> &7- &fExecute spell");
            helpMessage.add("&a/magicpaper publicwords <words> &7- &fExecute words with public context");
            helpMessage.add("&a/magicpaper publicspell <spell> &7- &fExecute spell with public context");
            helpMessage.add("&a/magicpaper functions &7- &fList all functions");
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
            words = words.replace(","," ");
            List<String> spellList=new ArrayList<>();
            spellList.add(words);
            Spell spell=new Spell(spellList,MagicPaper.getMagicManager());
            NormalContext normalContext=new NormalContext();
            normalContext.putObject("self",commandSender);
            SpellContext spellContext = spell.execute(normalContext);

            if (spellContext.hasExecuteError()){
                MagicPaper.getSender().sendToSender(commandSender,"&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
        }else if (args[0].equalsIgnoreCase("spell")) {
            String spellID=args[1];
            if (!MagicDataManager.isSpell(spellID)){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"spell-not-found","&cSpell not found!"));
                return true;
            }
            Spell spell = MagicDataManager.getSpell(spellID);
            NormalContext normalContext=new NormalContext();
            normalContext.putObject("self",commandSender);
            SpellContext spellContext = spell.execute(normalContext);
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()){
                MagicPaper.getSender().sendToSender(commandSender,"&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
        }else if(args[0].equalsIgnoreCase("publicspell")){
            String spellID=args[1];
            if (!MagicDataManager.isSpell(spellID)){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"spell-not-found","&cSpell not found!"));
                return true;
            }
            Spell spell = MagicDataManager.getSpell(spellID);
            SpellContext spellContext = spell.execute(MagicPaper.getContext());
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()){
                MagicPaper.getSender().sendToSender(commandSender,"&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
        }else if (args[0].equalsIgnoreCase("publicwords")){
            String words=args[1];
            words = words.replace(","," ");
            List<String> spellList=new ArrayList<>();
            spellList.add(words);
            Spell spell=new Spell(spellList,MagicPaper.getMagicManager());
            SpellContext spellContext = spell.execute(MagicPaper.getContext());

            if (MagicPaper.isDebug() && spellContext.hasExecuteError()){
                MagicPaper.getSender().sendToSender(commandSender,"&c"+spellContext.getExecuteError().getErrorId()+":"+spellContext.getExecuteError().getInfo());
            }
        }else if (args[0].equalsIgnoreCase("functions")){
            List<String> message = new ArrayList<>(MagicPaper.getMagicManager().getFunctionsRealNames());
            if (!message.isEmpty()){
                MagicPaper.getSender().sendToSender(commandSender,message);
            }else {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"functions-none","Functions is none"));
            }
        }else if (args[0].equalsIgnoreCase("functioninfo")) {
            String functionName = args[1];
            String info= FunctionRegister.funInfo.get(functionName);
            String argsInfo= FunctionRegister.argsInfo.get(functionName);
            if (info==null){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"function-not-found","&cFunction not found!"));
                return true;
            }
            if (argsInfo==null){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"function-args-not-found","&cFunction args not found!"));
                return true;
            }
            MagicPaper.getSender().sendToSender(commandSender,"&a&b"+info+"&7:&c"+argsInfo);
            return true;
        }
        return true;
    }
}
