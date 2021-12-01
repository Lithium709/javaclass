package com.company.crossover.test;


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WordGrouping {

    private static final char BASE_LETTER = 'a';

    /**
     * Main starting point for this app, used as testing run.
     *
     * @param args - application input argumnts.
     */
    public static void main(String[] args) throws InterruptedException {

        rearrange(new String[]{"student", "students", "dog", "studentssess", "god", "cat"}, 0);

        int a = 10;
    }

    static void rearrange(String words[], int n) {
        Map<Integer, List<String>> linkedHashMap = new LinkedHashMap<>();
        for (String word : words) {
            processWord(word, linkedHashMap);
        }

        linkedHashMap.forEach((key, value) -> System.out.println(String.join(",", value) + ","));
    }


    private static void processWord(String word, Map<Integer, List<String>> linkedHashMap) {
        int numberForWord = getNumberForWord(word);
        List<String> valueList = linkedHashMap.getOrDefault(numberForWord, new LinkedList<>());
        valueList.add(word);
        linkedHashMap.put(numberForWord, valueList);
    }

    private static int processWord(String word, int num) {
        for (int i = 1; i < word.length(); i++) {
            num = processLetter(num, word.charAt(i));
        }
        return num;
    }

    private static int getNumberForWord(String word) {
        int num = 0;
        if (word == null || word.isEmpty()) {
            return num;
        }
        num = processLetter(num, word.charAt(0));
        return processWord(word, num);
    }

    private static int processLetter(int num, char currentLetter) {
        return num | 1 << (currentLetter - BASE_LETTER);
    }
}



