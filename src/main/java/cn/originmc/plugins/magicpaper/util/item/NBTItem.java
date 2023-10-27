package cn.originmc.plugins.magicpaper.util.item;

import cn.originmc.plugins.magicpaper.hook.PlaceholderAPIHook;
import cn.originmc.plugins.magicpaper.util.text.Color;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTType;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.DecimalFormat;
import java.util.*;

public class NBTItem {
    private ItemStack origin;

    //构造/获取 方法

    /**
     * 构造方法
     *
     * @param itemStack Bukkit中的ItemStack实例
     */
    public NBTItem(ItemStack itemStack) {
        if (itemStack == null) {
            throw new IllegalArgumentException("ItemStack is empty");
        }
        updateOrigin(itemStack);
        initMeta();
    }

    /**
     * 构造方法
     *
     * @param itemString 格式化字符串形式的物品
     */
    public NBTItem(String itemString) {
        if (itemString == null) {
            throw new IllegalArgumentException("String is empty");
        }
        ItemStack itemStack = toItemStack(itemString);
        if (itemStack == null) {
            throw new IllegalArgumentException("Format string exception");
        }
        updateOrigin(itemStack);
        initMeta();
    }

    /**
     * 初始化元数据
     *
     * @return 已有元数据/物品不为null且没有元数据:true | 物品为null:false
     */
    public boolean initMeta() {
        if (isNull()) {
            return false;
        }
        if (!hasItemMeta()) {
            getItemStack().setItemMeta(getItemStack().getItemMeta());
        }
        return true;
    }

    /**
     * 更新NBTItem中的origin实例
     *
     * @param itemStack Bukkit中的ItemStack实例
     */
    public void updateOrigin(ItemStack itemStack) {
        this.origin = itemStack;
    }

    /**
     * 更新NBTItem中的origin实例
     *
     * @param itemString 格式化字符串型式的物品
     */
    public void updateOrigin(String itemString) {
        this.origin = toItemStack(itemString);
    }

    /**
     * 获得Bukkit中的ItemStack实例
     *
     * @return ItemStack实例
     */
    public ItemStack getItemStack() {
        return origin;
    }

    /**
     * 将NBTItem转换为格式化字符串
     *
     * @return
     */
    public String getString() {
        return toString(getItemStack());
    }

    /* 判断方法 */
    public boolean hasItemMeta() {
        if (isNull()) {
            return false;
        }
        return getItemStack().hasItemMeta();
    }

    public boolean isAir() {
        if (isNull()) {
            return false;
        }
        return getItemStack().getType() == Material.AIR;
    }

    public boolean isNull() {
        return getItemStack() == null;
    }

    public boolean hasLore() {
        if (hasItemMeta()) {
            return Objects.requireNonNull(getItemStack().getItemMeta()).hasLore();
        } else {
            return false;
        }
    }

    public boolean hasDisplay() {
        if (hasItemMeta()) {
            if (Objects.requireNonNull(getItemStack().getItemMeta()).hasDisplayName()) {
                return true;
            }
        }
        return false;
    }

    /* 通常方法 */
    public String getDisplay() {
        return getItemStack().getItemMeta().getDisplayName();
    }

    public Material getType() {
        return getItemStack().getType();
    }

    public int getAmount() {
        return getItemStack().getAmount();
    }

    public int getMaxStackSize() {
        return getItemStack().getMaxStackSize();
    }

    public int getEnchantmentLevel(Enchantment enchantment) {
        return getItemStack().getEnchantmentLevel(enchantment);
    }

    /**
     * 隐藏编号含义
     * 1 - 隐藏附魔
     * 　　2 - 隐藏自定义属性
     * 　　3 - 隐藏附魔和自定义属性
     * 　　4 - 隐藏{Unbreakable} (永久不毁)
     * 　　8 - 隐藏{CanDestroy} (可破坏)
     * 　　16 - 隐藏{CanPlaceOn} (可放置在)
     * 　　32 - 隐藏大部分信息(药水信息，书作者，烟花效果等等)
     * 　　63 - 隐藏所有的信息，除了名字和附加文字
     */
    public void setHideFlags(int flag) {
        set("HideFlags", flag, "/");
    }

