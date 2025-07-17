import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombSort {
    public static void combSort(int[] arr) {
        int gap = arr.length;
        double shrink = 1.3;
        boolean sorted = false;

        while (sorted == false) {
            gap = Math.max(1, (int) Math.floor(gap / shrink));
            if(gap ==1){
                sorted = true;
            }
            else{
                sorted = false;
            }

            for (int i = 0; i < arr.length - gap ; i++) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    sorted = false;
                }
            }
        }
    }

}
