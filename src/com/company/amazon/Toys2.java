package com.company.amazon;

import java.util.*;
import java.util.stream.Collectors;

public class Toys2 {

    public static void main(String[] args) {
        new Toys2().getTopToys(new String[]{"three", "two", "one"}, new String[]{
                "its not always easy to be the one or two or three",
                "one two three",
                "and not like the one",
                "and not that one",
                "another one",
                "Only one"
        }, 100);
    }

    public List<String> getTopToys(String[] toys, String[] quotes, int topToys) {
        if (toys == null || toys.length == 0 || quotes == null || quotes.length == 0) return Collections.emptyList();

        // O(1)
        final Set<String> toysSet = Arrays.stream(toys)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        // calculate frequencies
        TreeMap<String, Integer> resultMap = new TreeMap<>();

        SortedMap<String, Integer> result = (SortedMap<String, Integer>) Arrays.stream(quotes)
                .map(String::toLowerCase)
                .map(quote -> quoteWords(quote, toysSet))
                .reduce(resultMap, (accum, newValue) -> {
                    addMapEntriesIntoAccum(accum, newValue);
                    return accum;
                });

        return Collections.emptyList();
    }

    private void addMapEntriesIntoAccum(Map<String, Integer> accum, Map<String, Integer> newValue) {
        for (Map.Entry<String, Integer> entry : newValue.entrySet()) {
            if (!accum.containsKey(entry.getKey())) {
                accum.put(entry.getKey(), entry.getValue());
            } else {
                accum.put(entry.getKey(), accum.get(entry.getKey()) + entry.getValue());
            }
        }
    }

    private Map<String, Integer> quoteWords(String lowerQuote, Set<String> toys) {
        return Arrays.stream(lowerQuote.split("\\s+"))
                .filter(toys::contains)
                .collect(Collectors.toMap(word -> word, word -> 1));
    }

}
