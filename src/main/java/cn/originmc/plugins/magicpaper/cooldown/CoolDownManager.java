package cn.originmc.plugins.magicpaper.cooldown;

import java.util.concurrent.ConcurrentHashMap;

public class CoolDownManager {
    private final ConcurrentHashMap<String, CoolDown> coolDowns; // 使用ConcurrentHashMap保证线程安全

    public CoolDownManager() {
        coolDowns = new ConcurrentHashMap<>();
    }

    public void addCoolDown(CoolDown coolDown) {
        coolDowns.put(coolDown.getId(), coolDown);
    }

    public boolean isCoolDownActive(String id) {
        CoolDown coolDown = coolDowns.get(id);
        if (coolDown != null) {
            if (coolDown.isEnd()) {
                coolDowns.remove(id);
                return false;
            }
            return true;
        }
        return false;
    }

    public void removeCoolDown(String id) {
        coolDowns.remove(id);
    }

    public CoolDown getCoolDown(String id) {
        return coolDowns.get(id);
    }
}
