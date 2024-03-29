package cn.originmc.plugins.magicpaper.data.manager;

import cn.originmc.plugins.magicpaper.data.attribute.AttributeData;
import cn.originmc.plugins.magicpaper.data.attribute.MagicAttribute;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AttributeManager {
    public static Map<String, MagicAttribute> attributeMap = new HashMap<>();

    public static void load() {
        attributeMap = new HashMap<>();
        AttributeData.load();
        for (String s : AttributeData.yamlManager.getIdList()) {
            MagicAttribute magicAttribute = new MagicAttribute(AttributeData.yamlManager, s);
            attributeMap.put(s, magicAttribute);
        }
    }
}
