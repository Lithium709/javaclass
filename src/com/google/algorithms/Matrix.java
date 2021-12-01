package com.google.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    private List<int[]> compress(int[][] A) {
        List<int[]> ans = new ArrayList<>();
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++) {
                if(A[i][j] != 0) {
                    ans.add(new int[]{i, j, A[i][j]});
                }
            }
        }
        return ans;
    }

    public int[][] multiply(int[][] A, int[][] B) {
        int[][] ans = new int[A.length][B[0].length];
        for(int[] a : compress(A)) {
            for(int[] b: compress(B)) {
                if(a[1] == b[0]) {
                    ans[a[0]][b[1]] += a[2] * b[2];
                }
            }
        }
        return ans;
    }
}
