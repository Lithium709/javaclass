package com.company.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Alien {
    private Map<Character, Set<Character>> graph = new HashMap<>();

    private int bfs(char k){
        boolean[] visited = new boolean[26];
        Queue<Character> queue = new LinkedList<>();
        queue.offer(k);
        int rank = 0;
        while(!queue.isEmpty()) {
            char c = queue.poll();
            visited[c - 'a'] = true;
            rank++;
            for(char nei : graph.getOrDefault(c, Collections.emptySet())) {
                 if(nei == k) {
                     return -1;
                 }
                if(!visited[nei - 'a']) {
                    queue.offer(nei);
                }
            }
        }
        return rank - 1;
    }



    public String alienOrder(String[] words) {
        char[] prev = new char[100];
        // populate graph
        for(String w: words) {
            for(int i = 0; i < w.length(); i++){
                char ch = w.charAt(i);
                if(prev[i] == 0) {
                    prev[i] = ch;
                } else if(ch != prev[i]) {
                    graph.computeIfAbsent(prev[i], x -> new HashSet<>()).add(ch);
                    prev[i] = ch;
                }
                graph.computeIfAbsent(ch, x -> new HashSet<>());
            }
            for (int i = w.length(); i < 100; i++) {
                prev[i] = 0;
            }
        }
        String[] rankTab = new String[26];
        Arrays.fill(rankTab, "");
        for(char key: graph.keySet()) {
            int r = bfs(key);
            if(r == -1) {
                return "";
            }
            rankTab[r] += key;
        }

        String ans = "";
        for(int i = 25; i >= 0; i--) {
            ans += rankTab[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Alien alien = new Alien();
        System.out.println(alien.alienOrder(new String[]{"wrt", "wrf","er","ett","rftt"}));
    }
}
