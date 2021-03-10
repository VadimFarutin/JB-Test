package ru.spb.hse.farutin;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StringUtils {
    /**
     * Returns indexes within specified string of all occurrences of the
     * specified substring.
     *
     * @param str the string to search in.
     * @param substr the substring to search for.
     * @return indexes of all occurrences of the specified substring.
     */
    public static @NotNull ArrayList<Integer> findSubstring(
            @NotNull String str, @NotNull String substr) {
        ArrayList<Integer> indexes = new ArrayList<>();

        if (substr.equals("")) {
            return indexes;
        }

        int substr_len = substr.length();
        int last_index = 0;

        while (true) {
            int current_index = str.indexOf(substr, last_index);

            if (current_index == -1) {
                break;
            }

            indexes.add(current_index);
            last_index = current_index + substr_len;
        }

        return indexes;
    }
}
