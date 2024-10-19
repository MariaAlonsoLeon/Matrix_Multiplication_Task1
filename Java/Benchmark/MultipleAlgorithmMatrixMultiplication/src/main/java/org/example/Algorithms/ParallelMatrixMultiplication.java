package org.example.Algorithms;

import java.util.Arrays;

public class ParallelMatrixMultiplication implements MatrixMultiplication {

    static class MatrixMultiplicationTask extends Thread {
        private final double[][] A;
        private final double[][] B;
        private final double[][] C;
        private final int startRow;
        private final int endRow;

        public MatrixMultiplicationTask(double[][] A, double[][] B, double[][] C, int startRow, int endRow) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            // Perform matrix multiplication for the assigned rows
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    C[i][j] = 0; // Initialize result element
                    for (int k = 0; k < B.length; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    }

    @Override
    public void multiply(double[][] A, double[][] B, double[][] C, int N) {
        int rowsA = A.length;
        int colsB = B[0].length;

        double[][] result = new double[rowsA][colsB];

        MatrixMultiplicationTask[] tasks = new MatrixMultiplicationTask[N];

        int rowsPerThread = rowsA / N;
        int remainingRows = rowsA % N;

        int startRow = 0;
        for (int i = 0; i < N; i++) {
            int endRow = startRow + rowsPerThread + (i < remainingRows ? 1 : 0);
            tasks[i] = new MatrixMultiplicationTask(A, B, result, startRow, endRow);
            tasks[i].start();
            startRow = endRow; // Update the start row for the next thread
        }

        for (MatrixMultiplicationTask task : tasks) {
            //task.join();
        }

        for (int i = 0; i < rowsA; i++) {
            System.arraycopy(result[i], 0, C[i], 0, colsB);
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}