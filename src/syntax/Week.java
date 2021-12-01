package syntax;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Week {

    public static void main(String[] args) {

        int[] weeks = new int[52];
        weeks[15] = 7;
        weeks[25] = 4;
        weeks[30] = 3;
        weeks[34] = 3;
        weeks[45] = 2;
        System.out.println(Arrays.toString(weeks));

        int current = 33;
        fillSegments(weeks, current);

        System.out.println(Arrays.toString(weeks));


    }

    private static void fillSegments(int[] weeks, int current) {
        int prev = 0;
        for (int i = current + 1; i <= current + 52; i++) {

            if (weeks[i % 52] == 0) {
                if (prev != 0) {
                    weeks[i % 52] = prev;
                }
            } else {
                prev = weeks[i % 52];
            }
        }
    }

}
