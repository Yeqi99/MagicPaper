package cn.originmc.plugins.magicpaper.magic.result;

import cn.origincraft.magic.function.results.ObjectResult;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

public class YamlManagerResult extends ObjectResult {
    public YamlManagerResult(Object object) {
        super(object);
    }
    public YamlManager getYamlManager() {
        return (YamlManager) getObject();
    }
    public String getName() {
        return "YamlManager";
    }
}
