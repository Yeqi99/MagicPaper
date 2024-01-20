package cn.originmc.plugins.magicpaper.bungeecord;

import cn.originmc.plugins.magicpaper.MagicPaper;
import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class BungeeCordSender {
    public static boolean sendBungeeCordMessage(Player player, List<String> data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        for (String datum : data) {
            out.writeUTF(datum);
        }
        if (player == null) {
            player = getAnyPlayer();
        }
        if (player == null) {
            return false;
        }
        player.sendPluginMessage(MagicPaper.getInstance(), "BungeeCord", out.toByteArray());
        return true;
    }

    public static boolean sendMagicBungeeCordMessage(Player player, List<String> data) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("MagicPaper");
        for (String datum : data) {
            out.writeUTF(datum);
        }
        if (player == null) {
            player = getAnyPlayer();
        }
        if (player == null) {
            return false;
        }
        player.sendPluginMessage(MagicPaper.getInstance(), "BungeeCord", out.toByteArray());
        return true;
    }

    public static void playerToServer(Player player, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.sendPluginMessage(MagicPaper.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static void playerToServer(String playerName, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(playerName);
        out.writeUTF(serverName);
        Player player=getAnyPlayer();
        if (player == null) {
            return;
        }
        player.sendPluginMessage(MagicPaper.getInstance(), "BungeeCord", out.toByteArray());
    }

    public static Player getAnyPlayer(){
        return Iterables.getFirst(Bukkit.getOnlinePlayers(), null);
    }
}
