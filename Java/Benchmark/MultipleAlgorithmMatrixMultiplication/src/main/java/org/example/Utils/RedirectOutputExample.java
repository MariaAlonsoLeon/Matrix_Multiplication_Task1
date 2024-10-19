package org.example.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RedirectOutputExample {
    public static void main(String[] args) {
        try {
            // Crear un archivo donde se redirigirá la salida
            File file = new File("output.txt");
            // Crear un PrintStream que escribe en el archivo
            PrintStream printStream = new PrintStream(file);
            // Redirigir la salida estándar a el PrintStream
            System.setOut(printStream);

            // Ahora todo lo que se imprima en la consola se guardará en output.txt
            System.out.println("Esta línea se guardará en el archivo output.txt");
            System.out.println("Puedes redirigir la salida estándar a un archivo usando PrintStream.");

            // Cerrar el PrintStream
            printStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}