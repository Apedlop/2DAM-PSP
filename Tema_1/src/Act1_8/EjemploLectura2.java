package Act1_8;

import java.io.*;

public class EjemploLectura2 {

    public static void main(String[] args) throws IOException {

        // Directorio donde está el archivo .class de EjemploLectura
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\pedre\\.jdks\\openjdk-23\\bin\\java.exe", "Act1_7.EjemploLectura");

        // Fichero de entrada
        File archivoEntrada = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\Ficheros\\Act1_8\\Entrada.txt");
        pb.redirectInput(ProcessBuilder.Redirect.from(archivoEntrada));

        // Fichero salida
        File archivoSalida = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\Ficheros\\Act1_8\\Salida.txt");
        pb.redirectOutput(ProcessBuilder.Redirect.to(archivoSalida));

        // Se ejecuta el proceso
        Process p = pb.start();

        // lectura - obtiene la salida
        InputStream is = p.getInputStream();
        int c;
        while ((c = is.read()) != -1) {
            System.out.print((char) c); // usa print en lugar de println para evitar saltos de línea innecesarios
        }

        // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
        int exiVal;
        try {
            exiVal = p.waitFor();
            System.out.println("Valor de Salida: " + exiVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
