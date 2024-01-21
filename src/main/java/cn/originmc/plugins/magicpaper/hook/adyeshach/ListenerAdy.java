package cn.originmc.plugins.magicpaper.hook.adyeshach;


import ink.ptms.adyeshach.core.event.AdyeshachEntityDamageEvent;
import ink.ptms.adyeshach.core.event.AdyeshachEntityInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ListenerAdy implements Listener { // 记得自己注册一下

    @EventHandler
    public void i(AdyeshachEntityInteractEvent event) {
        // 交互实体事件
    }

    @EventHandler
    public void b(AdyeshachEntityDamageEvent event) {
        // 打实体事件
    }


}
