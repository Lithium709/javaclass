package com.company.codementor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnSort {


    public static String sortCsvColumns(String csvData) {

        final String[] lines = csvData.split("\\n");
        final String[] header = lines[0].split(",");
        String[][] values = new String[lines.length][header.length];
        for (int i = 0; i < values.length; i++) {
            String[] line = lines[i].split(",");
            values[i] = Arrays.copyOf(line, header.length);
        }

        List<String> headerList = Arrays.stream(header).sorted().collect(Collectors.toList());
        StringBuilder result = new StringBuilder(csvData.length());

        result.append(String.join(",", headerList));
        result.append("\n");

        for (int i = 1; i < values.length; i++) {
            String[] newLine = new String[header.length];
            for (int j = 0; j < header.length; j++) {
                newLine[headerList.indexOf(header[j])] = values[i][j];
            }
            result.append(String.join(",", Arrays.asList(newLine)));
            if (i != values.length - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {

            String s = Long.toBinaryString(2147483647);
        for (int i = 0; i < Long.numberOfLeadingZeros(3)-1; i++) {
            s = "0" + s;
        }
        System.out.println(Long.parseUnsignedLong("0" + s.replaceAll("0", "X").replaceAll("1", "0").replaceAll("X", "1"),2));
    }
}
