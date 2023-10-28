package cn.originmc.plugins.magicpaper.trigger.listener;

import cn.origincraft.magic.function.results.BooleanResult;
import cn.origincraft.magic.function.results.IntegerResult;
import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.NormalContext;
import cn.originmc.plugins.magicpaper.magic.result.PlayerResult;
import cn.originmc.plugins.magicpaper.trigger.MagicPaperTriggerManager;
import dev.rgbmc.remotekeyboard.events.KeyInputEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class RemoteKeyboardBukkitListener implements Listener {
    public RemoteKeyboardBukkitListener(JavaPlugin plugin){
        // bukkit 注册监听器
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKeyboard(KeyInputEvent e){
        NormalContext normalContext=new NormalContext();
        normalContext.putVariable("event_name",new StringResult(e.getEventName()));
        normalContext.putVariable("self",new PlayerResult(Bukkit.getPlayer(e.getPlayer())));
        normalContext.putVariable("key",new IntegerResult(e.getKeycode()));
        normalContext.putVariable("mod",new IntegerResult(e.getMods()));
        normalContext.putVariable("isPressed",new BooleanResult(e.isPressed()));
        normalContext.putVariable("isRelease",new BooleanResult(e.isRelease()));
        MagicPaperTriggerManager.trigger("PlayerKeyboardTrigger", normalContext);
    }
}
