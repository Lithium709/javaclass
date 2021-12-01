package com.company.leetcode;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaStream {



    public static void main(String[] args) {
       new JavaStream().getScore();
    }

    private void getScore() {
        Map<LocalDate, Long> map = new HashMap() {{
            put(LocalDate.of(2021, 5, 22), 2345L);
            put(LocalDate.of(2021, 5, 29), 12123L);
        }};

        long currentWeek = (long) map.getOrDefault(LocalDate.of(2021, 5, 29), 0L);
        final Collection<Long> weeks = map.values();
        final long mean = weeks.stream().mapToLong(i -> i).sum()/ weeks.size();
        double std = Math.sqrt(weeks.stream().mapToDouble(d -> (d - mean)*(d - mean)).average().getAsDouble());
        System.out.println(mean);
        System.out.println(std);
    }

}
