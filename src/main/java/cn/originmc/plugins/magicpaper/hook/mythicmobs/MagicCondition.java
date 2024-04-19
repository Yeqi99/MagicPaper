package cn.originmc.plugins.magicpaper.hook.mythicmobs;

import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;
import io.lumine.mythic.bukkit.adapters.BukkitItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MagicCondition implements IEntityCondition {
    private final String itemSpellName;
    public MagicCondition(MythicLineConfig config) {
        this.itemSpellName = config.getString(new String[]{"id","i"});
    }

    @Override
    public boolean check(AbstractEntity abstractEntity) {
        Spell spell = MagicDataManager.getSpell(this.itemSpellName);
        if (spell == null) {
            return false;
        } else {
            NormalContext normalContext=new NormalContext();
            SpellContext spellContext = spell.execute(normalContext);
            return spellContext.getSpellReturn().toBoolean(false);
        }
    }
}
