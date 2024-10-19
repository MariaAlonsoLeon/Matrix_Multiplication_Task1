package org.example.Utils;

public class BenchmarkResult {
    private String algorithm;
    private long executionTime;

    public BenchmarkResult(String algorithm, long executionTime) {
        this.algorithm = algorithm;
        this.executionTime = executionTime;
    }

    // Getters y Setters
    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}
