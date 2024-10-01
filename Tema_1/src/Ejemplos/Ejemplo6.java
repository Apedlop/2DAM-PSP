package Ejemplos;

import java.io.InputStream;
import java.util.*;

public class Ejemplo6 {

    public static void main(String[] args) {

        ProcessBuilder test = new ProcessBuilder();
        Map entorno = test.environment();

        System.out.println("Variables de entorno: ");
        System.out.println(entorno);

        test = new ProcessBuilder("C:\\Users\\pedre\\.jdks\\openjdk-23\\bin\\java.exe", "Act1_4.LeerNombre", "María Jesús");

        // Devuelve el nombre del proceso y sus argumentos
        List l = test.command();
        Iterator iter = l.iterator();
        System.out.println("\nArgumentos del comando: ");
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        test = test.command("cmd", "dir", "ver");
        try {
            Process p = test.start(); // Se ejecuta dir
            InputStream is = p.getInputStream();
            System.out.println();

            // Mostramos en pantalla caracter a caracter
            int c;
            while ((c = is.read()) != -1) {
                System.out.print((char) c);
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
