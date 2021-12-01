package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        // System.out.println(s);
        List<String> list = new ArrayList<>();
        if(s.isEmpty()) {
            return list;
        }
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                for(String suffix : wordBreak(s.substring(word.length()), wordDict)) {
                    list.add(word + " " + suffix);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
       // wordBreak("catsanddog", Arrays.asList("cat","cats","and","sand","dog"));
       String a = "/";
        final String[] split = "|||".split("\\|", -1);
        System.out.println(split.length);
    }
}
