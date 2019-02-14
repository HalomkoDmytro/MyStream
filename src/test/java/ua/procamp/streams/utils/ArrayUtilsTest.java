package ua.procamp.streams.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

    @Test
    public void joinArray() {
        int[] expecte = {1, 2, 3, 4};
        int[] left = {1, 2};
        int[] right = {3, 4};

        int[] actual = ArrayUtils.joinArray(left, right);

        assertTrue(Arrays.equals(expecte, actual));
    }
}