package Act2_03;

import javax.swing.*;

public class HiloContador extends Thread {
    private long contador;
    private boolean parar;
    private Principal app; // Referencia a la ventana principal

    public HiloContador(long contadorInicial, Principal app) {
        this.contador = contadorInicial;
        this.parar = false;
        this.app = app; // Guardar la referencia
    }

    public void detenerHilo() {
        parar = true; // Señal para detener el hilo
    }

    public void run() {
        while (!parar) {
            try {
                Thread.sleep(300); // Pausa de 300ms entre incrementos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contador++; // Incrementar el contador
            app.repaint(); // Solicitar un redibujo desde el hilo
        }
    }

    public long getContador() {
        return contador; // Obtener el valor actual del contador
    }
}
