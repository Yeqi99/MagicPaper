package cn.originmc.plugins.magicpaper.hook.adyeshach;

import ink.ptms.adyeshach.core.Adyeshach;
import ink.ptms.adyeshach.core.entity.EntityInstance;
import ink.ptms.adyeshach.core.entity.EntityTypes;
import ink.ptms.adyeshach.core.entity.manager.FastAPIKt;
import ink.ptms.adyeshach.core.entity.manager.ManagerType;
import ink.ptms.adyeshach.core.entity.type.AdyBlockDisplay;
import ink.ptms.adyeshach.core.entity.type.AdyInteraction;
import ink.ptms.adyeshach.core.entity.type.AdyItemDisplay;
import ink.ptms.adyeshach.core.entity.type.AdyTextDisplay;
import ink.ptms.adyeshach.taboolib.module.chat.ComponentText;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

public class AdyeshachManager {

    public static AdyTextDisplay spawnTextDisplay(World world, Location location, ManagerType managerType) {
        // ManagerType.PERSISTENT 是持久化 重启了这个实体也在
        // ManagerType.TEMPORARY 不是持久化 重启了这个实体就没了
        EntityInstance entityInstance = FastAPIKt.spawnEntity(world, location, EntityTypes.TEXT_DISPLAY, managerType,(entity) ->{

        });
        return (AdyTextDisplay) entityInstance;
    }

    public static AdyItemDisplay spawnItemDisplay(World world, Location location, ManagerType managerType) {
        // ManagerType.PERSISTENT 是持久化 重启了这个实体也在
        // ManagerType.TEMPORARY 不是持久化 重启了这个实体就没了
        EntityInstance entityInstance = FastAPIKt.spawnEntity(world, location, EntityTypes.ITEM_DISPLAY, managerType,(entity) ->{

        });
        return (AdyItemDisplay) entityInstance;
    }

    public static AdyBlockDisplay spawnBlockDisplay(World world, Location location, ManagerType managerType) {
        // ManagerType.PERSISTENT 是持久化 重启了这个实体也在
        // ManagerType.TEMPORARY 不是持久化 重启了这个实体就没了
        EntityInstance entityInstance = FastAPIKt.spawnEntity(world, location, EntityTypes.BLOCK_DISPLAY, managerType,(entity) ->{

        });
        return (AdyBlockDisplay) entityInstance;
    }

    public static AdyInteraction spawnInteraction(World world, Location location, ManagerType managerType) {
        // ManagerType.PERSISTENT 是持久化 重启了这个实体也在
        // ManagerType.TEMPORARY 不是持久化 重启了这个实体就没了
        EntityInstance entityInstance = FastAPIKt.spawnEntity(world, location, EntityTypes.INTERACTION, managerType,(entity) ->{

        });
        return (AdyInteraction) entityInstance;
    }

    public static void allEntity() {
        Adyeshach.INSTANCE.api().getPublicEntityManager(ManagerType.PERSISTENT).getEntities();
    }


}
