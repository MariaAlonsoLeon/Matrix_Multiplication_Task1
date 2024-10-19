package org.example;
import org.example.Utils.ExtractBenchmarkData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        PrintStream originalOut = System.out;
        try {
            File outputFile = new File("benchmark_output.txt");
            PrintStream printStream = new PrintStream(outputFile);
            System.setOut(printStream);
            org.openjdk.jmh.Main.main(args);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
            ExtractBenchmarkData.main();
        }
    }
}