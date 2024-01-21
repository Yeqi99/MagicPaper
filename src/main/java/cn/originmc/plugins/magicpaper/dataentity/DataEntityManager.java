package cn.originmc.plugins.magicpaper.dataentity;

import cn.originmc.plugins.magicpaper.MagicPaper;
import cn.originmc.tools.minecraft.yamlcore.object.YamlElement;
import cn.originmc.tools.minecraft.yamlcore.object.YamlManager;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataEntityManager {
    private BukkitRunnable saveTask;
    private int saveInterval = 600; // 默认保存间隔为10分钟（以ticks为单位）
    private boolean isPaused = false;

    public void pauseAsyncSaveTask() {
        isPaused = true;
    }

    public void resumeAsyncSaveTask() {
        isPaused = false;
    }

    public void stopAsyncSaveTask() {
        if (saveTask != null) {
            saveTask.cancel();
        }
    }

    public void setSaveInterval(int saveInterval) {
        this.saveInterval = saveInterval;
    }

    public void startAsyncSaveTask() {
        if (saveTask != null) {
            saveTask.cancel();
        }

        saveTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (!isPaused) {
                    saveAll();
                }
            }
        };

        saveTask.runTaskTimerAsynchronously(MagicPaper.getInstance(), 0L, saveInterval);
    }

    YamlManager yamlManager;
    Map<String, DataEntity> dataEntityMap = new ConcurrentHashMap<>();

    public DataEntityManager() {
    }

    public boolean hasDataEntity(String id) {
        return dataEntityMap.containsKey(id);
    }

    public boolean hasData(String entityId, String dataId) {
        return dataEntityMap.get(entityId).hasData(dataId);
    }

    public void putData(String entityId, String dataId, Object data) {
        if (dataEntityMap.containsKey(entityId)) {
            DataEntity dataEntity = getDataEntity(entityId);
            dataEntity.putData(dataId, data);
        } else {
            DataEntity dataEntity = new DataEntity(entityId);
            dataEntity.putData(dataId, data);
            dataEntityMap.put(entityId, dataEntity);
        }
        save(entityId);
    }

    public Object getData(String entityId, String dataId) {
        if (dataEntityMap.containsKey(entityId)) {
            DataEntity dataEntity = getDataEntity(entityId);
            return dataEntity.getData().get(dataId);
        } else {
            return null;
        }
    }

    public void removeData(String entityId, String dataId) {
        if (dataEntityMap.containsKey(entityId)) {
            DataEntity dataEntity = getDataEntity(entityId);
            dataEntity.getData().remove(dataId);
            save(entityId);
        }
    }

    public DataEntity getDataEntity(String id) {
        return dataEntityMap.get(id);
    }

    public void putDataEntity(DataEntity dataEntity) {
        dataEntityMap.put(dataEntity.getId(), dataEntity);
    }

    public void removeDataEntity(String id) {
        dataEntityMap.remove(id);
        yamlManager.delElement(id);
    }

    public void load() {
        dataEntityMap.clear();
        if (MagicPaper.getDataEntityType().equals("yaml")) {
            yamlManager = new YamlManager(MagicPaper.getInstance(), "data", true);
            for (YamlElement yamlElement : yamlManager.getYamlElements()) {
                DataEntity dataEntity = (DataEntity) yamlElement.getYml().get("data");
                if (dataEntity == null) {
                    MagicPaper.getSender().sendToLogger("DataEntity " + yamlElement.getId() + " error！");
                    continue;
                }
                dataEntityMap.put(dataEntity.getId(), dataEntity);
            }
        }

    }

    public void saveAll() {
        if (MagicPaper.getDataEntityType().equals("yaml")) {
            for (Map.Entry<String, DataEntity> entry : dataEntityMap.entrySet()) {
                if (yamlManager.hasElement(entry.getKey())) {
                    yamlManager.getYaml(entry.getKey()).set("data", entry.getValue());
                } else {
                    yamlManager.create(entry.getKey());
                    yamlManager.getYaml(entry.getKey()).set("data", entry.getValue());
                }
            }
            yamlManager.saveAll();
            MagicPaper.getSender().sendToLogger("&aSave all dataEntity to yaml successful!");
        }
    }

    public void save(String id) {
        if (!dataEntityMap.containsKey(id)) {
            return;
        }
        if (MagicPaper.getDataEntityType().equals("yaml")) {
            DataEntity dataEntity = dataEntityMap.get(id);
            if (yamlManager.hasElement(id)) {
                yamlManager.getYaml(id).set("data", dataEntity);
            } else {
                yamlManager.create(id);
                yamlManager.getYaml(id).set("data", dataEntity);
            }
            yamlManager.save(id);
        }
    }
}
