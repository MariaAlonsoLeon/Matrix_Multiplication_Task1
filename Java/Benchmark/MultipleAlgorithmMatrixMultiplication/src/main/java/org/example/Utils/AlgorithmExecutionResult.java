package org.example.Utils;

import java.util.List;

public class AlgorithmExecutionResult {
    private String algorithm;
    private List<Long> executionTimes; // Tiempos de ejecución por iteración
    private double averageExecutionTime; // Tiempo promedio de ejecución

    // Constructor
    public AlgorithmExecutionResult(String algorithm, List<Long> executionTimes) {
        this.algorithm = algorithm;
        this.executionTimes = executionTimes;
        this.averageExecutionTime = executionTimes.stream().mapToLong(Long::longValue).average().orElse(0.0);
    }

    // Getters
    public String getAlgorithm() {
        return algorithm;
    }

    public List<Long> getExecutionTimes() {
        return executionTimes;
    }

    public double getAverageExecutionTime() {
        return averageExecutionTime;
    }
}
