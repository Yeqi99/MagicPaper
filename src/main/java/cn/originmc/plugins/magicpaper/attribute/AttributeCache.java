package cn.originmc.plugins.magicpaper.attribute;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.attribute.MagicAttribute;
import cn.originmc.plugins.magicpaper.data.manager.AttributeManager;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class AttributeCache {

    private BukkitRunnable saveTask;
    private int saveInterval = 600; // 默认保存间隔为10分钟（以ticks为单位）
    private boolean isPaused = false;

    public void pauseAsyncSaveTask() {
        isPaused = true;
    }

    public void resumeAsyncSaveTask() {
        isPaused = false;
    }

    public void stopAsyncSaveTask() {
        if (saveTask != null) {
            saveTask.cancel();
        }
    }

    public void setSaveInterval(int saveInterval) {
        this.saveInterval = saveInterval;
    }

    public void startAsyncSaveTask() {
        if (saveTask != null) {
            saveTask.cancel();
        }

        saveTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isPaused) {
                    upOnlineCache();
                }
            }
        };

        saveTask.runTaskTimerAsynchronously(MagicPaper.getInstance(), 0L, saveInterval);
    }

    public Map<String, Map<String, Double>> attributeMap = new HashMap<>();

    public AttributeCache() {

    }

    public void load() {
        attributeMap = new ConcurrentHashMap<>();
        AttributeManager.load();
    }

    public void upOnlineCache() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Map<String, Double> playerAttributes = new ConcurrentHashMap<>();
            for (MagicAttribute value : AttributeManager.attributeMap.values()) {
                playerAttributes.put(value.id, getPlayerAttribute(onlinePlayer, value.id, false));
            }
            attributeMap.put(onlinePlayer.getUniqueId().toString(), playerAttributes);
        }
    }

    public static double getPlayerAttribute(Player player, String attributeName, boolean addDataEntity) {
        long startTime = System.nanoTime(); // 开始时间
        double value = 0;
        String slots = MagicPaper.getCheckSlots();
        for (String slot : slots.split(" ")) {
            switch (slot) {
                case "mh": {
                    ItemStack itemStack = player.getInventory().getItemInMainHand();
                    if (itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "oh": {
                    ItemStack itemStack = player.getInventory().getItemInOffHand();
                    if (itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "h": {
                    ItemStack itemStack = player.getInventory().getHelmet();
                    if (itemStack == null || itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "c": {
                    ItemStack itemStack = player.getInventory().getChestplate();
                    if (itemStack == null || itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "l": {
                    ItemStack itemStack = player.getInventory().getLeggings();
                    if (itemStack == null || itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "b": {
                    ItemStack itemStack = player.getInventory().getBoots();
                    if (itemStack == null || itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                default: {
                    int index = Integer.parseInt(slot);
                    ItemStack itemStack = player.getInventory().getItem(index);
                    if (itemStack == null || itemStack.isEmpty()) {
                        continue;
                    }
                    MagicItem magicItem = new MagicItem(itemStack);
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
            }
            long endTime = System.nanoTime(); // 结束时间
            long duration = (endTime - startTime); // 计算耗时，单位为纳秒
            MagicPaper.getSender().sendToLogger("缓存一个玩家的当前属性，未计算数据实体时耗时"+duration+"毫秒");
        }
        long endTime = System.nanoTime(); // 结束时间
        long duration = (endTime - startTime); // 计算耗时，单位为纳秒
        MagicPaper.getSender().sendToLogger("缓存全部玩家的当前属性，未计算数据实体时耗时"+duration+"毫秒");
        if (addDataEntity) {
            Object object= MagicPaper.dataEntityManager.getData(player.getUniqueId().toString(), attributeName);
            if (object==null){
                return value;
            }
            if (object instanceof Double){
                value+=(double) object;
            }else if (object instanceof String){
                value+=Double.parseDouble((String) object);
            }
        }
        endTime = System.nanoTime(); // 结束时间
        duration = (endTime - startTime); // 计算耗时，单位为纳秒
        MagicPaper.getSender().sendToLogger("缓存全部玩家的当前属性，总体耗时"+duration+"毫秒");
        return value;
    }

    public static double getPlayerAttribute(Player player, String attributeName, String slot, boolean addDataEntity) {
        double value = 0;
        switch (slot) {
            case "mh": {
                ItemStack itemStack = player.getInventory().getItemInMainHand();
                if (itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "oh": {
                ItemStack itemStack = player.getInventory().getItemInOffHand();
                if (itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "h": {
                ItemStack itemStack = player.getInventory().getHelmet();
                if (itemStack == null || itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "c": {
                ItemStack itemStack = player.getInventory().getChestplate();
                if (itemStack == null || itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "l": {
                ItemStack itemStack = player.getInventory().getLeggings();
                if (itemStack == null || itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "b": {
                ItemStack itemStack = player.getInventory().getBoots();
                if (itemStack == null || itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            default: {
                int index = Integer.parseInt(slot);
                ItemStack itemStack = player.getInventory().getItem(index);
                if (itemStack == null || itemStack.isEmpty()) {
                    return 0;
                }
                MagicItem magicItem = new MagicItem(itemStack);
                value += magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
        }
        if (addDataEntity) {
            Object object= MagicPaper.dataEntityManager.getData(player.getUniqueId().toString(), attributeName);
            if (object instanceof Double){
                value+=(double) object;
            }else if (object instanceof String){
                value+=Double.parseDouble((String) object);
            }
        }
        return value;
    }
}
