package cn.originmc.plugins.magicpaper.command;

import cn.origincraft.magic.object.MagicWords;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.config.MagicData;
import cn.originmc.plugins.magicpaper.data.item.format.ItemFormatData;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.FunctionRegister;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import cn.originmc.plugins.magicpaper.util.text.Sender;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length==0){
            List<String> helpMessage=new ArrayList<>();
            if (!MagicPaper.getLang().contains("Chinese")){
                helpMessage.add("&aMagicPaper &7v"+MagicPaper.getVersion());
                helpMessage.add("&a/magicpaper reload &7- &fReload config");
                helpMessage.add("&a/magicpaper reloadall &7- &fReload all");
                helpMessage.add("&a/magicpaper spells &7- &fList all spells");
                helpMessage.add("&a/magicpaper words <words> &7- &fExecute words");
                helpMessage.add("&a/magicpaper spell <spell> &7- &fExecute spell");
                helpMessage.add("&a/magicpaper publicwords <words> &7- &fExecute words with public context");
                helpMessage.add("&a/magicpaper publicspell <spell> &7- &fExecute spell with public context");
                helpMessage.add("&a/magicpaper functions &7- &fList all functions");
                helpMessage.add("&a/magicpaper functioninfo <function> &7- &fGet function info");
                helpMessage.add("&a/magicpaper triggers &7- &fList all triggers");
                helpMessage.add("&a/magicpaper onload &7- &fExecute onload spell");
            }else {
                helpMessage.add("&aMagicPaper &7v"+MagicPaper.getVersion());
                helpMessage.add("&a/magicpaper reload &7- &f重载配置");
                helpMessage.add("&a/magicpaper reloadall &7- &f重载所有");
                helpMessage.add("&a/magicpaper spells &7- &f列出所有法术");
                helpMessage.add("&a/magicpaper words <words> &7- &f执行语句");
                helpMessage.add("&a/magicpaper spell <spell> &7- &f执行法术");
                helpMessage.add("&a/magicpaper publicwords <words> &7- &f执行语句(公共上下文)");
                helpMessage.add("&a/magicpaper publicspell <spell> &7- &f执行法术(公共上下文)");
                helpMessage.add("&a/magicpaper functions &7- &f列出所有函数");
                helpMessage.add("&a/magicpaper functioninfo <function> &7- &f获取函数信息");
                helpMessage.add("&a/magicpaper triggers &7- &f列出所有触发器");
                helpMessage.add("&a/magicpaper onload &7- &f执行onload法术");
            }
            MagicPaper.getSender().sendToSender(commandSender, helpMessage);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            MagicPaper.getInstance().reloadConfig();
            LangData.load();
            MagicData.load();
            ItemFormatData.load();
            MagicPaper.importSpell(MagicPaper.getContext());
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"reload","&aReloaded!"));
        }else if(args[0].equalsIgnoreCase("reloadall")){
            MagicPaper.getInstance().reloadConfig();
            LangData.load();
            MagicData.load();
            ItemFormatData.load();
            MagicPaper.importSpell(MagicPaper.getContext());
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"reload","&aReloaded!"));
            MagicPaper.initMagicManager();
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
            MagicPaper.importSpell(normalContext);
            normalContext.putVariable("self",new PlayerResult((Player) commandSender));
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
            MagicPaper.importSpell(normalContext);
            normalContext.putVariable("self",new PlayerResult((Player) commandSender));
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
            spellContext.putVariable("self",new PlayerResult((Player) commandSender));
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
            spellContext.putVariable("self",new PlayerResult((Player) commandSender));
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
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"function-info-not-found","&cFunction info not found!"));
                return true;
            }
            if (argsInfo==null){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"function-args-info-not-found","&cFunction args info not found!"));
                return true;
            }
            MagicPaper.getSender().sendToSender(commandSender,"&a&b"+info+"&7:&c"+argsInfo);
            return true;
        }else if(args[0].equalsIgnoreCase("triggers")){
            for (MagicPaperTrigger magicPaperTrigger : MagicPaperTriggerManager.magicPaperTriggers) {
                MagicPaper.getSender().sendToSender(commandSender, magicPaperTrigger.getName());
            }
        }else if (args[0].equalsIgnoreCase("onload")){
            MagicPaper.onLoadSpell();
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"onload","&aOnload spell executed!"));
        }else  if(args[0].equalsIgnoreCase("boreremove")){
            Player player= (Player) commandSender;
            ItemStack itemStack=player.getInventory().getItemInMainHand();
            if (itemStack.getType().isAir()){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"item-not-found","&cItem not found!"));
                return true;
            }
            MagicItem magicItem=new MagicItem(itemStack);
            ItemStack removeItem= magicItem.removeItemFromBore(args[1],Integer.parseInt(args[2]));
            if (removeItem==null){
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(),"dont-have-item-bore","&cDont have item in bore!"));
                return true;
            }
            player.getInventory().addItem(removeItem);
            player.getInventory().setItemInMainHand(magicItem.getItemStack());
        }
        return true;
    }
}
