package cn.originmc.plugins.magicpaper.command;


import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.expression.functions.FastFunction;
import cn.origincraft.magic.function.ArgsFunction;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.origincraft.magic.utils.ErrorUtils;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.data.manager.TimerDataManager;
import cn.originmc.plugins.magicpaper.data.manager.TriggerDataManager;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.listener.CodingListener;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import cn.originmc.plugins.magicpaper.trigger.abs.MagicPaperTrigger;
import cn.originmc.plugins.magicpaper.util.error.PaperErrorUtils;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MagicPaperCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, String[] args) {
        if (args.length == 0) {
            List<String> helpMessage = new ArrayList<>();
            if (!MagicPaper.getLang().contains("Chinese")) {
                helpMessage.add("&aMagicPaper &7v" + MagicPaper.getVersion());
                helpMessage.add("&a/magicpaper reload &7- &fReload config");
                helpMessage.add("&a/magicpaper reloadall &7- &fReload all");
                helpMessage.add("&a/magicpaper spells &7- &fList all spells");
                helpMessage.add("&a/magicpaper words <words> &7- &fExecute words");
                helpMessage.add("&a/magicpaper spell <spell> &7- &fExecute spell");
                helpMessage.add("&a/magicpaper publicwords <words> &7- &fExecute words with public context");
                helpMessage.add("&a/magicpaper publicspell <spell> &7- &fExecute spell with public context");
                helpMessage.add("&a/magicpaper functions &7- &fList all functions");
                helpMessage.add("&a/magicpaper functioninfo <function> &7- &fGet function info(part available)");
                helpMessage.add("&a/magicpaper triggers &7- &fList all triggers");
                helpMessage.add("&a/magicpaper onload &7- &fExecute onload spell");
                helpMessage.add("&a/magicpaper boreremove <id> <amount> &7- &fRemove item from bore");
                helpMessage.add("&a/magicpaper restart &7- &fRestart plugin");
                helpMessage.add("&a/magicpaper coding &7- &fOpen coding mode");
                helpMessage.add("&a/magicpaper gui <id> [player] &7- &fOpen gui");
                helpMessage.add("&a/magicpaper clearguidata <id> [player] &7- &fClear gui data");
                helpMessage.add("&a/magicpaper updateguidata <id> [player] &7- &fUpdate gui data");
                helpMessage.add("&a/magicpaper lookailas <function> &7- &fLook function alias");
            } else {
                helpMessage.add("&aMagicPaper &7v" + MagicPaper.getVersion());
                helpMessage.add("&a/magicpaper reload &7- &f重载配置");
                helpMessage.add("&a/magicpaper reloadall &7- &f重载所有");
                helpMessage.add("&a/magicpaper spells &7- &f列出所有法术");
                helpMessage.add("&a/magicpaper words <words> &7- &f执行语句");
                helpMessage.add("&a/magicpaper spell <spell> &7- &f执行法术");
                helpMessage.add("&a/magicpaper publicwords <words> &7- &f执行语句(公共上下文)");
                helpMessage.add("&a/magicpaper publicspell <spell> &7- &f执行法术(公共上下文)");
                helpMessage.add("&a/magicpaper functions &7- &f列出所有函数");
                helpMessage.add("&a/magicpaper functioninfo <function> &7- &f获取函数信息(部分可用)");
                helpMessage.add("&a/magicpaper triggers &7- &f列出所有触发器");
                helpMessage.add("&a/magicpaper onload &7- &f执行onload法术");
                helpMessage.add("&a/magicpaper boreremove <address> <index> &7- &f移除某个镶嵌孔的物品");
                helpMessage.add("&a/magicpaper restart &7- &f重启插件");
                helpMessage.add("&a/magicpaper coding &7- &f切换编码模式");
                helpMessage.add("&a/magicpaper gui <id> [player] &7- &f打开gui");
                helpMessage.add("&a/magicpaper clearguidata <id> [player] &7- &f清除gui数据");
                helpMessage.add("&a/magicpaper updateguidata <id> [player] &7- &f更新gui数据");

                helpMessage.add("&a/magicpaper lookailas <function> &7- &f查看语义别名");
            }
            MagicPaper.getSender().sendToSender(commandSender, helpMessage);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            MagicPaper.dataEntityManager.stopAsyncSaveTask();
            MagicPaper.attributeCache.stopAsyncSaveTask();

            MagicPaper.getInstance().reloadConfig();
            MagicPaper.loadData();
            MagicPaper.importSpell(MagicPaper.getContext());
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "reload", "&aReloaded!"));

            // 开启异步定时器，定时存储数据实体
            if (MagicPaper.getInstance().getConfig().getBoolean("data-entity-auto-save.enable", true)) {
                MagicPaper.dataEntityManager.setSaveInterval(MagicPaper.getInstance().getConfig().getInt("data-entity-auto-save.interval", 600));
                MagicPaper.dataEntityManager.startAsyncSaveTask();
            }

            // 开启异步定时器，定时统计玩家数据并缓存
            if (MagicPaper.getInstance().getConfig().getBoolean("attribute-cache-auto-update.enable", true)) {
                MagicPaper.dataEntityManager.setSaveInterval(MagicPaper.getInstance().getConfig().getInt("attribute-cache-auto-update.interval", 600));
                MagicPaper.attributeCache.startAsyncSaveTask();
            }
            TriggerDataManager.reInit();
            TimerDataManager.reInit();
        } else if (args[0].equalsIgnoreCase("reloadall")) {
            MagicPaper.getInstance().reloadConfig();
            MagicPaper.loadData();
            MagicPaper.importSpell(MagicPaper.getContext());
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "reload", "&aReloaded!"));
            MagicPaper.initMagicManager();
            TriggerDataManager.reInit();
            TimerDataManager.reInit();
        } else if (args[0].equalsIgnoreCase("spells")) {
            List<String> spells = MagicDataManager.getSpellsID();
            MagicPaper.getSender().sendToSender(commandSender, spells);
        } else if (args[0].equalsIgnoreCase("words")) {
            String words = args[1];
            words = words.replace(",", " ");
            List<String> spellList = new ArrayList<>();
            spellList.add(words);
            Spell spell = new Spell(spellList, MagicPaper.getMagicManager());
            NormalContext normalContext = new NormalContext();
            MagicPaper.importSpell(normalContext);
            if (args.length > 2) {
                Player player = Bukkit.getPlayer(args[2]);
                if (player != null) {
                    normalContext.putVariable("self", new PlayerResult(player));
                }
            } else {
                normalContext.putVariable("self", new PlayerResult((Player) commandSender));
            }
            SpellContext spellContext = spell.execute(normalContext);

            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                MagicPaper.getSender().sendToSender(commandSender, ErrorUtils.normalError(spellContext));
            }
        } else if (args[0].equalsIgnoreCase("spell")) {
            String spellID = args[1];
            if (!MagicDataManager.isSpell(spellID)) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "spell-not-found", "&cSpell not found!"));
                return true;
            }
            Spell spell = MagicDataManager.getSpell(spellID);
            NormalContext normalContext = new NormalContext();
            MagicPaper.importSpell(normalContext);
            if (args.length > 2) {
                Player player = Bukkit.getPlayer(args[2]);
                if (player != null) {
                    normalContext.putVariable("self", new PlayerResult(player));
                }
            } else {
                normalContext.putVariable("self", new PlayerResult((Player) commandSender));
            }
            SpellContext spellContext = Objects.requireNonNull(spell).execute(normalContext);
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log = PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToSender(commandSender, log);
            }
        } else if (args[0].equalsIgnoreCase("publicspell")) {
            String spellID = args[1];
            if (!MagicDataManager.isSpell(spellID)) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "spell-not-found", "&cSpell not found!"));
                return true;
            }
            Spell spell = MagicDataManager.getSpell(spellID);
            SpellContext spellContext = Objects.requireNonNull(spell).execute(MagicPaper.getContext());
            spellContext.putVariable("self", new PlayerResult((Player) commandSender));
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log = PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToSender(commandSender, log);
            }
        } else if (args[0].equalsIgnoreCase("publicwords")) {
            String words = args[1];
            words = words.replace(",", " ");
            List<String> spellList = new ArrayList<>();
            spellList.add(words);
            Spell spell = new Spell(spellList, MagicPaper.getMagicManager());
            SpellContext spellContext = spell.execute(MagicPaper.getContext());
            spellContext.putVariable("self", new PlayerResult((Player) commandSender));
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log = PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToSender(commandSender, log);
            }
        } else if (args[0].equalsIgnoreCase("functions")) {
            List<String> message = new ArrayList<>(MagicPaper.getMagicManager().getFunctionsRealNames());
            if (!message.isEmpty()) {
                MagicPaper.getSender().sendToSender(commandSender, message, "&d");
            } else {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "functions-none", "Functions is none"));
            }
        } else if (args[0].equalsIgnoreCase("lookailas")) {
            MagicManager magicManager = MagicPaper.getMagicManager();
            Map<String, List<String>> aliases = magicManager.getFastExpression().getAliasesManager().getAliases();
            String functionName = args[1];
            if (magicManager.getFastExpression().getFunctionManager().get(functionName) == null) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "functions-none", "&cFunction not found!"));
                return true;
            }
            List<String> aliasList = aliases.get(functionName);
            if (aliasList == null) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "functions-alias-none", "&cFunction don't have alias!"));
                return true;
            }
            MagicPaper.getSender().sendToSender(commandSender, "&a" + functionName + ":");
            MagicPaper.getSender().sendToSender(commandSender, aliasList, "&d");
        } else if (args[0].equalsIgnoreCase("functioninfo")) {
            String functionName = args[1];
            FastFunction function = MagicPaper.getMagicManager().getFastExpression().getFunctionManager().get(functionName);
            if (function == null) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "functions-none", "&cFunction not found!"));
                return true;
            }
            if (!(function instanceof ArgsFunction)) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "function-info-not-found", "&cFunction info not found!"));
                return true;
            }
            ArgsFunction argsFunction = (ArgsFunction) function;
            List<String> usage = argsFunction.getUsage();
            List<String> result = new ArrayList<>();
            for (String string : usage) {
                result.add("&a" + string);
            }
            MagicPaper.getSender().sendToSender(commandSender, result);
            return true;
        } else if (args[0].equalsIgnoreCase("triggers")) {
            for (MagicPaperTrigger magicPaperTrigger : MagicPaperTriggerManager.magicPaperTriggers) {
                MagicPaper.getSender().sendToSender(commandSender, "&a" + magicPaperTrigger.getName());
                MagicPaper.getSender().sendToSender(commandSender, "&7" + magicPaperTrigger.getInfo());
            }
        } else if (args[0].equalsIgnoreCase("onload")) {
            MagicPaper.onLoadSpell();
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "onload", "&aOnload spell executed!"));
        } else if (args[0].equalsIgnoreCase("boreremove")) {
            Player player = (Player) commandSender;
            if (args.length != 3) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "args-error", "&cArgs error!"));
                return true;
            }
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack.getType().isAir()) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "item-not-found", "&cItem not found!"));
                return true;
            }
            MagicItem magicItem = new MagicItem(itemStack);
            ItemStack removeItem = magicItem.removeItemFromBore(args[1], Integer.parseInt(args[2]));
            if (removeItem == null) {
                MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "dont-have-item-bore", "&cDont have item in bore!"));
                return true;
            }
            player.getInventory().addItem(removeItem);
            magicItem.refresh(true, PlaceholderAPIHook.status, player);
            player.getInventory().setItemInMainHand(magicItem.getItemStack());
        } else if (args[0].equalsIgnoreCase("restart")) {
            MagicPaper.getInstance().reloadConfig();
            MagicPaper.loadData();
            MagicPaper.importSpell(MagicPaper.getContext());
            MagicPaper.initMagicManager();
            MagicPaper.onLoadSpell();
            MagicPaper.getSender().sendToSender(commandSender, LangData.get(MagicPaper.getLang(), "reload", "&aReloaded!"));
            TriggerDataManager.reInit();
            TimerDataManager.reInit();
        } else if (args[0].equalsIgnoreCase("coding")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (CodingListener.codingPlayers.contains(player.getName())) {
                    CodingListener.codingPlayers.remove(player.getName());
                    return true;
                } else {
                    CodingListener.codingPlayers.add(player.getName());
                    return true;
                }
            }
        } else if (args[0].equalsIgnoreCase("gui")) {
            if (args.length < 2) {
                return true;
            }
            String id = args[1];
            Player player = (Player) commandSender;
            if (args.length > 3) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    return true;
                }
            }
            MagicPaper.getMagicGuiManager().getGui(player, id).open(player);
        } else if (args[0].equalsIgnoreCase("clearguidata")) {
            if (args.length < 2) {
                return true;
            }
            String id = args[1];
            Player player = (Player) commandSender;
            if (args.length > 3) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    return true;
                }
            }
            MagicPaper.getMagicGuiManager().removeGui(player, id);
        } else if (args[0].equalsIgnoreCase("updateguidata")) {
            if (args.length < 2) {
                return true;
            }
            String id = args[1];
            Player player = (Player) commandSender;
            if (args.length > 3) {
                player = Bukkit.getPlayer(args[2]);
                if (player == null) {
                    return true;
                }
            }
            MagicPaper.getMagicGuiManager().getGui(player, id).update(player);
        }
        return true;
    }
}
