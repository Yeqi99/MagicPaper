package cn.originmc.plugins.magicpaper.hook.mythicmobs;

import com.google.common.collect.Lists;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.mobs.GenericCaster;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.api.skills.Skill;
import io.lumine.mythic.api.skills.SkillCaster;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.skills.SkillMetadataImpl;
import io.lumine.mythic.core.skills.SkillTriggers;
import io.lumine.mythic.core.utils.MythicUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Consumer;

public class MythicMobsManager {
    public static ActiveMob spawnMob(String mobName, Location spawnLoc){
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        Location spawnLocation = spawnLoc;
        if(mob != null){
            // spawns mob
            ActiveMob someMob = mob.spawn(BukkitAdapter.adapt(spawnLocation),1);
            return someMob;
        }
        return null;
    }
    public static Entity spawnMobGetEntity(String mobName, Location spawnLoc){
        MythicMob mob = MythicBukkit.inst().getMobManager().getMythicMob(mobName).orElse(null);
        Location spawnLocation = spawnLoc;
        if(mob != null){
            // spawns mob
            ActiveMob someMob = mob.spawn(BukkitAdapter.adapt(spawnLocation),1);
            return someMob.getEntity().getBukkitEntity();
        }
        return null;
    }
    public static Entity getEntity(ActiveMob mob){
        return mob.getEntity().getBukkitEntity();
    }
    public static ActiveMob getActiveMob(Entity entity){
        return MythicBukkit.inst().getMobManager().getActiveMob(entity.getUniqueId()).orElse(null);
    }
    public static boolean isMythicMob(Entity entity){
        ActiveMob mythicMob=getActiveMob(entity);
        if(mythicMob==null){
            return false;
        }
        return true;
    }
    public static boolean isNamedMythicMob(Entity entity,String mobName){
        if(isMythicMob(entity)){
            return getActiveMob(entity).getName().equals(mobName);
        }else {
            return false;
        }
    }
    public static LivingEntity getTarget(Player player){
        return MythicUtil.getTargetedEntity(player);
    }
    public static boolean castSkill(Entity e, String skillName, Entity trigger, Location origin, Collection<Entity> eTargets, Collection<Location> lTargets, float power, Consumer<SkillMetadata> transformer){
        Optional<Skill> maybeSkill = MythicBukkit.inst().getSkillManager().getSkill(skillName);
        if (!maybeSkill.isPresent()) {
            return false;
        } else {
            Object caster;
            if (MythicBukkit.inst().getMobManager().isActiveMob(e.getUniqueId())) {
                caster = MythicBukkit.inst().getMobManager().getMythicMobInstance(e);
            } else {
                caster = new GenericCaster(BukkitAdapter.adapt(e));
            }
            Skill skill = maybeSkill.get();
            Collection<AbstractEntity> feTargets = Lists.newArrayList();
            Collection<AbstractLocation> flTargets = Lists.newArrayList();
            Iterator var14;
            if (eTargets != null) {
                var14 = eTargets.iterator();

                while(var14.hasNext()) {
                    Entity en = (Entity)var14.next();
                    feTargets.add(BukkitAdapter.adapt(en));
                }
            }
            if (lTargets != null) {
                var14 = lTargets.iterator();

                while(var14.hasNext()) {
                    Location l = (Location)var14.next();
                    flTargets.add(BukkitAdapter.adapt(l));
                }
            }
            SkillMetadata data = new SkillMetadataImpl(SkillTriggers.API, (SkillCaster)caster, BukkitAdapter.adapt(trigger), BukkitAdapter.adapt(origin), feTargets, flTargets, power);
            if (transformer != null) {
                transformer.accept(data);
            }

            if (skill.isUsable(data, SkillTriggers.API)) {
                skill.execute(data);
            }
            return true;
        }
    }
    public static boolean castSkill(Player player,String skill,Float power){
        LivingEntity livingEntity=MythicMobsManager.getTarget(player);
        if (livingEntity==null){
            return MythicMobsManager.castSkill(player,skill,player,player.getLocation(),null,null,power,null);
        }
        List<Entity> targets = new ArrayList();
        targets.add(livingEntity);
        List<Location> targetL=new ArrayList<>();
        for (Entity target : targets) {
            targetL.add(target.getLocation());
        }
        return MythicMobsManager.castSkill(player,skill,player,player.getLocation(),targets,targetL,power,null);
    }
}
