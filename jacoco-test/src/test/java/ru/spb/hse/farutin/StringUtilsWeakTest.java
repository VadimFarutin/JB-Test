package ru.spb.hse.farutin;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Testing methods of StringUtils class.
 * These tests are intentionally weak.
 */
public class StringUtilsWeakTest {

    @Test
    public void testFindSubstring() {
        String str = "hello world!";
        String substr = "hello";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0));

        ArrayList<Integer> actual = StringUtils.findSubstring(str, substr);

        assertEquals(expected, actual);
    }
}
