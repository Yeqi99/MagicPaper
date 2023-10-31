package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import net.kyori.adventure.text.Component;

public class ComponentResult extends ObjectResult {
    public ComponentResult(Object object) {
        super(object);
    }

    public Component getComponent() {
        return (Component) getObject();
    }

    public String getName() {
        return "Component";
    }
}
