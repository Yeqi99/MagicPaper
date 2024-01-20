package cn.originmc.plugins.magicpaper.listener;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemVariableRefreshListener extends PacketAdapter {
    public static boolean canVarRefresh(){
        return MagicPaper.getInstance().getConfig().getBoolean("ProtocolLib.item-variable-refresh",false);
    }
    public static boolean canPapiRefresh(){
        if (!PlaceholderAPIHook.status){
            return false;
        }
        return MagicPaper.getInstance().getConfig().getBoolean("ProtocolLib.item-papi-refresh",false);
    }
    public ItemVariableRefreshListener(JavaPlugin plugin) {
        super(plugin, ListenerPriority.HIGH,  PacketType.Play.Server.WINDOW_ITEMS);
    }
    public void onPacketSending(PacketEvent e){
        PacketContainer packet=e.getPacket();
        List<ItemStack> items=packet.getItemListModifier().read(0);
        List<ItemStack> resultItems=new ArrayList<>();
        Player player = e.getPlayer();
        for(ItemStack item:items){
            if(item.getType().equals(Material.AIR)){
                resultItems.add(item);
                continue;
            }
            MagicItem magicItem=new MagicItem(item);
            magicItem.refresh(canVarRefresh(),canPapiRefresh(),player);
            resultItems.add(magicItem.getItemStack());
        }
        packet.getItemListModifier().write(0,resultItems);
    }
}
