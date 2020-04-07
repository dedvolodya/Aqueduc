package utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static utils.CodeUtils.hemmingDistance;
import static utils.CodeUtils.toBinary;

public class UtilsTests {
    @Test
    public void hemmingDistanceTest() {
        Assertions.assertEquals(0, hemmingDistance(4, 4, 6));
        Assertions.assertEquals(2, hemmingDistance(11, 13, 4));
        Assertions.assertEquals(1, hemmingDistance(15, 31, 5));
    }

    @Test
    public void toBinaryTests() {
        Assertions.assertArrayEquals(new int[] {0, 0, 0, 0}, toBinary(0, 4));
        Assertions.assertArrayEquals(new int[] {1, 0, 0, 0}, toBinary(1, 4));
        Assertions.assertArrayEquals(new int[] {0, 1, 0, 0}, toBinary(2, 4));
        Assertions.assertArrayEquals(new int[] {0, 1, 0, 1}, toBinary(10, 4));
        Assertions.assertArrayEquals(new int[] {0, 1, 1, 1}, toBinary(14, 4));
        Assertions.assertArrayEquals(new int[] {1, 1, 1, 1}, toBinary(15, 4));
    }
}
