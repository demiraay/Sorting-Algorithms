import java.util.Arrays;

public class RadixSort {


    public static int[] radixSort(int[] arr, int digit) {
        int pos;
        for (pos = 1; pos <= digit; pos++) {
            arr = countingSort(arr, pos);
        }
        return arr;
    }


    private static int[] countingSort(int[] arr, int pos) {
        int[] count = new int[10];
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }

        int size = arr.length;
        int[] output = new int[arr.length];


        for (int i = 1; i <= size; i++) {
            int dig = getDigit(arr[i - 1], pos);
            count[dig]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }


        for (int i = size; i >= 1; i--) {
            int dig = getDigit(arr[i - 1], pos);
            count[dig]--;
            output[count[dig]] = arr[i - 1];
        }

        return output;
    }



    private static int getDigit(int num, int pos) {
        int div = 1;
        int i ;

        for ( i = 0; i <= pos; i++) {
            div = div * 10;
        }

        return (num / div) % 10;
    }








}
