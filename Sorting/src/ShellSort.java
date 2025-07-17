public class ShellSort {
    public static void shellSort(int[] arr) {
        int len = arr.length;
        int gap = len / 2;

        while (gap > 0) {
            for (int i = gap; i <= len - 1 ; i++) {
                int temp = arr[i];
                int j = i;

                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j = j - gap;
                }

                arr[j] = temp;
            }
            gap = gap / 2;
        }
    }

}
