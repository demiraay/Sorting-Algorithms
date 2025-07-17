public class ShakerSort {
    public static void shakerSort(int[] arr) {
        boolean swapped = true;
        while (swapped == true) {
            swapped = false;
            for (int i = 0; i <= arr.length - 2; i++) {
                if (arr[i] > arr[i + 1]) {
                    int sw = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = sw;
                    swapped = true;
                }
            }
            if (swapped == false){
                break;
            }
            swapped = false;
            for (int i = arr.length - 2; i >= 0; i--) {
                if (arr[i] > arr[i + 1]) {
                    int sw = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = sw;
                    swapped = true;
                }
            }
        }
    }
}
