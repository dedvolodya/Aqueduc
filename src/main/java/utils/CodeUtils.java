package utils;

public class CodeUtils {

    public static int numberWeight(int num, int size) {
        int weight = 0;
        for (int i = 0; i < size; i++) {
            if ((num >> i & 1) > 0) {
                weight++;
            }
        }
        return weight;
    }

    public static int hemmingDistance(int a, int b, int size) {
        return numberWeight(a ^ b, size);
    }

    public static int[] toBinary(int number, int base) {
        final int[] ret = new int[base];
        for (int i = base - 1; i >= 0; i--) {
            ret[base - i - 1] = number >> i & 1;
        }
        return ret;
    }

    public static int makeIntWithWeight(int weight, int size) {
        for (int i = 0; ; i++) {
            if (numberWeight(i, size) == weight) {
                return i;
            }
        }
    }
}
