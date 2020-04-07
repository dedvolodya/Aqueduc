package utils;

public class CodeUtils {
    public static int hemmingDistance(int a, int b, int size) {
        int resultNum = a ^ b;
        int distance = 0;
        for (int i = 0; i < size; i++) {
            if ((resultNum >> i & 1) > 0) {
                distance++;
            }
        }
        return distance;
    }

    public static int[] toBinary(int number, int base) {
        final int[] ret = new int[base];
        for (int i = 0; i < base; i++) {
            ret[i] = number >> i & 1;
        }
        return ret;
    }
}
