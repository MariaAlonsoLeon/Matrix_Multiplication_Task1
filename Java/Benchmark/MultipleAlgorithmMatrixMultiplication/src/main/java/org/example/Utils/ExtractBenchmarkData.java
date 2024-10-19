package org.example.Utils;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import static org.example.Utils.FileUtils.readFileAsString;

public class ExtractBenchmarkData {
    public static void main() {
        String input = readFileAsString("benchmark_output.txt");

        // Regex para buscar las líneas de resultado para Benchmark.MatrixMultiplicationBenchmarkJMH
        String regex = "^(Benchmark\\.MatrixMultiplicationBenchmarkJMH.*)$";
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        // Lista para almacenar los resultados
        List<Map<String, Object>> results = new ArrayList<>();

        // Procesar cada coincidencia
        while (matcher.find()) {
            // Extraer las partes de la línea de resultados
            String benchmarkName = matcher.group(1); // Nombre del benchmark
            //String mode = matcher.group(2); // Modo
            System.out.println(benchmarkName);
            //System.out.println(mode);
            int count = Integer.parseInt(matcher.group(3)); // Conteo
            double score = Double.parseDouble(matcher.group(4)); // Score
            double error = Double.parseDouble(matcher.group(5));

            String units = matcher.group(6); // Unidades
            Map<String, Object> result = new HashMap<>();
            result.put("benchmark", benchmarkName);
            //result.put("Mode", mode);
            result.put("Cnt", count);
            result.put("Score", score);
            result.put("Error", error);
            result.put("Units", units);

            results.add(result);
        }

        String jsonOutput = new com.google.gson.Gson().toJson(results);
        System.out.println(jsonOutput);

        try (FileWriter fileWriter = new FileWriter("benchmark_results.json")) {
            fileWriter.write(jsonOutput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}