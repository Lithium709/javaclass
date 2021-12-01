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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Toys3 {

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


    private static String[] getTopQuoted(int numToys, int topToys, String[] toys, int numQuotes, String[] quotes) {

        Map<String, Toy> map = new HashMap<>();
        Set<String> toySet = new HashSet<>(Arrays.asList(toys));
        PriorityQueue<Toy> priorityQueue = new PriorityQueue<>(
                (t1, t2) -> t2.count - t1.count == 0 ? t1.name.compareToIgnoreCase(t2.name) : t2.count - t1.count);
        for (int i = 0; i < numQuotes; i++) {

            final Set<String> stringSet = Arrays.stream(quotes[i].split("\\b")).filter(w -> w.matches("[a-zA-Z0-9]+"))
                    .map(String::toLowerCase).collect(Collectors.toSet());

            stringSet.retainAll(toySet);
            for (String toyName : stringSet) {
                Toy toy = map.get(toyName);
                if (toy == null) {
                    toy = new Toy(toyName);
                    priorityQueue.offer(toy);
                    map.put(toyName, toy);
                } else {
                    toy.increment();
                    priorityQueue.remove(toy);
                    priorityQueue.offer(toy);
                }
            }
        }

        String[] results = new String[topToys];

        int top = Math.min(topToys, priorityQueue.size());
        for (int i = 0; i < top; i++) {
            final Toy toy = priorityQueue.poll();
            results[i] = toy.name;
        }

        return results;

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
        for (String toy : getTopQuoted(140000, 10, keywordArray, quotes.size(), quotesArray)) {
            System.out.println(toy);
        }
        afterTime = System.currentTimeMillis();

        System.out.println("Ranking took " + (afterTime - beforeTime) + " ms");

    }

}
