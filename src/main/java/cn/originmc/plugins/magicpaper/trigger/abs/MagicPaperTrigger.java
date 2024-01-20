package cn.originmc.plugins.magicpaper.trigger.abs;


import cn.origincraft.magic.object.ContextMap;
import cn.origincraft.magic.object.Spell;
import cn.origincraft.magic.object.SpellContext;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.config.LangData;
import cn.originmc.plugins.magicpaper.util.error.PaperErrorUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicPaperTrigger {
    private final List<Spell> spells = new ArrayList<>();

    public void register(Spell spell) {
        spells.add(spell);
    }

    public void register(List<Spell> spells) {
        this.spells.addAll(spells);
    }

    public void unregister(Spell spell) {
        spells.remove(spell);
    }

    public void onTrigger(ContextMap context) {
        for (Spell spell : spells) {
            SpellContext spellContext = spell.execute(context);
            if (MagicPaper.isDebug() && spellContext.hasExecuteError()) {
                List<String> log = PaperErrorUtils.getErrorAllLog(spellContext, "&c");
                MagicPaper.getSender().sendToLogger(log);
            }
        }
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public abstract String getDefaultInfo();

    public String getInfo() {
        return LangData.get(MagicPaper.getLang(), "trigger." + getName(), getDefaultInfo());
    }

    public abstract String getName();
}
