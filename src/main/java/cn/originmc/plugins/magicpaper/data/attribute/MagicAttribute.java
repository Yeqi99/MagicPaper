package cn.originmc.plugins.magicpaper.data.attribute;

import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;

import java.util.List;

public class MagicAttribute {
    public String id;
    public String display;
    public String label;
    public List<String> description;

    public MagicAttribute(YamlManager yamlManager, String id) {
        this.id=id;
        this.display = yamlManager.getElement(id).getYml().getString("display");
        this.label = yamlManager.getElement(id).getYml().getString("label");
        this.description = yamlManager.getElement(id).getYml().getStringList("description");
    }


}
