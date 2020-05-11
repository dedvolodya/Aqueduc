package utils;

import java.util.Arrays;
import java.util.List;

public class Code {
    private int bitRate;
    private List<Integer> codes;

    public Code(int bitRate, List<Integer> codes) {
        this.bitRate = bitRate;
        this.codes = codes;
    }

    public int[][] toMatrix() {
        int[][] matrix = new int[getSize()][];
        int i = 0;
        for (int code : codes) {
            int[] array = CodeUtils.toBinary(code, bitRate);
            matrix[i++] = array;
        }
        return matrix;
    }

    public int getSize() {
        return codes.size();
    }

    public int getBitRate() {
        return bitRate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int word : codes) {
            builder.append(Arrays.toString(CodeUtils.toBinary(word, bitRate)));
            builder.append('\n');
        }
        return builder.toString();
    }
}
