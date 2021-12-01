package com.company.reintech.teamformation;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class TeamFormation {

    static long teamFormation(List<Integer> score, int team, int m) {
        LinkedList<Integer> inputScore = new LinkedList<>(score);
        List<Integer> resultTeam = new ArrayList<>(team);
        PriorityQueue<Integer> selectFromLeft = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> selectFromRight = new PriorityQueue<>(Comparator.reverseOrder());

        // populate left
        for (int i = 0; i < m; i++) {
            populateLeft(inputScore, selectFromLeft);
            populateRight(inputScore, selectFromRight);
        }

        // selecting best team
        while (resultTeam.size() < team) {
            // left
            Integer maxLeft = isNull(selectFromLeft.peek());
            // right
            Integer maxRight = isNull(selectFromRight.peek());

            if (maxLeft >= maxRight) {
                // going with left
                resultTeam.add(selectFromLeft.poll());
                populateLeft(inputScore, selectFromLeft);
            } else {
                // going with right
                resultTeam.add(selectFromRight.poll());
                populateRight(inputScore, selectFromRight);
            }

        }
        return resultTeam.stream().mapToInt(x -> x).sum();
    }

    private static Integer isNull(Integer i) {
        return i == null ? -1 : i;
    }

    private static void populateRight(LinkedList<Integer> inputScore,
            PriorityQueue<Integer> selectFromRight) {
        if (!inputScore.isEmpty()) {
            selectFromRight.add(inputScore.removeLast());
        }
    }

    private static void populateLeft(LinkedList<Integer> inputScore,
            PriorityQueue<Integer> selectFromLeft) {
        if (!inputScore.isEmpty()) {
            selectFromLeft.add(inputScore.removeFirst());
        }
    }
}