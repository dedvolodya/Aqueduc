import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Code;

import java.util.ArrayList;
import java.util.List;

public class CodeTests {
    private Code code;

    @BeforeEach
    public void init() {
        List<Integer> codes = new ArrayList<>();
        codes.add(5);
        codes.add(0);
        codes.add(2);
        codes.add(8);
        code = new Code(6, codes);
    }

    @Test
    public void codeSizeTest() {
        Assertions.assertEquals(4, code.getSize());
    }

    @Test
    public void codeDistanceTest1() {
        Assertions.assertEquals(1, code.getDistance());
    }

    @Test
    public void codeDistanceTest2() {
        Assertions.assertEquals(2, code.getDistance(0));
    }

    @Test
    public void toMatrixTest() {
        int[][] matrix = code.toMatrix();
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 1, 0, 1}, matrix[0]);
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0}, matrix[1]);
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 0, 1, 0}, matrix[2]);
        Assertions.assertArrayEquals(new int[] {0, 0, 1, 0, 0, 0}, matrix[3]);
    }

    @Test
    public void sortTest() {
        code.sort();
        int[][] matrix = code.toMatrix();
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0}, matrix[0]);
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 0, 1, 0}, matrix[1]);
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 1, 0, 1}, matrix[2]);
        Assertions.assertArrayEquals(new int[] {0, 0, 1, 0, 0, 0}, matrix[3]);
    }

}
