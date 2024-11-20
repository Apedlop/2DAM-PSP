package Act2_06;

import Act2_05.Ejer_9;

import javax.swing.*;
public class Ejer_6_HiloContador extends Thread {
    private int contador = 0;
    private final Ejer_6 app;

    public Ejer_6_HiloContador(Ejer_6 app) {
        this.app = app;
    }

    @Override
    public void run() {
        while (contador < 100) {
            try {
                Thread.sleep(calcularSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++;
            app.actualizarProgressBar(this);

            // Si un hilo alcanza el valor máximo, detenemos todos los hilos y mostramos el ganador
            if (contador >= 100) {
                app.mostrarGanador(this);
                break;
            }
        }
    }

    public int getContador() {
        return contador;
    }

    private int calcularSleepTime() {
        switch (getPriority()) {
            case Thread.MAX_PRIORITY:
                return 50;
            case Thread.NORM_PRIORITY:
                return 100;
            case Thread.MIN_PRIORITY:
                return 150;
            default:
                return 100;
        }
    }
}