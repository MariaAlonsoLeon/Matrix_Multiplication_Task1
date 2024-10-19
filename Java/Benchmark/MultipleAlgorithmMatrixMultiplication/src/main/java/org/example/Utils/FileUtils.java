package org.example.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

    // Método para leer un archivo de texto y devolver su contenido como String
    public static String readFileAsString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append(System.lineSeparator()); // Añadir cada línea con un salto de línea
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return contentBuilder.toString(); // Devolver el contenido como String
    }

    public static void main(String[] args) {
        String filePath = "ruta/del/archivo.txt"; // Cambia esto a la ruta de tu archivo
        String fileContent = readFileAsString(filePath);
        System.out.println(fileContent); // Mostrar el contenido del archivo
    }
}