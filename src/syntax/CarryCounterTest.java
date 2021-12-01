package syntax;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CarryCounterTest {


    private static int numberOfCarryOperations(int a, int b) {
        int cs = 0;
        int r = 0;

        while ((b + a + cs) > 9) {

            int da = a % 10;
            int db = b % 10;
            int c = da + db + cs;

            if (c > 9) {
                r++;
                cs = c / 10;
            }

            a /= 10;
            b /= 10;

        }
        return r;
    }


    @Test
    public void equal_length() {
        assertEquals(0, numberOfCarryOperations(123, 456));
        assertEquals(3, numberOfCarryOperations(555, 555));
        assertEquals(0, numberOfCarryOperations(900, 11));
        assertEquals(1, numberOfCarryOperations(101, 809));
        assertEquals(1, numberOfCarryOperations(189, 209));
    }

    @Test
    public void different_length() {
        assertEquals(numberOfCarryOperations(145, 55), 2);
        assertEquals(numberOfCarryOperations(0, 0), 0);
        assertEquals(numberOfCarryOperations(1, 99999), 5);
        assertEquals(numberOfCarryOperations(999045, 1055), 5);
    }


}