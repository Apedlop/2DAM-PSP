package Ejer2_03;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    // Método para contar caracteres de forma secuencial
    public static void contarSecuencialmente(String[] archivos) {
        long tInicio = System.currentTimeMillis();

        for (String archivo : archivos) {
            long tArchivoInicio = System.currentTimeMillis();
            int caracteres = contarCaracteres(archivo);
            long tArchivoFin = System.currentTimeMillis();
            System.out.println("Archivo: " + archivo + " | Caracteres: " + caracteres + " | Tiempo: " + (tArchivoFin - tArchivoInicio) + " ms");
        }

        long tFin = System.currentTimeMillis();
        System.out.println("Tiempo total (secuencial): " + (tFin - tInicio) + " ms\n");
    }

    // Método para contar caracteres usando hilos
    public static void contarConHilos(String[] archivos) {
        long tInicio = System.currentTimeMillis();

        List<Hilo> hilos = new ArrayList<>();

        for (String archivo : archivos) {
            Hilo hilo = new Hilo(archivo);
            hilos.add(hilo);
            hilo.start();
        }

        // Esperar a que todos los hilos terminen
        for (Hilo hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long tFin = System.currentTimeMillis();
        System.out.println("Tiempo total (con hilos): " + (tFin - tInicio) + " ms");
    }

    // Método para contar caracteres en un archivo
    public static int contarCaracteres(String nombreArchivo) {
        int contador = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            int c;
            while ((c = br.read()) != -1) {
                contador++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + nombreArchivo);
        }
        return contador;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java ContadorCaracteres <archivo1> <archivo2> ...");
            return;
        }

        System.out.println("Conteo secuencial:");
        contarSecuencialmente(args);

        System.out.println("\nConteo con hilos:");
        contarConHilos(args);
    }
}
