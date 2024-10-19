package org.example.Benchmark;
import org.example.Algorithms.*;
import org.example.Utils.MatrixInitialization;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class MatrixMultiplicationBenchmarkJMH {

    private static final int N = 128;

    private MatrixMultiplication naiveMultiplication = new NaiveMatrixMultiplication();
    private MatrixMultiplication blockMultiplication = new BlockMatrixMultiplication();
    private MatrixMultiplication rowColumnMajorMultiplication = new RowColumnMajorMultiplication();
    private MatrixMultiplication sparseMatrixCSRMul = new SparseMatrixCSRMul();
    private MatrixMultiplication sparseMatrixCSCMul = new SparseMatrixCSCMul();
    private MatrixMultiplication sparseMatrixCOOMul = new SparseMatrixCOOMul();

    private double[][] A;
    private double[][] B;

    private double[][] resultMatrix;

    @Setup(Level.Trial)
    public void setup() {
        A = new double[N][N];
        B = new double[N][N];
        MatrixInitialization.initializeMatrix(A);
        MatrixInitialization.initializeMatrix(B);
    }

    private void runMultiplication(MatrixMultiplication multiplication) {
        resultMatrix = new double[N][N];
        multiplication.multiply(A, B, resultMatrix, N);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testRowColumnMajorMultiplication() {
        runMultiplication(rowColumnMajorMultiplication);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testNaiveMultiplication() {
        runMultiplication(naiveMultiplication);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testBlockMultiplication() {
        runMultiplication(blockMultiplication);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testSparseMatrixCSRMul() {
        runMultiplication(sparseMatrixCSRMul);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testSparseMatrixCSCMul() {
        runMultiplication(sparseMatrixCSCMul);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Warmup(iterations = 2)
    @Measurement(iterations = 5)
    public void testSparseMatrixCOOMul() {
        runMultiplication(sparseMatrixCOOMul);
    }
}