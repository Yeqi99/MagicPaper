package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import org.bukkit.entity.Entity;

public class EntityResult extends ObjectResult {
    public EntityResult(Entity entity) {
        super(entity);
    }

    public Entity getEntity() {
        return (Entity) getObject();
    }
    public String getName() {
        return "Entity";
    }
}
