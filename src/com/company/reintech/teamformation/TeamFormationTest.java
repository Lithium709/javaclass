package com.company.reintech.teamformation;


import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamFormationTest {


    @Test
    public void teamFormationTest1() {

        long result = TeamFormation.teamFormation(new ArrayList<>(Arrays.asList( 17, 12, 10, 2, 7, 2, 11, 20, 8)), 3, 4);
        assertEquals(49L, result);
    }

    @Test
    public void teamFormationTest2() {

        long result = TeamFormation.teamFormation(new ArrayList<>(Arrays.asList( 6, 18, 8, 14, 10, 12, 18, 9)), 8, 3);
        assertEquals(95L, result);
    }

    @Test
    public void teamFormationTest3() {

        long result = TeamFormation.teamFormation(new ArrayList<>(Arrays.asList( 18, 5, 15, 18, 11, 15, 9, 7)), 5, 1);
        assertEquals(60L, result);
    }

}