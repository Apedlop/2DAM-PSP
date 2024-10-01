package Ejemplos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Ejemplo3 {

    public static void main(String[] args) throws IOException {

        // Cambiamos el formato del directorio para que sea compatible
//        File directorio = new File("/home/usuario/Documentos/2DAM-PSP/Tema_1/out/production/Tema_1");  // Ubuntu
        File directorio = new File("C:\\Users\\pedre\\OneDrive\\Documentos\\2DAM-PSP\\Tema_1\\out\\production\\Tema_1"); // Windows

        // Configuramos el ProcessBuilder para ejecutar Ejemplo2
//        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplos.Ejemplo2"); // Ubuntu
        ProcessBuilder pb = new ProcessBuilder("C:\\Users\\pedre\\.jdks\\openjdk-23\\bin\\java.exe", "Ejemplos.NoExist"); // Windows

        // Establecemos el directorio de trabajo
        pb.directory(directorio);
        System.out.printf("Directorio de trabajo: %s%n", pb.directory());

        // Iniciamos el proceso
        Process p = pb.start();

        try {
            // Leemos la salida del proceso
            InputStream is = p.getInputStream();
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close(); // Movemos el cierre del InputStream fuera del ciclo
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}