package com.company.codementor;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColumnSortTest {

    @Test
    public void shouldSort() {
        String input = "Beth,Charles,Danielle,Adam,Eric\n"
                + "17945,10091,10088,3907,10132\n"
                + "2,12,13,48,11";
        String output = ColumnSort.sortCsvColumns(input);
        assertEquals("Adam,Beth,Charles,Danielle,Eric\n"
                + "3907,17945,10091,10088,10132\n"
                + "48,2,12,13,11", output);
    }

}