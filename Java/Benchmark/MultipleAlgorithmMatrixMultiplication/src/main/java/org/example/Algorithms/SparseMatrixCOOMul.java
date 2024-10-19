package org.example.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseMatrixCOOMul implements MatrixMultiplication {

    static class COOMatrix {
        List<Integer> rowIndices;
        List<Integer> colIndices;
        List<Double> values;
        int rows, cols;

        COOMatrix(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.rowIndices = new ArrayList<>();
            this.colIndices = new ArrayList<>();
            this.values = new ArrayList<>();
        }

        public void addValue(int row, int col, double value) {
            if (value != 0) {
                rowIndices.add(row);
                colIndices.add(col);
                values.add(value);
            }
        }

        public void printCOODetails() {
            System.out.println("COO Representation:");
            System.out.println("Row Indices: " + rowIndices);
            System.out.println("Column Indices: " + colIndices);
            System.out.println("Values: " + values);
        }

        public void printDenseMatrix() {
            double[][] denseMatrix = new double[rows][cols];

            for (int i = 0; i < values.size(); i++) {
                denseMatrix[rowIndices.get(i)][colIndices.get(i)] = values.get(i);
            }

            System.out.println("Dense Matrix:");
            for (double[] row : denseMatrix) {
                System.out.println(Arrays.toString(row));
            }
        }

        public COOMatrix multiply(COOMatrix B) {
            if (this.cols != B.rows) {
                throw new IllegalArgumentException("Matrix dimensions do not match for multiplication.");
            }

            COOMatrix result = new COOMatrix(this.rows, B.cols);

            double[][] tempResult = new double[this.rows][B.cols];

            for (int i = 0; i < this.values.size(); i++) {
                int rowA = this.rowIndices.get(i);
                int colA = this.colIndices.get(i);
                double valA = this.values.get(i);

                for (int j = 0; j < B.values.size(); j++) {
                    if (B.rowIndices.get(j) == colA) {
                        int colB = B.colIndices.get(j);
                        double valB = B.values.get(j);
                        tempResult[rowA][colB] += valA * valB;
                    }
                }
            }

            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < B.cols; j++) {
                    if (tempResult[i][j] != 0) {
                        result.addValue(i, j, tempResult[i][j]);
                    }
                }
            }

            return result;
        }
    }

    public static COOMatrix convertToCOO(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        COOMatrix cooMatrix = new COOMatrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0) {
                    cooMatrix.addValue(i, j, matrix[i][j]);
                }
            }
        }

        return cooMatrix;
    }

    @Override
    public void multiply(double[][] A, double[][] B, double[][] C, int N) {
        COOMatrix cooA = convertToCOO(A);
        COOMatrix cooB = convertToCOO(B);

        COOMatrix resultMatrix = cooA.multiply(cooB);

        double[][] denseResult = new double[resultMatrix.rows][resultMatrix.cols];

        for (int i = 0; i < resultMatrix.values.size(); i++) {
            int row = resultMatrix.rowIndices.get(i);
            int col = resultMatrix.colIndices.get(i);
            denseResult[row][col] = resultMatrix.values.get(i);
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(denseResult[i], 0, C[i], 0, N);
        }
    }
}