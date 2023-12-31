package base.gamesys.utils;

public class ScratchSelectsort {

    // implement generic selection sort
    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        // iterate over array
        for (int i = 0; i < array.length - 1; i++) {
            // find index of smallest element
            int smallest = i;
            for (int j = i + 1; j < array.length; j++) {
                // if current element is smaller than previous smallest, set smallest to current
                if (array[j].compareTo(array[smallest]) < 0) {
                    smallest = j;
                }
            }
            // swap elements
            T temp = array[i];
            array[i] = array[smallest];
            array[smallest] = temp;
        }
    }

}
