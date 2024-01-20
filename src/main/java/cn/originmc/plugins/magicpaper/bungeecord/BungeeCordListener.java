package cn.originmc.plugins.magicpaper.bungeecord;

import cn.origincraft.magic.function.results.ListResult;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BungeeCordListener implements PluginMessageListener {
    public JavaPlugin plugin;
    public BungeeCordListener(JavaPlugin plugin){
        this.plugin=plugin;
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
    }
    public void unRegister(){
        plugin.getServer().getMessenger().unregisterIncomingPluginChannel(plugin, "BungeeCord", this);
        plugin.getServer().getMessenger().unregisterOutgoingPluginChannel(plugin, "BungeeCord");
    }
    @Override
    public void onPluginMessageReceived(String channel, @NotNull Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF(); // 读取子通道名称
        if (!subchannel.equalsIgnoreCase("magicpaper")){
            return;
        }
        int len = in.readInt(); // 读取数据长度
        List<String> data =new ArrayList<>();
        for (int i = 0 ;i<len;i++){
            data.add(in.readUTF());
        }
        ContextMap contextMap=new NormalContext();
        contextMap.putVariable("sender",new PlayerResult(player));
        contextMap.putVariable("data",new ListResult(data));
        MagicPaperTriggerManager.trigger("BungeeCordMessageTrigger",contextMap);
    }
}
