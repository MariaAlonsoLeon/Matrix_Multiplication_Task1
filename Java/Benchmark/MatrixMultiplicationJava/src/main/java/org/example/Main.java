package org.example;
import java.util.Random;

public class Main {
    public static double[][] matrixMultiplication(int n) {
        Random random = new Random();

        double[][] A = new double[n][n];
        double[][] B = new double[n][n];
        double[][] C = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextDouble();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                B[i][j] = random.nextDouble();
            }
        }

        return C;
    }
}