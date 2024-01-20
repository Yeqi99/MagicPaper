package cn.originmc.plugins.magicpaper.dataentity;


import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataEntity implements ConfigurationSerializable {
    private String id;
    private Map<String, Object> data = new ConcurrentHashMap<>();

    public DataEntity(String id) {
        this.id = id;
    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> result = new HashMap<>(data);
        result.put("id", this.id);
        return result;
    }

    public static DataEntity deserialize(Map<String, Object> map) {
        DataEntity dataEntity = new DataEntity((String) map.get("id"));
        map.remove("id");
        dataEntity.setData(map);
        return dataEntity;
    }
}
