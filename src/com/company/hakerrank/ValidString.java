package com.company.hakerrank;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Test;

public class ValidString {

    public static void main(String[] args) {

        //  assert isValid("abcdefghhgfedecba") .equals( "YES");
        //   assert isValid("aabbccddeefghi").equals("NO");
        //   assert isValid("aabbcd").equals("NO");
        //   sys isValid("aaaabbcc").equals("NO");

    }

    @Test
    public void testNO1() {

        assertEquals("NO", isValid("aaaabbcc"));
        assertEquals("NO", isValid("aabbcd"));
        assertEquals("NO", isValid("aabbccddeefghi"));

    }

    @Test
    public void testYes() {
        assertEquals("YES", isValid("abcdefghhgfedecba"));
          assertEquals("YES", isValid("aaaaaaaac"));
        assertEquals("YES", isValid("zzzaahh"));
    }

    @Test
    public void testLong() throws IOException {

       InputStream inputStream = ValidString.class.getResourceAsStream("test3");


        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        assertEquals("YES", isValid(textBuilder.toString()));

    }


    static String isValid(String s) {

        final int range = 'z' - 'a';
        int[] freq = new int[range + 1];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        Map<Integer, Integer> freqGroups = new HashMap<>();
        for (int i = 0; i <= range; i++) {
            if (freq[i] != 0) {
                freqGroups.merge(freq[i], 1, (v1, v2) -> v1 + v2);
            }
        }

        if (freqGroups.keySet().size() > 2) {
            return "NO";
        }

        if(freqGroups.keySet().size() == 1){
            return "YES";
        }

        int oddKey = 0;
        int commonKey = 0;
        for (Entry<Integer, Integer> entry : freqGroups.entrySet()) {
            if (entry.getValue() == 1) {
                oddKey = entry.getKey();
            } else {
                commonKey = entry.getKey();
            }
        }
        if (oddKey == 0) {
            return "NO";
        }

        if ((freqGroups.get(oddKey) == 1 && oddKey == 1) || (freqGroups.get(oddKey) == 1 && oddKey - commonKey == 1)) {
            return "YES";
        }
        return "NO";
    }

}
