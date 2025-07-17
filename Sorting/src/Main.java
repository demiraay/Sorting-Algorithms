import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


class Main {
    public static void main(String args[]) throws IOException {


        int[] dataSizes = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};
        int[] dataset = readCSV("/Users/yusufdemiray/Desktop/HuCeng/4th Term/Assignments/Assignment1/Sorting/src/TrafficFlowDataset.csv");
        // time matrix
        double[][] randomResultMatrix= new double[5][dataSizes.length];
        double[][] sortedResultMatrix= new double[5][dataSizes.length];
        double[][] reverseSortedResultMatrix= new double[5][dataSizes.length];






        for (int i = 0; i < dataSizes.length; i++) {
            int size = dataSizes[i];
            int[] testData = Arrays.copyOfRange(dataset, 0, size);

            randomResultMatrix[0][i] = AlgorithmTestFunction(testData, "Comb Sort");
            randomResultMatrix[1][i] = AlgorithmTestFunction(testData, "Insertion Sort");
            randomResultMatrix[2][i] = AlgorithmTestFunction(testData, "Shaker Sort");
            randomResultMatrix[3][i] = AlgorithmTestFunction(testData, "Shell Sort");
            randomResultMatrix[4][i] = AlgorithmTestFunction(testData, "Radix Sort");
        }

        showAndSaveChart("Sorting Algorithms Performance(Random Data)", dataSizes, randomResultMatrix,false);


        // sort array for different cases !!!!!!!!!!!!!!
        Arrays.sort(dataset);


        for (int i = 0; i < dataSizes.length; i++) {
            int size = dataSizes[i];
            int[] testData = Arrays.copyOfRange(dataset, 0, size);

            sortedResultMatrix[0][i] = AlgorithmTestFunction(testData, "Comb Sort");
            sortedResultMatrix[1][i] = AlgorithmTestFunction(testData, "Insertion Sort");
            sortedResultMatrix[2][i] = AlgorithmTestFunction(testData, "Shaker Sort");
            sortedResultMatrix[3][i] = AlgorithmTestFunction(testData, "Shell Sort");
            sortedResultMatrix[4][i] = AlgorithmTestFunction(testData, "Radix Sort");
        }

        showAndSaveChart("Sorting Algorithms Performance(Sorted Data)", dataSizes, sortedResultMatrix,false);

        //reversely sort array for different cases !!!!!!!!!!!!!!!!!
        for (int i = 0; i < dataset.length / 2; i++) {
            int temp = dataset[i];
            dataset[i] = dataset[dataset.length - 1 - i];
            dataset[dataset.length - 1 - i] = temp;
        }


        for (int i = 0; i < dataSizes.length; i++) {
            int size = dataSizes[i];
            int[] testData = Arrays.copyOfRange(dataset, 0, size);

            reverseSortedResultMatrix[0][i] = AlgorithmTestFunction(testData, "Comb Sort");
            reverseSortedResultMatrix[1][i] = AlgorithmTestFunction(testData, "Insertion Sort");
            reverseSortedResultMatrix[2][i] = AlgorithmTestFunction(testData, "Shaker Sort");
            reverseSortedResultMatrix[3][i] = AlgorithmTestFunction(testData, "Shell Sort");
            reverseSortedResultMatrix[4][i] = AlgorithmTestFunction(testData, "Radix Sort");
        }


        showAndSaveChart("Sorting Algorithms Performance(Reverse Sorted Data)", dataSizes, reverseSortedResultMatrix,false);


        // algorithm  bases plotting !!!!!!!!!!
        String[] algorithms = {"Comb Sort", "Insertion Sort", "Shaker Sort", "Shell Sort", "Radix Sort"};
        for (int i = 0; i < 5; i++) {
            double[][] algorithmPerformance = {randomResultMatrix[i], sortedResultMatrix[i], reverseSortedResultMatrix[i]};
            showAndSaveChart(algorithms[i] + " Performance Across Random Sorted and Reverse Sorted Data Sets", dataSizes, algorithmPerformance, true);
        }


        // Results
        System.out.println("######################################      RANDOM  RESULT MATRIX     ######################################");
        for(int i =0 ; i< randomResultMatrix.length; i++){
            System.out.println("**********************************");
            for(int j =0 ; j< randomResultMatrix[i].length; j++){
                System.out.println(randomResultMatrix[i][j]);
            }
        }
        System.out.println("######################################     SORTED  RESULT MATRIX       ######################################");
        for(int i =0 ; i< sortedResultMatrix.length; i++){
            System.out.println("**********************************");
            for(int j =0 ; j< sortedResultMatrix[i].length; j++){
                System.out.println(sortedResultMatrix[i][j]);
            }
        }
        System.out.println("######################################     REVERSE SORTED  RESULT MATRIX       ######################################");
        for(int i =0 ; i< reverseSortedResultMatrix.length; i++){
            System.out.println("**********************************");
            for(int j =0 ; j< reverseSortedResultMatrix[i].length; j++){
                System.out.println(reverseSortedResultMatrix[i][j]);
            }
        }

    }


    public static int[] readCSV(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return lines.stream().skip(1)
                .map(line -> line.split(",")[2])
                .map(Integer::parseInt)
                .mapToInt(i -> i)
                .toArray();
    }


    public static double AlgorithmTestFunction(int[] originalData, String algorithmName) {
        double totalTime = 0;

        for (int i = 0; i < 10; i++) {
            int[] data = Arrays.copyOf(originalData, originalData.length);
            long startTime = System.nanoTime();

            switch (algorithmName) {
                case "Comb Sort":
                    CombSort.combSort(data);
                    break;
                case "Insertion Sort":
                    InsertionSort.insertionSort(data);
                    break;
                case "Shaker Sort":
                    ShakerSort.shakerSort(data);
                    break;
                case "Shell Sort":
                    ShellSort.shellSort(data);
                    break;
                case "Radix Sort":
                    int dig = getMaxDigits(data);
                    RadixSort.radixSort(data, dig);
                    break;
            }

            long endTime = System.nanoTime();
            totalTime += (endTime - startTime) / 1_000_000.0;
        }

        double avgTime = totalTime / 10;
        System.out.println(algorithmName + " - Average Time: " + avgTime + " ms");
        return avgTime;
    }


    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, boolean isSingleAlgorithm) throws IOException {
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        if (isSingleAlgorithm) {
            String[] dataTypes = {"Random Data", "Sorted Data", "Reverse Sorted Data"};
            for (int i = 0; i < 3; i++) {
                chart.addSeries(dataTypes[i], doubleX, yAxis[i]);
            }
        } else {
            String[] algorithms = {"Comb Sort", "Insertion Sort", "Shaker Sort", "Shell Sort", "Radix Sort"};
            for (int i = 0; i < 5; i++) {
                chart.addSeries(algorithms[i], doubleX, yAxis[i]);
            }
        }

        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);
        new SwingWrapper(chart).displayChart();
    }

    // for radix sort algorithm
    private static int getMaxDigits(int[] arr) {
        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        int digits = 0;
        while (max > 0) {
            max /= 10;
            digits++;
        }

        return digits;
    }
}
