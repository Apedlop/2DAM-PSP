package Ejemplos;

import java.io.IOException;
import java.io.InputStream;

public class Ejemplo2 {

    public static void main(String[] args) throws IOException {

//        Process p = new ProcessBuilder("bash", "-c", "ls").start(); // Ubuntu
        Process p = new ProcessBuilder("cmd", "ver", "dir").start(); // Windows

        try (InputStream is = p.getInputStream()) {
            int c;
            // Leer hasta el final del flujo, cuando `read()` devuelve -1
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // COMPROBACIÓN DE ERROR - 0 bien - 1 mal
        int exitVal;

        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
