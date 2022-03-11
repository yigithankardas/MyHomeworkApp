

package odev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    public void testInRange() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6};
        assertTrue(App.isItsAverageInRangeBetween(arr, 1, 4));
    }

    @Test
    public void testNotInRange() {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6};
        assertFalse(App.isItsAverageInRangeBetween(arr, 4, 5));
    }

    @Test
    public void testNull() {
        int[] arr = null;
        assertFalse(App.isItsAverageInRangeBetween(arr, 1, 4));
    }

    @Test
    public void testEmpty() {
        int[] arr = new int[] {};
        assertFalse(App.isItsAverageInRangeBetween(arr, 1, 4));
    }
}