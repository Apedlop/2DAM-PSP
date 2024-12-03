package Act2_06;

import javax.swing.*;

public class Ejer_6_HiloContador extends Thread {
    private int contador = 0; // Contador de progreso
    private final Ejer_6 app; // Aplicación principal
    private boolean parado = false; // Flag para determinar si el hilo debe detenerse

    public Ejer_6_HiloContador(Ejer_6 app) {
        this.app = app;
    }

    @Override
    public void run() {
        while (contador < 100 && !parado) { // Mientras no se haya alcanzado 100 o el hilo no esté parado
            try {
                Thread.sleep(calcularSleepTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++; // Incrementar el contador
            app.actualizarProgressBar(this); // Actualizar barra de progreso

            // Si el hilo alcanza el valor 100, muestra al ganador y detiene los demás
            if (contador >= 100) {
                app.mostrarGanador(this);
                app.detenerOtrosHilos(this); // Detiene los otros hilos
                break;
            }
        }
    }

    // Devuelve el valor actual del contador
    public int getContador() {
        return contador;
    }

    // Calcula el tiempo de espera entre cada incremento basado en la prioridad del hilo
    private int calcularSleepTime() {
        // Generamos un valor aleatorio único entre 0 y 1000
        float numMax = (float) (Math.random() * 1000 + 1);
        float numNorm = (float) (Math.random() * 1000 + 1);
        float numMin = (float) (Math.random() * 1000 + 1);

        // Aseguramos que los valores siguen la jerarquía de prioridades
        // numMax > numNorm > numMin, si no se cumple, generamos un nuevo valor

        // Ajustar numNorm para que sea menor o igual a numMax
        while (numNorm > numMin) {
            numNorm = (float) (Math.random() * numMax); // Aseguramos que numNorm no sea mayor que numMax
        }

        // Ajustar numMin para que sea menor o igual a numNorm
        while (numMax > numNorm) {
            numMax = (float) (Math.random() * numNorm); // Aseguramos que numMin no sea mayor que numNorm
        }

        switch (getPriority()) {
            case Thread.MAX_PRIORITY:
                return (int) numMax; // Hilo con máxima prioridad
            case Thread.NORM_PRIORITY:
                return (int) numNorm; // Hilo con prioridad normal
            case Thread.MIN_PRIORITY:
                return (int) numMin; // Hilo con prioridad mínima
            default:
                return (int) (Math.random() * 1000 + 1); // Valor aleatorio por defecto
        }
    }

    // Método para detener el hilo
    public void detener() {
        parado = true; // Cambia el flag a true para detener el hilo
    }
}
