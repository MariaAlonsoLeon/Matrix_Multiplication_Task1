package org.example.Algorithms;

public class RowColumnMajorMultiplication implements MatrixMultiplication {

    @Override
    public void multiply(double[][] A, double[][] B, double[][] C, int N) {
        rowMajorMatrixMultiplication(A, B, C, N);

        double[][] C2 = new double[N][N];
        columnMajorMatrixMultiplication(A, B, C2, N);

        boolean areEqual = matricesAreEqual(C, C2);
        System.out.println("Are the results the same? " + areEqual);
    }

    private void rowMajorMatrixMultiplication(double[][] A, double[][] B, double[][] C, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    private void columnMajorMatrixMultiplication(double[][] A, double[][] B, double[][] C, int N) {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }

    private boolean matricesAreEqual(double[][] C1, double[][] C2) {
        for (int i = 0; i < C1.length; i++) {
            for (int j = 0; j < C1[i].length; j++) {
                if (Math.abs(C1[i][j] - C2[i][j]) > 1e-9) {
                    return false;
                }
            }
        }
        return true;
    }
}