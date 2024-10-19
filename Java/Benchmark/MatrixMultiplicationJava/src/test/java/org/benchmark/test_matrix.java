package org.benchmark;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.*;
import static org.example.Main.matrixMultiplication;

@BenchmarkMode(Mode.All)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
@Fork(2)
public class test_matrix{

    @Param({"64", "128", "256", "512", "1024", "2048"})
    private int size;

    public test_matrix() {
    }

    @Benchmark
    public double[][] testMethod(){
        return matrixMultiplication(size);
    }

}