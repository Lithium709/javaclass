package com.company.codementor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PokerHandBit {

    private static String cardValue = "#_23456789TJQKA";
    //                                "AKQJT98765432"
    //                                 1111100000000


    //                                 0000001000000
    //                                 0000010000000
    private static String cardSuit = "SHDC";

    private String[] cards;

    public enum Result {TIE, WIN, LOSS}

    PokerHandBit(String hand) {
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

    public Result compareWith(PokerHandBit hand) {
        long thisHandRank = getHandPower(this);
        long otherHandRank = getHandPower(hand);

        if (thisHandRank == otherHandRank) {
            return Result.TIE;
        }

        // lower is better 1 beats 2...9
        return thisHandRank > otherHandRank ? Result.WIN : Result.LOSS;
    }

    private static long getHandPower(PokerHandBit hand) {
        // this function returns top rank
        int[] bits = getBitPresetation(hand);
        int or = getOrProduct(bits);
        int xor = getXorProduct(bits);
        int and = getAndProduct(bits);
        if (hasStraight(or) && hasFlash(bits)) {
            return Integer.lowestOneBit(or) * 10_00_00_00_00_00_00_00_00L;
        } else if (hasFourOfKind(and)) {
            return getHighestBitPosition(and) * 10_00_00_00_00_00_00_00L;
        } else if (hasFullHouse(or, xor)) {
            return ((getHighestBitPosition(xor)) * 15 + getHighestBitPosition(or & ~xor)) * 100_00_00_00_00_00L;
        } else if (hasFlash(bits)) {
            return (getHighestBitPosition(Integer.highestOneBit(or))) * 100_00_00_00_00L;
        } else if (hasStraight(or)) {
            return getHighestBitPosition(Integer.lowestOneBit(or)) * 100_00_00_00L;
        } else if (hasTheeOfKind(or, xor)) {
            return getHighestBitPosition((bits[0] & bits[1]) | (bits[2] & bits[3])) * 100_00_00L;
        } else if (hasTwoPairOfKind(or, xor)) {
            return getHighestBitPosition(or & ~xor) * 1000L + getHighestBitPosition(xor);
        } else if (hasPair(or, xor)) {
            return (getHighestBitPosition(or ^ xor)) * 100L + rankSum(or & ~(or ^ xor));
        } else {
            return rankSum(or);
        }
    }

    private static int getHighestBitPosition(int and) {
        return 32 - Integer.numberOfLeadingZeros(and);
    }

    private static int[] getBitPresetation(PokerHandBit hand) {

        int[] result = new int[4];
        for (int i = 0; i < 5; i++) {
            final String card = hand.cards[i];
            result[cardSuit.indexOf(card.charAt(1))] |= (1 << cardValue.indexOf(card.charAt(0)) - 2);
        }
        return result;
    }

    private static boolean hasStraight(int or) {

        int mask = 31;
        boolean hasStraight = false;
        for (int i = 0; i < 11; i++) {
            hasStraight = hasStraight || ((mask & or >> i) == mask);
        }
        if (hasStraight) {
            return true;
        }
        // wheel
        return or == 0b1000000001111;
    }

    private static boolean hasFlash(int[] bits) {

        int zeroes = 0;
        for (int i = 0; i < 4; i++) {
            if (bits[i] == 0) {
                zeroes++;
            }
        }
        return zeroes == 3;
    }

    private static boolean hasFourOfKind(int and) {
        return Integer.bitCount(and) == 1;
    }

    private static boolean hasFullHouse(int or, int xor) {
        return Integer.bitCount(or) == 2 && Integer.bitCount(xor) == 1;
    }

    private static boolean hasTheeOfKind(int or, int xor) {
        return Integer.bitCount(or) == 3 && Integer.bitCount(xor) == 3;
    }

    private static boolean hasTwoPairOfKind(int or, int xor) {
        return Integer.bitCount(or) == 3 && Integer.bitCount(xor) == 1;
    }

    private static boolean hasPair(int or, int xor) {
        return Integer.bitCount(or) == 4 && Integer.bitCount(xor) == 3;
    }

    private static int getOrProduct(int[] bits) {
        return bits[0] | bits[1] | bits[2] | bits[3];
    }

    private static int getXorProduct(int[] bits) {
        return bits[0] ^ bits[1] ^ bits[2] ^ bits[3];
    }

    private static int getAndProduct(int[] bits) {
        return bits[0] & bits[1] & bits[2] & bits[3];
    }

    private static int rankSum(int bitMap) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            sum += (bitMap >> i & 1) == 1 ? i : 0;
        }
        return sum;
    }
}