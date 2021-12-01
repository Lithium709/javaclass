package com.google.foobar;

public class Task21 {

    public static int[] solution(int[] l, int t) {

        for (int i = 0; i < l.length; i++) {

            int sum = 0;
            for (int j = i; j < l.length; j++) {
                sum += l[j];
                if (sum == t) {
                    return new int[]{i, j};
                }
                if (sum > t) {
                    break;
                }
            }

        }
        return new int[]{-1, -1};
    }


    public static void main(String[] args) {
        final int[] solution = solution(new int[]{1, 2, 3, 4}, 15);
        for (int i = 0; i < solution.length ; i++) {
            System.out.println(solution[i]);
        }

        final int[] solution1 = solution(new int[]{4, 3, 10, 2, 8}, 12);
        for (int i = 0; i < solution1.length ; i++) {
            System.out.println(solution1[i]);
        }

    }
}
