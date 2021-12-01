package com.company.amazon;

/*
    given :
    List of toys (keywords)
    List of phrases

    find:
    top N keywords by number of phrases where these keywords occur

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Toys4 {

    public static class Toy {

        public Toy(String name) {
            this.name = name;
            this.count = 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Toy toy = (Toy) o;
            return Objects.equals(name, toy.name) &&
                    Objects.equals(count, toy.count);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        public void increment() {
            this.count++;
        }

        String name;
        Integer count;

    }

    static class Node {
        public String word;
        public long count;

        public Node(String word, long count) {
            this.word = word;
            this.count = count;
        }
    }

    private static void fillMap(String[] quotes) {
        for (String str: quotes) {
            fillMap(str);
        }
    }
    private static void fillMap(String str) {
        Set<String> set = new HashSet<>();
        String[] split = str.split("\\b");
        for (String string: split) {
            String cur = string.replaceAll("[.,!?\\-+()]", "");
            if (!cur.isEmpty()) {
                set.add(cur.toLowerCase());
            }
        }
        for (String string: set) {
            Long count = counter.get(string);
            if (count == null) {
                count = 0L;
            }
            count++;
            counter.put(string, count);
        }
    }

    static Map<String, Long> counter = new HashMap<>();

    public static Comparator<Node> idComparator = new Comparator<Node>(){

        @Override
        public int compare(Node n1, Node n2) {
            return Long.compare(n1.count, n2.count);
        }
    };
    private static String[] getTopQuoted(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {
        if (numToys <= topToys) {
            return toys;
        }
        fillMap(quotes);
        Queue<Node> customerPriorityQueue = new PriorityQueue<>(topToys, idComparator);
        for (int i = 0; i < topToys; i++) {
            long count = counter.get(toys[i]) == null ? 0 : counter.get(toys[i]);
            customerPriorityQueue.add(new Node(toys[i], count));
        }
        for (int i = topToys; i < numToys; i++) {
            long min = customerPriorityQueue.peek().count;
            long count = counter.get(toys[i]) == null ? 0 : counter.get(toys[i]);
            if (count > min) {
                customerPriorityQueue.poll();
                customerPriorityQueue.add(new Node(toys[i], count));
            }
        }
        String[] result = new String[topToys];
        int count = 0;
        while (!customerPriorityQueue.isEmpty()) {
            result[count] = customerPriorityQueue.poll().word;
            count++;
        }
        return result;
    }

    private static String getRecordFromLine(String line) {
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            if (rowScanner.hasNext()) {
                return rowScanner.next();
            }
        }
        throw new RuntimeException("empty string");
    }

    public static void main(String[] args) {

        final String[] toys1 = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        final String[] quotes1 = {"Elmo is the hottest toy of the season. Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality.", "Expect the Elsa dolls to be very popular this year",
                "Elsa and Elmo are the toys I'll be buying for my kids",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"};

        long beforeTime = System.currentTimeMillis();

        List<String> quotes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("/Users/Oleksandr/Documents/nexus/csv/relevant_content-3.csv"));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                quotes.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> keywords = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(
                "/Users/Oleksandr/Documents/nexus/csv/us-one-worders2/part-00000-f16bbcad-5883-4e5a-8a66-8a98e58cefff-c000.csv"));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                keywords.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long afterTime = System.currentTimeMillis();

        System.out.println("Loading took " + (afterTime - beforeTime) + " ms");

        beforeTime = System.currentTimeMillis();
        final String[] quotesArray = quotes.toArray(new String[quotes.size()]);
        final int size = keywords.size();
        final String[] keywordArray = keywords.toArray(new String[size]);
        for (String toy : getTopQuoted(1400000, 10, keywordArray, quotes.size(), quotesArray)) {
            System.out.println(toy);
        }
        afterTime = System.currentTimeMillis();

        System.out.println("Ranking took " + (afterTime - beforeTime) + " ms");

    }

}
