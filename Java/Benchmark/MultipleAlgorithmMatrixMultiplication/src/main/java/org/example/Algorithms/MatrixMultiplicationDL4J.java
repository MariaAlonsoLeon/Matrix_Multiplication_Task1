package org.example.Algorithms;

/*import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class MatrixMultiplicationDL4J implements MatrixMultiplication{
    @Override
    public void multiply(double[][] A, double[][] B, double[][] C, int N) {
        // Convertir las matrices A y B a INDArray
        INDArray matrixA = Nd4j.create(A);
        INDArray matrixB = Nd4j.create(B);

        // Multiplicar las matrices
        INDArray result = matrixA.mmul(matrixB);

        // Convertir el resultado de nuevo a una matriz 2D
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = result.getDouble(i, j);
            }
        }
    }
}*/
