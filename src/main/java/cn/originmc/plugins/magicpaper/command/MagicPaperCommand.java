package cn.originmc.plugins.magicpaper.command;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MagicPaperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length==0){
            MagicPaper.getSender().sendToSender(commandSender,"&aMagicPaper &7v"+MagicPaper.getVersion()+" &aby &7OriginMC");
        }
        if (args[0].equalsIgnoreCase("reload")){
            MagicPaper.getMagicManager();
            MagicPaper.getSender().sendToSender(commandSender, LangData.get("lang","reload","&aReloaded!"));
        }

        return false;
    }
}
