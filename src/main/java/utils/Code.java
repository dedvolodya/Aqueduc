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
        //TODO:implement me
        return new int[0][0];
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
