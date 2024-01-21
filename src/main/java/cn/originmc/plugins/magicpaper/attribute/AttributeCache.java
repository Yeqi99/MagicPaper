package cn.originmc.plugins.magicpaper.attribute;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.attribute.MagicAttribute;
import cn.originmc.plugins.magicpaper.data.manager.AttributeManager;
import cn.originmc.plugins.magicpaper.util.item.MagicItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

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

    public Map<String, Map<String, Double>> attributeMap = new ConcurrentHashMap<>();

    public AttributeCache() {

    }

    public void load() {
        AttributeManager.load();
    }

    public void upOnlineCache() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Map<String, Double> playerAttributes = new ConcurrentHashMap<>();
            for (MagicAttribute value : AttributeManager.attributeMap.values()) {
                playerAttributes.put(value.id, getPlayerAttribute(onlinePlayer, value.id, true));
            }
            attributeMap.put(onlinePlayer.getUniqueId().toString(), playerAttributes);
        }
    }

    public static double getPlayerAttribute(Player player, String attributeName, boolean addDataEntity) {
        double value = 0;
        String slots = MagicPaper.getCheckSlots();
        for (String slot : slots.split(" ")) {
            switch (slot) {
                case "mh": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "oh": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getItemInOffHand());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "h": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getHelmet());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "c": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getChestplate());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "l": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getLeggings());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                case "b": {
                    MagicItem magicItem = new MagicItem(player.getInventory().getBoots());
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
                default: {
                    int index = Integer.parseInt(slot);
                    MagicItem magicItem = new MagicItem(player.getInventory().getItem(index));
                    value += magicItem.getAttributeValueBySlot(attributeName, slot);
                    break;
                }
            }
        }

        if (addDataEntity) {
            value += (double) MagicPaper.dataEntityManager.getData(player.getUniqueId().toString(), attributeName);
        }
        return value;
    }

    public static double getPlayerAttribute(Player player, String attributeName, String slot, boolean addDataEntity) {
        double value = 0;
        switch (slot) {
            case "mh": {
                MagicItem magicItem = new MagicItem(player.getInventory().getItemInMainHand());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "oh": {
                MagicItem magicItem = new MagicItem(player.getInventory().getItemInOffHand());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "h": {
                MagicItem magicItem = new MagicItem(player.getInventory().getHelmet());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "c": {
                MagicItem magicItem = new MagicItem(player.getInventory().getChestplate());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "l": {
                MagicItem magicItem = new MagicItem(player.getInventory().getLeggings());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            case "b": {
                MagicItem magicItem = new MagicItem(player.getInventory().getBoots());
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
            default: {
                int index = Integer.parseInt(slot);
                MagicItem magicItem = new MagicItem(player.getInventory().getItem(index));
                value = magicItem.getAttributeValueBySlot(attributeName, slot);
                break;
            }
        }
        if (addDataEntity) {
            value += (double) MagicPaper.dataEntityManager.getData(player.getUniqueId().toString(), attributeName);
        }
        return value;
    }
}
