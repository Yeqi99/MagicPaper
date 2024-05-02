package cn.originmc.plugins.magicpaper.hook.mythicmobs;

import cn.origincraft.magic.MagicManager;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.adapters.BukkitItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MagicItemDrop implements IItemDrop {
    private final String itemSpellName;

    public MagicItemDrop(MythicLineConfig config, String argument) {
        this.itemSpellName = config.getString(new String[]{"id", "i"}, argument);
    }

    @Override
    public AbstractItemStack getDrop(DropMetadata dropMetadata, double amount) {
        Spell spell = MagicDataManager.getSpell(this.itemSpellName);
        if (spell == null) {
            return new BukkitItemStack(new ItemStack(Material.AIR));
        } else {
            NormalContext normalContext = new NormalContext();
            SpellContext spellContext = spell.execute(normalContext);
            Object o = spellContext.getSpellReturn().getObject();
            if (o instanceof ItemStack) {
                ItemStack itemStack = (ItemStack) o;
                itemStack.setAmount((int) amount);
                return new BukkitItemStack(itemStack);
            } else {
                return new BukkitItemStack(new ItemStack(Material.AIR));
            }
        }
    }
}
