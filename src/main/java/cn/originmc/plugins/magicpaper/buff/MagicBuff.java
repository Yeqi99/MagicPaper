package cn.originmc.plugins.magicpaper.buff;

import cn.origincraft.magic.function.results.ErrorResult;
import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MagicBuff {
    private String id;
    private long startTime;
    private long duration;
    private float reduction = 1;
    private long fixedReduction = 0;
    private String buffSetting = "";
    private ContextMap contextMap = new NormalContext();
    private List<Spell> spell = new ArrayList<>();
    private long tick = 0;
    private long maxTick = 100;

    public MagicBuff(String id, long startTime, long duration, float reduction, long fixedReduction) {
        this.id = id;
        this.startTime = startTime;
        this.duration = duration;
        this.reduction = reduction;
        this.fixedReduction = fixedReduction;
    }

    public MagicBuff(String id, long duration, float reduction, long fixedReduction) {
        this.id = id;
        this.startTime = System.currentTimeMillis();
        this.duration = duration;
        this.reduction = reduction;
        this.fixedReduction = fixedReduction;
    }

    public MagicBuff(String id, long duration) {
        this.id = id;
        this.startTime = System.currentTimeMillis();
        this.duration = duration;
    }

    public long getResultDuration() {
        return (long) (duration * reduction) + fixedReduction;
    }

    public boolean isEnd() {
        return System.currentTimeMillis() >= startTime + getResultDuration();
    }

    public boolean isEnd(long time) {
        return time >= startTime + getResultDuration();
    }

    public long getEndTime() {
        return startTime + getResultDuration();
    }

    public long getNowToEnd() {
        return getEndTime() - System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    public long getFixedReduction() {
        return fixedReduction;
    }

    public void setFixedReduction(long fixedReduction) {
        this.fixedReduction = fixedReduction;
    }

    public String getBuffSetting() {
        return buffSetting;
    }

    public void setBuffSetting(String buffSetting) {
        this.buffSetting = buffSetting;
    }


    public ContextMap getContextMap() {
        return contextMap;
    }

    public void setContextMap(ContextMap contextMap) {
        this.contextMap = contextMap;
    }

    public List<Spell> getSpell() {
        return spell;
    }

    public void setSpell(List<Spell> spell) {
        this.spell = spell;
    }

    public void addSpell(Spell spell) {
        this.spell.add(spell);
    }

    public void execute(Player player) {
        if (tick++ > maxTick) {
            tick = 0;
        }
        this.contextMap.putVariable("tick", tick);
        for (Spell spell : this.spell) {
            SpellContext spellContext= spell.execute(this.contextMap);
            if (spellContext.hasExecuteError()){
                ErrorResult errorResult=spellContext.getExecuteError();
                MagicPaper.getSender().sendToPlayer(player, "&c"+errorResult.getErrorId()+":"+errorResult.getInfo());
                MagicPaper.getSender().sendToPlayer(player, spellContext.getExecuteErrorLocation());
            }

        }
    }

    public long getTick() {
        return tick;
    }

    public void setTick(long tick) {
        this.tick = tick;
    }

    public long getMaxTick() {
        return maxTick;
    }

    public void setMaxTick(long maxTick) {
        this.maxTick = maxTick;
    }
}
