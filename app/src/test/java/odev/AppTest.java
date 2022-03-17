

package odev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    public void testInRange() {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6};
        assertTrue(App.isItsAverageInRangeBetween(arr, 1, 4));
    }

    @Test
    public void testNotInRange() {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6};
        assertFalse(App.isItsAverageInRangeBetween(arr, 4, 5));
    }

    @Test
    public void testNull() {
        Integer[] arr = null;
        assertFalse(App.isItsAverageInRangeBetween(arr, 1, 4));
    }

    @Test
    public void testEmpty() {
        Integer[] arr = new Integer[] {};
        assertFalse(App.isItsAverageInRangeBetween(arr, 1, 4));
    }
}