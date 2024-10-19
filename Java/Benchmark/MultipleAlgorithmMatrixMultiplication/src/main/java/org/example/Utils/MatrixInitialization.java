package org.example.Utils;

public class MatrixInitialization {

    public static void initializeMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Math.random() * 10; // Valores aleatorios entre 0 y 10
            }
        }
    }
}