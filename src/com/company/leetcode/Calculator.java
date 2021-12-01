package com.company.leetcode;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculator {

    // s = "catsanddog"
    Set<String> dict = new HashSet<>();
    List<String> ans = new ArrayList<>();

    private void helper(String s, String res) {

        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(0, i + 1);
            if (dict.contains(sub)) {
                if (i == s.length() - 1) {
                    ans.add(res.substring(1) + " " + sub);
                    return;
                }
                helper(s.substring(i + 1), res + " " + sub);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String word : wordDict) {
            dict.add(word);
        }
        helper(s, "");
        return ans;
    }

    private static int getWeekNumber(ZonedDateTime date) {
        return date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }

    public static int getWeekIndex(ZonedDateTime localDate) {
        int year = localDate.getYear();
        final int weekNumber = getWeekNumber(localDate);
        final int month = localDate.getMonthValue();
        if (month == 12 && weekNumber == 1) {
            year++;
        }
        if (month == 1 && weekNumber >= 52) {
            year--;
        }
        return 100 * (year - 2000) + weekNumber;
    }


    public static int decWeek(int value, int shift) {
        int year = 2000 + value / 100;
        int week = value % 100;
        ZonedDateTime date = ZonedDateTime.of(year, 1, 1, 0, 0, 0, 0, ZoneId.of("UTC"))
                .plusWeeks((week - 1 - shift)).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return getWeekIndex(date);
    }

    public static LocalDate getBeginningOfLastWeek(int weekIndex) {
        int year = 2000 + weekIndex / 100;
        return LocalDate.of(year, 1, 1).
                with(IsoFields.WEEK_OF_WEEK_BASED_YEAR, decWeek(weekIndex, 2) % 100)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    public static void main(String[] args) {
        System.out.println(getWeekIndex(ZonedDateTime.of(LocalDate.of(2021,1,1), LocalTime.MIDNIGHT, ZoneId.of("UTC"))));
        System.out.println(getBeginningOfLastWeek(2050));
    }
}
