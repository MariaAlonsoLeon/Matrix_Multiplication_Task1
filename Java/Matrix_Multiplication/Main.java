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

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Use: java Main <matrix size>");
            System.exit(1);
        }

        int n = Integer.parseInt(args[0]);

        long startTime = System.nanoTime();

        matrixMultiplication(n);

        long endTime = System.nanoTime();

        System.out.println("Time execution for a matrix of " + n + "x" + n + ": " + (endTime - startTime) / 1e6 + " ms");
    }
}