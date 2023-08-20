package cn.originmc.plugins.magicpaper.magic.result;

import dev.rgbmc.expression.functions.FunctionResult;
import org.bukkit.entity.Entity;

public class EntityResult extends FunctionResult {
    private final Entity entity;
    public EntityResult(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
