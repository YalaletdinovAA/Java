package src.main.java.homeworks.homework10;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    public static int[] filter(int[] array, ByCondition condition) {
        List<Integer> result = new ArrayList<>();
        for (int number : array) {
            if (condition.isOk(number)) {
                result.add(number);
            }
        }
        // Преобразуем List<Integer> в int[]
        int[] filteredArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            filteredArray[i] = result.get(i);
        }
        return filteredArray;
    }
}
