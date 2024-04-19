package cn.originmc.plugins.magicpaper.hook.mythicmobs;

import cn.origincraft.magic.function.results.StringResult;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.manager.MagicDataManager;
import cn.originmc.plugins.magicpaper.magic.result.EntityResult;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.adapters.BukkitItemStack;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class MagicMechanic implements ITargetedEntitySkill {
    private final String itemSpellName;

    public MagicMechanic(MythicLineConfig config) {
        this.itemSpellName = config.getString(new String[]{"id", "i"});
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(abstractEntity);
        Spell spell = MagicDataManager.getSpell(this.itemSpellName);
        if (spell == null) {
            return SkillResult.ERROR;
        } else {
            NormalContext normalContext = new NormalContext();
            normalContext.putVariable("entity", new EntityResult(bukkitTarget));
            SpellContext spellContext = spell.execute(normalContext);
            if (spellContext.hasExecuteError()) {
                return SkillResult.ERROR;
            } else {
                return SkillResult.SUCCESS;
            }
        }


    }
}
