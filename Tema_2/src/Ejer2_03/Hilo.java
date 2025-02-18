package Ejer2_03;

import Ejer2_03.Principal;

public class Hilo extends Thread {
    private String archivo;

    public Hilo(String archivo) {
        this.archivo = archivo;
    }

    @Override
    public void run() {
        long tInicio = System.currentTimeMillis();
        int caracteres = Principal.contarCaracteres(archivo);
        long tFin = System.currentTimeMillis();
        System.out.println("Archivo: " + archivo + " | Caracteres: " + caracteres + " | Tiempo: " + (tFin - tInicio) + " ms");
    }
}
