package cn.originmc.plugins.magicpaper.util.list;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static List<String> formatStringGrid(List<String> inputList, int columns, int charactersPerColumn) {
        List<String> resultList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            String currentStr = inputList.get(i);
            int startIndex = 0;
            while (startIndex < currentStr.length()) {
                int endIndex = Math.min(startIndex + charactersPerColumn, currentStr.length());
                String column = currentStr.substring(startIndex, endIndex);
                resultList.add(column);
                startIndex = endIndex;
            }
        }

        // Fill with spaces to ensure each column has 'charactersPerColumn' characters
        int elementsToAdd = columns - (resultList.size() % columns);
        for (int i = 0; i < elementsToAdd; i++) {
            resultList.add("");
        }

        List<String> finalResult = new ArrayList<>();

        for (int i = 0; i < resultList.size(); i += columns) {
            StringBuilder rowBuilder = new StringBuilder();
            for (int j = 0; j < columns; j++) {
                rowBuilder.append(resultList.get(i + j));
            }
            finalResult.add(rowBuilder.toString());
        }

        return finalResult;
    }
    public static char[][] stringListToCharArray(List<String> stringList) {
        // 创建一个6x9的字符数组
        char[][] charArray = new char[6][9];

        // 遍历字符串列表并将字符填充到字符数组中
        for (int i = 0; i < 6; i++) {
            if (i < stringList.size()) {
                String str = stringList.get(i);
                for (int j = 0; j < 9; j++) {
                    if (j < str.length()) {
                        charArray[i][j] = str.charAt(j);
                    } else {
                        // 如果字符串不够长，可以在此设置默认值，例如空格
                        charArray[i][j] = ' ';
                    }
                }
            } else {
                // 如果字符串列表不足6个，可以在此设置默认值，例如空字符串
                for (int j = 0; j < 9; j++) {
                    charArray[i][j] = ' ';
                }
            }
        }

        return charArray;
    }


}
