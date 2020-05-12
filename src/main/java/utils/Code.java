package utils;

import javafx.print.Collation;

import java.util.*;

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

    public Collection<Integer> getList() {
        return codes;
    }

    public void sort() {
        Collections.sort(codes);
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

    public int getDistance() {
        int minimal = bitRate;
        for (int code1 : codes) {
            for (int code2 : codes) {
                if (code1 != code2) {
                    int distance = CodeUtils.hemmingDistance(code1, code2, bitRate);
                    if (distance < minimal) {
                        minimal = distance;
                    }
                }
            }
        }
        return minimal;
    }

    public int getDistance(int codeIdx) {
        int minimal = bitRate;
        int code = codes.get(codeIdx);
        for (int code1 : codes) {
            if (code1 != code) {
                int distance = CodeUtils.hemmingDistance(code1, code, bitRate);
                if (distance < minimal) {
                    minimal = distance;
                }
            }
        }
        return minimal;
    }
}
