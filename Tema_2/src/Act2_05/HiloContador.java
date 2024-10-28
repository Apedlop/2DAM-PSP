package Act2_05;

import javax.swing.*;

public class HiloContador extends Thread {
    private long contador;
    private Principal app; // Referencia a la ventana principal

    public HiloContador(long contadorInicial, Principal app) {
        this.contador = contadorInicial;
        this.app = app; // Guardar la referencia
    }

    public void detenerHilo() {
        interrupt(); // Interrumpir el hilo si está en espera
    }

    public void run() {
        while (true) { // Bucle infinito
            try {
                Thread.sleep(300); // Pausa de 300ms entre incrementos
            } catch (InterruptedException e) {
                // Si se interrumpe, salimos del bucle y finalizamos el hilo
                return; // Finaliza el hilo
            }
            contador++; // Incrementar el contador
            app.repaint(); // Solicitar un redibujo desde el hilo
        }
    }

    public long getContador() {
        return contador; // Obtener el valor actual del contador
    }
}
