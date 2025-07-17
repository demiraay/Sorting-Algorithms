public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int len = arr.length;
        int i ;
        for (int j = 1; j < len; j++) {
            int key = arr[j];
            i = j - 1;

            while (i >= 0 && arr[i] > key) {
                arr[i + 1] = arr[i];
                i--;
            }
            arr[i + 1] = key;
        }
    }

}
