package cn.originmc.plugins.magicpaper.util.random;

import java.util.List;
import java.util.Random;

public class RandomStringSelector {
    private final List<String> dataList; // 存储字符串列表
    private final Random random; // 用于生成随机数

    public RandomStringSelector(List<String> dataList) {
        this.dataList = dataList;
        this.random = new Random();
    }

    public String getRandomString() {
        if (dataList.isEmpty()) {
            return null;
        }

        int totalWeight = 0;
        int weightedCount = 0;

        for (String data : dataList) {
            int weight = getWeight(data);
            totalWeight += weight;
            weightedCount += weight > 0 ? 1 : 0;
        }

        int averageWeight = weightedCount > 0 ? totalWeight / weightedCount : 1;

        int randomWeight = random.nextInt(totalWeight) + 1;

        int accumulatedWeight = 0;
        for (String data : dataList) {
            int weight = getWeight(data) > 0 ? getWeight(data) : averageWeight;
            accumulatedWeight += weight;
            if (randomWeight <= accumulatedWeight) {
                return data.split("\\|")[0];  // 返回不含权重的数据部分
            }
        }

        return null;
    }

    private int getWeight(String data) {
        // 从数据中提取权重，如果不存在权重，则返回0
        String[] parts = data.split("\\|");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                // 处理无效的权重格式
                return 0;
            }
        }
        return 0;
    }
}
