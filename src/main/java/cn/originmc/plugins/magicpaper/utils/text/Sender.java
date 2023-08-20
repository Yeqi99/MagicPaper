package cn.originmc.plugins.magicpaper.utils.text;

import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static cn.originmc.plugins.magicpaper.utils.text.Color.toColor;

public class Sender {
    private static JavaPlugin plugin;

    /**
     * 构造方法
     * @param plugin 插件实例
     */
    public Sender(JavaPlugin plugin){
        setPlugin(plugin);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void setPlugin(JavaPlugin plugin) {
        Sender.plugin = plugin;
    }

    /**
     * 向控制台发送字符串
     * @param inStr 要发送的字符串
     */
    public void sendToLogger(String inStr){
        getPlugin().getServer().getConsoleSender().sendMessage(toColor(inStr));
    }
    /**
     * 向控制台发送字符串列表
     * @param inList 要发送的字符串列表
     */
    public void sendToLogger(List<String> inList){
        for (String s : inList) {
            sendToLogger(s);
        }
    }

    /**
     * 给玩家发送字符串
     * @param player 对应玩家
     * @param inStr 要发送的字符串
     */
    public void sendToPlayer(Player player, String inStr){
        player.sendMessage(toColor(inStr));
    }

    /**
     * 给玩家发送字符串列表
     * @param player 对应玩家
     * @param inList 要发送的字符串列表
     */
    public void sendToPlayer(Player player, List<String> inList){
        for (String s : inList) {
            player.sendMessage(toColor(s));
        }
    }

    /**
     * 给玩家发送交互文本列表
     * @param player 对应玩家
     * @param bcList 交互文本列表
     */
    public void sendToPlayerBC(Player player, List<BaseComponent[]> bcList){
        if(bcList==null){
            return;
        }
        for(BaseComponent[] bc:bcList){
            player.spigot().sendMessage(bc);
        }
    }

    /**
     * 给玩家发送交互文本
     * @param player 对应玩家
     * @param bc 交互文本
     */
    public void sendToPlayerBC(Player player, BaseComponent[] bc){
        if(bc==null){
            return;
        }
        player.spigot().sendMessage(bc);
    }
    public void sendToSender(CommandSender sender, String inStr) {
        sender.sendMessage(toColor(String.valueOf(inStr)));
    }
    public void sendToSender(CommandSender sender, List<String> inList) {
        for (String s : inList) {
            sender.sendMessage(toColor(s));
        }
    }
    public void sendToAllPlayer(String str){
        Bukkit.broadcastMessage(toColor(str));
    }
    public void sendToAllPlayer(List<String> inList){
        for (String s : inList) {
            Bukkit.broadcastMessage(toColor(s));
        }
    }
    public void sendToAllTitle(String title,String sub,int fadeIn,int stay,int fadeOut){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.sendTitle(toColor(title),toColor(sub),fadeIn,stay,fadeOut);
        }
    }
    public void sendOnEnableMsgToLogger(String pluginName,String author,String version,String type){
        sendMiniMessageToConsole("");
        sendMiniMessageToConsole("    <gradient:#30fbfb:#6adafd><bold>"+pluginName+"  v"+version+"-"+type+"  Successfully loaded</gradient>");
        sendMiniMessageToConsole("                   <gradient:#677042:#757853>Made by "+author+"</gradient>");
        sendMiniMessageToConsole("");
    }
    public void sendOnDisableMsgToLogger(String pluginName,String author,String version,String type){
        sendMiniMessageToConsole("");
        sendMiniMessageToConsole("    <gradient:#30fbfb:#6adafd><bold>"+pluginName+"  v"+version+"-"+type+"  Successfully unloaded</gradient>");
        sendMiniMessageToConsole("                   <gradient:#677042:#757853>Made by "+author+"</gradient>");
        sendMiniMessageToConsole("");
    }
    public void sendMiniMessageToPlayer(Player player,String formatMessage){
        BukkitAudiences audience= BukkitAudiences.create(getPlugin());
        MiniMessage miniMessage=MiniMessage.miniMessage();
        Component message= MiniMessage.miniMessage().deserialize(
                formatMessage
        );
        audience.player(player).sendMessage(message);
    }
    public void sendMiniMessageToSender(CommandSender sender,String formatMessage){
        BukkitAudiences audience= BukkitAudiences.create(getPlugin());
        MiniMessage miniMessage=MiniMessage.miniMessage();
        Component message= MiniMessage.miniMessage().deserialize(
                formatMessage
        );
        audience.sender(sender).sendMessage(message);
    }
    public void sendMiniMessageToConsole(String formatMessage){
        BukkitAudiences audience= BukkitAudiences.create(getPlugin());
        MiniMessage miniMessage=MiniMessage.miniMessage();
        Component message= MiniMessage.miniMessage().deserialize(
                formatMessage
        );
        audience.console().sendMessage(message);
    }
    public void sendMiniMessageToConsole(List<String> formatMessages){
        for (String formatMessage : formatMessages) {
            sendMiniMessageToConsole(formatMessage);
        }
    }
    public void sendMiniMessageToSender(CommandSender sender,List<String> formatMessages){
        for (String formatMessage : formatMessages) {
            sendMiniMessageToSender(sender,formatMessage);
        }
    }
    public void sendMiniMessageToPlayer(Player player,List<String> formatMessages){
        for (String formatMessage : formatMessages) {
            sendMiniMessageToPlayer(player,formatMessage);
        }
    }
    public void sendGradientToPlayer(Player player,List<String> rgbColors,String message){
        StringBuilder start= new StringBuilder("<gradient");
        for (String rgbColor : rgbColors) {
            start.append(":").append(rgbColor);
        }
        start.append(">");
        String returnMessage=start+message+"</gradient>";
        BukkitAudiences audience= BukkitAudiences.create(getPlugin());
        audience.player(player).sendMessage(Component.text(returnMessage));
    }
    public void sendPAPIToOnline(String inStr){
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String message= PlaceholderAPIHook.getPlaceholder(onlinePlayer,inStr);
            sendToPlayer(onlinePlayer,message);
        }
    }
}
