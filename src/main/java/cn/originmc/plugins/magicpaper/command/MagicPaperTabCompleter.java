package cn.originmc.plugins.magicpaper.command;

import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // 提示第一个参数的补全
            completions.add("reload");
            completions.add("spells");
            completions.add("words");
            completions.add("spell");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("spell")) {
                // 提示第二个参数的补全，可能是法术ID
                List<String> spellIds = MagicDataManager.getSpellsID();
                completions.addAll(spellIds);
            }
        }

        // 对补全进行排序，可以自行根据需要更改
        completions.sort(String.CASE_INSENSITIVE_ORDER);

        return completions;
    }


}
