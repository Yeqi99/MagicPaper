package cn.originmc.plugins.magicpaper.command;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.FunctionRegister;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MagicPaperTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            completions.add("reload");
            completions.add("spells");
            completions.add("words");
            completions.add("spell");
            completions.add("publicwords");
            completions.add("publicspell");
            completions.add("functions");
            completions.add("functioninfo");
            completions.add("triggers");
            completions.add("reloadall");
            completions.add("onload");
            completions.add("boreremove");
            completions.add("restart");
            completions.add("coding");
            completions.add("gui");
            completions.add("clearguidata");
            completions.add("updateguidata");
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("gui")){
                MagicPaper.getMagicGuiManager().getMagicGuiSettings().forEach(magicGuiSetting -> completions.add(magicGuiSetting.getId()));
            }
            if (args[0].equalsIgnoreCase("spell")) {
                // 提示第二个参数的补全，可能是法术ID
                List<String> spellIds = MagicDataManager.getSpellsID();
                completions.addAll(spellIds);
            }
            if (args[0].equalsIgnoreCase("publicspell")) {
                // 提示第二个参数的补全，可能是法术ID
                List<String> spellIds = MagicDataManager.getSpellsID();
                completions.addAll(spellIds);
            }
            if (args[0].equalsIgnoreCase("functioninfo")) {
                // 提示第二个参数的补全，可能是法术ID
                completions.addAll(new ArrayList<>(FunctionRegister.funInfo.keySet()));
            }
            if (args[0].equalsIgnoreCase("functions")) {
                // 提示第二个参数的补全，可能是法术ID
                completions.addAll(MagicPaper.getMagicManager().getFunctionsRealNames());
            }
            if (args[0].equalsIgnoreCase("triggers")) {
                completions.addAll(MagicPaperTriggerManager.getTriggerNames());
            }
            if (args[0].equalsIgnoreCase("boreremove")) {
                completions.add("address");
            }
            if (args[0].equalsIgnoreCase("clearguidata")){
                MagicPaper.getMagicGuiManager().getMagicGuiSettings().forEach(magicGuiSetting -> completions.add(magicGuiSetting.getId()));
            }
            if (args[0].equalsIgnoreCase("updateguidata")){
                MagicPaper.getMagicGuiManager().getMagicGuiSettings().forEach(magicGuiSetting -> completions.add(magicGuiSetting.getId()));
            }
        } else if (args.length == 3){
            if (args[0].equalsIgnoreCase("boreremove")) {
                if (args[1].equalsIgnoreCase("address")) {
                    completions.add("index");
                }
            }
        }

        // 对补全进行排序，可以自行根据需要更改
        completions.sort(String.CASE_INSENSITIVE_ORDER);

        return completions;
    }


}