    public int getCustomModelData() {
        return (int) get("CustomModelData", DataType.INT, "/");
    }

    public void setUnbreakable(boolean flag) {
        set("Unbreakable", flag, "/");
    }

    public boolean isUnbreakable() {
        return (boolean) get("Unbreakable", DataType.BOOLEAN, "/");
    }

    public int getEnchantmentLevel(String enchantment) {
        Enchantment e = Enchantment.getByName(enchantment);
        if (e == null) {
            return 0;
        }
        return getItemStack().getEnchantmentLevel(e);
    }

    public List<String> refreshVar(List<String> format, char varChar) {
        List<String> lore = ItemVariable.parse(getItemStack(), format, varChar);
        lore = Color.toColor(lore);
        ItemMeta itemMeta = origin.getItemMeta();
        itemMeta.setLore(lore);
        origin.setItemMeta(itemMeta);
        return lore;
    }

    public List<String> refreshPapi(List<String> format, Player player) {
        List<String> lore = PlaceholderAPIHook.getPlaceholder(player, format);
        lore = Color.toColor(lore);
        ItemMeta itemMeta = origin.getItemMeta();
        itemMeta.setLore(lore);
        origin.setItemMeta(itemMeta);
        return lore;
    }

    private UUID getAttributeUUID(Attribute attribute, String id) {
        ItemMeta itemMeta = getItemStack().getItemMeta();
        if (!itemMeta.hasAttributeModifiers()) {
            return null;
        }
        for (AttributeModifier mod : itemMeta.getAttributeModifiers(attribute)) {
            if (mod.getName().equalsIgnoreCase(id)) {
                return mod.getUniqueId();
            }
        }
        return null;
    }

    public void setAttribute(String id, Attribute attribute, double value, AttributeModifier.Operation operation, EquipmentSlot slot) {
        ItemMeta itemMeta = getItemStack().getItemMeta();
        UUID uuid = getAttributeUUID(attribute, id);
        AttributeModifier attributeModifier;
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        attributeModifier = new AttributeModifier(uuid, id, value, operation, slot);
        if (!itemMeta.hasAttributeModifiers()) {
            itemMeta.addAttributeModifier(attribute, attributeModifier);
            getItemStack().setItemMeta(itemMeta);
            return;
        }
        Collection<AttributeModifier> oldAMod = itemMeta.getAttributeModifiers(attribute);
        if (oldAMod == null) {
            itemMeta.addAttributeModifier(attribute, attributeModifier);
            getItemStack().setItemMeta(itemMeta);
            return;
        }
        Collection<AttributeModifier> newAMod = new ArrayList<>();
        for (AttributeModifier modifier : oldAMod) {
            if (modifier.getUniqueId().toString().equalsIgnoreCase(uuid.toString())) {
                continue;
            }
            newAMod.add(modifier);
        }
        itemMeta.removeAttributeModifier(attribute);
        newAMod.add(attributeModifier);
        for (AttributeModifier modifier : newAMod) {
            itemMeta.addAttributeModifier(attribute, modifier);
        }
        getItemStack().setItemMeta(itemMeta);
    }

    public double getAttributeValue(String id, Attribute attribute, AttributeModifier.Operation operation, EquipmentSlot slot) {
        ItemMeta itemMeta = getItemStack().getItemMeta();
        UUID uuid = getAttributeUUID(attribute, id);
        if (uuid == null) {
            return 0;
        }
        Collection<AttributeModifier> oldAMod = itemMeta.getAttributeModifiers(attribute);
        if (oldAMod == null) {
            return 0;
        }
        for (AttributeModifier modifier : oldAMod) {
            if (modifier.getUniqueId().equals(uuid)) {
                if (modifier.getOperation() == operation) {
                    if (modifier.getSlot() == slot) {
                        return modifier.getAmount();
                    }
                }
            }
        }
        return 0;
    }

    public void addAttributeValue(String id, Attribute attribute, double value, AttributeModifier.Operation operation, EquipmentSlot slot) {
        setAttribute(id, attribute, getAttributeValue(id, attribute, operation, slot) + value, operation, slot);
    }

