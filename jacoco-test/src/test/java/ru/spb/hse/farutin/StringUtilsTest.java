package ru.spb.hse.farutin;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Testing methods of StringUtils class.
 */
public class StringUtilsTest {

    @Test
    public void testEmptyString() {
        String str = "";
        String substr = "hello";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> actual = StringUtils.findSubstring(str, substr);

        assertEquals(expected, actual);
    }

    @Test
    public void testEmptySubstring() {
        String str = "hello";
        String substr = "";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> actual = StringUtils.findSubstring(str, substr);

        assertEquals(expected, actual);
    }

    @Test
    public void testNotFound() {
        String str = "hello world!";
        String substr = "42";
        ArrayList<Integer> expected = new ArrayList<>();

        ArrayList<Integer> actual = StringUtils.findSubstring(str, substr);

        assertEquals(expected, actual);
    }

    @Test
    public void testSeveralIndexes() {
        String str = "hello world! hello!";
        String substr = "hello";
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0, 13));

        ArrayList<Integer> actual = StringUtils.findSubstring(str, substr);

        assertEquals(expected, actual);
    }
}
