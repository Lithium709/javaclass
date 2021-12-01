package com.company.codementor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerHand {

    private static String cardValue = "#_23456789TJQKA";
    private static String cardSuit = "SHDC";

    private String[] cards;

    public enum Result {TIE, WIN, LOSS}

    PokerHand(String hand) {
        // we accept 5 cards here like
        // "KS 2H 5C JD TD"
        if (hand == null || hand.length() == 0) {
            throw new IllegalArgumentException("Empty hand provided without cards");
        }
        String[] cards = hand.split("\\s");
        if (cards.length != 5) {
            throw new IllegalArgumentException("5 cards must be provided");
        }

        this.cards = cards;
    }

    public Result compareWith(PokerHand hand) {
        long thisHandRank = getHandPower(this);
        long otherHandRank = getHandPower(hand);

        if (thisHandRank == otherHandRank) {
            return Result.TIE;
        }

        // lower is better 1 beats 2...9
        return thisHandRank > otherHandRank ? Result.WIN : Result.LOSS;
    }

    private static long getHandPower(PokerHand hand) {
        // this function returns top rank
        if (hasStraight(hand) > 0 && hasFlash(hand) > 0) {
            return hasStraight(hand) * 10_00_00_00_00_00_00_00_00L;
        } else if (hasFourOfKind(hand) > 0) {
            return hasFourOfKind(hand) * 100_00_00_00_00_00_00_00L;
        } else if (hasFullHouse(hand) > 0) {
            return hasFullHouse(hand) * 100_00_00_00_00_00L;
        } else if (hasFlash(hand) > 0) {
            return hasFlash(hand) * 100_00_00_00_00L;
        } else if (hasStraight(hand) > 0) {
            return hasStraight(hand) * 100_00_00_00L;
        } else if (hasTheeOfKind(hand) > 0) {
            return hasTheeOfKind(hand) * 100_00L;
        } else if (hasTwoPairOfKind(hand) > 0) {
            return hasTwoPairOfKind(hand) * 100L;
        } else if (hasPair(hand) > 0) {
            return hasPair(hand);
        } else if (hasHighestRank(hand) > 0) {
            return hasHighestRank(hand);
        }
        return 0;
    }

    private static int hasStraight(PokerHand hand) {

        final List<Integer> ranks = getRankList(hand);
        boolean hasStraight = true;
        for (int i = 1; i < 4; i++) {
            hasStraight = hasStraight && ranks.get(i) - ranks.get(i - 1) == 1;
        }
        if (hasStraight && (ranks.get(4) - ranks.get(3) == 1)) {
            return ranks.get(4);
        }
        // wheel
        if (hasStraight && (ranks.get(3) == 5 && ranks.get(4) == 14)) {
            return ranks.get(3);
        }
        return 0;
    }

    private static List<Integer> getRankList(PokerHand hand) {
        return Arrays.stream(hand.cards).map(s -> s.charAt(0)).map(k -> cardValue.indexOf(k))
                .sorted().collect(Collectors.toList());
    }

    private static long hasFlash(PokerHand hand) {

        List<Character> suits = Arrays.stream(hand.cards).map(s -> s.charAt(1))
                .collect(Collectors.toList());
        for (int i = 1; i < 5; i++) {
            if (suits.get(i) != suits.get(i - 1)) {
                return 0;
            }
        }
        return hasHighestRank(hand);
    }

    private static long hasFourOfKind(PokerHand hand) {

        Map<Character, Long> map = getRankMap(hand);

        if (map.values().stream().anyMatch(k -> k == 4)) {
            return getNOfKindRank(map, 4);
        }

        return 0;
    }

    private static int getNOfKindRank(Map<Character, Long> map, int i) {
        return cardValue.indexOf(
                map.entrySet().stream().filter(e -> e.getValue() == i).map(Entry::getKey).findFirst().get());
    }

    private static long hasFullHouse(PokerHand hand) {
        Map<Character, Long> map = getRankMap(hand);

        if (map.values().stream().anyMatch(k -> k == 3) && map.values().stream().anyMatch(k -> k == 2)) {
            return 100L * getNOfKindRank(map, 3) +
                    getNOfKindRank(map, 2)
                    ;
        }
        return 0;
    }

    private static long hasTheeOfKind(PokerHand hand) {
        Map<Character, Long> map = getRankMap(hand);

        if (map.values().stream().anyMatch(k -> k == 3)) {
            return 100 * getNOfKindRank(map, 3)
                    + getSumOfRanks(map, 1);
        }
        return 0;
    }

    private static long hasTwoPairOfKind(PokerHand hand) {

        Map<Character, Long> map = getRankMap(hand);
        if (map.entrySet().stream().filter(e -> e.getValue() == 2).count() == 2) {
            return 100 * getSumOfRanks(map, 2)
                    + getSumOfRanks(map, 1);
        }
        return 0;
    }

    private static long getSumOfRanks(Map<Character, Long> map, int i) {
        return map.entrySet().stream().filter(e -> e.getValue() == i).map(Entry::getKey)
                .mapToLong(k -> cardValue.indexOf(k)).sum();
    }

    private static long hasPair(PokerHand hand) {

        Map<Character, Long> map = getRankMap(hand);
        if (map.entrySet().stream().anyMatch(e -> e.getValue() == 2)) {
            return 100 * getSumOfRanks(map, 2)
                    + getSumOfRanks(map, 1);
        }
        return 0;
    }


    private static long hasHighestRank(PokerHand hand) {
        return Arrays.stream(hand.cards).map(s -> s.charAt(0)).mapToLong(k -> cardValue.indexOf(k)).sum();
    }

    private static Map<Character, Long> getRankMap(PokerHand hand) {
        return Arrays.stream(hand.cards).map(s -> s.charAt(0))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}