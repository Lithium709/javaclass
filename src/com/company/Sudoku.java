package com.company;

import java.io.*;
import java.util.*;

class Sudoku {

    static int depth = 0;

    static String getPossible(char[][] board, int i, int j) {

        Set<Character> set = new HashSet<>();
        for (int k = 0; k < 9; k++) {
            // check row
            if (board[i][k] != '.') {
                set.add(board[i][k]);
            }
            // check column
            if (board[k][j] != '.') {
                set.add(board[k][j]);
            }
        }
        // check sub board
        for (int k = 3 * (i / 3); k < 3 * (i / 3) + 3; k++) {
            if (board[i][k] != '.') {
                set.add(board[i][k]);
            }
            // check column
            if (board[k][j] != '.') {
                set.add(board[k][j]);
            }
        }
        String res = "";
        for (char k = '1'; k <= '9'; k++) {
            if (!set.contains(k)) {
                res = res + k;
            }
        }
        return res;
    }

    static boolean sudokuSolve(char[][] board) {
        depth++;
        // boolean res = false;
        int min_row = -1;
        int min_col = -1;
        String min_cand = "123456789";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    String posib = getPossible(board, i, j);
                    if (posib.length() < min_cand.length()) {
                        min_cand = posib;
                        min_row = i;
                        min_col = j;
                    }
                }
            }
        }
        // solved
        if (min_row < 0) {
            for (char[] row : board) {
                for (char col : row) {
                    System.out.print(col);
                }
                System.out.println();
            }
            System.out.println("depth " + depth);
            return true;
        }

        for (int k = 0; k < min_cand.length(); k++) {
            board[min_row][min_col] = min_cand.charAt(k);
            if (sudokuSolve(board)) {
                return true;
            }
            board[min_row][min_col] = '.';
        }
        return false;

    }

    public static void main(String[] args) {

        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'},
        };

        System.out.print(sudokuSolve(board1));

    }

}