package cn.originmc.plugins.magicpaper.util.time;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static boolean isCurrentTimeInTimeRange(String timeRange) {
        // 获取当前时间
        Date currentTime = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String currentTimeStr = timeFormat.format(currentTime);

        if (timeRange.contains("-")) {
            // 如果时间段包含 "-"，表示是一个时间范围
            String[] parts = timeRange.split("-");
            String startTime = parts[0].trim();
            String endTime = parts[1].trim();

            if (currentTimeStr.compareTo(startTime) >= 0 && currentTimeStr.compareTo(endTime) <= 0) {
                return true;
            }
        } else {
            // 如果时间段没有 "-", 则表示是一个时间点
            if (currentTimeStr.equals(timeRange)) {
                return true;
            }
        }

        return false;
    }
}
