package cn.originmc.plugins.magicpaper.data.manager;

import cn.origincraft.magic.object.NormalContext;
import cn.origincraft.magic.object.Spell;
import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.plugins.magicpaper.data.timer.TimerData;
import cn.originmc.plugins.magicpaper.timer.MagicTimer;
import cn.originmc.plugins.magicpaper.timer.MagicTimerManager;
import cn.originmc.plugins.magicpaper.timer.impl.FoliaTimer;
import cn.originmc.plugins.magicpaper.timer.impl.PaperTimer;

import java.util.ArrayList;
import java.util.List;

public class TimerDataManager {
    public static List<MagicTimer> getTimers() {
        List<MagicTimer> result = new ArrayList<>();
        for (String s : TimerData.yamlManager.getIdList()) {
            String type = (String) TimerData.yamlManager.get(s, "type", "paper");
            int later = (int) TimerData.yamlManager.get(s, "later", 20L);
            int interval = (int) TimerData.yamlManager.get(s, "interval", 20L);
            List<String> executeSpellNames = (List<String>) TimerData.yamlManager.get(s, "execute-spell", null);
            if (executeSpellNames == null) {
                continue;
            }
            if (executeSpellNames.isEmpty()) {
                continue;
            }
            List<Spell> spells = MagicDataManager.getSpell(executeSpellNames);
            if (spells.isEmpty()) {
                continue;
            }
            NormalContext normalContext = new NormalContext();
            MagicPaper.importSpell(normalContext);
            if (type.equalsIgnoreCase("paper")) {
                PaperTimer paperTimer = new PaperTimer(s, later, interval, normalContext);
                paperTimer.addAllTask(spells);
                paperTimer.start();
                result.add(paperTimer);
            } else if (type.equalsIgnoreCase("folia")) {
                FoliaTimer foliaTimer = new FoliaTimer(s, later, interval, normalContext);
                foliaTimer.addAllTask(spells);
                foliaTimer.start();
                result.add(foliaTimer);
            }
            MagicPaper.getSender().sendToLogger("§a[§bMagicPaper§a] §e计时器 §a" + s + " §e已注册");
        }
        return result;
    }
    public static void initConfigTimer(){
        MagicTimerManager.registerTimer(getTimers());
    }
    public static void reInit(){
        MagicTimerManager.stopAllTimer();
        MagicTimerManager.removeAllTimer();
        initConfigTimer();
    }
}
