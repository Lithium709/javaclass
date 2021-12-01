package com.company.reintech;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MinimalDiscountTest {


    @Test
    public void finalPrice1() {

        List<Integer> prices = new ArrayList<>(Arrays.asList(5, 1, 3, 4, 6, 2));
        MinimalDiscount.finalPrice(prices);
       // assertEquals(14, MinimalDiscount.finalPrice(prices));

    }

    @Test
    public void finalPrice2() {

        List<Integer> prices = new ArrayList<>(Arrays.asList(2, 3, 1, 2, 4, 2));
        MinimalDiscount.finalPrice(prices);
      //  assertEquals(8, MinimalDiscount.finalPrice(prices));

    }

    @Test
    public void finalPrice3() {

        List<Integer> prices = new ArrayList<>(Arrays.asList( 1, 3, 3, 2, 5));
        MinimalDiscount.finalPrice(prices);
        //  assertEquals(10, MinimalDiscount.finalPrice(prices));

    }
}