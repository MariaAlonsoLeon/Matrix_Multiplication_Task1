package org.benchmark;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class ResourceUsage {

    public static double getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        return memory / (1024.0 * 1024.0);  // Convertir a MB
    }


    public static double[][] multiplyMatrices(double[][] matrix1, double[][] matrix2) {
        int n = matrix1.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static double[][] generateMatrix(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Math.random();
            }
        }
        return matrix;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] sizes = {64, 128, 256, 512, 1024, 2048};

        for (int size : sizes) {
            System.out.println("Matrix size: " + size + "x" + size);

            double[][] matrix1 = generateMatrix(size);
            double[][] matrix2 = generateMatrix(size);

            double initialMemoryUsage = getMemoryUsage();

            long startTime = System.nanoTime();

            double[][] result = multiplyMatrices(matrix1, matrix2);

            long endTime = System.nanoTime();

            double finalMemoryUsage = getMemoryUsage();

            double executionTime = (endTime - startTime) / 1_000_000.0;

            System.out.println("Execution time: " + executionTime + " ms");
            System.out.println("Memory used: " + (finalMemoryUsage - initialMemoryUsage) + " MB");
            System.out.println("--------------------------------------");
        }
    }
}
