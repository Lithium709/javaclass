package com.company.codementor;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PokerHandTest {

    private PokerHandBit.Result loss = PokerHandBit.Result.LOSS;
    private PokerHandBit.Result win = PokerHandBit.Result.WIN;
    private PokerHandBit.Result tie = PokerHandBit.Result.TIE;

    @Test
    public void test1() {
        Test("Highest straight flush wins", loss, "2H 3H 4H 5H 6H", "KS AS TS QS JS");
    }

    @Test
    public void test2() {
        Test("Straight flush wins of 4 of a kind", win, "2H 3H 4H 5H 6H", "AS AD AC AH JD");
    }

    @Test
    public void test3() {
        Test("Highest 4 of a kind wins", win, "AS AH 2H AD AC", "JS JD JC JH 3D");
    }

    @Test
    public void test4() {
        Test("4 Of a kind wins of full house", loss, "2S AH 2H AS AC", "JS JD JC JH AD");
    }

    @Test
    public void test5() {
        Test("Full house wins of flush", win, "2S AH 2H AS AC", "2H 3H 5H 6H 7H");
    }

    @Test
    public void test6() {
        Test("Highest flush wins", win, "AS 3S 4S 8S 2S", "2H 3H 5H 6H 7H");
    }

    @Test
    public void test7() {
        Test("Flush wins of straight", win, "2H 3H 5H 6H 7H", "2S 3H 4H 5S 6C");
    }

    @Test
    public void test8() {
        Test("Equal straight is tie", tie, "2S 3H 4H 5S 6C", "3D 4C 5H 6H 2S");
    }

    @Test
    public void test9() {
        Test("Straight wins of three of a kind", win, "2S 3H 4H 5S 6C", "AH AC 5H 6H AS");
    }

    @Test
    public void test10() {
        Test("3 Of a kind wins of two pair", loss, "2S 2H 4H 5S 4C", "AH AC 5H 6H AS");
    }

    @Test
    public void test11() {
        Test("2 Pair wins of pair", win, "2S 2H 4H 5S 4C", "AH AC 5H 6H 7S");
    }

    @Test
    public void test12() {
        Test("Highest pair wins", loss, "6S AD 7H 4S AS", "AH AC 5H 6H 7S");
    }

    @Test
    public void test13() {
        Test("Pair wins of nothing", loss, "2S AH 4H 5S KC", "AH AC 5H 6H 7S");
    }

    @Test
    public void test14() {
        Test("Highest card loses", loss, "2S 3H 6H 7S 9C", "7H 3C TH 6H 9S");
    }

    @Test
    public void test15() {
        Test("Highest card wins", win, "4S 5H 6H TS AC", "3S 5H 6H TS AC");
    }

    @Test
    public void test16() {
        Test("Equal cards is tie", tie, "2S AH 4H 5S 6C", "AD 4C 5H 6H 2C");
    }

    @Test
    public void test17() {
        Test("Randomized test 1", loss, "KS 8D 4D 9S 4S", "KC 4H KS 2H 8D");
    }

    @Test
    public void test18() {
        Test("Randomized test 2", win, "KD 4S KC 3H 8S", "QH 8H KD JH 8S");
    }

    @Test
    public void test19() {
        Test("Randomized test 3", win, "KH KC 3S 3H 3D", "2H 2C 3S 3H 3D");
    }

    @Test
    public void test20() {
        Test("Randomized test 3", loss, "8C 4S KH JS 4D", "KC 4H KS 2H 8D");
    }

    @Test
    public void test21() {
        Test("Randomized test 3", loss, "2H 2C 3S 3H 3D", "KH KC 3S 3H 3D");
    }

    @Test
    public void test22() {
        Test("Randomized test 3", win, "2H 2C 3S 3H 3D", "3D 2H 3H 2C 2D");
    }

    @Test
    public void test23() {
        Test("Wheel straight", win, "AH 2S 3H 4H 5S", "KD KC 5H 6H 2D");
    }

    @Test
    public void test24() {
        Test("Wheel straight has lowest rank ", loss, "AH 2S 3H 4H 5S", "KD QC JH TH 9D");
    }

    @Test
    public void test25() {
        Test("random test 25 ", loss, "AC KH QH AH AS", "6S 8S 7S 5H 9H");
    }


    @Test
    public void test26() {
        Test("random test 25 ", win, "2C 2H 3H 6H 5S", "AS KS QS JH 9H");
    }

    private void Test(String description, PokerHandBit.Result expected, String playerHand,
            String opponentHand) {
        PokerHandBit player = new PokerHandBit(playerHand);
        PokerHandBit opponent = new PokerHandBit(opponentHand);
        assertEquals(description + ":", expected, player.compareWith(opponent));
    }
}

