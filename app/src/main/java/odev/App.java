

package odev;

public class App {

    public static void main(String[] args) {

    }

    public static boolean isItsAverageInRangeBetween(int[] array, int a, int b) {
        if (array == null)
            return false;
        if (array.length == 0)
            return false;
        
        int total = 0;
        for (int i = 0; i < array.length; i++)
            total += array[i];
        double average = (double)total / array.length;
        return average >= a && average <= b;
    }
}