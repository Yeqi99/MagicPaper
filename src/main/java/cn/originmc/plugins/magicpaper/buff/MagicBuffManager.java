package cn.originmc.plugins.magicpaper.buff;


import cn.originmc.plugins.magicpaper.cooldown.CoolDown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MagicBuffManager {
    private final ConcurrentHashMap<String, List<MagicBuff>> magicBuffs;

    public MagicBuffManager() {
        this.magicBuffs = new ConcurrentHashMap<>();
    }
    public void addMagicBuff(String playerId,MagicBuff magicBuff) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(playerId);
        if (magicBuffList == null) {
            magicBuffList = new java.util.ArrayList<>();
            magicBuffList.add(magicBuff);
            this.magicBuffs.put(playerId, magicBuffList);
        } else {
            List<MagicBuff> clone=new ArrayList<>();
            for (MagicBuff buff : magicBuffList) {
                if (!buff.getId().equalsIgnoreCase(magicBuff.getId())) {
                    clone.add(buff);
                }
            }
            clone.add(magicBuff);
            this.magicBuffs.put(playerId, clone);
        }
    }
    public boolean isBuffActive(String player,String id) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return false;
        }
        for (MagicBuff buff : magicBuffList) {
            if (buff.getId().equalsIgnoreCase(id)) {
                if (buff.isEnd()) {
                    magicBuffList.remove(buff);
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    public void removeBuff(String player,String id) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return;
        }
        for (MagicBuff buff : magicBuffList) {
            if (buff.getId().equalsIgnoreCase(id)) {
                magicBuffList.remove(buff);
                return;
            }
        }
    }
    public void removeBuff(String player) {
        this.magicBuffs.remove(player);
    }
    public List<MagicBuff> getMagicBuffs(String player) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return null;
        }
        List<MagicBuff> clone=new ArrayList<>();
        for (MagicBuff magicBuff : magicBuffList) {
            if (magicBuff.isEnd()){
                continue;
            }
            clone.add(magicBuff);
        }
        return clone;
    }
    public MagicBuff getMagicBuff(String player,String id) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return null;
        }
        for (MagicBuff buff : magicBuffList) {
            if (buff.getId().equalsIgnoreCase(id)) {
                return buff;
            }
        }
        return null;
    }
    public void clear() {
        this.magicBuffs.clear();
    }
    public long getEndTime(String player,String id) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return 0;
        }
        for (MagicBuff buff : magicBuffList) {
            if (buff.getId().equalsIgnoreCase(id)) {
                return buff.getEndTime();
            }
        }
        return 0;
    }
    public long getNowToEnd(String player,String id) {
        List<MagicBuff> magicBuffList = this.magicBuffs.get(player);
        if (magicBuffList == null) {
            return 0;
        }
        for (MagicBuff buff : magicBuffList) {
            if (buff.getId().equalsIgnoreCase(id)) {
                return buff.getNowToEnd();
            }
        }
        return 0;
    }
}