    /* 数据方法 */
    public void mergeItemNBT(ItemStack itemStack) {
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound itemData = de.tr7zw.nbtapi.NBTItem.convertItemtoNBT(itemStack);
        nbtItem.mergeCompound(itemData);
        updateOrigin(nbtItem.getItem());
    }

    public void mergeItemNBT(String itemString) {
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound itemData = de.tr7zw.nbtapi.NBTItem.convertItemtoNBT(toItemStack(itemString));
        nbtItem.mergeCompound(itemData);
        updateOrigin(nbtItem.getItem());
    }

    public void addSpace(String spaceName) {
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = spaceName.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    nextCompound = compound.addCompound(addressPart);
                }
                compound = nextCompound;
            }
        }
        updateOrigin(nbtItem.getItem());
    }

    public boolean hasSpace(String spaceName) {
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = spaceName.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return false;
                }
                compound = nextCompound;
            }
        }
        return true;
    }

    public void removeSpace(String spaceName) {
        if (!spaceName.equals("/")) {
            return;
        }
        if (!hasSpace(spaceName)) {
            return;
        }
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;
        NBTCompound beforeCompound = null;
        String[] addressParts = spaceName.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return;
                }
                beforeCompound = compound;
                compound = nextCompound;
            }
        }
        if (beforeCompound != null) {
            beforeCompound.removeKey(addressParts[addressParts.length - 1]);
        }
        updateOrigin(nbtItem.getItem());
    }

    public boolean set(String key, Object value, String address) {
        if (isNull() || isAir()) {
            return false;
        }

        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = address.startsWith("/") ? address.substring(1).split("/") : address.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    nextCompound = compound.addCompound(addressPart);
                }
                compound = nextCompound;
            }
        }

        switch (DataType.getType(value)) {
            case INT: {
                compound.setInteger(key, (Integer) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case FLOAT: {
                compound.setFloat(key, (Float) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case DOUBLE: {
                compound.setDouble(key, (Double) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case STRING: {
                compound.setString(key, (String) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case ITEMSTACK: {
                compound.setItemStack(key, (ItemStack) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case BOOLEAN: {
                compound.setBoolean(key, (Boolean) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case LONG: {
                compound.setLong(key, (Long) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case UUID: {
                compound.setUUID(key, (UUID) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case SHORT: {
                compound.setShort(key, (Short) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case INTARRAY: {
                compound.setIntArray(key, (int[]) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case ITEMSTACKARRAY: {
                compound.setItemStackArray(key, (ItemStack[]) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case BYTE: {
                compound.setByte(key, (Byte) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }
            case BYTEARRAY: {
                compound.setByteArray(key, (byte[]) value);
                updateOrigin(nbtItem.getItem());
                return true;
            }

            default: {
                return false;
            }
        }
    }

    public Object get(String key, DataType dataType, String address) {
        if (isNull() || isAir()) {
            return null;
        }

        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = address.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return null; // 如果路径不存在，直接返回null
                }
                compound = nextCompound;
            }
        }
        switch (dataType) {
            case INT: {
                return compound.getInteger(key);
            }
            case FLOAT: {
                return compound.getFloat(key);
            }
            case DOUBLE: {
                return compound.getDouble(key);
            }
            case STRING: {
                return compound.getString(key);
            }
            case ITEMSTACK: {
                return compound.getItemStack(key);
            }
            case BOOLEAN: {
                return compound.getBoolean(key);
            }
            case LONG: {
                return compound.getLong(key);
            }
            case UUID: {
                return compound.getUUID(key);
            }
            case SHORT: {
                return compound.getShort(key);
            }
            case INTARRAY: {
                return compound.getIntArray(key);
            }
            case ITEMSTACKARRAY: {
                return compound.getItemStackArray(key);
            }
            case BYTE: {
                return compound.getByte(key);
            }
            case BYTEARRAY: {
                return compound.getByteArray(key);
            }
            default: {
                return null;
            }
        }
    }

    public List<String> getKeys() {
        if (isNull() || isAir()) {
            return Collections.emptyList();
        }

        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        List<String> keys = new ArrayList<>();
        collectKeys(compound, "", keys);

        return keys;
    }

    public Set<String> getKeys(String address) {
        if (isNull() || isAir()) {
            return Collections.emptySet();
        }
        NBTCompound nbtCompound = getCompound(getItemStack(), address);

        return nbtCompound.getKeys();
    }

    private void collectKeys(NBTCompound compound, String currentAddress, List<String> keys) {
        for (String key : compound.getKeys()) {
            NBTCompound nestedCompound = compound.getCompound(key);
            if (nestedCompound != null) {
                String newAddress = currentAddress.isEmpty() ? key : currentAddress + "/" + key;
                keys.add(newAddress);
                collectKeys(nestedCompound, newAddress, keys);
            } else {
                keys.add(currentAddress.isEmpty() ? key : currentAddress + "/" + key);
            }
        }
    }

    public boolean hasKey(String key, String address) {
        if (isNull() || isAir()) {
            return false;
        }

        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = address.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return false;
                }
                compound = nextCompound;
            }
        }

        return compound.hasKey(key);
    }

    public boolean removeKey(String key, String address) {
        if (isNull() || isAir()) {
            return false;
        }

        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(getItemStack());
        NBTCompound compound = nbtItem;

        String[] addressParts = address.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return false;
                }
                compound = nextCompound;
            }
        }

        if (compound.hasKey(key)) {
            compound.removeKey(key);
            updateOrigin(nbtItem.getItem());
            return true;
        }

        return false;
    }
    /* 静态方法 */

    /**
     * 将字符串还原为ItemStack实例
     *
     * @param itemString 格式化字符串
     * @return ItemStack实例
     */
    public static ItemStack toItemStack(String itemString) {
        ReadWriteNBT nbt = NBT.parseNBT(itemString);
        return NBT.itemStackFromNBT(nbt);
    }

    /**
     * 将ItemStack实例转为格式化字符串
     *
     * @param itemStack ItemStack实例
     * @return 格式化字符串
     */
    public static String toString(ItemStack itemStack) {
        ReadWriteNBT nbt = NBT.itemStackToNBT(itemStack);
        return nbt.toString();
    }

    public static AttributeModifier.Operation getOperation(String sign) {
        switch (sign) {
            case "+":
                return AttributeModifier.Operation.ADD_NUMBER;
            case "+%":
                return AttributeModifier.Operation.ADD_SCALAR;
            case "+*":
                return AttributeModifier.Operation.MULTIPLY_SCALAR_1;
            default:
                return AttributeModifier.Operation.ADD_NUMBER;
        }
    }

    public static EquipmentSlot getSlot(String sign) {
        switch (sign) {
            case "mh":
                return EquipmentSlot.HAND;
            case "oh":
                return EquipmentSlot.OFF_HAND;
            case "s":
                return EquipmentSlot.FEET;
            case "l":
                return EquipmentSlot.LEGS;
            case "c":
                return EquipmentSlot.CHEST;
            case "h":
                return EquipmentSlot.HEAD;
            default:
                return EquipmentSlot.HAND;
        }
    }

    public static NBTCompound getCompound(ItemStack itemStack, String address) {
        de.tr7zw.nbtapi.NBTItem nbtItem = new de.tr7zw.nbtapi.NBTItem(itemStack);
        NBTCompound compound = nbtItem;
        String[] addressParts = address.split("/");
        for (String addressPart : addressParts) {
            if (!addressPart.isEmpty()) {
                NBTCompound nextCompound = compound.getCompound(addressPart);
                if (nextCompound == null) {
                    return null;
                }
                compound = nextCompound;
            }
        }
        return compound;
    }

    /*
    将另一个物品的nbt添加到本物品，如果本物品没有该nbt则添加，如果有，数值相加，如果是字符串则覆盖
    addItem:添加的物品
    targetAddress:目标物品NBT所在地址
    originAddress:被添加物品NBT所在地址
     */
    public void addItem(ItemStack addItem, String targetAddress, String originAddress) {
        NBTCompound targetCompound = getCompound(getItemStack(), targetAddress);
        NBTCompound originCompound = getCompound(addItem, originAddress);
        Set<String> keys = originCompound.getKeys();
        for (String key : keys) {
            NBTType nbtType = originCompound.getType(key);
            if (nbtType == NBTType.NBTTagDouble) {
                double value = originCompound.getDouble(key);
                if (targetCompound.hasKey(key)) {
                    value = value + targetCompound.getDouble(key);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                value=Double.parseDouble(decimalFormat.format(value));
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagFloat) {
                float value = originCompound.getFloat(key);
                if (targetCompound.hasKey(key)) {
                    value = value + targetCompound.getFloat(key);
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                value=Float.parseFloat(decimalFormat.format(value));
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagInt) {
                int value = originCompound.getInteger(key);
                if (targetCompound.hasKey(key)) {
                    value = value + targetCompound.getInteger(key);
                }
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagLong) {
                long value = originCompound.getLong(key);
                if (targetCompound.hasKey(key)) {
                    value = value + targetCompound.getLong(key);
                }
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagShort) {
                short value = originCompound.getShort(key);
                if (targetCompound.hasKey(key)) {
                    value = (short) (value + targetCompound.getShort(key));
                }
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagString) {
                String value = originCompound.getString(key);
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagByteArray) {
                byte[] value = originCompound.getByteArray(key);
                set(key, value, targetAddress);
            } else if (nbtType == NBTType.NBTTagIntArray) {
                int[] value = originCompound.getIntArray(key);
                set(key, value, targetAddress);
            }
        }
    }

    public void removeItem(ItemStack addItem, String targetAddress, String originAddress) {
        NBTCompound targetCompound = getCompound(getItemStack(), targetAddress);
        NBTCompound originCompound = getCompound(addItem, originAddress);
        Set<String> keys = originCompound.getKeys();
        for (String key : keys) {
            NBTType nbtType = originCompound.getType(key);
            if (nbtType == NBTType.NBTTagDouble) {
                double value = originCompound.getDouble(key);
                if (targetCompound.hasKey(key)) {
                    value = targetCompound.getDouble(key) - value;
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                value=Double.parseDouble(decimalFormat.format(value));

                if (value <= 0) {
                    removeKey(key, targetAddress);
                } else {
                    set(key, value, targetAddress);
                }
            } else if (nbtType == NBTType.NBTTagFloat) {
                float value = originCompound.getFloat(key);
                if (targetCompound.hasKey(key)) {
                    value = targetCompound.getFloat(key) - value;
                }
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                value=Float.parseFloat(decimalFormat.format(value));
                if (value <= 0) {
                    removeKey(key, targetAddress);
                } else {
                    set(key, value, targetAddress);
                }
            } else if (nbtType == NBTType.NBTTagInt) {
                int value = originCompound.getInteger(key);
                if (targetCompound.hasKey(key)) {
                    value = targetCompound.getInteger(key) - value;
                }
                if (value <= 0) {
                    removeKey(key, targetAddress);
                } else {
                    set(key, value, targetAddress);
                }
            } else if (nbtType == NBTType.NBTTagLong) {
                long value = originCompound.getLong(key);
                if (targetCompound.hasKey(key)) {
                    value =targetCompound.getLong(key)- value;
                }
                if (value <= 0) {
                    removeKey(key, targetAddress);
                } else {
                    set(key, value, targetAddress);
                }
            } else if (nbtType == NBTType.NBTTagShort) {
                short value = originCompound.getShort(key);
                if (targetCompound.hasKey(key)) {
                    value = (short) (targetCompound.getShort(key)-value);
                }
                if (value <= 0) {
                    removeKey(key, targetAddress);
                } else {
                    set(key, value, targetAddress);
                }
            } else {
                if (targetCompound.hasKey(key)) {
                    removeKey(key, targetAddress);
                }
            }
        }
    }
    public ItemStack getPlayerHead(Player player,int amount) {
        ItemStack playerHead = getItemStack().clone();
        playerHead.setType(Material.PLAYER_HEAD);
        playerHead.setAmount(amount);
        // 设置头颅的皮肤，将玩家名字设置为头颅的皮肤
        playerHead = setPlayerHeadTexture(playerHead, player.getName());
        return playerHead;
    }

    // 设置头颅的皮肤
    public ItemStack setPlayerHeadTexture(ItemStack itemStack, String playerName) {
        if (itemStack.getItemMeta() instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(playerName));
            itemStack.setItemMeta(skullMeta);
        }
        return itemStack;
    }
}
